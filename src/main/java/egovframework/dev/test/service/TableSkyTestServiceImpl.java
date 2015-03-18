package egovframework.dev.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.dev.test.dao.TableSkyTestDAO;
import egovframework.dev.test.vo.PagingVO;
import egovframework.dev.test.vo.TableListFileVO;
import egovframework.dev.test.vo.TableListVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("tableSkyTestService")
public class TableSkyTestServiceImpl extends AbstractServiceImpl implements
		TableSkyTestService {

	@Resource
	private TableSkyTestDAO tableSkyTestDAO;

	@Override
	public List<TableListVO> getTableListPlusFile(PagingVO pagingVO){
		return tableSkyTestDAO.getTableListPlusFile(pagingVO);
	}

	@Override
	public List<TableListVO> searchBoard(int temp) {
		return tableSkyTestDAO.searchBoard(temp);
	}

	@Override
	public int addBoard(TableListVO tableListVO) {
		return tableSkyTestDAO.addBoard(tableListVO);
	}

	@Override
	public void deleteBoard(int seq) {
		tableSkyTestDAO.deleteBoard(seq);
	}

	@Override
	public void updateBoard(TableListVO tableListVO) {
		tableSkyTestDAO.updateBoard(tableListVO);
	}
	@Override
	public Integer getCountByObject(TableListVO tableListVO) {
		return tableSkyTestDAO.getCountByObject(tableListVO);
	}

	@Override
	public void addAttachFile(TableListFileVO tableListFileVO) {
		tableSkyTestDAO.addAttachFile(tableListFileVO);
	}

	@Override
	public void deleteAttachFile(int seq) {
		tableSkyTestDAO.deleteAttachFile(seq);
	}
	@Override
	public void updateAttachFile(TableListFileVO tableListFileVO) {
		tableSkyTestDAO.updateAttachFile(tableListFileVO);
	}
}
