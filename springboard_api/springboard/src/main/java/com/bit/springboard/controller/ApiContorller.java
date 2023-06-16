package com.bit.springboard.controller;

import com.bit.springboard.dto.BoardDTO;
import com.bit.springboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //@Controller + @ResponseBody
@RequestMapping("/api")
public class ApiContorller {
    private BoardService boardService;

    //생성자 주입
    @Autowired
    public ApiContorller(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/testApi")
    public Map<String, String> testApi() {
        Map<String, String> returnMap = new HashMap<String, String>();

        returnMap.put("first", "one");
        returnMap.put("second", "two");

        return returnMap;
    }

    @GetMapping("/board")
    public BoardDTO getBoard(int boardNo) {
        return boardService.getBoard(boardNo);
    }

    //글 등록
    @PostMapping("/board")
    public void insertBoard(BoardDTO boardDTO) {
        
    }

    //글수정
    @PutMapping("/board")
    public void updateBoard(BoardDTO boardDTO) {

    }

    //글삭제
    @DeleteMapping("/board")
    public void deleteBoard(BoardDTO boardDTO) {

    }

    @GetMapping("/board-list")
    public List<BoardDTO> getBoardList() {
        return boardService.getBoardList();
    }







}
