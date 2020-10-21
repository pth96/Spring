package com.icia.memberboard.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.memberboard.dto.BoardDTO;
import com.icia.memberboard.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService bService;
	
	private ModelAndView mav;
	
	@RequestMapping(value="/boardwriteform")
	public String boardwriteform() {
		return "boardv/BoardWrite";
	}
	@RequestMapping(value="/boardwrite")
	public ModelAndView boardwrite(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {
		mav = bService.boardwrite(board);
		return mav;
	}
	@RequestMapping(value="/boardlist")
	public ModelAndView boardlist(@RequestParam (value="page",required=false,defaultValue="1") int page) {
		mav = bService.boardlist(page);
		return mav;
	}
	@RequestMapping(value="boardview")
	public ModelAndView boardView(@RequestParam("bnumber") int bnumber,@RequestParam(value="page",required=false,defaultValue="1") int page) {
		mav = bService.boardView(bnumber, page);
		return mav;
	}
	@RequestMapping(value="boardupdate")
	public ModelAndView boardupdate(@RequestParam("bnumber") int bnumber) {
		mav = bService.boardupdate(bnumber);
		return mav;
	}
	@RequestMapping(value="/boardupdateprocess")
	public ModelAndView boardupdateprocess(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {
		mav = bService.boardupdateprocess(board);
		return mav;
	}
	@RequestMapping(value="boarddelete")
	public ModelAndView boarddelete(@RequestParam("bnumber") int bnumber) {
		mav = bService.boarddelete(bnumber);
		return mav;
	}
	@RequestMapping(value="boardsearch")
	public ModelAndView boardsearch(@RequestParam("searchtype") String searchtype, @RequestParam("keyword") String keyword) {
		mav = bService.boardsearch(searchtype, keyword);
		return mav;
	}
	@RequestMapping(value="/hitslist")
	public ModelAndView hitslist(@RequestParam (value="page",required=false,defaultValue="1") int page) {
		mav = bService.hitslist(page);
		return mav;
	}

}
