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
    private Map<String, String> getCache (
    ) throws IOException {
	    
	Jedis jedis = new Jedis();
	log.info(jedis.get("cache"));
	    
	Map<String, String> res = new HashMap();
	String cache = messariService.getCache();
	res.put("cache", cache);
	return res;
    }
    
    @GetMapping(value = "/getAllStr", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    private String getAllStr(
    ) throws IOException {
        return messariService.getAllStr();
    }

}
