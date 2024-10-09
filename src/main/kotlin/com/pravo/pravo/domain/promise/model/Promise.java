package com.pravo.pravo.domain.promise.model;

import com.pravo.pravo.domain.promise.model.enums.PromiseStatus;
import com.pravo.pravo.global.common.model.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Promise extends BaseTimeEntity {

    @Id
    @Column(name = "promise_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime promiseDate;

    @Column
    private String location;

    @Column
    private PromiseStatus status;
}
