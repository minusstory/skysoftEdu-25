package egovframework.dev.test.service;

import java.util.List;

import egovframework.dev.test.vo.BoardVO;
import egovframework.dev.test.vo.PagingVO;
import egovframework.dev.test.vo.TableListVO;

public interface TableSkyTestService {
	public Integer getCountAll();
	public List<TableListVO> getTableList(PagingVO paingVO);
	public List<TableListVO> searchBoard(int temp);
	public void addBoard(BoardVO boardVO);
	public void deleteBoard(int seq);
	public void updateBoard(TableListVO tableListVO);
}
