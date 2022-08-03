package com.glen.myLibrary.biz.account;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/account/save")
    public Map<String, String> save(@RequestBody @Valid AccountDTO accountDTO){
        accountService.save(accountDTO);
        return Map.of();
    }
}
