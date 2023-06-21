package com.example.springboot_jpa_realfinal.service;


import com.example.springboot_jpa_realfinal.dto.BoardDTO;
import com.example.springboot_jpa_realfinal.entity.Board;

import java.util.List;


public interface BoardService {
    BoardDTO getBoard(int boardNo);

    List<Board> getBoardList();

    void insertBoard(Board board);

    void updateBoard(BoardDTO boardDTO);

    void deleteBoard(int boardNo);
}
