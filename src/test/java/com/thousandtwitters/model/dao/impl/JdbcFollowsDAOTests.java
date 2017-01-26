package com.thousandtwitters.model.dao.impl;

import com.thousandtwitters.controller.rest.exception.InvalidDAOParameterException;
import com.thousandtwitters.model.dao.entities.User;
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
public class JdbcFollowsDAOTests {

    @Autowired
    private JdbcFollowsDAO jdbcFollowsDAO;

    private User user1 = new User(1, "Cartman", "cartman@whitehouse.gov");
    private User user2 = new User(2, "Stab", "stan@whitehouse.gov");
    private User user3 = new User(3, "Kyle", "kyle@whitehouse.gov");
    private User user4 = new User(4, "Tweek", "tweek@whitehouse.gov");


    @Test
    public void getFollowedSize() {
        assertThat(jdbcFollowsDAO.getFollowed(user1)).hasSize(5);
        assertThat(jdbcFollowsDAO.getFollowed(user3)).hasSize(1);
        assertThat(jdbcFollowsDAO.getFollowed(user4)).hasSize(0);
    }

    @Test
    public void getFollowedNames() {
        List<User> followed = jdbcFollowsDAO.getFollowed(user1);
        HashSet<String> expectedUsers = Stream.of("Stan", "Tweek", "Butters", "Ike", "Wendy").collect(Collectors.toCollection(HashSet::new));
        for (User follow : followed) {
            assertThat(expectedUsers.remove(follow.getUsername())).isTrue();
        }
    }

    @Test
    public void getFollowersSize() {
        assertThat(jdbcFollowsDAO.getFollowers(user1)).hasSize(2);
        assertThat(jdbcFollowsDAO.getFollowers(user2)).hasSize(1);
        assertThat(jdbcFollowsDAO.getFollowers(user3)).hasSize(0);
    }

    @Test
    public void getFollowersNames() {
        List<User> followers = jdbcFollowsDAO.getFollowers(user1);
        HashSet<String> expectedUsers = Stream.of("Stan", "Kyle").collect(Collectors.toCollection(HashSet::new));
        for (User follow : followers) {
            assertThat(expectedUsers.remove(follow.getUsername())).isTrue();
        }
    }

    @Test
    public void follow() {
        assertThat(jdbcFollowsDAO.getFollowed(user4)).hasSize(0);
        jdbcFollowsDAO.follow(user4, user1);
        jdbcFollowsDAO.follow(user4, user1);
        assertThat(jdbcFollowsDAO.getFollowed(user4)).hasSize(1);
    }

    @Test
    public void unfollow() {
        assertThat(jdbcFollowsDAO.getFollowed(user3)).hasSize(1);
        jdbcFollowsDAO.unfollow(user3, user1);
        jdbcFollowsDAO.unfollow(user3, user1);
        assertThat(jdbcFollowsDAO.getFollowed(user3)).hasSize(0);
    }

    @Test(expected=InvalidDAOParameterException.class)
    public void unfollowSame() {
        jdbcFollowsDAO.unfollow(user1, user1);
    }

    @Test(expected=InvalidDAOParameterException.class)
    public void followSame() {
        jdbcFollowsDAO.follow(user2, user2);
    }

}
