package egovframework.dev.test.junitTest;

import java.util.HashMap;

import javax.sql.DataSource;

import org.junit.Test;


import egovframework.dev.test.dao.TableSkyTestDAO;
import egovframework.dev.test.utils.PageUtilSky;

public class TestClass {
	private DataSource dataSource;
	private TableSkyTestDAO tableSkyTestDAO;

	@Test
	public void test(){
		PageUtilSky page = new PageUtilSky();
		HashMap map = new HashMap<String,Object>();
		map.put("searchItem","제목");
		map.put("itemText", "엑셀");
		System.out.println(page.makePageAnchorByHref("1", "500", "10", "10", "/test/list.do", map));
	}

}
