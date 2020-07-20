package com.huang.controller;

import com.huang.pojo.Rank;
import com.huang.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private RedisService redisService;

    @GetMapping("/topn")
    public List<Rank> showTopN(@RequestParam(value = "n") int n) {
        return redisService.getTopNRanks(n);
    }

    @GetMapping("/update")
    public Rank updateScore(@RequestParam(value = "userId") long userId,@RequestParam(value = "score") float score) {
        return redisService.updateRank(userId, score);
    }

    @GetMapping("/rank")
    public Rank queryRank(long userId) {
        return redisService.getRank(userId);
    }

    @GetMapping("/around")
    public List<Rank> around(@RequestParam(value = "userId") long userId,@RequestParam(value = "n") int n) {
        return redisService.getRankAroundUser(userId, n);
    }
}
