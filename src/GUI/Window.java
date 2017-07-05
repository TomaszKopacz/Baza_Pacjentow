package GUI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import Controller.Controller;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

/**
 * Window frame containing {@link PatientPanel} for patient data input,
 * {@link TestPanel} for test data input and {@link ListPanel} with a registered patients table.
 * Window is sized 870x610 by default.
 * @author Aleksandra Zaj¹c
 */
public class Window extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private PatientPanel patientPanel;					
	private TestPanel testPanel;
	private ListPanel listPanel;
	private JMenuItem itemClose;
	private JPanel mainPanel = new JPanel();
	
	public Window()
	{
		this.setSize(1100,700);
		this.setTitle("Rejestracja wynikow badania");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		// application bar
		JMenu menuApplication = new JMenu("Aplikacja");
		itemClose = new JMenuItem("Zamknij");
		menuApplication.add(itemClose);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menuApplication);
		this.setJMenuBar(menuBar);			
		
		patientPanel= new PatientPanel();
		testPanel = new TestPanel();
		listPanel = new ListPanel();
	
		mainPanel.setVisible(true);
		this.getContentPane().add(mainPanel);
				
		
		GroupLayout panelsGroup = new GroupLayout(mainPanel);
		mainPanel.setLayout(panelsGroup);
				
		panelsGroup.setAutoCreateGaps(true);
		panelsGroup.setAutoCreateContainerGaps(true);

		
		//CONFIGURE GROUPS
		//horizontal groups
		panelsGroup.setHorizontalGroup(panelsGroup.createSequentialGroup()
				.addGroup(panelsGroup.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(patientPanel)
						.addComponent(testPanel))
				.addComponent(listPanel));
		
		//vertical groups		
		panelsGroup.setVerticalGroup(panelsGroup.createSequentialGroup()
				.addGroup(panelsGroup.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGroup(panelsGroup.createSequentialGroup()			
									.addComponent(patientPanel)
									.addComponent(testPanel))
						.addComponent(listPanel)));
			
			}
	
	
	/**
	 * Adds Window controller.
	 * @param  controller - the Controller to add
	 */
	public void setController(Controller controller){
		itemClose.addActionListener(controller);
		patientPanel.setController(controller);
		listPanel.setController(controller);
		testPanel.setController(controller);
	}
	
	public JMenuItem getCloseItem(){
		return itemClose;
	}
	
	/**
	 * Unable access to all fields and clears them. If any table row was selected, clears selection also.
	 */
	public void clearAll(){
		this.patientPanel.setAvailable(false);
		this.testPanel.setAvailable(false);
		this.listPanel.clearSelection();
	}
	
	/**
	 * Clears PatientPanel fields, but doesn't turn off the access to this panel.
	 */
	public void clearPatientPanel(){
		this.patientPanel.clearFields();
	}
	
	/**
	 * Clears TestPanel fields, but doesn't turn off the access to this panel.
	 */
	public void clearTestPanel(){
		this.testPanel.clearFields();
	}
	
	/**
	 * Clears table selection. 
	 */
	public void clearSelection(){
		this.listPanel.clearSelection();
	}
	
	/**
	 * Enables or disable access to PatientPanel.
	 * @param b
	 */
	public void showPatientPanel(boolean b){
		this.patientPanel.setAvailable(b);
	}
	
	
	/**
	 * Enables or disables access to TestPanel.
	 * @param b
	 */
	public void showTestPanel(boolean b){
		this.testPanel.setAvailable(b);
	}

	
	public PatientPanel getPatientPanel() {
		return patientPanel;
	}

	
	public TestPanel getTestPanel() {
		return testPanel;
	}

	
	public ListPanel getListPanel() {
		return listPanel;
	}
	
}
