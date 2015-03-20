package egovframework.dev.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.dev.test.vo.MemberVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("memberDAO")
public class MemberDao extends EgovAbstractDAO{

	public List<MemberVO> isMemberCheck(MemberVO memberVO) {
		return list("MemberDAO.isMemberCheck",memberVO);
	}

	public Integer searchMemberById(MemberVO memberVO) {
		return (Integer)selectByPk("MemberDAO.searchMemberById",memberVO);
	}

	public void addMember(MemberVO memberVO) {
		insert("MemberDAO.addMember",memberVO);
	}
}
