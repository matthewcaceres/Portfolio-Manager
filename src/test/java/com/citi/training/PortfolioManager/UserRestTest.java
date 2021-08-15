package com.citi.training.PortfolioManager;

import com.citi.training.PortfolioManager.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserRestTest {
    private RestTemplate template = new RestTemplate();
    @Test
    public void testFindAll() {
        List<User> users = template.getForObject("http://localhost:8080/account/all", List.class);
        assertThat(users.size(),  equalTo(2));
    }


}
