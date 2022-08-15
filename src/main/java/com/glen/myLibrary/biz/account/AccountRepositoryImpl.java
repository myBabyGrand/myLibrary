package com.glen.myLibrary.biz.account;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.glen.myLibrary.biz.account.QAccount.*;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Account> getPageList(AccountSearch accountSearch) {
        return queryFactory.selectFrom(account)
                .limit(accountSearch.getSize())
                .offset(accountSearch.getOffset())
                .orderBy(account.id.desc())
                .fetch();
    }
}
