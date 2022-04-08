package com.crypto.coin.service;

import com.crypto.coin.model.Post;
import com.crypto.coin.model.AggreCache;

import java.io.IOException;
import java.util.List;

public interface MessariService {
    public void fetch(Long limit) throws IOException;

    public List<Post> getAll();
    
    public String getAllStr();
    
    public AggreCache getCache() throws IOException;
    
    public AggreCache setCache();

}
