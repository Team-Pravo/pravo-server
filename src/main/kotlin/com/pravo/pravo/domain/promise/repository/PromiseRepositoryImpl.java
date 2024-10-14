package com.pravo.pravo.domain.promise.repository;

import com.pravo.pravo.domain.promise.model.Promise;
import com.pravo.pravo.domain.promise.model.QPromise;
import com.pravo.pravo.domain.promise.model.QPromiseRole;
import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalTime;
import java.util.List;

public class PromiseRepositoryImpl {
    private final JPAQueryFactory queryFactory;

    public PromiseRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<Promise> getPromisesByMemberIdAndStartedAtAndEndedAt(Long memberId, LocalDate startedAt, LocalDate endedAt) {
        QPromise promise = QPromise.promise;
        QPromiseRole promiseRole = QPromiseRole.promiseRole;

        BooleanBuilder whereClause = new BooleanBuilder();
        whereClause.and(promiseRole.member.id.eq(memberId));

        if (startedAt != null) {
            whereClause.and(promise.promiseDate.goe(startedAt.atStartOfDay()));
        }
        if (endedAt != null) {
            whereClause.and(promise.promiseDate.loe(endedAt.atTime(LocalTime.MAX)));
        }

        List<Promise> result = queryFactory
            .selectFrom(promise)
            .join(promise.promiseRoles, promiseRole)
            .where(whereClause)
            .fetch();

        System.out.println(result);

        return queryFactory
            .selectFrom(promise)
            .join(promise.promiseRoles, promiseRole)
            .where(whereClause)
            .fetch();
    }
}
