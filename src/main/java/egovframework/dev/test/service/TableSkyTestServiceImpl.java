package egovframework.dev.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.dev.test.dao.TableSkyTestDAO;
import egovframework.dev.test.vo.BoardVO;
import egovframework.dev.test.vo.PagingVO;
import egovframework.dev.test.vo.TableListVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("tableSkyTestService")
public class TableSkyTestServiceImpl extends AbstractServiceImpl implements
		TableSkyTestService {

	@Resource
	private TableSkyTestDAO tableSkyTestDAO;

	@Override
	public Integer getCountAll() {
		return tableSkyTestDAO.getCountAll();
	}

	@Override
	public List<TableListVO> getTableList(PagingVO pagingVO) {
		return tableSkyTestDAO.getTableList(pagingVO);
	}

	@Override
	public List<TableListVO> searchBoard(int temp) {
		return tableSkyTestDAO.searchBoard(temp);
	}

	@Override
	public void addBoard(BoardVO boardVO) {
		tableSkyTestDAO.addBoard(boardVO);
	}

	@Override
	public void deleteBoard(int seq) {
		tableSkyTestDAO.deleteBoard(seq);
	}

	@Override
	public void updateBoard(TableListVO tableListVO) {
		tableSkyTestDAO.updateBoard(tableListVO);
	}
}
