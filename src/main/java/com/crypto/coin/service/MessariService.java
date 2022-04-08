package com.crypto.coin.service;

import com.crypto.coin.model.Post;

import java.io.IOException;
import java.util.List;

public interface MessariService {
    public void fetch(Long limit) throws IOException;

    public List<Post> getAll();
    
    public String getAllStr();
    
    public String getCache() throws IOException;

}
