package com.glen.myLibrary.biz.borrow;

import com.glen.myLibrary.common.exception.InvalidRequestException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BorrowDTO {
    
    @ToString
    @Setter
    @Getter
    public class BorrowCreateDTO {
        @NotNull(message = "libraryBookId는 null 일 수 없습니다.")
        private Long libraryBookId;

        @NotNull(message = "libraryMemberId는 null 일 수 없습니다.")
        private Long libraryMemberId;

        @NotNull(message = "libraryId는 null 일 수 없습니다.")
        private Long libraryId;

        private LocalDateTime expiredAt;

        @Builder
        public BorrowCreateDTO(Long libraryBookId, Long libraryMemberId, Long libraryId, LocalDateTime expiredAt) {
            this.libraryBookId = libraryBookId;
            this.libraryMemberId = libraryMemberId;
            this.libraryId = libraryId;
            this.expiredAt = expiredAt;
        }

        public void validate(){
            if(expiredAt.isBefore(LocalDateTime.now())){
                throw new InvalidRequestException("expiredAt","대출만료일자는 오늘보다 빠를 수 없습니다");
            }
        }

    }
}
