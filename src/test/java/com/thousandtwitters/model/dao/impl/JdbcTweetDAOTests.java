package com.thousandtwitters.model.dao.impl;

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
public class JdbcTweetDAOTests {

    @Autowired
    private JdbcTweetDAO jdbcTweetDAO;

    private final User user1 = new User(1, "Cartman", "cartman@1ktwitter.com");
    private final User user3 = new User(3, "Kyle", "kyle@1ktwitter.com");
    private final User user4 = new User(4, "Tweek", "tweek@1ktwitter.com");
    private final User user5 = new User(5, "Butters", "leopoldstotch@1ktwitter.com");

    @Test
    public void getNewsfeedSize() {
        assertThat(jdbcTweetDAO.getNewsfeed(user1, "")).hasSize(7);
        assertThat(jdbcTweetDAO.getNewsfeed(user3, "")).hasSize(5);
        assertThat(jdbcTweetDAO.getNewsfeed(user4, "")).hasSize(1);
        assertThat(jdbcTweetDAO.getNewsfeed(user5, "")).hasSize(0);
    }

    @Test
    public void getNewsfeedSearchSize() {
        assertThat(jdbcTweetDAO.getNewsfeed(user1, "Limiting")).hasSize(1);
        assertThat(jdbcTweetDAO.getNewsfeed(user1, "Limiting  here")).hasSize(0);
        assertThat(jdbcTweetDAO.getNewsfeed(user3, "Limiting")).hasSize(1);
        assertThat(jdbcTweetDAO.getNewsfeed(user3, "STOP SPAMMING")).hasSize(2);
        assertThat(jdbcTweetDAO.getNewsfeed(user5, "Limiting")).hasSize(0);
    }


}
