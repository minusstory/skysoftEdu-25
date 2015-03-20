package egovframework.dev.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.dev.test.vo.MemberVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("loginDAO")
public class LoginDao extends EgovAbstractDAO{

	public List<MemberVO> getMember(MemberVO memberVO) {
		return list("LoginDAO.getMember",memberVO);
	}

}
