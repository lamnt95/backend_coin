package com.crypto.coin.controller;


import com.crypto.coin.repository.PostRepo;
import com.crypto.coin.service.MessariService;
import com.crypto.coin.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/post")
@Validated
public class PostController {

    @Autowired
    private MessariService messariService;

    @GetMapping(value = "/fetchMessari", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    private void fetchMessari() throws IOException {
        messariService.fetch();
    }

}
