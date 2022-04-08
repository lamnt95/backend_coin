package com.crypto.coin.repository;

import com.crypto.coin.model.AggreCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface AggreCacheRepository extends JpaRepository<AggreCache, Long> {
  @Query("from AggreCache c")
  List<AggreCache> getCache();
}
