package com.nianway.core.mapping;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;

public class ArticleTest extends TestCase {
	final static int BUFFER_SIZE = 4096;

	public void testCreateTable() throws Exception {

		InputStream inputStream = ArticleTest.class
				.getResourceAsStream("article_table");

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = inputStream.read(data, 0, BUFFER_SIZE)) != -1) {
			outStream.write(data, 0, count);
		}
		data = null;
		String statements = new String(outStream.toByteArray(), "GBK");
		String[] statementArray = statements.split(";");
		for (String item : statementArray) {
			if (item != null && !item.trim().isEmpty())
				executeSql(item);
		}
	}

	private void executeSql(String sql) throws SQLException,
			UnsupportedEncodingException {
		Connection connection = DriverManager.getConnection(
				"jdbc:hsqldb:file:D:/UserData/zhizhang.zhou/dbFolder/demo",
				"SA", "");
		Statement statement = connection.createStatement();
		System.out.println("SQL ：" + sql);
		System.out.println("执行结果 ：" + statement.execute(sql));
		System.out.println("变更行数 ：" + statement.getUpdateCount());
		connection.commit();
		statement.close();
		connection.close();
	}
}
