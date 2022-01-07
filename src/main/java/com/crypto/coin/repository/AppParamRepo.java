package com.crypto.coin.repository;

import com.crypto.coin.model.AppParam;
import com.crypto.coin.model.Cate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppParamRepo extends CrudRepository<AppParam, Long> {
}
