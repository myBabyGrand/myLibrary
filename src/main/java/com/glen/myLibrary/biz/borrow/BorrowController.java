package com.glen.myLibrary.biz.borrow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class BorrowController {

    @GetMapping("/getFirstBorrow")
    public String getFirstBorrow(){
        return "firstBorrow";
    }

    @PostMapping("/postBorrow")
    public String borrow(@RequestParam Long userId, @RequestParam Long bookId){
        log.info("userId : {}, bookId : {}", userId, bookId);
        return "borrow OK";
    }

    @PostMapping("/postBorrow2")
    public String borrow2(@RequestParam Map<String, String > Params){
        log.info("Params : {}", Params);
        return "borrow OK";
    }

    @PostMapping("/postBorrow3")
    public String borrow3(@RequestBody BorrowCreateDTO borrowCreateDTO){
        log.info("borrowCreateDTO : {}", borrowCreateDTO);
        return "borrow_done";
    }

}
