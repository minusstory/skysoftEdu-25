package egovframework.dev.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.dev.test.dao.MyPageDao;
import egovframework.dev.test.vo.MemberVO;

@Service("myPageService")
public class MyPageServiceImpl implements MyPageService {

	@Resource
	private MyPageDao myPageDao;


	@Override
	public void updateInfo(MemberVO memberVO) throws Exception {
		myPageDao.updateInfo(memberVO);
	}
}
