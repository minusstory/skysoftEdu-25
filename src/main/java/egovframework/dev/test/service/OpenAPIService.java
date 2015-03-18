package egovframework.dev.test.service;

import java.util.List;

import egovframework.dev.test.vo.ApiVO;

public interface OpenAPIService {
	public Integer addNews(ApiVO apiVO) throws Exception;
	public Integer getCountAll() throws Exception;
	public List<ApiVO> getNewsList(ApiVO apiVO) throws Exception;
	public ApiVO searchNews(int seq) throws Exception;
}
