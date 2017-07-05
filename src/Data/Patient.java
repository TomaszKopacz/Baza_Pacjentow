package Data;

/**
 * Patient class holding basic patient attributes:
 * name, surname, pesel, sex, insurance, test.
 * @author Aleksandra Zając
 */

public class Patient {
	
	private String name;
	private String surname;
	private String pesel;
	private String sex;
	private String insurance;
	private Test test;

	public Patient(){
		
	}
	
	/**
	 * Creates new Patient with attributes equaled to a Patient p attributes.
	 * @param Patient p
	 */
	public Patient(Patient p){
		this.name = p.getName();
		this.surname = p.getSurname();
		this.pesel = p.getPesel();
		this.sex = p.getSex();
		this.insurance = p.getInsurance();
		if(p.test != null)
			this.test = new Test(p.getTest());
		else
			this.test = null;
	}


	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	
	public String getPesel() {
		return pesel;
	}

	
	public String getSex() {
		return sex;
	}


	public String getInsurance() {
		return insurance;
	}

	
	public Test getTest() {
		return test;
	}


	public void setName(String name) {
		this.name = name;
	}

	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	
	public void setSex(String sex) {
		this.sex = sex;
	}

	
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	
	public void setTest(Test test) {
		this.test = test;
	}

	@Override
	public String toString() {
		return "Patient "
				+ "[name=" + name 
				+ ", surname=" + surname 
				+ ", pesel=" + pesel + "]";
	}
}
