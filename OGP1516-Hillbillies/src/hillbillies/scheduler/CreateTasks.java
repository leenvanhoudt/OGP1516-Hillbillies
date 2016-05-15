package hillbillies.scheduler;

import java.util.ArrayList;
import java.util.List;

public class CreateTasks {
	
	private String name;
	private int priority;
	private MyStatement statementActivity;
	private List<int[]> selectedCubes;

	public CreateTasks(String name, int priority, MyStatement activity, List<int[]> selectedCubes){
		System.out.println("create tasks constructor");
		this.name = name;
		this.priority = priority;
		this.statementActivity = activity;
		this.selectedCubes = selectedCubes;
	}
	
	public List<Task> tasks(){
		System.out.println("create task");
		List<Task> taskOnMoreCubes = new ArrayList<Task>();
		if (!this.selectedCubes.isEmpty()){
			for (int[] cube: this.selectedCubes){
				Task task = new Task(this.name,this.priority,this.statementActivity,cube);
				taskOnMoreCubes.add(task);
			}
		} else if (this.selectedCubes.isEmpty() && !this.statementActivity.containSelectedCube()){
			Task task = new Task(this.name,this.priority,this.statementActivity,null);
			taskOnMoreCubes.add(task);
		} else{
			throw new Error("no selectedCube when needed for task");
		}
		return taskOnMoreCubes;
	}

}
