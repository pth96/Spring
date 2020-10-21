package com.icia.memberboard.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.memberboard.dto.PageDTO;
import com.icia.memberboard.dao.BoardDAO;
import com.icia.memberboard.dao.CommentDAO;
import com.icia.memberboard.dto.BoardDTO;
import com.icia.memberboard.dto.CommentDTO;

@Service
public class BoardService {
	@Autowired
	private BoardDAO bDAO;
	
	@Autowired
	private CommentDAO cDAO;
	
	private ModelAndView mav;
	
	private static final int PAGE_LIMIT = 3;
	private static final int BLOCK_LIMIT = 5;


	public ModelAndView boardwrite(BoardDTO board) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile bfile = board.getBfile();
		String bfilename = bfile.getOriginalFilename();
		
		String savePath = "E:\\source\\spring\\MemberBoard\\src\\main\\webapp\\resources\\uploadfile\\"+bfilename;
		
		if(!bfile.isEmpty()) {
			bfile.transferTo(new File(savePath));
		}
		board.setBfilename(bfilename);
		int writeResult = bDAO.boardwrite(board);
		if(writeResult > 0)
			mav.setViewName("Main");
		else
			mav.setViewName("boardv/BoardWriteFileFail");
		
		return mav;
	}




	public ModelAndView boardlist(int page) {
		mav = new ModelAndView();
		int listcount = bDAO.listcount();
		int startRow = (page-1)*PAGE_LIMIT + 1;
		int endRow = page*PAGE_LIMIT;
		
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		List<BoardDTO> boardList = bDAO.boardlistpaging(paging);
		
		int maxPage =(int)(Math.ceil((double)listcount/PAGE_LIMIT));
		int startPage = (((int)(Math.ceil((double)page/BLOCK_LIMIT))) -1) * BLOCK_LIMIT + 1;
		
		int endPage = startPage + BLOCK_LIMIT -1;
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		
		mav.addObject("paging", paging);
		mav.addObject("boardList", boardList);
		mav.setViewName("boardv/BoardList");
		
		return mav;
	}




	public ModelAndView boardView(int bnumber, int page) {
		mav = new ModelAndView();
		List<CommentDTO> commentList = new ArrayList<CommentDTO>();
		CommentDTO cDTO = new CommentDTO();
		cDTO.setCbnumber(bnumber);
		BoardDTO bDTO = new BoardDTO();
		commentList = cDAO.commentList(cDTO.getCbnumber());
		bDAO.boardhits(bnumber);
		bDTO = bDAO.boardview(bnumber);
		mav.addObject("page", page);
		mav.addObject("boardView", bDTO);
		mav.addObject("commentList", commentList);
		mav.setViewName("boardv/BoardView");
		return mav;
	}




	public ModelAndView boardupdate(int bnumber) {
		mav = new ModelAndView();
		BoardDTO bDTO = new BoardDTO();
		bDTO = bDAO.boardview(bnumber);
		mav.addObject("boardUpdate", bDTO);
		mav.setViewName("boardv/BoardUpdate");
		return mav;
	}




	public ModelAndView boardupdateprocess(BoardDTO board) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile bfile = board.getBfile();
		String bfilename = bfile.getOriginalFilename();
		
		String savePath = "E:\\source\\spring\\MemberBoard\\src\\main\\webapp\\resources\\uploadfile\\"+bfilename;
		
		if(!bfile.isEmpty()) {
			bfile.transferTo(new File(savePath));
		}
		board.setBfilename(bfilename);
		int updateResult = bDAO.boardUpdateProcess(board);
		if(updateResult > 0) {
			mav.setViewName("redirect:/boardview?bnumber="+board.getBnumber());
		} else {
			mav.setViewName("boardv/BoardUpdateFail");
		}
		return mav;
	}




	public ModelAndView boarddelete(int bnumber) {
		mav = new ModelAndView();
		int deleteResult = bDAO.boarddelete(bnumber);
		if(deleteResult > 0) {
			mav.setViewName("redirect:/boardlist");
		} else {
			mav.setViewName("boardv/BoardDeleteFail");
		}
		return mav;
	}




	public ModelAndView boardsearch(String searchtype, String keyword) {
		mav = new ModelAndView();
		List<BoardDTO> serchlist = bDAO.boardSearch(searchtype,keyword);
		mav.addObject("boardList", serchlist);
		mav.setViewName("boardv/BoardList");
		return mav;
	}




	public ModelAndView hitslist(int page) {
		mav = new ModelAndView();
		int listcount = bDAO.hitslistcount();
		int startRow = (page-1)*PAGE_LIMIT + 1;
		int endRow = page*PAGE_LIMIT;
		
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		List<BoardDTO> boardList = bDAO.hitslist(paging);
		
		int maxPage =(int)(Math.ceil((double)listcount/PAGE_LIMIT));
		int startPage = (((int)(Math.ceil((double)page/BLOCK_LIMIT))) -1) * BLOCK_LIMIT + 1;
		
		int endPage = startPage + BLOCK_LIMIT -1;
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		
		mav.addObject("paging", paging);
		mav.addObject("boardList", boardList);
		mav.setViewName("boardv/HitsList");
		
		return mav;
	}

}
