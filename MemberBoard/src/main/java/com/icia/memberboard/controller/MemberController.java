package com.icia.memberboard.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.icia.memberboard.api.KakaoJoinApi;
import com.icia.memberboard.api.KakaoLoginApi;
import com.icia.memberboard.api.NaverJoinApi;
import com.icia.memberboard.api.NaverLoginApi;
import com.icia.memberboard.dto.MemberDTO;
import com.icia.memberboard.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService mService;
	
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private KakaoJoinApi kakaojoinApi;
	
	@Autowired
	private KakaoLoginApi kakaoLoginApi;
	
	@Autowired
	private NaverJoinApi naverJoinApi;
	
	@Autowired
	private NaverLoginApi naverLoginApi;
	
	@RequestMapping(value="/memberjoinform")
	public String memberjoinform() {
		return "memberv/MemberJoin";
	}
	
	@RequestMapping(value="/memberloginform")
	public String memberloginform() {
		return "memberv/MemberLogin";
	}

	@RequestMapping(value="/memberjoin")
	public ModelAndView memberjoin(@ModelAttribute MemberDTO member) throws IllegalStateException, IOException {
	mav = mService.memberjoin(member);
	return mav;
	}
	
	@RequestMapping(value="/memberlogin")
	public ModelAndView memberlogin(@ModelAttribute MemberDTO member) {
		mav = mService.memberlogin(member);
		return mav;
	}
	
	@RequestMapping(value="/memberlist")
	public ModelAndView memberlist() {
		mav = mService.memberlist();
		return mav;
	}
	
	@RequestMapping(value="/memberview")
	public ModelAndView memberview(@RequestParam("mid") String mid) {
		mav = mService.memberview(mid);
		return mav;
	}
	@RequestMapping(value="/memberdelete")
	public ModelAndView memberdelete(@RequestParam("mid") String mid) {
		mav = mService.memberdelete(mid);
		return mav;
	}
	@RequestMapping(value="/memberupdate")
	public ModelAndView memberupdate() {
		mav = mService.memberupdate();
		return mav;
	}
	@RequestMapping(value="/memberupdateprocess")
	public ModelAndView memberupdateprocess(@ModelAttribute MemberDTO member) throws IllegalStateException, IOException {
		mav = mService.memberupdateprocess(member);
		return mav;
	}
	@RequestMapping(value="/memberlogout")
	public String memberlogout() {
		
		session.invalidate();
		
		return "home";
	}
	//카카오 서버 로그인
		@RequestMapping(value="/kakaojoin")
		public ModelAndView kakaojoin(HttpSession session) {
			
			String kakaoUrl = kakaojoinApi.getAuthorizationUrl(session);
			mav = new ModelAndView();
			mav.addObject("kakaoUrl", kakaoUrl);
			mav.setViewName("KakaoLogin");
			
			return mav;
		}
		//카카오 서버 인증 통과 후 처리
		@RequestMapping(value="/kakaojoinok")
		public ModelAndView kakaojoinOK(@RequestParam("code") String code, HttpSession session) {
			
			JsonNode token = kakaojoinApi.getAccessToken(code);
			JsonNode profile = kakaojoinApi.getKakaoUserInfo(token.path("access_token"));
			System.out.println("profile"+profile);
			//profile에 담긴 id 값을 가지고 MemberJoin.jsp로 ㅣ동
			String kakaoId = profile.get("id").asText();
			mav = new ModelAndView();
			mav.addObject("kakaoId", kakaoId);
			mav.setViewName("memberv/MemberJoin");
			return mav;
		}
		//카카오 로그인
		@RequestMapping(value="/kakaologin")
		public ModelAndView kakaologin(HttpSession session) {
			
			String kakaoUrl = kakaoLoginApi.getAuthorizationUrl(session);
			mav = new ModelAndView();
			mav.addObject("kakaoUrl", kakaoUrl);
			mav.setViewName("KakaoLogin");
			
			return mav;
		}
		
		@RequestMapping(value="/kakaologinok")
		public ModelAndView kakaologinok(@RequestParam("code") String code) {
			JsonNode token = kakaoLoginApi.getAccessToken(code);
			JsonNode profile = kakaoLoginApi.getKakaoUserInfo(token.path("access_token"));
			
			mav = mService.kakaologin(profile);
			return mav;
		}
		
		@RequestMapping(value="/naverjoin")
		public ModelAndView naverjoin(HttpSession session) {
			String naverUrl = naverJoinApi.getAuthorizationUrl(session);
			mav = new ModelAndView();
			mav.addObject("naverUrl", naverUrl);
			mav.setViewName("NaverLogin");
			return mav;
		}
		
		@RequestMapping(value="/naverjoinok")
		public ModelAndView naverJoinOk(@RequestParam("code") String code,@RequestParam("state") String state,HttpSession session) throws IOException, ParseException {
		mav = new ModelAndView();
		OAuth2AccessToken oauthToken = naverJoinApi.getAccessToken(session, code, state);
		String profile = naverJoinApi.getUserProfile(oauthToken);
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(profile);
		
		JSONObject naverUser = (JSONObject)obj;
		JSONObject userInfo = (JSONObject)naverUser.get("response");
		String naverId = (String) userInfo.get("id");
		
		mav.addObject("naverId", naverId);
		mav.setViewName("memberv/MemberJoin");
		
		return mav;

	}
		@RequestMapping(value="/naverlogin")
		public ModelAndView naverlogin(HttpSession session) {
			String naverUrl = naverLoginApi.getAuthorizationUrl(session);
			mav = new ModelAndView();
			mav.addObject("naverUrl", naverUrl);
			mav.setViewName("NaverLogin");
			return mav;
		}
		
		@RequestMapping(value="/naverloginok")
		public ModelAndView naverLoginOk(@RequestParam("code") String code,@RequestParam("state") String state,HttpSession session) throws IOException, ParseException {
			OAuth2AccessToken oauthToken = naverLoginApi.getAccessToken(session, code, state);
			String profile = naverJoinApi.getUserProfile(oauthToken);
			mav = mService.naverlogin(profile);
			
			return mav;
		}
		@RequestMapping(value="/idoverlap")
		public @ResponseBody String idOverlap(@RequestParam("mid") String mid) {
			System.out.println("전달받은 값"+mid);
			String resultMsg = mService.idOverlap(mid);
			return resultMsg;
		}
		

	

	

}
