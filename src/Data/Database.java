package Data;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Model database holding {@link Patient} objects in static HashSet.
 * @author Tomasz Kopacz
 */
public class Database {
	
	private static HashSet<Patient> database = new HashSet<>();
	
	/**
	 * Creates Database.
	 */
	public Database(){
	}
	
	/**
	 * Adds new Patient to set.
	 * @param p - the Patient to add
	 */
	public void add(Patient p){
		if(p != null)
			database.add(p);
	}
	
	/**
	 * Returns Patient with given pesel from set.
	 * @param String pesel
	 * @return Patient patient
	 */
	public Patient get(String pesel){
		for(Patient p : database){
			if(p.getPesel().equals(pesel))
				return p;
		}
		return null;
	}
	
	/**
	 * Sets Patient attributes to object with a given pesel, if exists in set.
	 * @param String pesel
	 * @param Patient patient
	 */
	public void set(String pesel, Patient patient){
		if(patient != null){
			for(Patient p : database){
				if(p.getPesel().equals(pesel)){
					p.setName(patient.getName());
					p.setSurname(patient.getSurname());
					p.setPesel(patient.getPesel());
					p.setSex(patient.getSex());
					p.setInsurance(patient.getInsurance());
					p.setTest(patient.getTest());
				}
			}
		}
	}
	
	/**
	 * Checks if a Patient with a given pesel exists in set.
	 * @param String pesel
	 * @return <code>true</code> if Patient with the given pesel exists, <code>false</code> otherwise
	 */
	public boolean contains(String pesel){
		for(Patient p : database){
			if(p.getPesel().equals(pesel))
				return true;
		}
		return false;
	}
	
	/**
	 * Removes a Patient with a given pesel from set.
	 * @param String pesel
	 */
	public void remove(String pesel){
		if(pesel != null)
			for(Iterator<Patient> iter = database.iterator(); iter.hasNext(); ){
				Patient p = iter.next();
				if(p.getPesel().equals(pesel)){
					iter.remove();
				}
			}
	}

	/**
	 * Returns Patients HashSet database.
	 * @return HashSet database
	 */
	public HashSet<Patient> getDatabase() {
		return database;
	}
}
