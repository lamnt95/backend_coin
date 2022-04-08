package com.crypto.coin.controller;


import com.crypto.coin.model.Post;
import com.crypto.coin.repository.PostRepo;
import com.crypto.coin.service.MessariService;
import com.crypto.coin.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/post")
@Validated
public class PostController {

    @Autowired
    private MessariService messariService;

    @GetMapping(value = "/fetchMessari", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    private void fetchMessari(
            @RequestParam(name = "limit") Long limit
    ) throws IOException {
        messariService.fetch(limit);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    private List<Post> getAll(
    ) throws IOException {
        return messariService.getAll();
    }
    
    @GetMapping(value = "/getCache", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    private String getCache(
    ) throws IOException {
	log.info("start");
        JedisPool pool = new JedisPool(new JedisPoolConfig(),
				"redis-18234.c1.asia-nortosystem1-1.gce.cloud.redislabs.com",
				18234,
				Protocol.DEFAULT_TIMEOUT,
				"P5U9yQGw1mfJzDNowyT850v447bjTf1f");
		Jedis jedis = pool.getResource();
	    
	log.info("end");
        log.info(jedis.info());
	log.info(jedis.get("cache"));
        return jedis.get("cache");
    }
    
    @GetMapping(value = "/getAllStr", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    private String getAllStr(
    ) throws IOException {
        return messariService.getAllStr();
    }

}
