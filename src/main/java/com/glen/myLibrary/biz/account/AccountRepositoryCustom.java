package com.glen.myLibrary.biz.account;

import java.util.List;

public interface AccountRepositoryCustom {

    List<Account> getPageList (AccountSearch accountSearch);
}
