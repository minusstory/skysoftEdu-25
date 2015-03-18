package egovframework.dev.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.dev.test.dao.OpenAPIDAO;
import egovframework.dev.test.vo.ApiVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("openApiService")
public class OpenAPIServiceImpl extends AbstractServiceImpl implements OpenAPIService {

	@Resource
	private OpenAPIDAO openApiDao;

	@Override
	public Integer addNews(ApiVO apiVO) {
		return openApiDao.addNews(apiVO);
	}

	@Override
	public Integer getCountAll() throws Exception {
		return openApiDao.getCountAll();
	}

	@Override
	public List<ApiVO> getNewsList(ApiVO apiVO) throws Exception {
		return openApiDao.getNewsList(apiVO);
	}

	@Override
	public ApiVO searchNews(int seq) throws Exception {
		return openApiDao.searchNews(seq);
	}

}
