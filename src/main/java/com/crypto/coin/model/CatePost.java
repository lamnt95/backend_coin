package com.crypto.coin.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "cate_post")
public class CatePost {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cate_id")
    private Long cateId;

    @Column(name = "post_id")
    private Long postId;
}
