package task1;

public class WorkDemo {

	public static void main(String[] args) {
		// Creating an array of Tasks:
		AllWork workTasks = new AllWork();
		
		// Filling the array of Tasks:
		workTasks.addTask(new Task("cleaning their workplace", 4));
		workTasks.addTask(new Task("painting the walls of the office", 48));
		workTasks.addTask(new Task("talking to the new clients and making arrangements for the upcoming business trip", 8));
		workTasks.addTask(new Task("making decisions about allocating new financial resources", 13));
		workTasks.addTask(new Task("arranging the Business meeting before the Holidays", 15));
		workTasks.addTask(new Task("ordering catering for the Business meeting and preparing the menu", 10));
		workTasks.addTask(new Task("arranging the special Banquet for all the employees before the Holidays", 20));
		workTasks.addTask(new Task("holding a special seminar about increasing the productivity and the motivation of the employees for the Team managers of the Company", 4));
		workTasks.addTask(new Task("wrapping up the accounting and taxation before the Holidays", 46));
		workTasks.addTask(new Task("finding and signing new clients", 12));
		workTasks.addTask(new Task("arranging the main manager's schedule", 20));
		workTasks.addTask(new Task("informing the staff about the current plan of the Company", 5));
		workTasks.addTask(new Task("Having fun at the office", 20)); // This task will not enter the array, because the array is already full
		
		// Creating the employees:
		Employee employee1=new Employee("John Johnson");
		Employee employee2=new Employee("Paty Robertson");
		Employee employee3=new Employee("Richard Ericsson");
		Employee employee4=new Employee("Jack Brown");
		
		// Assigning all the employees to the same Working Tasks Sheet:
		employee1.setAllwork(workTasks);
		
		// Making an array of employees:
		Employee[] employees = new Employee[4];
		employees[0]=employee1;
		employees[1]=employee2;
		employees[2]=employee3;
		employees[3]=employee4;
		//...............................................................
		
		
		boolean dayIsOver=false; // Shows whether the Working day is over or not
		int cntDays=1; // A counter for the Days
		
		
		// ------------------------------------ Working process: -----------------------------------

		// An infinite loop (breaks when all the work has been done): 
		while (true) {
			System.out.println("\n======================================== DAY "+cntDays+": ========================================");
			System.out.println("                           ( The new working day has started )\n");
			dayIsOver=false; // When starting a new working day the dayIsOver becomes false again
			
			// Assigning Employees' working hours for the day:
			for (int i=0; i<employees.length; i++) {
				employees[i].startWorkingDay();
			}
			
			
			// Working (if the employee doesn't have a current Task, they are assigned the next available one from the array; otherwise they just continue their current task from the previous day)
			while (!dayIsOver) { // The loop continues until either the day ends or when the whole work is done
				for (int i=0; i<employees.length; i++) { // All the employees start working
					employees[i].work(); 
				}
				
				// Checks whether all the employees' working hours are over
				for (int i=0; i<employees.length; i++) { 
					if (employees[i].getHoursLeft()!=0 && workTasks.getCurrentUnassighnedTask()!=-1) {
						dayIsOver=false; // If there is at least one employee who has working hours left and there are still tasks to be assigned the day is not over
						break; 
					}
					dayIsOver=true; // When all the employees have finished their working hours or there are no available tasks to be assigned to the employees with additional working time
				}

				// Checks if whether the Working day is over
				if (dayIsOver) { 
					cntDays++; // If the day is over the counter for the days starts pointing towards the next day
				}
				
				// Checks if whether All the work is done
				if (workTasks.isAllWorkDone()) { // Checks if whether All the work is done
					System.out.println(" --------=========== All the work has been done!!! ===========--------\n--------========== It is time for the Holidays now!! ==========--------");
					return; // End of the program
				}
			}
		}	
	}
}