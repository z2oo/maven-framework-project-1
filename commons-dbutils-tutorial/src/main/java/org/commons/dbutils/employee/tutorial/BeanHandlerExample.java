package org.commons.dbutils.employee.tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class BeanHandlerExample {

	public static void main(String[] args) throws SQLException {

		final String url = "jdbc:h2:./target/test;AUTO_SERVER=TRUE";
		final String driver = "org.h2.Driver";
		final String usr = "sa";
		final String pwd = "";

		QueryRunner run = new QueryRunner();

		DbUtils.loadDriver(driver);
		Connection conn = DriverManager.getConnection(url, usr, pwd);
		// -----------------------------------------------------------------------------------
		ResultSetHandler<Employee> resultHandler = new BeanHandler<Employee>(
				Employee.class);

		try {
			Employee emp = run.query(conn,
					"SELECT * FROM employee WHERE employeename=?",
					resultHandler, "Jose");
			System.out.println(emp.getEmployeeId());
		} finally {
			DbUtils.close(conn);
		}

	}
}
