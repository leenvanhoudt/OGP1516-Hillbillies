package hillbillies.model;

import java.util.List;

import ogp.framework.util.ModelException;

public class Task {

	private String nameTask;
	private int priority;
	private MyStatement statementactivity;
	private List<int[]> selectedCubes;

	public Task(String name, int priority, MyStatement activity, List<int[]> selectedCubes){
		this.nameTask = name;
		this.priority = priority;
		this.statementactivity = activity;
		this.selectedCubes = selectedCubes;
	}
	
	public List<int[]> getSelectedCubes(){
		return this.getSelectedCubes();
	}
	
	public boolean isWellFormed(){
		return false;
	}
	
	
}
