package hillbillies.scheduler;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.Faction;
import hillbillies.model.Unit;

public class Task {

	private String nameTask;
	private int priority;
	private MyStatement statementactivity;
	private int[] selectedCube;

	public Task(String name, int priority, MyStatement activity, int[] selectedCube)throws IllegalArgumentException{
		System.out.println("task class");
		this.nameTask = name;
		this.priority = priority;
		this.statementactivity = activity;
		this.selectedCube = selectedCube;	
		if (!this.isWellFormed())
			throw new IllegalArgumentException();
	}
	
	public boolean isWellFormed(){
		return this.isWellFormed.CheckWellFormedness();
	}
	
	private IsWellFormed isWellFormed = new IsWellFormed(this);
	
	public Set<Scheduler> getSchedulersForTask(){
		System.out.println("task getschedulersfortask");
		Set<Scheduler> setOfSchedulers = new HashSet<Scheduler>();
		for (Faction faction: this.getAssignedUnit().getWorld().getActiveFactions()){
			if(faction.getScheduler().getScheduledTasks().contains(this)){
				setOfSchedulers.add(faction.getScheduler());
			}
		}
		return setOfSchedulers;
	}
	
	
	public Unit getAssignedUnit(){
		return this.assignedUnit;
	}
	
	public void setAssignedUnit(Unit unit){
		this.assignedUnit = unit;
	}
	
	private Unit assignedUnit;


	public String getName(){
		return this.nameTask;
	}
	
	public int getPriority(){
		return this.priority;
	}
	
	public void setPriority(int priority){
		if (priority > Integer.MIN_VALUE+100)
			this.priority = priority; 
		else{
			this.priority = Integer.MIN_VALUE;
		}
	}
	
	public MyStatement getActivity(){
		return this.statementactivity;
	}
	
	public int[] getSelectedCube(){
		return this.selectedCube;
	}
	
	/**
	 * Terminate this task.
	 *
	 * @post   This task  is terminated.
	 *       | new.isTerminated()
	 */
	 public void terminate() {
		 this.isTerminated = true;
	 }
	 
	 /**
	  * Return a boolean indicating whether or not this task
	  * is terminated.
	  */
	 @Basic @Raw
	 public boolean isTerminated() {
		 return this.isTerminated;
	 }
	 
	 /**
	  * Variable registering whether this person is terminated.
	  */
	 private boolean isTerminated = false;
	 
	
}
