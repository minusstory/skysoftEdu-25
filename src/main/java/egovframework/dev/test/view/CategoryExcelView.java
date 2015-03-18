package egovframework.dev.test.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import egovframework.dev.test.vo.TableListVO;


public class CategoryExcelView extends AbstractExcelView {
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook wb,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HSSFCell cell = null;

		String fileName = "ExcelDownload_연월일시분초.xls";
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
		resp.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		resp.setHeader("Content-Transfer-Encoding", "binary");

		HSSFSheet sheet = wb.createSheet("sheet1");

		// put text in first cell
//		cell = getCell(sheet, 0, 0);
//		setText(cell, "User List");
		setText(getCell(sheet,0,0),"번호");
		setText(getCell(sheet,0,1),"제목");
		setText(getCell(sheet,0,2),"등록일");

		// set header information
//		setText(getCell(sheet, 2, 0), "seq");
//		setText(getCell(sheet, 2, 1), "title");
//		setText(getCell(sheet, 2, 2), "description");
//		setText(getCell(sheet, 2, 3), "use_yn");
//		setText(getCell(sheet, 2, 4), "reg_user");

		Map<String, Object> map= (Map<String, Object>) model.get("categoryMap");
		List<TableListVO> categories = (List<TableListVO>) map.get("category");

		boolean isVO = false;

		if (categories.size() > 0) {
			Object obj = categories.get(0);
			isVO = obj instanceof TableListVO;
		}

		for (int i = 0; i < categories.size(); i++) {

			if (isVO) {	// VO

				TableListVO tableListVO = (TableListVO) categories.get(i);

				cell = getCell(sheet, 1 + i, 0);
				setText(cell,Integer.toString(tableListVO.getSeq()));

				cell = getCell(sheet, 1 + i, 1);
				setText(cell, tableListVO.getTitle());

				cell = getCell(sheet, 1 + i, 2);
				setText(cell, toChangeChar(tableListVO.getRegdtm()));

			} else {	// Map

				TableListVO tableListVO = (TableListVO) categories.get(i);

				cell = getCell(sheet, 1 + i, 0);
				setText(cell,Integer.toString(tableListVO.getSeq()));

				cell = getCell(sheet, 1 + i, 1);
				setText(cell, tableListVO.getTitle());

				cell = getCell(sheet, 1 + i, 2);
				setText(cell, toChangeChar(tableListVO.getRegdtm()));

			}
		}
	}
	private String toChangeChar(Date date){
		SimpleDateFormat simpleFmt = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder builder = new StringBuilder();
		StringTokenizer stringTokenizer = new StringTokenizer(simpleFmt.format(date),"-");
		while(stringTokenizer.hasMoreTokens()){
			builder.append(stringTokenizer.nextToken());
		}
		return builder.toString();
	}
}