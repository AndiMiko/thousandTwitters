package com.thousandtwitters;

import com.thousandtwitters.controller.rest.FollowController;
import com.thousandtwitters.controller.rest.NewsfeedController;
import com.thousandtwitters.model.dao.IFollowsDAO;
import com.thousandtwitters.model.dao.ITweetDAO;
import com.thousandtwitters.model.dao.IUserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ThousandTwittersApplicationTests {

	@Autowired
	private FollowController followController;
	@Autowired
	private NewsfeedController newsfeedController;
	@Autowired
	private IFollowsDAO followsDAO;
	@Autowired
	private ITweetDAO tweetDAO;
	@Autowired
	private IUserDAO userDAO;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
		assertThat(followController).isNotNull();
		assertThat(newsfeedController).isNotNull();
		assertThat(followsDAO).isNotNull();
		assertThat(tweetDAO).isNotNull();
		assertThat(userDAO).isNotNull();
	}

	@Test
	public void restIsAnswering() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).isNotNull();
	}

}
