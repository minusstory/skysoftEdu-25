package egovframework.dev.test.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import egovframework.dev.test.service.TableSkyTestService;
import egovframework.dev.test.utils.PageUtilSky;
import egovframework.dev.test.vo.TableListFileVO;
import egovframework.dev.test.vo.TableListVO;
import egovframework.framework.annotation.PageTitle;
import egovframework.framework.util.ExcelUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * -----------------------------------------------------------------------
 * skysoft edu Project
 * ------------------------------------------------------------------------
 *
 * @Class TestController.java
 * @Description Test 과제 프로젝트
 * @author anonymous
 * @since yyyy.mm.dd
 * @version 1.0
 *
 * @Copyright (c) (주) 하늘연소프트 개발사업부 개발팀 All rights reserved.
 *            ----------------------
 *            -------------------------------------------------- Modification
 *            Information
 *            --------------------------------------------------------
 *            ---------------- 수정일 수정자 수정내용
 *            --------------------------------------
 *            ---------------------------------- yyyy.mm.dd anonymous 최초생성
 */
@Controller
public class TestController {// 주석

	/** Log Info */
	protected Log log = LogFactory.getLog(this.getClass());

	@Resource(name = "tableSkyTestService")
	private TableSkyTestService tableSkyTestService;

	@Resource(name = "fileuploadPropService")
	private EgovPropertyService fileuploadPropService;

	@PageTitle("입력(C)")
	@RequestMapping(value = "/test/secondInsert.do", method = RequestMethod.POST)
	public ModelAndView secondInsertBoard(@ModelAttribute("TableListVO") TableListVO tableListVO, ModelMap model) throws Exception {

		//글 등록
		int result = (Integer) tableSkyTestService.addBoard(tableListVO);

		//첨부파일 존재 시 처리
		if(!tableListVO.getAttachFile().isEmpty()){
			if(!uploadFile(tableListVO.getAttachFile(),"N", result)){
				//!!!!!문제생김 철회 경고창....
				//등록시 오류발생하면 등록페이지로
				String msg = "입력시 오류 발생하였습니다 ";
				model.addAttribute("msg", msg);
				return new ModelAndView("test/insert");
			}
		}
		return new ModelAndView("redirect:/test/list.do");
	}

	private boolean uploadFile(MultipartFile file, String delFileYn , int seq) {
		// prevRealfilenm 기본값 null
		String storedPath = fileuploadPropService.getString("fileupload.global.target-directory");//저장경로
		try{
			if(delFileYn.equals("Y")){ //기존 파일을 삭제할 경우
				List<TableListVO> list = tableSkyTestService.searchBoard(seq);
				File prevFile = new File(storedPath , list.get(0).getSavefilenm()); //기존 파일 이름
				prevFile.delete(); // 이전파일 삭제

				tableSkyTestService.deleteAttachFile(seq); //DB파일 삭제
			}

			if(!(file.isEmpty())){ //파일 등록
				String realfilenm = file.getOriginalFilename(); //신규 파일 이름
				String savefilemn = getSaveFilemn(seq);
				File targetFile = new File(storedPath,savefilemn);
				file.transferTo(targetFile); // 신규 파일 등록

				TableListFileVO tlfVO = new TableListFileVO();
				tlfVO.setOrgseq(seq);
				tlfVO.setRealfilenm(realfilenm);
				tlfVO.setUseyn("Y");
				tlfVO.setSavefilenm(savefilemn);
				tableSkyTestService.addAttachFile(tlfVO);//DB에 파일 등록
			}
			return true;
		}
		catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	//savefilemn 받아오는 내부함수
	private String getSaveFilemn(int seq){
		long today = System.currentTimeMillis();
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String todayTime = dayFormat.format(new Date(today));
		return seq+""+1+todayTime; //seq + 파일갯수 + todayTime 하여 리턴
	}

	@PageTitle("등록화면 보기")
	@RequestMapping(value = "/test/firstInsert.do")
	public String firstInsertBoard() {
		return "test/insert";
	}

	@PageTitle("조회(R)")
	@RequestMapping(value = "/test/detail.do")
	public ModelAndView detailBoard(int seq , ModelMap model) throws Exception {
		TableListVO tableListVO = null;
		List<TableListVO> list = tableSkyTestService.searchBoard(seq);
		tableListVO = list.get(0);

		model.addAttribute("tableListVO", tableListVO);
		return new ModelAndView("test/detail");
		//간결하게 수정하기
	}

	@PageTitle("수정(U)")
	@RequestMapping(value = "/test/update.do")
	public ModelAndView updateBoard(@RequestParam("delFileYn") String delFileYn , @ModelAttribute("tableListVO") TableListVO tableListVO , ModelMap model) throws Exception {
		//!!!! 불필요한 코드 삭제
		int seq = tableListVO.getSeq();
		MultipartFile attachFile = tableListVO.getAttachFile();
		tableSkyTestService.updateBoard(tableListVO);

		if(!(uploadFile(attachFile, delFileYn ,seq))){
			String msg = "수정 시 오류 발생하였습니다";
			model.addAttribute("msg",msg);
			return new ModelAndView("redirect:/test/detail.do?seq="+seq);
		}
		return new ModelAndView("redirect:/test/detail.do?seq="+seq);//뷰페이지
	}

	@PageTitle("삭제(D)")
	@RequestMapping(value = "/test/delete.do")
	public ModelAndView deleteBoard(int seq) throws Exception {
		List<TableListVO> lists = tableSkyTestService.searchBoard(seq);
		if(lists.get(0).getSavefilenm() !=null )  //파일이 있는 경우 삭제
		{
			tableSkyTestService.deleteAttachFile(seq);
		}

		tableSkyTestService.deleteBoard(seq);
		return new ModelAndView("redirect:/test/list.do");
	}

	@PageTitle("리스트(L)")
	@RequestMapping(value = "/test/list.do") //형식은 왠만하면 VO :
	public ModelAndView retrieveList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @ModelAttribute("tableListVO") TableListVO tableListVO, ModelMap model) throws Exception {
		int totalRowCount = 0 ;
		tableListVO.setRecordCountPerPage(10);
		int startRow = (pageNum-1)*tableListVO.getRecordCountPerPage();

		tableListVO.setStartrow(startRow); // totalRowCount가 paging의 FirstRecordIndex에 영향을 미치지 않는다.
		tableListVO.setRecordCountPerPage(10);

		List<TableListVO> list = tableSkyTestService.getTableListPlusFile(tableListVO);

		totalRowCount = tableSkyTestService.getCountByObject(tableListVO);
		model.addAttribute("currentPageNo", pageNum);
		model.addAttribute("list", list);
		model.addAttribute("tableListVO",tableListVO);

		HashMap<String,Object> map  = new HashMap<String,Object>();
		map.put("searchItem", tableListVO.getSearchItem());
		map.put("itemText", tableListVO.getItemText());
		PageUtilSky pageUtil = new PageUtilSky();
		model.addAttribute("pageString",pageUtil.makePageAnchorByHref(String.valueOf(pageNum), String.valueOf(totalRowCount), String.valueOf(10), String.valueOf(10), "/test/list.do", map));

		return new ModelAndView("test/list");

		// tableListVO는 jsp에서 불려지는데 넘겨지는 방법??
//		PageUtil pu = new PageUtil(pageNum,totalRowCount,10,10);
//		PaginationInfo paging = new PaginationInfo();
//		paging.setCurrentPageNo(pageNum);
//		paging.setRecordCountPerPage(10);
//		paging.setPageSize(10);
//		paging.setTotalRecordCount(totalRowCount);
//		model.addAttribute("paging", paging);

	}

	@PageTitle("선택된 글 삭제")
	@RequestMapping(value = "/test/selectDelete.do")
	public ModelAndView deleteBoards(String seqs) throws Exception {
		StringTokenizer st = new StringTokenizer(seqs, " ,");
		int seq=0;
		while (st.hasMoreTokens()) { // 하나하나씩 뽑아진다.
			seq = Integer.valueOf(st.nextToken());
			List<TableListVO> lists = tableSkyTestService.searchBoard(seq);

			if(lists.get(0).getRealfilenm()!=null)  //첨부 파일이 있는 경우 삭제
			{
				tableSkyTestService.deleteAttachFile(seq);
			}
			tableSkyTestService.deleteBoard(seq);
		}
		return new ModelAndView("redirect:/test/list.do");
	}



	@PageTitle("엑셀 다운")
	@RequestMapping(value = "/test/excelDown.do")
	public ModelAndView excelDown(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @ModelAttribute("tableListVO") TableListVO tableListVO) throws Exception {
		// 해당 페이지만 엑셀로 다운 받아야 되므로 Paging 이용
		int totalRowCount = tableSkyTestService.getCountByObject(tableListVO);
		PaginationInfo paging = new PaginationInfo();
		paging.setCurrentPageNo(pageNum); //
		paging.setRecordCountPerPage(10);
		paging.setPageSize(10);
		paging.setTotalRecordCount(totalRowCount);

		tableListVO.setStartrow(paging.getFirstRecordIndex()); // totalRowCount가 paging의 FirstRecordIndex에 영향을 미치지 않는다.
		tableListVO.setRecordCountPerPage(10);

		List<TableListVO> lists = tableSkyTestService.getTableListPlusFile(tableListVO);
		return excelSameMethod("categoryExcelView", "categoryMap", lists);
	}

	@PageTitle("엑셀 전체 다운")
	@RequestMapping(value = "/test/excelAllDown.do")
	public ModelAndView excelAllDown(@ModelAttribute("tableListVO") TableListVO tableListVO, ModelMap model) throws Exception {
		int totalRowCount = tableSkyTestService.getCountByObject(tableListVO);
		tableListVO.setStartrow(0);
		tableListVO.setRecordCountPerPage(totalRowCount);

		List<TableListVO> lists = tableSkyTestService.getTableListPlusFile(tableListVO);
		return excelSameMethod("categoryExcelView", "categoryMap", lists);
	}

	// 소스코드 중복으로 인한 내부함수
	private ModelAndView excelSameMethod(String viewName, String mapName, List<TableListVO> lists) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", lists);
		return new ModelAndView(viewName, mapName, map);
	}


	@PageTitle("팝업창 ")
	@RequestMapping(value = "/test/excelUploadPopup.do")
	public ModelAndView excelUploadPopup() throws Exception {
		return new ModelAndView("test/popup");
	}



	@PageTitle("엑셀 업로드")
	@RequestMapping(value = "/test/excelUpload.do")
	public ModelAndView excelUpload(@RequestParam("excelFile") MultipartFile excelFile, ModelMap model) throws Exception {
		String result = "false";
		int i;

		String path = fileuploadPropService.getString("fileupload.global.target-directory") + "\\" + excelFile.getOriginalFilename();
		File targetFile = new File(path);
		excelFile.transferTo(targetFile);
		String[] fieldNames = { "title", "contents" };

		List<TableListVO> lists = ExcelUtil.loadExcelFile(path, TableListVO.class, fieldNames, 1);
		for (i = 0; i < lists.size(); i++) {
			tableSkyTestService.addBoard(lists.get(i));
		}

		if (i == lists.size())// 반복문을 마치고 나왔을 경우 (모두 결과 실행)
			result = "true";
		model.addAttribute("result", result);
		return new ModelAndView("test/popupresult");
	}// popup
		// 경고창
		// result page 작성

	//DB like 쓰는법
	//ibatis 동적쿼리 parameterClass 안의 프로퍼티 값을 그냥 접근할 수 있다.

	@PageTitle("첨부파일 다운로드")
	@RequestMapping(value="/test/attachFileDown.do")
	public void attachFileDown(int seq , HttpServletResponse response) throws Exception{
		List<TableListVO> list = tableSkyTestService.searchBoard(seq);
		TableListVO tableListVO = list.get(0);

		String filename = tableListVO.getSavefilenm(); // 파일 경로 저장

		String filename2 = new String(tableListVO.getRealfilenm().getBytes("euc-kr"),"8859_1");

		String fileStoredPath = fileuploadPropService.getString("fileupload.global.target-directory");

		File sFile = new File(fileStoredPath,filename);

		int fSize = (int) sFile.length();

		if(fSize > 0){
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(sFile));
			String mimetype = "text/html";

			response.setBufferSize(fSize);
			response.setContentType(mimetype);
			response.setHeader("Content-Disposition", "attachment; filename=\""
//					+ tableListVO.getRealfilenm() + "\""); //파일 한글 깨짐
					+ filename2 + "\"");
			response.setContentLength(fSize);

			FileCopyUtils.copy(in, response.getOutputStream());
			in.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}

	}

}