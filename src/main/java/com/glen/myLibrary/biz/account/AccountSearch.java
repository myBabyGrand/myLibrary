package com.glen.myLibrary.biz.account;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static java.lang.Math.*;

@Getter
@Builder
public class AccountSearch {
    private static final int MAX_SIZE = 2000;
    @Builder.Default
    private Integer page = 1;
    @Builder.Default
    private Integer size = 10;

    public long getOffset(){
        return (long)((max(1,page)-1) * min(size, MAX_SIZE));
    }
    
    //TODO : pagable 상속받아(아마도 구현체인 pageRequest가 대상이 되겠지) sorting을 구현해보자 
    
}
