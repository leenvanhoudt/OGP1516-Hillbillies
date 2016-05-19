package hillbillies.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import hillbillies.model.Unit;

/**
 * A class that schedules tasks that have to be executed for one faction.
 * 
 * @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 *
 */
public class Scheduler {

	/**
	 * Return the list of scheduled tasks.
	 */
	@Basic
	public List<Task> getScheduledTasks(){
		return this.scheduledList;
	}
	
	/**
	 * Schedule a task, in other words adding the task to the list with scheduled tasks.
	 * Add the scheduler to the list of schedulers which contain that task.
	 * 
	 * @param task
	 * 		The task that has to be scheduled.
	 * @effect ...
	 * 		| the task is scheduled.
	 * 		| this.scheduledList.add(task)
	 * @effect ...
	 * 		| add the task to the scheduler list of task.
	 * 		| task.addScheduler(this);
	 */
	public void schedule(Task task){
		this.scheduledList.add(task);
		task.addScheduler(this);
	}
	
	/**
	 * Remove a task from the list with scheduled tasks and terminate it.
	 * 
	 * @param task
	 * 		The task that has to be scheduled.
	 * @post ...
	 * 		| the task is terminated.
	 * 		| new.isTerminated == true
	 * @effect ...
	 * 		| the task is removed.
	 * 		| this.scheduledList.remove(task)
	 */
	public void removeTask(Task task){
		if (!task.isTerminated()){
			task.terminate();
			this.scheduledList.remove(task);
		}
	}
	
	/**
	 * List to save all scheduled tasks in.
	 */
	private ArrayList<Task> scheduledList = new ArrayList<Task>();
	
	/**
	 * Replace the original task with a new task.
	 * 
	 * @param original
	 * 		The original task that has to be replaced.
	 * @param replacement
	 * 		The task that replaces the original.
	 * @effect ...
	 * 		| remove the original task.
	 * 		| this.removeTask(original)
	 * @effect ...
	 * 		| schedule the replacement.
	 * 		| this.schedule(replacement)
	 */
	public void replace(Task original, Task replacement){
		if (original.getAssignedUnit() != null)
			original.getAssignedUnit().interruptTask();
		this.removeTask(original);
		this.schedule(replacement);
	}
	
	/**
	 * Check if all tasks are part of the list of scheduled tasks.
	 * 
	 * @param tasks
	 * 		The tasks to check.
	 * @return
	 * 		| Return false if scheduledList does not contain one of the tasks.
	 * 		| result == if (!this.getScheduledTasks().contains(task)
	 * 		|			then return false
	 */
	public boolean areTasksPartOf(Collection<Task> tasks){
		for (Task task : tasks){
			if (!this.getScheduledTasks().contains(task))
				return false;
		}
		return true;
	}
	
	/**
	 * Return the task with the highest priority that is not currently being executed.
	 * 
	 * @return ...
	 * 		| The task with the highest priority that is not currently being executed.
	 * 		| result == currentHighest
	 */
	public Task getTaskHighestPriority(){
		Task currentHighest = null;
		for (int i = 0; i<this.getScheduledTasks().size(); i++){
			if ((this.getScheduledTasks().get(i).getAssignedUnit() == null) && (currentHighest == null || 
					currentHighest.getPriority() < this.getScheduledTasks().get(i).getPriority())){
				currentHighest = this.getScheduledTasks().get(i);
			}
		}
		return currentHighest;
	}
	
	/**
	 * Return all tasks meeting the condition.
	 * 
	 * @param condition
	 * 		The condition that the tasks have to meet.
	 * @return ...
	 * 		| Return all the tasks meeting the condtion.
	 * 		| result == allTasksMeetingCondition
	 */
	public List<Task> getAllTasksCondition(Boolean condition){
		List<Task> allTasksMeetingCondition = new ArrayList<Task>();
		for (Task task: this.getScheduledTasks()){
			if (condition){
				allTasksMeetingCondition.add(task);
			}
		}
		return allTasksMeetingCondition;
	}
	
	/**
	 * Assign a task to a unit and a unit to a task.
	 * 
	 * @param task
	 * 		the task assigned to the unit.
	 * @param unit
	 * 		the unit assigned to the task.
	 * @effect ...
	 * 		| assign the unit to the task.
	 * 		| task.setAssignedUnit(unit)
	 * @effect ...
	 * 		| assign the task to the unit.
	 * 		| unit.setAssignedTask(task)
	 */
	public void assignTaskToUnit(Task task, Unit unit){
		task.setAssignedUnit(unit);
		unit.setAssignedTask(task);
	}
	
	/**
	 * Reset the assigned task and the assigned unit.
	 * 
	 * @param task
	 * 		The task for which the assigned unit has to be reset.
	 * @param unit
	 * 		The unit for which the assigned task has to be reset.
	 * @effect ...
	 * 		| set the assigned unit of task to null.
	 * 		| task.setAssignedUnit(null)
	 * @effect ...
	 * 		| set the assigned task of unit to null.
	 * 		| unit.setAssignedTask(null)
	 */
	public void reset(Task task, Unit unit){
		task.setAssignedUnit(null);
		unit.setAssignedTask(null);
	}
	
	
	/**
	 * Returns an iterator for all tasks currently managed by the given
	 * scheduler.
	 * 
	 * @param scheduler
	 *            The scheduler for which to return an iterator.
	 * 
	 * @return ...
	 * 		| An iterator that yields all scheduled tasks managed by the given
	 *      | scheduler, independent of whether they're currently assigned to a
	 *      | Unit or not.
	 *      | The tasks are ranked by decreasing priority
	 *      | result == new Iterator<Task>()
	 */
	public Iterator<Task> getAllTasksIterator(){
		return new Iterator<Task>(){
			
			private int nbHandeld;
			private boolean[] handelingItems = new boolean[scheduledList.size()];
			
			@Override
			public boolean hasNext() {
				return nbHandeld < scheduledList.size();
			}

			@Override
			public Task next() {
				Task currentHighest = null;
				for (int i = 0; i<scheduledList.size(); i++){
					if (!handelingItems[i]){
						if (currentHighest == null || 
								currentHighest.getPriority() < scheduledList.get(i).getPriority()){
							currentHighest = scheduledList.get(i);
						}
					}
				}
				if (currentHighest != null){
					handelingItems[scheduledList.indexOf(currentHighest)] = true;
					nbHandeld+=1;
				}
				return currentHighest;
			}
			
		};
	}
	
}
