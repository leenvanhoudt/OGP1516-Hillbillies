package hillbillies.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import hillbillies.model.Unit;
import ogp.framework.util.ModelException;

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
	 * 
	 * @param task
	 * 		The task that has to be scheduled.
	 * @effect ...
	 * 		| the task is scheduled.
	 * 		| this.scheduledList.add(task)
	 */
	public void schedule(Task task){
		System.out.println("schedule");
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
		System.out.println("remove");
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
	 * 
	 * @param original
	 * @param replacement
	 */
	public void replace(Task original, Task replacement){
		System.out.println("replace");
		if (original.getAssignedUnit() != null)
			original.getAssignedUnit().interruptTask();
		this.removeTask(original);
		this.schedule(replacement);
	}
	
	public boolean areTasksPartOf(Collection<Task> tasks){
		System.out.println("tasks are part of");
		for (Task task : tasks){
			if (!this.getScheduledTasks().contains(task))
				return false;
		}
		return true;
	}
	
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
	
	public List<Task> getAllTasksCondition(Boolean condition){
		System.out.println("get all tasks condition");
		List<Task> allTasksMeetingCondition = new ArrayList<Task>();
		for (Task task: this.getScheduledTasks()){
			if (condition){
				allTasksMeetingCondition.add(task);
			}
		}
		return allTasksMeetingCondition;
	}
	
	public void assignTaskToUnit(Task task, Unit unit){
		task.setAssignedUnit(unit);
		unit.setAssignedTask(task);
	}
	
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
	 * @return An iterator that yields all scheduled tasks managed by the given
	 *         scheduler, independent of whether they're currently assigned to a
	 *         Unit or not. Completed tasks should not be part of the result.
	 *         The tasks should be delivered by decreasing priority (highest
	 *         priority first).
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
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
