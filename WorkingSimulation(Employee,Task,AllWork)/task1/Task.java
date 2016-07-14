package task1;

public class Task {
	private String name;
	private int workingHours;
	
	// К-ри:	
	Task (String name, int workingHours) { // Uses the Setters
		this.setName(name);
		this.setWorkingHours(workingHours);
	}
	
	//Setter-и и Getter-и:
	void setName (final String newName) {
		if (newName!=null) {
			this.name=newName;
		}
	}
	
	String getName () {
		return this.name;
	}
	
	void setWorkingHours (final int newWorkingHours) {
		if (newWorkingHours>=0) {
			this.workingHours=newWorkingHours;
		}
	}
	
	public int getWorkingHours () {
		return this.workingHours;
	}
}