package com.bit.springboard.service;

import com.bit.springboard.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    BoardDTO getBoard(int boardNo);

    List<BoardDTO> getBoardList();
}
