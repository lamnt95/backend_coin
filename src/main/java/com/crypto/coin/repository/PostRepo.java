package com.crypto.coin.repository;

import com.crypto.coin.model.Cate;
import com.crypto.coin.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    @Query("select a.srcId from Post a where a.source like 'messari'")
    List<String> getListIdMessari();

    @Query("select a from Post a where a.id = ?1")
    Post getOne(Integer id);

    @Query("select new com.crypto.coin.model.Post(a.id, a.name, a.source, a.articleType, a.slug, a.srcId, a.link, a.date) from Post a ")
    List<Post> getAll3();
    
     @Query("select new com.crypto.coin.model.Post(a.id, a.name, a.articleType, a.date) from Post a ")
    List<Post> getAll2();
}
