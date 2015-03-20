package egovframework.dev.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.dev.test.vo.MemberVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("myPageDao")
public class MyPageDao extends EgovAbstractDAO{

	public void updateInfo(MemberVO memberVO) {
		update("MyPageDao.updateInfo",memberVO);
	}

}
