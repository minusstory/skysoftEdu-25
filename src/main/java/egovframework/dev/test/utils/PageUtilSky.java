package egovframework.dev.test.utils;

import java.util.HashMap;
import java.util.Iterator;

public class PageUtilSky {
	public String makePageAnchorByHref(
			String currentPage, // 현재 페이지
			String totalCnt, // 전체 레코드 수
			String viewCnt, // 페이지 당 보여줄 리스트 수
			String pageScale, // 페이지 블록 사이즈 << < 1 2 3 4 5 > >> 인 경우는 5
			String url, // url
			HashMap parameter// 검색파라미터
		){
		StringBuffer result = new StringBuffer(); // 결과값 저장 변수
		int retotalCnt = Integer.valueOf(totalCnt);
		int reviewCnt = Integer.valueOf(viewCnt);
		int repageScale = Integer.valueOf(pageScale);
		int reCurrentPage = Integer.valueOf(currentPage);

		int totalPageCnt=(int) Math.ceil((retotalCnt)/(double) reviewCnt);;  //총 보여줄 페이지 수
		int startPage = (((reCurrentPage-1) / repageScale) * repageScale) + 1; //시작페이지
		int endPage = startPage + repageScale -1;  //끝페이지

		if(endPage > totalPageCnt) endPage = totalPageCnt; //10개씩의 끝페이지가 보여줄 페이지 수 보다 적으면 총 보여줄 페이지수로 저장

		if(retotalCnt<=0){
			return "";
		} // 결과 없는게 들어왔을 경우 리턴 반환

		int i=0;
		StringBuffer param = new StringBuffer();
		Iterator iterator = parameter.keySet().iterator(); //parameter값 전달
		while(iterator.hasNext()){
			String key = (String)iterator.next();
			String object = (String)parameter.get(key);
			if(object==null)
				continue; // null일 경우 url에 보내지 않는다.
			param.append("&"+key+"="+object);

		}
		String paramString = param.toString();
		//url뒤에 hashmap파라미터 넘기기 위한 문자열 생성


		result.append(" <a href='"+url+"?pageNum=1"+paramString+"'> << </a>");

		//url뒤에 searchItem, itemText, pageNum
		if(reCurrentPage > repageScale){
			if(endPage != repageScale){
			//	result.append("<<"); //이거대신 사용해야된다.
			}

			//result.append("<");
			result.append(" <a href='"+url+"?pageNum="+(reCurrentPage-repageScale)+paramString+"'> < </a>");
		}
		System.out.println(startPage + "/" +endPage );
		for(i = startPage  ; i <= endPage ; i++){
			//result.append(" "+i);
			if(i==reCurrentPage){
				result.append(" <a href='"+url+"?pageNum="+i+paramString+"'>");
				result.append( "<span style='color:red; fon-size:10pt'>"+ i +"</span>");
				result.append("</a>");
			} // 강조
			else{
				result.append(" <a href='"+url+"?pageNum="+i+paramString+"'>"+ i +"</a>");
			}
		}


		if(reCurrentPage < (totalPageCnt-totalPageCnt%repageScale)){
			//result.append(" >");
			result.append(" <a href='"+url+"?pageNum="+(reCurrentPage+repageScale)+paramString+"'> > </a>");

			if(endPage != totalPageCnt){
			//	result.append(" >>"); // 이거대신 사용
			}
		}
		result.append(" <a href='"+url+"?pageNum="+totalPageCnt+paramString+"'> >> </a>");

		return result.toString();
	}
}
