package com.glen.myLibrary.biz.borrow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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
    public Map<String, String> borrow3(@Valid @RequestBody BorrowCreateDTO borrowCreateDTO, BindingResult result){
        if(result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField();
            String errorMessage = firstFieldError.getDefaultMessage();

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);
            return error;
        }
        log.info("borrowCreateDTO : {}", borrowCreateDTO);


        return Map.of();
    }

}
