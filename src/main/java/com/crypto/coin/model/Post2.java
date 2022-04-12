package com.crypto.coin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Post2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String articleType;
    private String date;

     public Post2(Integer id, String name, String articleType, String date) {
        this.id = id;
        this.name = name;
        this.articleType = articleType;
        this.date = date;
    }
}
