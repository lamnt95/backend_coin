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
    private Integer i;
    private String n;
    private String a;
    private String d;

     public Post2(Integer id, String name, String articleType, String date) {
        this.i = id;
        this.n = name;
        this.d = date;
        if(articleType!=null && ( articleType.equals("ENTERPRISE_RESEARCH") || articleType.equals("PRO_RESEARCH") )){
            this.a="PRO";
        }
    }
}
