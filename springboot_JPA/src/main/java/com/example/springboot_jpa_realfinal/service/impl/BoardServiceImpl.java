package com.example.springboot_jpa_realfinal.service.impl;

import com.example.springboot_jpa_realfinal.dto.BoardDTO;
import com.example.springboot_jpa_realfinal.entity.Board;
import com.example.springboot_jpa_realfinal.mapper.BoardMapper;
import com.example.springboot_jpa_realfinal.repository.BoardRepository;
import com.example.springboot_jpa_realfinal.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private BoardMapper boardMapper;

    private BoardRepository boardRepository;

    //생성자 주입
    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper, BoardRepository boardRepository) {
        this.boardMapper = boardMapper;
        this.boardRepository = boardRepository;
    }

    @Override
    public BoardDTO getBoard(int boardNo) {
        return boardMapper.getBoard(boardNo);
    }

    @Override
    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    @Override
    public void insertBoard(Board board) {
        boardRepository.save(board);
    }

    @Override
    public void updateBoard(BoardDTO boardDTO) {
        boardMapper.updateBoard(boardDTO);
    }

    @Override
    public void deleteBoard(int boardNo) {
        boardMapper.deleteBoard(boardNo);
    }
}
