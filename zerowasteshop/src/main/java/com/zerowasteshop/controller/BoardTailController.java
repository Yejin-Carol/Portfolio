package com.zerowasteshop.controller;

import com.zerowasteshop.entity.Board;
import com.zerowasteshop.entity.BoardTail;
import com.zerowasteshop.repository.BoardRepository;
import com.zerowasteshop.repository.BoardTailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boardtail")
public class BoardTailController {

    @Autowired
    BoardTailRepository boardTailRepository;

    @Autowired
    BoardRepository boardRepository;

    @PostMapping("/form")
    public String form(BoardTail boardtail, long board_id) {
        Board board = boardRepository.findById(board_id).orElse(new Board());
        System.out.println(boardtail);
        boardtail.setBoard(board);
        boardTailRepository.save(boardtail);
        return "redirect:/board/view?id="+board.getId();
    }

}
