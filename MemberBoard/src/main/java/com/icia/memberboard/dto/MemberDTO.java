package com.icia.memberboard.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class MemberDTO {
	private String mid;
	private String mpassword;
	private String mname;
	private String mbirth;
	private String memail;
	private String mpostnum;
	private String maddressroad;
	private String maddressjibun;
	private String maddress;
	private String maddress1;
	private String mphone;
	private MultipartFile mfile;
	private String mfilename;
	
	private String kakaoId;
	private String naverId;

}
