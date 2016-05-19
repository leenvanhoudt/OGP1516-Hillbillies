package hillbillies.scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating tasks.
 * 
 * @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 *
 */
public class CreateTasks {
	
	/**
	 * Create a task with the given parameters.
	 * 
	 * @param name
	 * 		The name of the task.
	 * @param priority
	 * 		The priority of the task.
	 * @param activity
	 * 		The statements out which the task exists.
	 * @param selectedCubes
	 * 		The selectedCubes on which the task has to be executed.
	 * @post ...
	 * 		| the name of the task is set to the given name.
	 * 		| new.nameTask == name
	 * @post ...
	 * 		| the priority of the task is set to the given priority.
	 * 		| new.priority == priority
	 * @post ...
	 * 		| the activity of the task is set to the given activity.
	 * 		| new.statementActivity == activity
	 * @post ...
	 * 		| the selectedCubes of the task are set to the given selectedCubes.
	 * 		| new.selectedCubes == selectedCubes
	 */
	public CreateTasks(String name, int priority, MyStatement activity, List<int[]> selectedCubes){
		System.out.println("create tasks constructor");
		this.name = name;
		this.priority = priority;
		this.statementActivity = activity;
		this.selectedCubes = selectedCubes;
	}
	
	/**
	 * Variables registering the name, priority, activity and selectedCubes of a task.
	 */
	private String name;
	private int priority;
	private MyStatement statementActivity;
	private List<int[]> selectedCubes;
	
	/**
	 * Return the tasks that are made for every selectedCube. If there is no selectedCube
	 * and it is not needed for the task, a task without a cube will be created. Otherwise
	 * if there is no selectedCube and there is one needed, an exception will be thrown.
	 *
	 * @return ...
	 * 		| Return all the created tasks.
	 * @Error ...
	 * 		| Throw an error if there are no selectedCubes and a selectedCube is expected.
	 */
	public List<Task> tasks() throws Error{
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
