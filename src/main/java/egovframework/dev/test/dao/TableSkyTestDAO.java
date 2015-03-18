package egovframework.dev.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.dev.test.vo.PagingVO;
import egovframework.dev.test.vo.TableListFileVO;
import egovframework.dev.test.vo.TableListVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("tableSkyTestDAO")
public class TableSkyTestDAO extends EgovAbstractDAO {

	public List<TableListVO> getTableListPlusFile(PagingVO pagingVO){
		return list("TableSkyTestDAO.getTableListPlusFile", pagingVO);
	}

	public List<TableListVO> searchBoard(int seq) {
		return list("TableSkyTestDAO.searchBoard",seq);
	}

	public int addBoard(TableListVO tableListVO) {
		return (Integer) insert("TableSkyTestDAO.addBoard",tableListVO);
	}
	public void addAttachFile(TableListFileVO tableListFileVO) {
		insert("TableSkyTestDAO.addAttachFile",tableListFileVO);
	}


	public void deleteBoard(int seq) {
		delete("TableSkyTestDAO.deleteBoard",seq);
	}
	public void deleteAttachFile(int seq) {
		delete("TableSkyTestDAO.deleteAttachFile",seq);
	}


	public void updateBoard(TableListVO tableListVO) {
		update("TableSkyTestDAO.updateBoard",tableListVO);
	}
	public void updateAttachFile(TableListFileVO tableListFileVO) {
		update("TableSkyTestDAO.updateAttachFile",tableListFileVO);
	}

	public Integer getCountByObject(TableListVO tableListVO) {
		return (Integer)selectByPk("TableSkyTestDAO.getCountByObject", tableListVO);
	}

}
