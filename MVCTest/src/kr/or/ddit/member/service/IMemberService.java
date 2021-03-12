package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

public interface IMemberService {

	public int insertMember(MemberVO mv);

	public boolean checkMember(String memId);

	public List<MemberVO> getAllMemberList();

	public int updateMember(MemberVO mv);

	public int deleteMember(String memId);
}
