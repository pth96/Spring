package com.icia.memberboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.memberboard.dto.BoardDTO;
import com.icia.memberboard.dto.PageDTO;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate sql;

	public int boardwrite(BoardDTO board) {
		return sql.insert("Board.boardwrite", board);
	}

	public int listcount() {
		return sql.selectOne("Board.boardlistcount");
	}

	public List<BoardDTO> boardlistpaging(PageDTO paging) {
		return sql.selectList("Board.boardlist", paging);
	}

	public void boardhits(int bnumber) {
		sql.update("Board.boardhits", bnumber);
		
	}

	public BoardDTO boardview(int bnumber) {
		return sql.selectOne("Board.boardview", bnumber);
	}

	public int boardUpdateProcess(BoardDTO board) {
		return sql.update("Board.boardupdateurocess", board);
	}

	public int boarddelete(int bnumber) {
		return sql.delete("Board.boarddelete", bnumber);
	}

	public List<BoardDTO> boardSearch(String searchtype, String keyword) {
		Map<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("type", searchtype);
		searchMap.put("word", keyword);
		return sql.selectList("Board.boardsearch", searchMap);
	}

	public List<BoardDTO> hitslist(PageDTO paging) {
		return sql.selectList("Board.hitslist", paging);
	}

	public int hitslistcount() {
		return sql.selectOne("Board.hitslistcount");
	}

	

}
