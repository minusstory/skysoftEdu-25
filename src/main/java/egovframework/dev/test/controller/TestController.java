package egovframework.dev.test.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import egovframework.dev.test.service.TableSkyTestService;
import egovframework.dev.test.vo.BoardVO;
import egovframework.dev.test.vo.TableListVO;
import egovframework.framework.annotation.PageTitle;
import egovframework.framework.util.ExcelUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
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

    @Resource(name="fileuploadPropService")
    private EgovPropertyService fileuploadPropService;

    @PageTitle("입력(C)")
    @RequestMapping(value="/test/secondInsert.do" , method=RequestMethod.POST )
    public ModelAndView secondInsertBoard(
    		@ModelAttribute("boardVO") BoardVO boardVO,
    		ModelMap model
    		) throws Exception{

    	tableSkyTestService.addBoard(boardVO);
    	return new ModelAndView("redirect:/test/list.do");
    }

    @PageTitle("등록화면 보기")
    @RequestMapping(value="/test/firstInsert.do")
    public String firstInsertBoard(){
    	return "test/insert";
    }



    @PageTitle("조회(R)")
    @RequestMapping(value="/test/detail.do")
    public ModelAndView detailBoard(
    		int seq,
    		@ModelAttribute("tableListVO") TableListVO tableListVO,
    		ModelMap model	)throws Exception
    {
    	List<TableListVO> list  = tableSkyTestService.searchBoard(seq);
    	tableListVO = list.get(0);
    	model.addAttribute("tableListVO",tableListVO);
    	return new ModelAndView("test/detail");
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

    	model.addAttribute("paging", paging);
    	model.addAttribute("list",list);

        return new ModelAndView("test/list");
        //tableListVO는 jsp에서 불려지는데 넘겨지는 방법??
    }


    @PageTitle("선택된 글 삭제")
    @RequestMapping(value="/test/selectDelete.do")
    public ModelAndView deleteBoards(
    		String seqs
    		)throws Exception{
    	StringTokenizer st = new StringTokenizer(seqs," ,");
    	while(st.hasMoreTokens()){
    		tableSkyTestService.deleteBoard(Integer.valueOf(st.nextToken()));
    	}
    	return new ModelAndView("redirect:/test/list.do");
    }

    @PageTitle("엑셀 다운")
    @RequestMapping(value="/test/excelDown.do")
    public ModelAndView excelDown(
    		@RequestParam(value="pageNum",defaultValue="1") int pageNum,
    		@ModelAttribute("tableListVO") TableListVO tableListVO
    		)	throws Exception
    {
    	//해당 페이지만 엑셀로 다운 받아야 되므로 Paging 이용
    	int totalRowCount = tableSkyTestService.getCountAll();
    	PaginationInfo paging = new PaginationInfo();
    	paging.setCurrentPageNo(pageNum+1); //초기값이 0부터 시작임
    	paging.setRecordCountPerPage(10);
    	paging.setPageSize(10);
    	paging.setTotalRecordCount(totalRowCount);
    	tableListVO.setStartrow(paging.getFirstRecordIndex()); //
    	tableListVO.setRecordCountPerPage(10);
    	List<TableListVO> lists = tableSkyTestService.getTableList(tableListVO);
    	return excelSameMethod("categoryExcelView", "categoryMap", lists);
    }

    @PageTitle("엑셀 전체 다운")
    @RequestMapping(value="/test/excelAllDown.do")
    public ModelAndView excelAllDown(

    		@ModelAttribute("tableListVO") TableListVO tableListVO,
    		ModelMap model
    		) throws Exception
    {
    	List<TableListVO> lists = tableSkyTestService.getAllTableList();
    	return excelSameMethod("categoryExcelView", "categoryMap", lists);
    }

    //소스코드 중복으로 인한 내부함수
    private ModelAndView excelSameMethod(String viewName,String mapName, List<TableListVO> lists ){
    	Map<String, Object> map = new HashMap<String , Object>();
    	map.put("category", lists);
    	return new ModelAndView(viewName,mapName,map);
    }

    @PageTitle("팝업창 ")
    @RequestMapping(value="/test/excelUploadPopup.do")
    public ModelAndView excelUploadPopup()
    	throws Exception{
    	return new ModelAndView("test/popup");
    }

    @PageTitle("엑셀 업로드")
    @RequestMapping(value="/test/excelUpload.do")
    public ModelAndView excelUpload(
    		@RequestParam("excelFile") MultipartFile excelFile,
    		ModelMap model
    	)throws Exception{
    	String result = "false" ;
    	int i;

    	//File 저장 경로 생성
    	StringBuilder filePath = new StringBuilder(fileuploadPropService.getString("fileupload.global.target-directory"));
    	//properties 에 지정되있는 경로의 값으로 StringBuilder 생성
    	System.out.println("excelFile.getOriginalFilename : " + excelFile.getOriginalFilename());
    	System.out.println("returnValue : " + filePath);

    	filePath.append("\\");
    	filePath.append(excelFile.getOriginalFilename());

    	byte fileData[] = excelFile.getBytes(); //byte 배열로 excelFile 데이터 모두 읽기
    	FileOutputStream fos = new FileOutputStream(filePath.toString());
    	fos.write(fileData);
    	fos.close();

    	String[] fieldNames = {"title" , "contents"};

    	List<BoardVO> lists = ExcelUtil.loadExcelFile(filePath.toString(), BoardVO.class, fieldNames, 1);
    	for(i=0;i<lists.size();i++){
    		tableSkyTestService.addBoard(lists.get(i));
    	}

    	if(i==lists.size())//반복문을 마치고 나왔을 경우 (모두 결과 실행)
    		result = "true";
    	model.addAttribute("result",result);
    	return new ModelAndView("test/popupresult");
    }//popup
    //경고창
    //result page 작성


}