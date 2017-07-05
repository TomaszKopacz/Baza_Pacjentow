package Data;

/**
 * Test class holding basic Test attributes:
 * date, cysts presence, albumen intensity, erythrocyte number.
 * @author Tomasz Kopacz
 */
public class Test {
	
	private Object date;
	private boolean cystsPresent;
	private float albumenIntensity;
	private int erythrocyteNumber;
	
	public Test(){
		
	}
	
	/**
	 * Creates new Test with attributes equaled to given Test t attributes.
	 * @param Test t
	 */
	public Test(Test t){
		
		this.date = t.getDate();
		this.cystsPresent = t.areCystsPresent();
		this.albumenIntensity = t.getAlbumenIntensity();
		this.erythrocyteNumber = t.getErythrocyteNumber();
	}
	

	public Object getDate() {
		return date;
	}

	public boolean areCystsPresent() {
		return cystsPresent;
	}

	public float getAlbumenIntensity() {
		return albumenIntensity;
	}

	public int getErythrocyteNumber() {
		return erythrocyteNumber;
	}

	public void setDate(Object date) {
		this.date = date;
	}

	public void setCystsPresent(boolean cystsPresent) {
		this.cystsPresent = cystsPresent;
	}

	public void setAlbumenIntensity(float albumenIntensity) {
		this.albumenIntensity = albumenIntensity;
	}

	public void setErythrocyteNumber(int erythrocyteNumber) {
		this.erythrocyteNumber = erythrocyteNumber;
	}
	
	@Override
	public String toString() {
		return "Test "
				+ "[date=" + date 
				+ ", cystsPresent=" + cystsPresent 
				+ ", albumenIntensity=" + albumenIntensity
				+ ", erythrocyteNumber=" + erythrocyteNumber + "]";
	}
}
