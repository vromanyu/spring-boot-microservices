package com.vromanyu.ws.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@NoArgsConstructor
@Data
public class UserRequestLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String server;

    @Column(length = 100, nullable = false)
    private String requestUrl;

    @Column(length = 100, nullable = false)
    private String method;

    @Column(length = 500)
    private String requestParameters;

    @Column(length = 100)
    private String ip;

    @CreationTimestamp
    private OffsetDateTime requestTime;

    public UserRequestLog(String server, String requestUrl, String method, String requestParameters, String ip) {
        this.server = server;
        this.requestUrl = requestUrl;
        this.method = method;
        this.requestParameters = requestParameters;
        this.ip = ip;
    }
}
