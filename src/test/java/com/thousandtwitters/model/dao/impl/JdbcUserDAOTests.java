package com.thousandtwitters.model.dao.impl;

import com.thousandtwitters.model.dao.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Sql({"/testdata/test-schema.sql", "/testdata/test-data.sql"})
public class JdbcUserDAOTests {

    @Autowired
    private JdbcUserDAO jdbcUserDAO;

    private User user1 = new User(1, "Cartman", "cartman@whitehouse.gov");
    private User user3 = new User(3, "Kyle", "kyle@whitehouse.gov");
    private User user4 = new User(4, "Tweek", "tweek@whitehouse.gov");
    private User user5 = new User(5, "Butters", "leopoldstotch@whitehouse.gov");

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


}
