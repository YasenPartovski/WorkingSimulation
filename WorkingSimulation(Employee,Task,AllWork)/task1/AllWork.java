package task1;

public class AllWork {
	// Fields:
	private Task[] tasks;
	private int freePlacesForTasks;
	private int currentUnassignedTask;
	
	int getCurrentUnassighnedTask() {
		return currentUnassignedTask;
	}
	
	// Constructors:
	AllWork() {
		this.tasks=new Task[12];
		this.freePlacesForTasks=12;
		this.currentUnassignedTask=0;
	}
	
	// Methods:
	public void addTask(Task task) { // Adds a new Task to the array of Tasks
		if (freePlacesForTasks>0) { // If there is an available empty element in the array
			for (int i=0; i<this.tasks.length; i++) {
				if (this.tasks[i]==null) { // If the element is null
					this.tasks[i]=task; // The new Task is assigned to the first available element in the array
					this.freePlacesForTasks--; // The available places for new Tasks is reduced by 1
					break; // Breaks the loop
				}
			}
		}
	}
	
	Task getNextTask() { // Returns the next available Task in the array
		int tempFreeTaskIndex=this.currentUnassignedTask; // The tempIndex stores the current available task's index in the array
		if (this.currentUnassignedTask<tasks.length-1 && this.currentUnassignedTask>-1) { // If the last free Task is NOT the last one in the array
			this.currentUnassignedTask++; // The index points to the next available Task
		}
		else { // If the last free Task IS the last one in the array
			if (this.currentUnassignedTask==this.tasks.length-1) {
				this.currentUnassignedTask=-1; // All tasks in the array have been assigned, so the index is set to -1 (out of the array)
				return this.tasks[tempFreeTaskIndex]; // Returns the last Task in the array
			}
			else {
				return null; // No next Task - returns empty Task
			}
		}
		return this.tasks[tempFreeTaskIndex]; // Returns the next available Task
	}
	
	boolean isAllWorkDone() {
		for (int i = 0; i < this.tasks.length; i++) {
			if (this.tasks[i]!=null && this.tasks[i].getWorkingHours()!=0) { // If there is a Task in the array and it is NOT finished
				return false; // All the work is NOT finished either
			}
		}
		return true; // If the array is empty or all the Tasks are finished - All the work is done
	}
}