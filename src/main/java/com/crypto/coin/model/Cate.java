package com.crypto.coin.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "cate")
public class Cate {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
}
