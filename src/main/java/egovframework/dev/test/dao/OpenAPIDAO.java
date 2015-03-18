package egovframework.dev.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.dev.test.vo.ApiVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("openApiDAO")
public class OpenAPIDAO extends EgovAbstractDAO {

	public Integer addNews(ApiVO apiVO) {
		return (Integer) insert("OpenApiDAO.addNews",apiVO);
	}

	public ApiVO searchNews(int seq) {
		return (ApiVO)selectByPk("OpenApiDAO.searchNews", seq);
	}

	public Integer getCountAll() {
		return (Integer)selectByPk("OpenApiDAO.getCountAll", null);
	}

	public List<ApiVO> getNewsList(ApiVO apiVO) {
		return list("OpenApiDAO.getNewsList",apiVO);
	}
}
