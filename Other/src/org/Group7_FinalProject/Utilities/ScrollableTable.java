package org.Group7_FinalProject.Utilities;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

//ScrollableTable class contains and controls all objects related to a ScrollableTable
public final class ScrollableTable extends JScrollPane {

	//Fields for a ScrollablTable
	private JTable table;
	private DefaultTableModel model;
	
	//Default no-arg constructor
	public ScrollableTable() {
		this(null, null);
	}
	
	//Constructor that accepts two arguments
	public ScrollableTable(Object[] head, Object[][] data) {
		
		this(head, data, 200, 200);
		
	}
	
	//Constructor that accepts four arguments
	public ScrollableTable(Object[] head, Object[][] data, Integer width, Integer height) {
		
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
		//Add the table to the JScrollPane. This creates the ScrollableTable
		this.getViewport().add(this.table);

	}
	
	//Method that returns the currently selected row on the table
	public Integer getSelectedRow() {
		return this.table.getSelectedRow();
	}
	
	//Method that sets the currently selected row on the table
	public void setSelectedRow(Integer i) {
		this.table.setRowSelectionInterval(i, i);
	}
	
	//Method that adds a row to the table
	public void addRow(Object[] row) {
		this.model.addRow(row);
	}

}
