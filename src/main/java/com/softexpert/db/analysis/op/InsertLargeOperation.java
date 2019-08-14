package com.softexpert.db.analysis.op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.softexpert.db.analysis.control.AbstractConnectionManager;
import com.softexpert.db.analysis.main.DatabaseRunnable;
import com.softexpert.db.analysis.vo.CustomerVO;

public class InsertLargeOperation implements DatabaseRunnable {

	@Override
	public void beforeRun(AbstractConnectionManager manager) {
		manager.beginTransaction();
	}

	@Override
	public void run(AbstractConnectionManager manager) throws Exception {
		manager.executeSQL(manager.getCreateLargeTableSQL());
		String longStr = createRandomString();
		byte[] bytes = createRandomBytes();
		CustomerVO vo;
		System.out.println("Storing data...");
		for (int i=1; i<=10000; i++) {
			vo = new CustomerVO();
			vo.setId(i);
			vo.setName("ValueObject "+i);
			vo.setText(longStr);
			vo.setRandomData(bytes);
			insert(manager.getConnection(), vo);
			if (i > 0 && i % 1000 == 0) {
				System.out.println(i+" rows inserted.");
			}
		}
	}

	@Override
	public void afterRun(AbstractConnectionManager manager) {
		manager.commitTransaction();
	}
	
	@Override
	public void onError(AbstractConnectionManager manager, Exception exception) {
		manager.rollbackTransaction();
	}

	private int insert(Connection connection, CustomerVO customer) throws Exception {
		try (PreparedStatement ppst = connection.prepareStatement("insert into customers values (?,?,?,?)")) {
			ppst.setInt(1, customer.getId());
			ppst.setString(2, customer.getName());
			ppst.setString(3, customer.getText());
			ppst.setBytes(4, customer.getRandomData());
			return ppst.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}
	
	private String createRandomString() {
		return RandomStringUtils.randomAlphanumeric(4000);
	}
	
	private static byte[] createRandomBytes() {
		byte[] bytes = new byte[1 * 1000 * 1000];
		new Random().nextBytes(bytes);
		return bytes;
	}
	
}
