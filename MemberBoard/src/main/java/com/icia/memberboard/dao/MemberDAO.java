package com.icia.memberboard.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.memberboard.dto.MemberDTO;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sql;

	public int memberjoin(MemberDTO member) {
		if(member.getKakaoId() != null) 
			return sql.insert("Member.kakaomemberjoin", member);
			
			else if(member.getNaverId() != null)
				return sql.insert("Member.navermemberjoin", member);
			else
				return sql.insert("Member.memberjoin", member);
			
			
		}

	public String memberlogin(MemberDTO member) {
		return sql.selectOne("Member.memberlogin", member);
	}

	public List<MemberDTO> memberlist() {
		return sql.selectList("Member.memberlist");
	}

	public MemberDTO memberview(String mid) {
		return sql.selectOne("Member.memberview", mid);
	}

	public int memberdelete(String mid) {
		return sql.delete("Member.memberdelete", mid);
	}

	public int memberupdateprocess(MemberDTO member) {
		return sql.update("Member.memberupdate", member);
	}

	public String kakaologin(String kakaoId) {
		return sql.selectOne("Member.kakaologin",kakaoId);
	}

	public String naverLogin(String naverId) {
		return sql.selectOne("Member.naverlogin", naverId);
	}

	public String idOverlap(String mid) {
		return sql.selectOne("Member.idOverlap", mid);
	}

}
