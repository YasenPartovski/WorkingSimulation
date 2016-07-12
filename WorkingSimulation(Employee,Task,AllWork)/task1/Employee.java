package task1;

public class Employee {
	public static final int hoursPerDay=8; // The amount of working hours a day for each employee
	
	
	private String name;
	private Task currentTask;
	private int hoursLeft;
	private AllWork allwork; 
	
	// Constructor:
	Employee (final String name) {
		if (name!=null && this.name==null) { //Ако въведеното име не е празно и ако работникът все още няма име; ако има вече зададено име, то то не може да се променя вече (с каквото е роден, с това остава)
			this.name=name;
		}
	}
	
	// Setters & Getters: 
	String getName () {
		return this.name;
	}
	
	void setHoursLeft (final int newHoursLeft) {
		if (newHoursLeft>=0) {
			this.hoursLeft=newHoursLeft;
		}
	}
	
	int getHoursLeft () {
		return this.hoursLeft;
	}
	
	void setCurrentTask (final Task newTask) {
		if (newTask!=null) {
			this.currentTask=newTask;
			System.out.println("* //// "+this.getName()+" has started "+this.getCurrentTask().getName()+".\n"); // A Message that the Employee has started a new Task
		}
	}
	
	Task getCurrentTask () {
		return this.currentTask;
	}
	
	AllWork getAllwork() {
		return this.allwork;
	}
	
	void setAllwork(AllWork allWork) {
		if (allWork!=null) {
			this.allwork=allWork;
		}
	}
	
	// Methods:
	void work () {
		
		if (this.getHoursLeft()==0) { // Base of the recursion
			return;
		}
		
		
		if (this.currentTask!=null && this.currentTask.getWorkingHours()!=0) { //ако работникът има текуща задача
			this.showReport(); // A Report on the current working process
			if (this.getHoursLeft()>this.currentTask.getWorkingHours()) { //ако работникът може да свърши задачата за текущия работен ден
				this.setHoursLeft(this.getHoursLeft()-this.currentTask.getWorkingHours()); // работното му време се намаля с времето за завършване на задачата
				this.currentTask.setWorkingHours(0); //времето за задачата става 0 (завършва се)
				System.out.println("* //// "+this.getName()+" has finished "+this.getCurrentTask().getName()+".\n"); // A Message that the Employee has finished the Task
				if (this.allwork.getCurrentUnassighnedTask()!=-1) { // If there are available Tasks
					this.setCurrentTask(this.allwork.getNextTask()); // The employee gets the next available Task
					this.work(); // The Employee starts working on the next Task for the day
					
				}
				
			}
			else { // If the Employee's working hours for the day are EQUAL to the hours needed for the Task
				if (this.getHoursLeft()==this.currentTask.getWorkingHours()) {
					this.currentTask.setWorkingHours(0);
					System.out.println("* //// "+this.getName()+" has finished "+this.getCurrentTask().getName()+".\n"); // A Message that the Employee has finished the Task
					this.setHoursLeft(0); // The Employee has finished working for the day
					this.setCurrentTask(null); // The Employee has finished the current Task and has none
					
				}
				else { // If the Employee's working hours for the day are less than the needed for the Task
					this.currentTask.setWorkingHours(this.currentTask.getWorkingHours()-this.getHoursLeft()); // The hours needed for the Task have reduced by the Employee's current hours left
					this.setHoursLeft(0); // The Employee's finished the working day
				}
			}
		}
		else { // If the Employee doesn't have a current task
			if (this.allwork.getCurrentUnassighnedTask()!=-1) { // If there are available Tasks
				this.setCurrentTask(this.allwork.getNextTask()); // The Employee get a new Task
				this.work(); // The Employee starts working on it
				
			}
			else return;
		}
	}
	
	void showReport () {
		System.out.println("------------------------------------");
		System.out.println(">>>> Current Task Information: <<<<");
		System.out.println("------------------------------------");
		System.out.println("Name: "+this.name); //име на работника
		System.out.println("Task: "+this.currentTask.getName()); // име на задачата
		System.out.println("Employee's hours left: "+this.hoursLeft+" hours"); // оставащи часове на работника
		System.out.println("Hours left for completing the current task: "+this.currentTask.getWorkingHours()+" hours"); //оставащи часове за приключване на задачата
		System.out.println("--------------------\n");
	}
	
	void startWorkingDay() { // Sets the employee's working hours for the new day at work
		this.hoursLeft=hoursPerDay; // 8 hours a day
	}
	

}