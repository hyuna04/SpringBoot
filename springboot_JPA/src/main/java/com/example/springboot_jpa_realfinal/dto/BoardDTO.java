package com.example.springboot_jpa_realfinal.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
    private int boardNo;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private String boardRegDate;
    private int boardCnt;
}
