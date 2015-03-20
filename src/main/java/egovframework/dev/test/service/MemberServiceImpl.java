package egovframework.dev.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.dev.test.dao.MemberDao;
import egovframework.dev.test.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Resource
	private MemberDao memberDao;

	@Override
	public List<MemberVO> isMemberCheck(MemberVO memberVO) throws Exception {
		return memberDao.isMemberCheck(memberVO);
	}
	@Override
	public Integer searchMemberById(MemberVO memberVO) throws Exception {
		return memberDao.searchMemberById(memberVO);
	}
	@Override
	public void addMember(MemberVO memberVO) throws Exception {
		memberDao.addMember(memberVO);
	}
}
