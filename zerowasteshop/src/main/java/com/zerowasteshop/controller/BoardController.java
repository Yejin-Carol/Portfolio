package com.zerowasteshop.controller;

import com.zerowasteshop.entity.Board;
import com.zerowasteshop.repository.BoardRepository;
import com.zerowasteshop.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;


@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardValidator boardValidator;

    @GetMapping("/list")
    public String board(Model model,
                        @RequestParam(required =false, defaultValue= "0")int page,
                        @RequestParam(required = false,defaultValue = "")String searchtxt) {
        int size = 5;
        Page<Board> list = boardRepository.findByTitleContainingOrContentContaining(
                searchtxt,
                searchtxt,
                PageRequest.of(page, size,
                    Sort.by(Sort.Direction.DESC, "id")
                    //select * from (findAll) order by desc limit 0,5; (SQL)
                )//1페이지 F8 한줄 진행 (디버깅)
        );
// 페이지 개수
        //int endpage = 0;
        // 6 / 5 = 1, 6 % 5 =1 나머지가 0보다 큰 경우
        //endpage = list.getSize()/size;
        /*System.out.println(list.getSize());
        System.out.println(list.getTotalPages());
        System.out.println(list.getTotalElements());
*/
        //System.out.println("curpageNumber"+list.getPageable().getPageNumber());
        model.addAttribute("startpage",1);
        //th:classappend -> active
        model.addAttribute("curpage",(list.getPageable().getPageNumber()+1));
        model.addAttribute("endpage", list.getTotalPages());
        model.addAttribute("list", list);
        return "board/list";
    }

        /*
select * from board a, board_tail b
where a.id = b.board_id;
select * from board a
left outer join board_tail b on a.id = b.board_id;
     */

    @GetMapping("/view")
    public String view(long id,Model model) {
        Board board = boardRepository.findById(id).orElse(null);
        model.addAttribute("board",board);
        return "board/view";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false, defaultValue = "0") long id) {
        Board board = boardRepository.findById(id).orElse(new Board());
        model.addAttribute("board", board);
        return "board/form";
    }

    @GetMapping("/delete")//211008 댓글달린 게시글 삭제 처리 추가!
    public String delete(@RequestParam(required = false, defaultValue = "0") long id) {
        System.out.println("id = " + id);
        Board board= boardRepository.findById(id).orElse(new Board());
        board.getBoardTailList().clear();
        boardRepository.delete(board);
        return "redirect:/board/list";
    }

    @PostMapping("/form")
    public String form(Model model, @Valid Board board, BindingResult bindingResult, Authentication authentication) {

        Board newboard = new Board();
        board.setName(authentication.getName());
        model.addAttribute("board",board);
        boardValidator.validate(board,bindingResult);
        if( bindingResult.hasErrors()){
            return "board/form";
        }
        long nano = System.currentTimeMillis();
        String curDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(nano);
        board.setDate(curDate);
        System.out.println("board.getId = "+board.getId());
        if(board.getId() !=0){
            newboard = boardRepository.findById(board.getId()).orElse(board);
            newboard.setTitle(board.getTitle());
            newboard.setContent(board.getContent());
            newboard.setName(board.getName());
            newboard.setContent(board.getContent());
            newboard.setId(board.getId());
            boardRepository.save(newboard);//save 안적어서 내용 수정 후 변경 안됐음
        }
        else {
            boardRepository.save(board);
        }
        return "redirect:/board/list";
    }
}
