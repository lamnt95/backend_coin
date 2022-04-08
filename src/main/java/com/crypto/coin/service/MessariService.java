package com.crypto.coin.service;

import com.crypto.coin.model.Post;
import com.crypto.coin.model.AggreCache;

import java.io.IOException;
import java.util.List;

public interface MessariService {
    public void fetch(Long limit) throws IOException;

    public Post getOne(Integer id);

    public List<Post> getAll();

    public List<Post> getAll2();

    public String getAllStr();
    
    public AggreCache getCache() throws IOException;
    
    public AggreCache setCache() throws IOException;

}
