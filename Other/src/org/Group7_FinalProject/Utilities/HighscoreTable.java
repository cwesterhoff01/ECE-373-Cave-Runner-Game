package org.Group7_FinalProject.Utilities;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

//ScrollableTable class contains and controls all objects related to an AccountTable
public final class HighscoreTable extends JScrollPane {

	//Fields for an AccountTable
	private JTable table;
	private DefaultTableModel model;
	
	//Default no-arg constructor
	public HighscoreTable() {
		this(null, null);
	}
	
	//Constructor that accepts two arguments
	public HighscoreTable(Object[] head, Object[][] data) {
		
		this(head, data, 160, 160);
		
	}
	
	//Constructor that accepts four arguments
	public HighscoreTable(Object[] head, Object[][] data, Integer width, Integer height) {
		
		super();
		
		//Create a new regular table
		this.model = new DefaultTableModel();
		this.table = new JTable(this.model);
		//Load the data into the table
		for (int i = 0; i < head.length; i++) {
			this.model.addColumn(head[i].toString());
		}
		for (int i = 0; i < data.length; i++) {
			this.model.addRow(data[i]);
		}
		//Set the table to fill the entire parent element
		this.table.setPreferredScrollableViewportSize(new Dimension(width, height));
		this.table.setFillsViewportHeight(true);
		//Limit the table to one selection at a time, and start with the first element selected
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setRowSelectionInterval(0, 0);
		//Add the table to the JScrollPane. This will make the table scrollable
		this.getViewport().add(this.table);

	}
	public void replaceData(Integer[][] data, String[] title) {
		//Replaces the title of the model
		table.getColumnModel().getColumn(0).setHeaderValue(title[0]);
		//removes the currentData
		for(int i = 9; i >= 0; i--) {
			model.removeRow(i);
		}
		//adds the correct data
		for(int i = 0; i < 10; i++) {
			Integer rowData[] = data[i];
			model.addRow(rowData);
		}
	}

	public void addAccountNames(ArrayList<Highscores> alltimeHighscores) {
		String[] accNames = new String[10];
		for(int i = 0; i < 10; i++) {
			accNames[i] = alltimeHighscores.get(i).getName();
		}
		model.addColumn("Account Names", accNames);
	}

	public void removeEmptyCol() {
		model.setColumnCount(1);	
	}


}