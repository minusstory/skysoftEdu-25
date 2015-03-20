package egovframework.dev.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.dev.test.dao.LoginDao;
import egovframework.dev.test.vo.MemberVO;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Resource
	private LoginDao loginDao;

	@Override
	public List<MemberVO> getMember(MemberVO memberVO) throws Exception {
		return loginDao.getMember(memberVO);
	}

}
