package GUI;

import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerDateModel;
import Controller.Controller;


/**
 * Panel including text fields for test attributes to fill by user.
 * There are following components: date chooser, cysts check box, 
 * albumen intensity text field and erythrocyte number text field.
 * To save or cancel typed data appropriate buttons are provided.
 * 
 * @author Aleksandra Zaj¹c
 */
public class TestPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel dateLabel;
	private JSpinner calendar;

	private JLabel cystsLabel;
	private JCheckBox cystsCheckBox;
	
	private JLabel erythrocyteLabel;
	private JTextField erythrocyteTextField;
	
	private JLabel albumenLabel;
	private JTextField albumenTextField;
	
	private JButton saveBtn;
	private JButton cancelBtn;

	/**
	 * Creates TestPanel.
	 */
	public TestPanel(){
		
		this.setBorder(BorderFactory.createTitledBorder("Badanie"));
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		
		dateLabel = new JLabel("Data:");
		calendar = new JSpinner();
		
		cystsLabel = new JLabel("Wystêpowanie torbieli w badaniu USG nerek:");
		cystsCheckBox = new JCheckBox();
		
		erythrocyteLabel = new JLabel("Liczba erytrocytów w moczu:");
		erythrocyteTextField = new JTextField();
		
		albumenLabel = new JLabel("Stê¿enie albuminy:");
		albumenTextField = new JTextField();
		
		saveBtn = new JButton("Zapisz");
		cancelBtn = new JButton("Anuluj");
				
		
		SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
		calendar = new JSpinner(dateModel);
		calendar.setEditor(new JSpinner.DateEditor(calendar,"dd/MM/yyyy"));
		
			
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//CONFIGURE GROUPS
		//horizontal groups
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
									.addComponent(dateLabel)
									.addComponent(cystsLabel)
									.addComponent(erythrocyteLabel)
									.addComponent(albumenLabel)
									.addGroup(layout.createSequentialGroup()
												.addComponent(saveBtn)
												.addComponent(cancelBtn)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
						GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
									.addComponent(calendar)
									.addComponent(cystsCheckBox)
									.addComponent(erythrocyteTextField)
									.addComponent(albumenTextField)));
									
		//vertical groups
		layout.setVerticalGroup( layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(dateLabel)
									.addComponent(calendar))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(cystsLabel)
									.addComponent(cystsCheckBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(erythrocyteLabel)
									.addComponent(erythrocyteTextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(albumenLabel)
									.addComponent(albumenTextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(saveBtn)
									.addComponent(cancelBtn)));
		setAvailable(false);
	}
	
	/**
	 * Adds a Controller to panel.
	 * @param c - the Controller to set
	 */
	public void setController(Controller c){
		saveBtn.addActionListener(c);
		cancelBtn.addActionListener(c);
		cystsCheckBox.addItemListener(c);
		albumenTextField.getDocument().addDocumentListener(c);
		erythrocyteTextField.getDocument().addDocumentListener(c);
	}
	
	/**
	 * If parameter b is <code>true</code> makes panel accessible, disable panel otherwise.
	 * @param b - boolean
	 */
	public void setAvailable(boolean b){
		if(!b)
			clearFields();
		this.setEnabled(b);
		dateLabel.setEnabled(b);
		calendar.setEnabled(b);
		cystsLabel.setEnabled(b);
		cystsCheckBox.setEnabled(b);
		albumenLabel.setEnabled(b);
		albumenTextField.setEnabled(b);
		erythrocyteLabel.setEnabled(b);
		erythrocyteTextField.setEnabled(b);
		saveBtn.setEnabled(b);
		cancelBtn.setEnabled(b);
	}
	
	/**
	 * Clears all fields.
	 */
	public void clearFields(){
		cystsCheckBox.setSelected(false);
		albumenTextField.setText("");
		erythrocyteTextField.setText("");
	}

	/**
	 * Returns save JButton.
	 * @return JButton save
	 */
	public JButton getSaveBtn() {
		return saveBtn;
	}

	/**
	 * Returns cancel JButton.
	 * @return JButton cancel 
	 */
	public JButton getCancelBtn() {
		return cancelBtn;
	}
	
	/**
	 * Returns JDateChooser.
	 * @return JDateChooser date chooser
	 */
	public JSpinner getCalendar(){
		return calendar;
	}

	/**
	 * Returns cysts JCheckBox.
	 * @return JCheckBox cysts
	 */
	public JCheckBox getCystsCheckBox() {
		return cystsCheckBox;
	}

	/**
	 * Returns albumen intensity JTextField.
	 * @return JTextField albumen intensity
	 */
	public JTextField getAlbumenTextField() {
		return albumenTextField;
	}

	/**
	 * Returns erythrocyte JTextField.
	 * @return JTextField erythrocyte
	 */
	public JTextField getErythrocyteTextField() {
		return erythrocyteTextField;
	}
}
