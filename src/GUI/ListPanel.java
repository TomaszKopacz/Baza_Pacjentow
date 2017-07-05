package GUI;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Controller.Controller;


/**
 * Panel including table with all registered patients. 
 * Table has columns: name and surname, sex, pesel, insurance and test.
 * To add new patient or remove patient appriopriate buttons are provided.
 * @author Aleksandra Zaj¹c
 */
public class ListPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JTable mTable;
	private DefaultTableModel model;
	private String[] columnNames = new String[]{"Imiê i Nazwisko", "P³eæ", "PESEL", "Ubezpieczenie", "Badanie"};
	
	private JButton addBtn;
	private JButton removeBtn;
	
	/**
	 * Creates new ListPanel.
	 */
	public ListPanel(){
		
				this.setBorder(BorderFactory.createTitledBorder("Lista pacjentów"));
				GroupLayout group = new GroupLayout(this);
				this.setLayout(group);
				this.setVisible(true);
				

				addBtn = new JButton("Dodaj");
				removeBtn = new JButton("Usuñ");
				
				//creating table
				mTable = new JTable();
				mTable.setBackground(Color.WHITE);
				mTable.setShowGrid(true);
				mTable.setGridColor(Color.BLUE);
				JScrollPane listScrollPanel = new JScrollPane(mTable);
				listScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				
				
				//model for table
				model = new DefaultTableModel(){
					
					private static final long serialVersionUID = 1L;
					

					//user can not edit table fields
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
					
					//adds check box in last column
					public Class<?> getColumnClass(int column){
						switch(column){
						case 4: 
							return Boolean.class;
						default: 
							return String.class;
						}
					}
				};
				model.setColumnIdentifiers(columnNames);
				mTable.getTableHeader().setReorderingAllowed(false);
				mTable.setModel(model);
				
				
				//margins								
				group.setAutoCreateGaps(true);
				group.setAutoCreateContainerGaps(true);
				
				//CONFIGURE GROUPS
				//horizontal groups
				//one main horizontal group, containing table and group of buttons
				group.setHorizontalGroup(group.createSequentialGroup()
						.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(listScrollPanel)
								.addGroup(group.createSequentialGroup()
												.addComponent(addBtn)
												.addComponent(removeBtn))));
				
				//vertical groups
				group.setVerticalGroup(group.createSequentialGroup()
						.addComponent(listScrollPanel)
						.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(addBtn)
								.addComponent(removeBtn)));
	}
	
	
	/**
	 * Adds a Controller to panel.
	 * @param c - the Controller to set
	 */
	public void setController(Controller c){
		mTable.getSelectionModel().addListSelectionListener(c);
		addBtn.addActionListener(c);
		removeBtn.addActionListener(c);
	}
	
	/**
	 * Clears table selection.
	 */
	public void clearSelection(){
		mTable.clearSelection();
	}

	/**
	 * Returns registered patients JTable.
	 * @return patients table
	 */
	public JTable getTable() {
		return mTable;
	}

	/**
	 * Returns add patient JButton.
	 * @return JButton add
	 */
	public JButton getAddBtn() {
		return addBtn;
	}

	/**
	 * Returns remove patient JButton
	 * @return JButton remove
	 */
	public JButton getRemoveBtn() {
		return removeBtn;
	}
}
