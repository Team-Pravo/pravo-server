package com.pravo.pravo.domain.promise.model;

import com.pravo.pravo.domain.member.model.Member;
import com.pravo.pravo.domain.promise.model.enums.ParticipantStatus;
import com.pravo.pravo.domain.promise.model.enums.RoleStatus;
import com.pravo.pravo.global.common.model.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PromiseRole extends BaseTimeEntity {

    @Id
    @Column(name = "promise_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promise_id")
    private Promise promise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private ParticipantStatus status;

    @Column(nullable = false)
    private RoleStatus role;
}
