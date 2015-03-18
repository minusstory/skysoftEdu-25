package egovframework.dev.test.service;

import java.util.List;

import egovframework.dev.test.vo.PagingVO;
import egovframework.dev.test.vo.TableListFileVO;
import egovframework.dev.test.vo.TableListVO;

public interface TableSkyTestService {

	public List<TableListVO> searchBoard(int temp);
	public int addBoard(TableListVO tableListVO);
	public void deleteBoard(int seq);
	public void updateBoard(TableListVO tableListVO);
	public Integer getCountByObject(TableListVO tableListVO);
	public void addAttachFile(TableListFileVO tableListFileVO);
	public void deleteAttachFile(int seq);
	public void updateAttachFile(TableListFileVO tableListFileVO);
	public List<TableListVO> getTableListPlusFile(PagingVO pagingVO);
}
