package egovframework.dev.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.dev.test.vo.BoardVO;
import egovframework.dev.test.vo.PagingVO;
import egovframework.dev.test.vo.TableListVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("tableSkyTestDAO")
public class TableSkyTestDAO extends EgovAbstractDAO {

	public List<TableListVO> getTableList(PagingVO pagingVO){
		return list("TableSkyTestDAO.getTableList", pagingVO);

//		return listWithPaging("TableSkyTestDAO.getList", pagingVO, 10, 10);
	}

	public Integer getCountAll(){
		return (Integer) selectByPk("TableSkyTestDAO.getCountAll", 10);
	}

	public List<TableListVO> searchBoard(int seq) {
		return list("TableSkyTestDAO.searchBoard",seq);
	}

	public void addBoard(BoardVO boardVO) {
		insert("TableSkyTestDAO.addBoard",boardVO);
	}

	public void deleteBoard(int seq) {
		delete("TableSkyTestDAO.deleteBoard",seq);
	}

	public void updateBoard(TableListVO tableListVO) {
		update("TableSkyTestDAO.updateBoard",tableListVO);
	}

	public List<TableListVO> getAllTabelList() {
		Object obj = null;
		return list("TableSkyTestDAO.getAllTableList",obj);
	}
}
