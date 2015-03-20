package egovframework.dev.test.service;

import java.util.List;

import egovframework.dev.test.vo.MemberVO;

public interface MemberService {
	public List<MemberVO> isMemberCheck(MemberVO memberVO) throws Exception;
	public Integer searchMemberById(MemberVO memberVO) throws Exception;
	public void addMember(MemberVO memberVO) throws Exception;
}
