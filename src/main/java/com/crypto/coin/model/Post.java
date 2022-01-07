package com.crypto.coin.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity()
@Table(name = "post")
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "source")
    private String source;

    @Column(name = "article_type")
    private String articleType;

    @Column(name = "slug")
    private String slug;

    @Column(name = "src_id")
    private String srcId;

    @Column(name = "link")
    private String link;

    @Column(name = "markdown")
    private String markdown;

    @Column(name = "html")
    private String html;

    @Column(name = "date")
    private String date;
}
