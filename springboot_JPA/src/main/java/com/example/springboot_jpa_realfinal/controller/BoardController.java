package com.example.springboot_jpa_realfinal.controller;

import com.example.springboot_jpa_realfinal.dto.BoardDTO;
import com.example.springboot_jpa_realfinal.dto.ResponseDTO;
import com.example.springboot_jpa_realfinal.entity.Board;
import com.example.springboot_jpa_realfinal.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {
    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board-list")
    public ResponseEntity<?> getBoardList() {
        ResponseDTO<BoardDTO> responseDTO = new ResponseDTO<BoardDTO>();

        try{
            List<Board> boardList = boardService.getBoardList();
//          List<Integer> List<String>
            List<BoardDTO> boardDtoList = new ArrayList<BoardDTO>();

            for(Board b : boardList) {
                BoardDTO returnBoardDTO = BoardDTO.builder()
                                                  .boardNo(b.getBoardNo())
                                                  .boardTitle(b.getBoardTitle())
                                                  .boardContent(b.getBoardContent())
                                                  .boardWriter(b.getBoardWriter())
                                                  .boardRegDate(b.getBoardRegdate().toString())
                                                  .boardCnt(b.getBoardCnt())
                                                  .build();

                boardDtoList.add(returnBoardDTO);
            }

            responseDTO.setItems(boardDtoList);

            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
            responseDTO.setErrorMessage(e.getMessage());
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("/board")
    public ResponseEntity<?> insertBoard(BoardDTO boardDTO) {
        ResponseDTO<Map<String, String>> responseDTO =
                new ResponseDTO<Map<String, String>>();

        try {
            Board board = Board.builder()
                    .boardTitle(boardDTO.getBoardTitle())
                    .boardContent(boardDTO.getBoardContent())
                    .boardWriter(boardDTO.getBoardWriter())
                    .build();

            boardService.insertBoard(board);

            Map<String, String> returnMap =
                    new HashMap<String, String>();

            returnMap.put("msg", "정상적으로 저장되었습니다.");

            responseDTO.setItem(returnMap);

            return ResponseEntity.ok().body(responseDTO);
        } catch(Exception e) {
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
            responseDTO.setErrorMessage(e.getMessage());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

}
