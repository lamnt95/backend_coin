package com.crypto.coin.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "app_param")
public class AppParam {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;
}
