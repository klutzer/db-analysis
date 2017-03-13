package com.softexpert.db.analysis.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.softexpert.db.analysis.control.AbstractConnectionManager;
import com.softexpert.db.analysis.op.InsertTest;
import com.softexpert.db.analysis.op.LoadTest;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class RunApp extends JFrame {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new RunApp().setVisible(true);
			}
		});
	}
	
	public RunApp() {
		
		setTitle("Database tests");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new MigLayout(new LC().insetsAll("12")));
		
		ActionListener action = new ExecuteTestAction();
		JButton btnPostgre = new JButton("PostgreSQL Insert");
		JButton btnOracle = new JButton("Oracle Insert");
		JButton btnSQL = new JButton("SQLServer Insert");
		JButton btnPostgreLoad = new JButton("PostgreSQL Load");
		JButton btnOracleLoad = new JButton("Oracle Load");
		JButton btnSQLLoad = new JButton("SQLServer Load");
		btnPostgre.addActionListener(action);
		btnOracle.addActionListener(action);
		btnSQL.addActionListener(action);
		btnPostgreLoad.addActionListener(action);
		btnOracleLoad.addActionListener(action);
		btnSQLLoad.addActionListener(action);
		add(btnPostgre);
		add(btnOracle);
		add(btnSQL, new CC().wrap());
		add(btnPostgreLoad);
		add(btnOracleLoad);
		add(btnSQLLoad, new CC().wrap());
		
		pack();
		setMinimumSize(getSize());
		setLocationRelativeTo(null);
	}
	
	private class ExecuteTestAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			String op = button.getText().substring(button.getText().indexOf(" ")+1);
			DatabaseRunnable runnable = null;
			if (op.equals("Load")) {
				runnable = new LoadTest();
			}else if (op.equals("Insert")) {
				runnable = new InsertTest();
			}
			
			if (op != null) {
				executeOperation(button.getText().substring(0, button.getText().indexOf(" ")), runnable);
			}
		}
	}
	
	public void executeOperation(String dbName, DatabaseRunnable runnable) {
		String className = "com.softexpert.db.analysis.control."+dbName+"ConnectionManager";
		try {
			AbstractConnectionManager manager = (AbstractConnectionManager) Class.forName(className).newInstance();
			DatabaseExecutor.execute(manager, runnable);
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

}
