package egovframework.dev.test.controller;

import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.dev.test.service.TableSkyTestService;
import egovframework.dev.test.vo.BoardVO;
import egovframework.dev.test.vo.TableListVO;
import egovframework.framework.annotation.PageTitle;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**-----------------------------------------------------------------------
 * skysoft edu Project
 *------------------------------------------------------------------------
 * @Class TestController.java
 * @Description Test 과제 프로젝트
 * @author anonymous
 * @since yyyy.mm.dd
 * @version 1.0
 *
 * @Copyright (c) (주) 하늘연소프트 개발사업부 개발팀 All rights reserved.
 *------------------------------------------------------------------------
 * Modification Information
 *------------------------------------------------------------------------
 * 수정일			수정자			수정내용
 * ------------------------------------------------------------------------
 * yyyy.mm.dd	anonymous	최초생성
 */
@Controller
public class TestController {//주석

    /** Log Info */
    protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name="tableSkyTestService")
    private TableSkyTestService tableSkyTestService;

    /**
     * 리스트(L)
     * @param TestVO 검색조건정보
     * @param model 화면모델
     * @return Test
     * @throws Exception
     */
    @PageTitle("리스트(L)")
    @RequestMapping(value="/test/list.do")
    public ModelAndView retrieveList(
    		@RequestParam(value="pageNum",defaultValue="1") int pageNum,
    		@ModelAttribute("tableListVO") TableListVO tableListVO,
    		ModelMap model
    	)throws Exception {

    	int totalRowCount = tableSkyTestService.getCountAll();

    	//PageUtil pu = new PageUtil(pageNum,totalRowCount,10,10);

    	//pagingVO.setStartrow(pu.getStartRow());
    	//pagingVO.setEndrow(pu.getEndRow());
    	PaginationInfo paging = new PaginationInfo();
    	paging.setCurrentPageNo(pageNum);
    	paging.setRecordCountPerPage(10);
    	paging.setPageSize(10);
    	paging.setTotalRecordCount(totalRowCount);

    	tableListVO.setStartrow(paging.getFirstRecordIndex());
    	tableListVO.setRecordCountPerPage(10);
    	List<TableListVO> list = tableSkyTestService.getTableList(tableListVO);

    	//System.out.println(list.get(0).getRegdtm()+"///////////////////////////");
    	//model.addAttribute("pu",pu);
    	model.addAttribute("paging", paging);
    	model.addAttribute("list",list);

        return new ModelAndView("test/list");
    }

    @PageTitle("조회(R)")
    @RequestMapping(value="/test/detail.do")
    public ModelAndView detailBoard(
    		int seq,
    		@ModelAttribute("tableListVO") TableListVO tableListVO,
    		ModelMap model	)throws Exception
    {
    	List<TableListVO> list  = tableSkyTestService.searchBoard(seq);
//    	System.out.println(listVO.getRegdtm()+"///////////////////////////");
    	tableListVO = list.get(0);
    	model.addAttribute("tableListVO",tableListVO);
    	return new ModelAndView("test/detail");
    }

    @PageTitle("입력(C)")
    @RequestMapping(value="/test/secondInsert.do" , method=RequestMethod.POST )
    public ModelAndView secondInsertBoard(
    		@ModelAttribute("boardVO") BoardVO boardVO,
    		ModelMap model
    		) throws Exception{

    	tableSkyTestService.addBoard(boardVO);
    	return new ModelAndView("redirect:/test/list.do");
    }

    @RequestMapping(value="/test/firstInsert.do")
    public String firstInsertBoard(){
    	return "test/insert";
    }


    @PageTitle("수정(U)")
    @RequestMapping(value="/test/update.do")
    public ModelAndView updateBoard(
    	@ModelAttribute("tableListVO") TableListVO tableListVO
    ) throws Exception
    {
//    	tableListVO = tableSkyTestService.searchBoard(seq);
    	tableSkyTestService.updateBoard(tableListVO);
    	return new ModelAndView("redirect:/test/list.do");
    }

    @PageTitle("삭제(D)")
    @RequestMapping(value="/test/delete.do")
    public ModelAndView deleteBoard(
    		int seq
    		)	throws Exception
    {
    	tableSkyTestService.deleteBoard(seq);
    	return new ModelAndView("redirect:/test/list.do");
    }

    @RequestMapping(value="/test/selectDelete.do")
    public ModelAndView deleteBoards(
    		String seqs
    		)throws Exception{
    	StringTokenizer st = new StringTokenizer(seqs," ,");
//    	System.out.println(seqs+"///////////////////////////////////////////////");
    	while(st.hasMoreTokens()){
//    		System.out.println(Integer.valueOf(st.nextToken())+"////////////////////////////////////");
    		tableSkyTestService.deleteBoard(Integer.valueOf(st.nextToken()));
    	}

    	return new ModelAndView("redirect:/test/list.do");
    }


}