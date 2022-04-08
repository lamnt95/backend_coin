package com.crypto.coin.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity()
@Table(name = "aggre_cache")
public class AggreCache implements Serializable  {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data")
    private String data;
}
