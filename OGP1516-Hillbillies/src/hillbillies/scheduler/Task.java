package hillbillies.scheduler;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Faction;
import hillbillies.model.Unit;

public class Task {

	public Task(String name, int priority, MyStatement activity, int[] selectedCube)throws IllegalArgumentException{
		System.out.println("task class");
		this.data.nameTask = name;
		this.data.priority = priority;
		this.data.statementactivity = activity;
		this.data.selectedCube = selectedCube;	
		if (!this.isWellFormed())
			throw new IllegalArgumentException();
	}
	
	//TODO check it
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
		return this.data.assignedUnit;
	}
	
	public void setAssignedUnit(Unit unit){
		this.data.assignedUnit = unit;
	}
	
	private TaskData data = new TaskData();


	public String getName(){
		return this.data.nameTask;
	}
	
	public int getPriority(){
		return this.data.priority;
	}
	
	public void setPriority(int priority){
		if (priority > Integer.MIN_VALUE+100)
			this.data.priority = priority; 
		else{
			this.data.priority = Integer.MIN_VALUE;
		}
	}
	
	public MyStatement getActivity(){
		return this.data.statementactivity;
	}
	
	public int[] getSelectedCube(){
		return this.data.selectedCube;
	}
	
}
