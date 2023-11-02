package com.glen.myLibrary.biz.borrow;

import com.glen.myLibrary.common.entity.SaveResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    //나의 대출 내역 조회 - 세션에서 가져와야함, 빌린날짜 기준 Desc
    @GetMapping("/my-borrow")
    public List<BorrowResponse> getMyBorrow (@ModelAttribute BorrowPageSearch borrowPageSearch){
        //TODO : 본인건인지 확인
        List<BorrowResponse> responses = new ArrayList<>();
        borrowService.getMyBorrows(1L).stream().forEach(t->responses.add(t.toResponse()));

        return responses;
    }


    //도서관의 대출 내역 조회 - 세션에서 도서관 정보를 가져와서 조회, 날짜 정보 필수
    @GetMapping("/my-library-borrow")
    public List<BorrowResponse> getMyLibraryBorrow (@ModelAttribute BorrowPageSearch borrowPageSearch){
        //TODO : library건인지 확인

        return borrowService.getBorrows(borrowPageSearch);
    }

    //대출정보 단건 생성
    @PostMapping("/borrow")
    public SaveResponse createBorrows(@Valid @RequestBody BorrowCreateDTO borrowCreateDTO){
        return borrowService.createBorrow(borrowCreateDTO);
    }


    //대출정보 수정 - 반납
    @PatchMapping("/return/{borrowId}")
    public SaveResponse returnBorrow(@PathVariable(name = "borrowId") Long id){
        //TODO : 본인건인지 확인
        return borrowService.returnBorrow(id);
    }


    //대출정보 수정 - 연장
    @PatchMapping("/extend/{borrowId}")
    public SaveResponse extendBorrow(@PathVariable(name = "borrowId") Long id){
        //TODO : 본인건인지 확인
        return borrowService.extendBorrow(id);
    }




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

    @PostMapping("/postBorrow4")
    public Map<String, String> borrow4(@Valid @RequestBody BorrowCreateDTO borrowCreateDTO){
        log.info("borrowCreateDTO : {}", borrowCreateDTO);


        return Map.of();
    }

}
