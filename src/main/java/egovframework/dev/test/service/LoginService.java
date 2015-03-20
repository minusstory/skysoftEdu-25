package egovframework.dev.test.service;

import java.util.List;

import egovframework.dev.test.vo.MemberVO;

public interface LoginService {
	public List<MemberVO> getMember(MemberVO memberVO) throws Exception;

}
