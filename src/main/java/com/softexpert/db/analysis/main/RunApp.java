package com.softexpert.db.analysis.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.softexpert.db.analysis.control.AbstractConnectionManager;
import com.softexpert.db.analysis.op.Operations;

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
		setLayout(new MigLayout(new LC().insetsAll("12").wrapAfter(3)));
		
		createButtons();
		
		pack();
		setMinimumSize(getSize());
		setLocationRelativeTo(null);
	}
	
	private void createButtons() {
	    ActionListener action = new ExecuteTestAction();
	    for (Operations operation : Operations.values()) {
	        add(createButton("PostgreSQL", operation, action));
	        add(createButton("Oracle", operation, action));
	        add(createButton("SQLServer", operation, action));
	    }
	}

	private JButton createButton(String database, Operations operation, ActionListener action) {
	    JButton button = new JButton(database + " - " + operation.getOperationName());
	    button.setName(operation.getRunnableClass().getName());
	    button.addActionListener(action);
	    return button;
	}

	private class ExecuteTestAction implements ActionListener {

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        try {
	            JButton button = (JButton) e.getSource();
	            String database = button.getText().substring(0, button.getText().indexOf(' '));
	            DatabaseRunnable runnable = (DatabaseRunnable) Class.forName(button.getName()).newInstance();
	            
	            new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        executeOperation(database, runnable);
                        return null;
                    }
                }.execute();
	        } catch (Exception err) {
	            err.printStackTrace();
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
