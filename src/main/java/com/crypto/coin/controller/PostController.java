package com.crypto.coin.controller;


import com.crypto.coin.model.Post;
import com.crypto.coin.model.Post2;
import com.crypto.coin.model.AggreCache;

import com.crypto.coin.repository.PostRepo;
import com.crypto.coin.service.MessariService;
import com.crypto.coin.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.Map;
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
    private List<Post2> fetchMessari(
            @RequestParam(name = "limit") Long limit
    ) throws IOException {
        return messariService.fetch(limit);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    private List<Post> getAll(
    ) throws IOException {
        return messariService.getAll();
    }

    @GetMapping(value = "/getOne", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    private Post getOne(
            @RequestParam(name = "id") Integer id
    ) throws IOException {
        return messariService.getOne(id);
    }

    @GetMapping(value = "/getAll2", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    private List<Post2> getAll2(
    ) throws IOException {
        return messariService.getAll2();
    }
	
    @GetMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    private Post create(
	    @RequestBody Post req
    ) throws IOException {
        return messariService.create(req);
    }
    
    @GetMapping(value = "/getCache", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    private AggreCache getCache (
    ) throws IOException {
	return messariService.getCache();
    }
	
    @GetMapping(value = "/setCache", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    private AggreCache setCache (
    ) throws IOException {
	return messariService.setCache();
    }
    
    @GetMapping(value = "/getAllStr", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    private String getAllStr(
    ) throws IOException {
        return messariService.getAllStr();
    }

}
