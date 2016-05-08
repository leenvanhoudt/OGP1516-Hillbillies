package hillbillies.scheduler;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.Faction;
import hillbillies.model.MyStatement;
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
		this.statementactivity.execute(unit.getWorld(), unit, this.selectedCube);
	}
	
	//TODO wordt nergens gebruikt
	public int[] getSelectedCube(){
		return this.selectedCube;
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
		System.out.println("task getassignedunit");
		return this.assignedUnit;
	}
	
	public void setAssignedUnit(Unit unit){
		this.assignedUnit = unit;
	}
	
	private Unit assignedUnit;
	
	
	public String getName(){
		System.out.println("getname task");
		return this.nameTask;
	}
	
	public int getPriority(){
		System.out.println("getpriority task");
		return this.priority;
	}
	
	
}
