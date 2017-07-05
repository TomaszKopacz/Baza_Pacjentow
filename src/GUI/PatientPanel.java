package GUI;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import Controller.Controller;

/**
 * Panel including text fields for patient attributes to launch by user.
 * There are following fields: name, surname and pesel. User can choose sex (by radio buttons)
 * and type of insurance (combo box). To save or cancel typed data appriopriate buttons are provided.
 * 
 * @author Aleksandra Zaj¹c
 */
public class PatientPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel nameLabel;
	private JTextField nameTextField;
	
	private JLabel surnameLabel;
	private JTextField surnameTextField;
	
	private JLabel peselLabel;
	private JTextField peselTextField;
	
	private JLabel sexLabel;
	private JRadioButton maleBtn;
	private JRadioButton femaleBtn;
	
	private JLabel insuranceLabel;
	private JComboBox<String> insuranceBox;
	
	private JButton saveBtn;
	private JButton cancelBtn;

	/**
	 * Creates {@link PatientPanel}.
	 */
	public PatientPanel(){
		
		this.setBorder(BorderFactory.createTitledBorder("Dane pacjenta"));
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		
		nameLabel = new JLabel("Imiê:");
		nameTextField = new JTextField(10);
		
		surnameLabel = new JLabel("Nazwisko:");
		surnameTextField = new JTextField(10);
		
		peselLabel = new JLabel("PESEL:");
		peselTextField = new JTextField(10);
		
		sexLabel = new JLabel("P³eæ:");
		femaleBtn = new JRadioButton("Kobieta");
		maleBtn = new JRadioButton("Mê¿czyzna");
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(femaleBtn);
		btnGroup.add(maleBtn);
		
		insuranceLabel = new JLabel("Ubezpieczenie");
		insuranceBox = new JComboBox<String>();
		insuranceBox.addItem("NFZ");
		insuranceBox.addItem("Prywatne");
		insuranceBox.addItem("Brak");
		
		saveBtn = new JButton("Zapisz");
		cancelBtn = new JButton("Anuluj");
		

		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		
		//CONFIGURE GROUPS
		//horizontal groups
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(nameLabel)
						.addComponent(surnameLabel)
						.addComponent(peselLabel)
						.addComponent(sexLabel)
						.addComponent(insuranceLabel)
						.addGroup(layout.createSequentialGroup()
								.addComponent(saveBtn)
								.addComponent(cancelBtn)))
										
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
						GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)

				.addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
						.addComponent(nameTextField)
						.addComponent(surnameTextField)
						.addComponent(peselTextField)
						.addGroup(layout.createSequentialGroup()
								.addComponent(femaleBtn)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
									GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)		
								.addComponent(maleBtn))
						.addComponent(insuranceBox)));
															
		
		//vertical groups
		layout.setVerticalGroup( layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(nameLabel)
						.addComponent(nameTextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(surnameLabel)
						.addComponent(surnameTextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(peselLabel)
						.addComponent(peselTextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(sexLabel)
						.addComponent(femaleBtn)
						.addComponent(maleBtn))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(insuranceLabel)
						.addComponent(insuranceBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(saveBtn)
						.addComponent(cancelBtn)));
		
		setAvailable(false);
	}
	
	/**
	 * Adds a Controller to panel. 
	 * @param c - Controller to set
	 */
	public void setController(Controller c){
		saveBtn.addActionListener(c);
		cancelBtn.addActionListener(c);
	}

	/**
	 * If parameter b is <code>true</code> make PatientPanel accessible, so user can edit fields.
	 * If fields were not empty, clears input.
	 * If b is <code>false</code> disable panel.
	 * @param b - boolean
	 */
	public void setAvailable(boolean b){
		if(!b)
			clearFields();
		this.setEnabled(b);
		nameLabel.setEnabled(b);
		nameTextField.setEnabled(b);
		surnameLabel.setEnabled(b);
		surnameTextField.setEnabled(b);
		peselLabel.setEnabled(b);
		peselTextField.setEnabled(b);
		sexLabel.setEnabled(b);
		maleBtn.setEnabled(b);
		femaleBtn.setEnabled(b);
		insuranceLabel.setEnabled(b);
		insuranceBox.setEnabled(b);
		saveBtn.setEnabled(b);
		cancelBtn.setEnabled(b);
	}
	
	/**
	 * Clears all the fields.
	 */
	public void clearFields(){
		nameTextField.setText("");
		surnameTextField.setText("");
		peselTextField.setText("");
		femaleBtn.setSelected(true);
		insuranceBox.setSelectedItem("brak");
	}

	/**
	 * Returns name JTextField.
	 * @return JTextField name
	 */
	public JTextField getNameTextField() {
		return nameTextField;
	}

	/**
	 * Returns surname JTextField.
	 * @return JTextFields surname
	 */
	public JTextField getSurnameTextField() {
		return surnameTextField;
	}

	/**
	 * Returns pesel JTextField.
	 * @return JTextField pesel
	 */
	public JTextField getPeselTextField() {
		return peselTextField;
	}

	/**
	 * Returns male JRadioButton.
	 * @return JRadioButton male
	 */
	public JRadioButton getMaleBtn() {
		return maleBtn;
	}

	/**
	 * Returns female JRadioButton.
	 * @return JRadioButton female
	 */
	public JRadioButton getFemaleBtn() {
		return femaleBtn;
	}

	/**
	 * Returns insurance JComboBox.
	 * @return JComboBox insurance
	 */
	public JComboBox<String> getInsuranceBox() {
		return insuranceBox;
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
	 * @return JBUtton cancel
	 */
	public JButton getCancelBtn() {
		return cancelBtn;
	}
}
