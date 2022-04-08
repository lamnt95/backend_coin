package com.crypto.coin.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "aggre_cache")
public class AggreCache {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data")
    private String data;
}
