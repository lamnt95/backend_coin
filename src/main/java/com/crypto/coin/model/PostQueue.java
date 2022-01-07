package com.crypto.coin.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "post_queue")
public class PostQueue {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status")
    private Long status;

    @Column(name = "time_exe")
    private Date timeExe;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "message")
    private String message;
}
