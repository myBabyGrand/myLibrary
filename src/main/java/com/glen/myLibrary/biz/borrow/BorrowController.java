package com.glen.myLibrary.biz.borrow;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowController {

    @GetMapping("/getFirstBorrow")
    public String getFirstBorrow(){
        return "firstBorrow";
    }
}
