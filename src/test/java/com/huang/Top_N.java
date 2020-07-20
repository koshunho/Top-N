package com.huang;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class Top_N {
    private Random random;
    private RestTemplate restTemplate;

    public void init() {
        random = new Random();
        restTemplate = new RestTemplate();
    }

    private int genUserId() {
        return random.nextInt(1024);
    }

    private double genScore() {
        return random.nextDouble() * 100;
    }

    @Test
    public void testInitRank() {
        init();
        try {
            for (int i = 0; i < 50; i++) {
                restTemplate.getForObject("http://localhost:8080/update?userId=" + genUserId() + "&score=" + genScore(),
                        String.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
