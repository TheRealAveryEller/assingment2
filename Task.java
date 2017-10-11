public class Task {
    
	private String description;
	private int priority;
	private int[] dueDate = new int[3];    
	private boolean completed;

	public Task(int[] due, String desc, int pri) {
     	this.description = desc;
     	this.dueDate = due;
     	this.priority = pri;
     	this.completed = false;
	}
    
	public Task(int[] due, String desc, int pri, int slug) {
     	this.description = desc;
     	this.dueDate = due;
     	this.priority = pri;
     	this.completed = true;
	}
    
	public void setDescription(String desc) {
    	this.description = desc;
	}
    
	public String getDescription() {
    	return this.description;
	}
    
	public void setDueDate(int[] due) {
    	this.dueDate = due;
	}
    
	public int[] getDueDate() {
    	return this.dueDate;
	}
    
	public void setPriority(int pri) {
    	this.priority = pri;
	}
    
	public String getPriority() {
    	// Write a Switch Returning a String
    	return "Write the damn function";
	}
    
	public void complete() {
    	this.completed = true;
	}
    
	public boolean isComplete() {
    	return this.completed;
	}
    
	// Maybe unneccessary
	@Override
	public String toString() {
    	return
        	this.description +
        	' ' + this.dueDate +
        	' ' + this.priority +
        	' ' + this.completed;
	}
}
