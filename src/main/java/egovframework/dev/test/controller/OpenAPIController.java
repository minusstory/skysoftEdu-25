package egovframework.dev.test.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import egovframework.dev.test.service.OpenAPIService;
import egovframework.dev.test.vo.ApiVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class OpenAPIController {

	/** Log Info */
	protected Log log = LogFactory.getLog(this.getClass());

	@Resource(name = "openApiService")
	private OpenAPIService openApiService;

	@RequestMapping(value = "/test/registNews.do")
	public ModelAndView registNews(@RequestParam("searchText") String searchText, ModelMap model) throws Exception {
		List<ApiVO> apiList = new ArrayList<ApiVO>();
		try {
			StringBuffer urlBuffer = new StringBuffer();
			String apiKey = "c044479bd9e8590e499399f0ecbe37f7";
			urlBuffer.append("http://openapi.naver.com/search?key=");
			urlBuffer.append(apiKey);
			urlBuffer.append("&query=");
			urlBuffer.append(URLEncoder.encode(searchText, "utf-8")); // 한글일경우UTF-8을
																		// 사용하라
																		// 명시되었다.
			urlBuffer.append("&target=news&start=1&display=10"); // 정렬은
																	// 기본값(Date)으로
																	// 한다.
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(urlBuffer.toString());
			NodeList list = doc.getElementsByTagName("channel");

			System.out.println("length " + list.getLength());
			int seq = 0;
			for (int i = 0; i < list.getLength(); i++) {
				for (Node node = list.item(i).getFirstChild(); node != null; node = node.getNextSibling()) {
					if (node.getNodeName().equals("lastBuildData")) { // lastBuildData
						// DB에 저장할 것 VO에 셋팅
					}
					if (node.getNodeName().equals("item")) { // item안의 데이터들 파싱
						// DB에 저장할 것 VO에 셋팅
						ApiVO apiVO = new ApiVO();
						for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
							if (node2.getNodeName().equals("title")) {
								// DB에 저장할 것 VO에 셋팅
								apiVO.setTitle(node2.getTextContent());
							}
							if (node2.getNodeName().equals("description")) {
								// DB에 저장할 것 VO에 셋팅
								apiVO.setDescription(node2.getTextContent());
							}
							if (node2.getNodeName().equals("link")) {
								// DB에 저장할 것 VO에 셋팅
								apiVO.setLink(node2.getTextContent());
							}
						}
						// 서비스에 등록하는 코드
						seq = openApiService.addNews(apiVO);
					}
				}
				System.out.println("등록이 완료 되었습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/test/apilist.do");
		// return new ModelAndView("redirect:/test/apilist.do");
		// 컨트롤 뷰 사이 뿐만이 아니라 컨트롤 과 컨트롤 사이에도 연관이 가능하다.
	}

	@RequestMapping(value = "/test/apilist.do")
	public ModelAndView listNews(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, ModelMap model) throws Exception {

		int totalRecordCount = openApiService.getCountAll(); //전체 크기 리턴받기
		PaginationInfo paging = new PaginationInfo();
		paging.setCurrentPageNo(pageNum);
		paging.setPageSize(10);
		paging.setRecordCountPerPage(10);
		paging.setTotalRecordCount(totalRecordCount);

		ApiVO apiVO = new ApiVO();
		apiVO.setRecordCountPerPage(10);
		apiVO.setStartrow(paging.getFirstRecordIndex());

		List<ApiVO> list = openApiService.getNewsList(apiVO);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPageNum", pageNum);
		model.addAttribute("list",list);

		return new ModelAndView("test/apilist");
	}

	@RequestMapping(value="/test/apidetail.do")
	public ModelAndView detailNews(@ModelAttribute("ApiVO")ApiVO apiVO , ModelMap model) throws Exception{
		int seq = apiVO.getSeq();
		apiVO = openApiService.searchNews(seq);
		model.addAttribute("apiVO",apiVO);
		return new ModelAndView("test/apidetail");
	}
}
