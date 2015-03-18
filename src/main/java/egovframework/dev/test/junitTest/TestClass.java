package egovframework.dev.test.junitTest;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import egovframework.dev.test.dao.TableSkyTestDAO;

public class TestClass {
	private DataSource dataSource;
	private TableSkyTestDAO tableSkyTestDAO;

	@Before
	public void setup(){
		start();
		this.tableSkyTestDAO = new TableSkyTestDAO();
	}

	private void start(){
		ComboPooledDataSource cdps = new ComboPooledDataSource();
		try{
			cdps.setDriverClass("oracle.jdbc.OracleDriver");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		cdps.setJdbcUrl("jdbc:oracle:thin:@127.0.0.1:1521:XE");
		cdps.setUser("user");
		cdps.setPassword("1234");
		cdps.setMinPoolSize(5);
		cdps.setAcquireIncrement(5);
		cdps.setMaxPoolSize(20);
		dataSource = cdps;
	}
	@Test
	public void test(){

	}

}
