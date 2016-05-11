package hillbillies.scheduler;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Faction;
import hillbillies.model.Unit;

public class Task {

	private String nameTask;
	private int priority;
	private MyStatement statementactivity;
	private int[] selectedCube;

	
	public Task(String name, int priority, MyStatement activity, int[] selectedCube){
		System.out.println("task class");
		this.nameTask = name;
		this.priority = priority;
		this.statementactivity = activity;
		this.selectedCube = selectedCube;
	}
	
	public void executeStatement(){
		System.out.println("execute statement");
		Unit unit = this.getAssignedUnit();
		TaskComponents taskComponents = new TaskComponents(unit.getWorld(), unit, this.selectedCube);
		this.statementactivity.execute(taskComponents);
	}
	
	//TODO fix it
	public boolean isWellFormed(){
		System.out.println("task iswellformed");
		return true;
	}
	
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
		//System.out.println("task getassignedunit");
		return this.assignedUnit;
	}
	
	public void setAssignedUnit(Unit unit){
		this.assignedUnit = unit;
	}
	
	private Unit assignedUnit;
	
	
	public String getName(){
		//System.out.println("getname task");
		return this.nameTask;
	}
	
	public int getPriority(){
		//System.out.println("getpriority task");
		return this.priority;
	}
	
	
}
