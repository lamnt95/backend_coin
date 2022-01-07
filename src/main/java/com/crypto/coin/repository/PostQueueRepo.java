package com.crypto.coin.repository;

import com.crypto.coin.model.Cate;
import com.crypto.coin.model.PostQueue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostQueueRepo extends CrudRepository<PostQueue, Long> {
}
