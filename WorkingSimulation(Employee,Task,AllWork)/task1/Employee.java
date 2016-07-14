package task1;

public class Employee {
	public static final int hoursPerDay=8; // The amount of working hours a day for each Employee
	
	
	private String name;
	private Task currentTask;
	private int hoursLeft;
	static private AllWork allwork; // All the work is the same for every Employee (static)
	
	// Constructor:
	Employee (final String name) {
		if (name!=null) { // Assigns the new name to the Employee if the name is not null
			this.name=name;
		}
	}
	
	// Setters & Getters: 
	String getName () {
		return this.name;
	}
	
	void setHoursLeft (final int newHoursLeft) { // Sets Employee's working hours for the day
		if (newHoursLeft>=0) {
			this.hoursLeft=newHoursLeft;
		}
	}
	
	int getHoursLeft () { // Returns Employee's hours left for the day
		return this.hoursLeft;
	}
	
	private void setCurrentTask (final Task newTask) { // Sets Employee's task
		if (newTask!=null) {
			this.currentTask=newTask;
			System.out.println("* //// "+this.getName()+" has started "+this.getCurrentTask().getName()+".\n"); // A Message that the Employee has started a new Task
		}
	}
	
	private Task getCurrentTask() { // *Included in the specification (Will be used only in this class)
		return this.currentTask;
	}
	
	public void setAllwork(AllWork allWork) { // Assigns new work for all the Employees
		if (allWork!=null) {
			Employee.allwork=allWork;
		}
	}
	
	private AllWork getAllwork() { // *Included in the specification (Will be used only in this class)
		return Employee.allwork;
	}
	
	// Methods:
	void work () {
		if (this.getHoursLeft()==0) { // Base of the recursion
			return;
		}
		
		if (this.getCurrentTask()!=null && this.getCurrentTask().getWorkingHours()!=0) { // If the Employee has a current task and the task is not finished yet
			this.showReport(); // A Report on the current working process
			if (this.getHoursLeft()>this.getCurrentTask().getWorkingHours()) { // If the Employee can finish the Task before the end of the working day
				this.setHoursLeft(this.getHoursLeft()-this.getCurrentTask().getWorkingHours()); // The Employee's working hours left are decreased by the time needed for the Task
				this.getCurrentTask().setWorkingHours(0); // The task is finished
				System.out.println("* //// "+this.getName()+" has finished "+this.getCurrentTask().getName()+".\n"); // A Message that the Employee has finished the Task
				if (this.getAllwork().getCurrentUnassighnedTask()!=-1) { // If there are available Tasks
					this.setCurrentTask(this.getAllwork().getNextTask()); // The Employee gets the next available Task
					this.work(); // The Employee starts working on the next Task for the day
				}
			}
			else { // If the Employee's working hours for the day are EQUAL to the hours needed for the Task
				if (this.getHoursLeft()==this.getCurrentTask().getWorkingHours()) {
					this.getCurrentTask().setWorkingHours(0);
					System.out.println("* //// "+this.getName()+" has finished "+this.getCurrentTask().getName()+".\n"); // A Message that the Employee has finished the Task
					this.setHoursLeft(0); // The Employee has finished working for the day
					this.setCurrentTask(null); // The Employee has finished the current Task and has none
				}
				else { // If the Employee's working hours for the day are less than the needed for the Task
					this.getCurrentTask().setWorkingHours(this.getCurrentTask().getWorkingHours()-this.getHoursLeft()); // The hours needed for the Task have reduced by the Employee's current hours left
					this.setHoursLeft(0); // The Employee's finished the working day
				}
			}
		}
		else { // If the Employee doesn't have a current task
			if (this.getAllwork().getCurrentUnassighnedTask()!=-1) { // If there are available Tasks
				this.setCurrentTask(this.getAllwork().getNextTask()); // The Employee get a new Task
				this.work(); // The Employee starts working on it
			}
			else return;
		}
	}
	
	void showReport () { // Prints current report
		System.out.println("------------------------------------");
		System.out.println(">>>> Current Task Information: <<<<");
		System.out.println("------------------------------------");
		System.out.println("Name: "+this.getName()); // Name
		System.out.println("Task: "+this.getCurrentTask().getName()); // Current Task
		System.out.println("Employee's hours left: "+this.getHoursLeft()+" hours"); // Hours Left
		System.out.println("Hours left for completing the current task: "+this.getCurrentTask().getWorkingHours()+" hours"); // Needed hours for the Task's completion
		System.out.println("--------------------\n");
	}
	
	void startWorkingDay() { // Sets the employee's working hours for the new day at work
		this.hoursLeft=hoursPerDay; // 8 hours a day
	}
}