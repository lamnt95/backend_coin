package com.crypto.coin.repository;

import com.crypto.coin.model.Cate;
import com.crypto.coin.model.CatePost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatePostRepo extends CrudRepository<CatePost, Long> {
}
