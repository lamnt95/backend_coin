package com.crypto.coin.service;

import com.crypto.coin.model.Cate;
import com.crypto.coin.model.Post;
import com.crypto.coin.model.Post2;
import com.crypto.coin.model.AggreCache;

import java.io.IOException;
import java.util.List;

public interface MessariService {
    public List<Post2> fetch(Long limit) throws IOException;

    public Post getOne(Integer id);
    
    public Post create(Post req);

    public List<Post> getAll();

    public List<Cate> getAllCate();


    public List<Post2> getAll2();

    public String getAllStr();
    
    public AggreCache getCache() throws IOException;
    
    public AggreCache setCache() throws IOException;

}
