package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

	private BoardService service;

	@GetMapping("/list")
	public void list(Model model) {

		log.info("list===========");
		model.addAttribute("list", service.getList());
	}

	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {

		// RedirectAttributes: 등록 작업이 끝난 후 다시 목록 화면으로 이동하기 위함
		// 새로 등록된 게시물의 번호를 같이 전달하기 위해서 이용함
		log.info("=====================Register: " + board);

		service.register(board);

		rttr.addFlashAttribute("result", board.getBno());

		return "redirect:/board/list";
	}
	
	@GetMapping("/register")
	public void register() {
		
	}

	@GetMapping("/get")
	public void get(@RequestParam("bno") Long bno, Model model) {
		// BoardController의 get() 메소드에 bno 값을 좀 더 명시적으로 처리하는 @RequestParam이용해서 지정함
		log.info("/get");
		model.addAttribute("board", service.get(bno));
	}

	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {

		log.info("/modify: " + board);

		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		
		log.info("/remove");
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}
}
