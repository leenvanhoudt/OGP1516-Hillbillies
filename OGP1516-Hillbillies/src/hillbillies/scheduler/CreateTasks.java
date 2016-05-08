package hillbillies.scheduler;

import java.util.ArrayList;
import java.util.List;

import hillbillies.model.MyStatement;

public class CreateTasks {
	
	private String name;
	private int priority;
	private MyStatement statementActivity;
	private List<int[]> selectedCubes;

	public CreateTasks(String name, int priority, MyStatement activity, List<int[]> selectedCubes){
		this.name = name;
		this.priority = priority;
		this.statementActivity = activity;
		this.selectedCubes = selectedCubes;
	}
	
	public List<Task> tasks(){
		System.out.println("create task");
		List<Task> taskonmorecubes = new ArrayList<Task>();
		if (!this.selectedCubes.isEmpty()){
			for (int[] cube: this.selectedCubes){
				Task task = new Task(this.name,this.priority,this.statementActivity,cube);
				taskonmorecubes.add(task);
			}
		} else{
			//TODO If selectedCubes is empty and the 'selected' expression does not occur 
			//in the activity, a list with exactly one Task instance should be returned.
			// ander geval: empty en wel selected -> error
		}
		return taskonmorecubes;
	}

}
