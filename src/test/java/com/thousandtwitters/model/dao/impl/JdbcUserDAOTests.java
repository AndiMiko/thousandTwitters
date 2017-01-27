package com.thousandtwitters.model.dao.impl;

import com.thousandtwitters.controller.rest.exception.InvalidDAOParameterException;
import com.thousandtwitters.model.dao.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Sql({"/testdata/test-schema.sql", "/testdata/test-data.sql"})
public class JdbcUserDAOTests {

    @Autowired
    private JdbcUserDAO jdbcUserDAO;

    private final User user1 = new User(1, "Cartman", "cartman@1ktwitter.com");
    private final User user2 = new User(2, "Stab", "stan@1ktwitter.com");
    private final User user3 = new User(3, "Kyle", "kyle@1ktwitter.com");
    private final User user4 = new User(4, "Tweek", "tweek@1ktwitter.com");

    @Test
    public void getUserById() {
        assertThat(jdbcUserDAO.getUser(1).getUsername()).isEqualTo("Cartman");
        assertThat(jdbcUserDAO.getUser(1).getId()).isEqualTo(1);
        assertThat(jdbcUserDAO.getUser(2).getUsername()).isEqualTo("Stan");
        assertThat(jdbcUserDAO.getUser(4).getUsername()).isEqualTo("Tweek");
        assertThat(jdbcUserDAO.getUser(7).getUsername()).isEqualTo("Wendy");
    }

    @Test
    public void getUserByName() {
        assertThat(jdbcUserDAO.getUser("Cartman").getUsername()).isEqualTo("Cartman");
        assertThat(jdbcUserDAO.getUser("Cartman").getId()).isEqualTo(1);
        assertThat(jdbcUserDAO.getUser("Stan").getUsername()).isEqualTo("Stan");
        assertThat(jdbcUserDAO.getUser("Tweek").getUsername()).isEqualTo("Tweek");
        assertThat(jdbcUserDAO.getUser("Wendy").getUsername()).isEqualTo("Wendy");
    }

    @Test
    public void getFollowedSize() {
        assertThat(jdbcUserDAO.getFollowedBy(user1)).hasSize(5);
        assertThat(jdbcUserDAO.getFollowedBy(user3)).hasSize(1);
        assertThat(jdbcUserDAO.getFollowedBy(user4)).hasSize(0);
    }

    @Test
    public void getFollowedNames() {
        List<User> followed = jdbcUserDAO.getFollowedBy(user1);
        HashSet<String> expectedUsers = Stream.of("Stan", "Tweek", "Butters", "Ike", "Wendy").collect(Collectors.toCollection(HashSet::new));
        for (User follow : followed) {
            assertThat(expectedUsers.remove(follow.getUsername())).isTrue();
        }
    }

    @Test
    public void getFollowersSize() {
        assertThat(jdbcUserDAO.getFollowersOf(user1)).hasSize(2);
        assertThat(jdbcUserDAO.getFollowersOf(user2)).hasSize(1);
        assertThat(jdbcUserDAO.getFollowersOf(user3)).hasSize(0);
    }

    @Test
    public void getFollowersNames() {
        List<User> followers = jdbcUserDAO.getFollowersOf(user1);
        HashSet<String> expectedUsers = Stream.of("Stan", "Kyle").collect(Collectors.toCollection(HashSet::new));
        for (User follow : followers) {
            assertThat(expectedUsers.remove(follow.getUsername())).isTrue();
        }
    }

    @Test
    public void follow() {
        assertThat(jdbcUserDAO.getFollowedBy(user4)).hasSize(0);
        jdbcUserDAO.follow(user4, user1);
        jdbcUserDAO.follow(user4, user1);
        assertThat(jdbcUserDAO.getFollowedBy(user4)).hasSize(1);
    }

    @Test
    public void unfollow() {
        assertThat(jdbcUserDAO.getFollowedBy(user3)).hasSize(1);
        jdbcUserDAO.unfollow(user3, user1);
        jdbcUserDAO.unfollow(user3, user1);
        assertThat(jdbcUserDAO.getFollowedBy(user3)).hasSize(0);
    }

    @Test(expected=InvalidDAOParameterException.class)
    public void unfollowSame() {
        jdbcUserDAO.unfollow(user1, user1);
    }

    @Test(expected=InvalidDAOParameterException.class)
    public void followSame() {
        jdbcUserDAO.follow(user2, user2);
    }

}
