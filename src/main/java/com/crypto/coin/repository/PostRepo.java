package com.crypto.coin.repository;

import com.crypto.coin.model.Cate;
import com.crypto.coin.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends CrudRepository<Post, Long> {

    @Query("select a.srcId from Post a where a.source like 'messari'")
    List<Long> getListIdMessari();
}
