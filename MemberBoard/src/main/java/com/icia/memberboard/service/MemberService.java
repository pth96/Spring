package com.icia.memberboard.service;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.memberboard.dao.MemberDAO;
import com.icia.memberboard.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private HttpSession session;
	
	private ModelAndView mav;

	public ModelAndView memberjoin(MemberDTO member) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile mfile = member.getMfile();
		String mfilename = mfile.getOriginalFilename();
		
		String savePath = "E:\\source\\spring\\MemberBoard\\src\\main\\webapp\\resources\\uploadfile\\"+mfilename;
		
		if(!mfile.isEmpty()) {
			mfile.transferTo(new File(savePath));
		}
		member.setMfilename(mfilename);
		int joinResult = mDAO.memberjoin(member);
		if(joinResult > 0)
			mav.setViewName("memberv/MemberLogin");
		else
			mav.setViewName("memberv/MemberJoinFail");
		
		return mav;
	}

	public ModelAndView memberlogin(MemberDTO member) {
		
		mav = new ModelAndView();
		String loginId = mDAO.memberlogin(member);
		if(loginId != null) {
			session.setAttribute("loginId", loginId);
			mav.setViewName("Main");
			
		}else {
			mav.setViewName("LoginFail");
		}
		return mav;
	}

	public ModelAndView memberlist() {
		mav = new ModelAndView();
		List<MemberDTO> memberlist = mDAO.memberlist();
		
		mav.addObject("memberlist", memberlist);
		mav.setViewName("memberv/MemberList");
		return mav;
	}

	public ModelAndView memberview(String mid) {
		mav = new ModelAndView();
		MemberDTO memberview = mDAO.memberview(mid);
		
		mav.addObject("memberview", memberview);
		mav.setViewName("memberv/MemberView");
		return mav;
	}

	public ModelAndView memberdelete(String mid) {
		mav = new ModelAndView();
		int deleteResult = mDAO.memberdelete(mid);
		
		if(deleteResult > 0) {
			mav.setViewName("redirect:/memberlist");
		}else {
			mav.setViewName("DeleteFail");
		}
		return mav;
	}

	public ModelAndView memberupdate() {
		mav = new ModelAndView();
		String mid = (String)session.getAttribute("loginId");
		MemberDTO memberupdate = mDAO.memberview(mid);
		
		mav.addObject("memberupdate", memberupdate);
		mav.setViewName("memberv/MemberUpdate");
		return mav;
	}

	public ModelAndView memberupdateprocess(MemberDTO member) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile mfile = member.getMfile();
		String mfilename = mfile.getOriginalFilename();
		
		String savePath = "E:\\source\\spring\\MemberBoard\\src\\main\\webapp\\resources\\uploadfile\\"+mfilename;
		
		if(!mfile.isEmpty()) {
			mfile.transferTo(new File(savePath));
		}
		member.setMfilename(mfilename);
		int memberupdateResult = mDAO.memberupdateprocess(member);
		
		if(memberupdateResult > 0) {
			mav.setViewName("Main");
		}else {
			mav.setViewName("UpdateFail");
			
		}
		return mav;
	}

	public ModelAndView kakaologin(JsonNode profile) {
		mav = new ModelAndView();
		String kakaoId = profile.get("id").asText();
		
		String loginId = mDAO.kakaologin(kakaoId);
		
		session.setAttribute("loginId", loginId);
		mav.setViewName("Main");
		return mav;
	}

	public ModelAndView naverlogin(String profile) throws ParseException {
		mav = new ModelAndView();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(profile);
		JSONObject naverUser = (JSONObject)obj;
		JSONObject userInfo = (JSONObject)naverUser.get("response");
		String naverId = (String)userInfo.get("id");
		String loginId = mDAO.naverLogin(naverId);
		session.setAttribute("loginId", loginId);
		mav.setViewName("Main");
		return mav;
	}

	public String idOverlap(String mid) {
		String checkResult = mDAO.idOverlap(mid);
		String resultMsg = null;
		if(checkResult == null) {
			resultMsg = "OK";
		}else {
			resultMsg = "NO";
		}
		return resultMsg;
	}

}

