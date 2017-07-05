package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.format.TextStyle;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.StyleSheet.ListPainter;

import Data.Database;
import Data.Patient;
import Data.Test;
import GUI.ListPanel;
import GUI.PatientPanel;
import GUI.TestPanel;
import GUI.Window;
import lib.MyUtils;

/**
 * Controller is a class that reacts on Window events.
 * It is a connector between GUI and its database - 
 * when some GUI event appears, controller makes appropriate changes in Window and Database set. 
 * 
 * @author Tomasz Kopacz
 */

public class Controller implements 	ActionListener, 
									ListSelectionListener,
									ItemListener,
									DocumentListener{
	
	private Window window;
	private PatientPanel patientPanel;
	private TestPanel testPanel;
	private ListPanel listPanel;
	private Database db = new Database();
	
	/**
	 * Creates Controller reacting to given Window events.
	 * @param window - the Window to control
	 */
	public Controller(Window window) {
		this.window = window;
		patientPanel = window.getPatientPanel();
		testPanel = window.getTestPanel();
		listPanel = window.getListPanel();
	}

	//ON CLICK LISTENNING
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//menu close item 
		if(e.getSource() == window.getCloseItem()){
			window.dispose();
		}
		
		//save patient button

		if(e.getSource() == patientPanel.getSaveBtn()){

			//gets data input from fields
			String name = patientPanel.getNameTextField().getText();
			String surname = patientPanel.getSurnameTextField().getText();
			String pesel = patientPanel.getPeselTextField().getText();
			
			String sex = "";
			
			if(patientPanel.getFemaleBtn().isSelected())
				sex = patientPanel.getFemaleBtn().getText();
			else if(patientPanel.getMaleBtn().isSelected())
				sex = patientPanel.getMaleBtn().getText();
			
			String insurance = patientPanel.getInsuranceBox().getSelectedItem().toString();
			
			Patient patient = null;
			
			//are fields empty
			if(	name.isEmpty()
				|| surname.isEmpty()
				|| pesel.isEmpty()
				|| sex.isEmpty()){
					JOptionPane.showMessageDialog(window, "Wype³nij wszystkie pola!");
					return ;
			}
			
			//is input incorrect
			else if(MyUtils.areNumbers(name)
				|| MyUtils.areNumbers(surname)
				|| MyUtils.areLetters(pesel)){
					JOptionPane.showMessageDialog(window, "Nieprawid³owe dane.");
					return;
			}
			
			//has pesel correct length
			else if((pesel.length()) != 11){
				JOptionPane.showMessageDialog(window, "Sprawdz d³ugoœæ peselu.");
				return;
			}

			//if input is correct, creates Patient object according to the fields
			else {
				patient = new Patient();
				patient.setName(name);
				patient.setSurname(surname);
				patient.setPesel(pesel);
				patient.setSex(sex);
				patient.setInsurance(insurance);
				if(anyRowSelected())
					patient.setTest(db.get(getSelectedPatientID()).getTest());
				else
					patient.setTest(null);
			}
			
			if(patient != null){
				//if pesel is not changed (equals to row selected in table) actualizes patient data in list and database
				if(patient.getPesel().equals(getSelectedPatientID())){
					actualizeTable(patient);
					db.set(patient.getPesel(), patient);
					window.clearAll();
					
				//if pesel is changed check if pesel is occupied
				//check if patient is selected from table (actualize this patient) or not (add new patient)
				}else{
					if(db.contains(patient.getPesel())){
						JOptionPane.showMessageDialog(window, "Pesel zajêty.");
					} else {
						if(anyRowSelected()){
							db.set(getSelectedPatientID(), patient);
							actualizeTable(patient);
							
						}else{
							add(patient);
							db.add(patient);
						}
						window.clearAll();
					}
				}
			}
		}
		
		
		//cancel patient
		if(e.getSource() == patientPanel.getCancelBtn()){
			window.clearPatientPanel();
			window.showPatientPanel(false);
			window.clearSelection();
		}
		
		//save test
		if(e.getSource() == testPanel.getSaveBtn()){
		
			//gets data input from fields
			Object date = testPanel.getCalendar().getModel().getValue();
			boolean cysts = testPanel.getCystsCheckBox().isSelected();
			String albumen = testPanel.getAlbumenTextField().getText();
			String erythrocyte = testPanel.getErythrocyteTextField().getText();
			
			Test test = null;
			
			if(albumen.isEmpty() || erythrocyte.isEmpty()){
				JOptionPane.showMessageDialog(window, "Uzupe³nij wszystkie pola.");
				return;
			}
			
			else if(!MyUtils.isFloat(albumen) || !MyUtils.isInteger(erythrocyte) ){
				JOptionPane.showMessageDialog(window, "Wprowadz prawid³owe dane.");
				return;
			}
			
			//if input is correct, creates new Test object according to fields 
			else {
				test = new Test();
				test.setDate(date);
				test.setCystsPresent(cysts);
				test.setAlbumenIntensity(Float.parseFloat(albumen));
				test.setErythrocyteNumber(Integer.parseInt(erythrocyte));
			}
			
			//actualize selected in table patient
			if(test != null){
				if(anyRowSelected()){
					String pesel = getSelectedPatientID();
					Patient p = db.get(pesel);
					p.setTest(test);
					db.set(pesel, p);
					actualizeTable(p);
					window.showTestPanel(false);
					window.clearSelection();
				}else{
					JOptionPane.showMessageDialog(window, "Nie wybrano pacjenta z listy.");
				}
			}
		}
		
		//cancel test
		if(e.getSource() == testPanel.getCancelBtn()){
			window.clearTestPanel();
			window.showTestPanel(false);
			window.clearSelection();
		}
		
		
		//add patient
		if(e.getSource() == listPanel.getAddBtn()){
			window.clearPatientPanel();
			window.showPatientPanel(true);
			window.showTestPanel(true);
			window.clearSelection();
		}
		
		
		//remove patient
		if(e.getSource() == listPanel.getRemoveBtn()){
			
			if(anyRowSelected()){
				db.remove(getSelectedPatientID());
				
				int index = listPanel.getTable().getSelectedRow();
				((DefaultTableModel) listPanel.getTable().getModel()).removeRow(index);
				
				window.clearAll();
			}
		}
	}

	//LIST SELECTION LISTENING
	//when some row selected in table
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		
		
		if(!e.getValueIsAdjusting() && anyRowSelected()){
			
			//gets Patient with selected id from set
			String id = getSelectedPatientID();
			Patient p = db.get(id);
			
			// fill fields in panels
			if(p != null){
				patientPanel.getNameTextField().setText(p.getName());
				patientPanel.getSurnameTextField().setText(p.getSurname());
				patientPanel.getPeselTextField().setText(p.getPesel());
				if(p.getSex() == "mê¿czyzna")
					patientPanel.getMaleBtn().setSelected(true);
				else
					patientPanel.getFemaleBtn().setSelected(true);
				patientPanel.getInsuranceBox().setSelectedItem(p.getInsurance());
				
				
				if(p.getTest() != null){
					Test test = p.getTest();
					testPanel.getCalendar().getModel().setValue(test.getDate());
					testPanel.getCystsCheckBox().setSelected(test.areCystsPresent());
					testPanel.getAlbumenTextField().setText(Float.toString(test.getAlbumenIntensity()));
					testPanel.getErythrocyteTextField().setText(Integer.toString(test.getErythrocyteNumber()));
					
				} else {
					window.clearTestPanel();
				}
				
				window.showPatientPanel(true);
				window.showTestPanel(true);
			}
		}
		
	}
	
	//ITEM CHANGE LISTENING
	// when check box changes state
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		//if selected change color to red
		if(e.getSource() == testPanel.getCystsCheckBox()){
			if(e.getStateChange() == ItemEvent.SELECTED){
				testPanel.getCystsCheckBox().setBackground(Color.RED);
				testPanel.getCystsCheckBox().setOpaque(true);
			}
			else{
				testPanel.getCystsCheckBox().setOpaque(false);
			}
		}
	}

	//FIELDS CHANGES LISTENING
	//when values in fields are above the limit (here 20) change color of input to red
	@Override
	public void changedUpdate(DocumentEvent e) {
		textChanged(e);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {	
		textChanged(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		textChanged(e);
	}
	
	//when text is changed
	private void textChanged(DocumentEvent event){
		
		JTextField albumenIntensity = testPanel.getAlbumenTextField();
		JTextField erythrocyteNumber = testPanel.getErythrocyteTextField();
		
		//when albumen value changed
		if(event.getDocument() == albumenIntensity.getDocument()){
			float number = 0;
			
			try{
				number = Float.parseFloat(albumenIntensity.getText());
				if(number > 20)
					albumenIntensity.setForeground(Color.RED);
				else
					albumenIntensity.setForeground(Color.BLACK);
			}catch (NumberFormatException e) {
				
			}
		}
		
		//when erythrocyte number changed
		if(event.getDocument() == erythrocyteNumber.getDocument()){
			int number = 0;
			
			try{
				number = Integer.parseInt(erythrocyteNumber.getText());
				if(number > 20)
					erythrocyteNumber.setForeground(Color.RED);
				else
					erythrocyteNumber.setForeground(Color.BLACK);
				
			}catch(NumberFormatException e){
				
			}
		}
	}
	
	/**
	 * Returns <code>true</code> if any table row is selected, <code>false</code> otherwise.
	 * @return boolean
	 */
	private boolean anyRowSelected(){
		if(listPanel.getTable().getSelectedRow() != -1)
			return true;
		return false;
	}
	
	/**
	 * Returns a String id (pesel) of selected patient. If no row is selected returns empty String.
	 * @return String id (pesel)
	 */
	private String getSelectedPatientID(){
		try{
			String id = listPanel.getTable().getValueAt(listPanel.getTable().getSelectedRow(), 2).toString();
			return id;
		}catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * creates array row from patient object
	 * @param patient
	 * @return Object[]
	 */
	private Object[] patientToRow(Patient patient){
		Object[] p = new Object[5];
		p[0] = patient.getName() + " " + patient.getSurname();
		if(patient.getSex().equals(""))
			p[1] = "";
		else
			p[1] = patient.getSex().toUpperCase().toCharArray()[0];
		p[2] = patient.getPesel();
		p[3] = patient.getInsurance();
		if(patient.getTest() != null)
			p[4] = true;
		else
			p[4] = false;
		
		return p;
	}
	
	/**
	 * Actualizes selected table row to a given Patient attributes.
	 * @param patient
	 */
	private void actualizeTable(Patient patient){
		if(anyRowSelected()){
			Object[] row = patientToRow(patient);
			int selectedRow = listPanel.getTable().getSelectedRow();
			for(int i = 0; i < row.length; i++){
				listPanel.getTable().setValueAt(row[i], selectedRow, i);
			}
		}
	}
	
	/**
	 * Adds new row to table. Row contains fields modeled on given Patient attributes.
	 * @param patient - the Patient to add
	 */
	private void add(Patient patient){
		if(patient != null){
			Object[] row = patientToRow(patient);
			((DefaultTableModel)listPanel.getTable().getModel()).addRow(row);
		}
	}

}
