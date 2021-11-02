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
		
		//Create a new scrollable table
		this.model = new DefaultTableModel();
		this.table = new JTable(this.model);
		for (int i = 0; i < head.length; i++) {
			this.model.addColumn(head[i].toString());
		}
		for (int i = 0; i < data.length; i++) {
			this.model.addRow(data[i]);
		}
		this.table.setPreferredScrollableViewportSize(new Dimension(width, height));
		this.table.setFillsViewportHeight(true);
		this.table.setRowSelectionInterval(0, 0);
		
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
