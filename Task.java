
import java.util.*;
public class Task {
    
	private String description;
	private int priority;
	private Date dueDate;    
	private boolean completed;

	public Task(Date due, String desc, int pri) {
     	this.description = desc;
     	this.dueDate = due;
     	this.priority = pri;
     	this.completed = false;
	}
    
	public Task(Date due, String desc, int pri, int slug) {
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
    
	public void setDueDate(Date due) {
    	this.dueDate = due;
	}
    
	public Date getDueDate() {
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
