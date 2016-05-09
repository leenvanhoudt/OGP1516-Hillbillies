package hillbillies.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import hillbillies.model.Unit;
import ogp.framework.util.ModelException;

public class Scheduler {

	
	public Scheduler(){
		
	}
	
	public List<Task> getScheduledTasks(){
		System.out.println("get scheduled tasks" + this.scheduledList.size());
		return this.scheduledList;
	}
		
	public void schedule(Task task){
		System.out.println("schedule");
		this.scheduledList.add(task);
	}
	
	public void removeTask(Task task){
		this.scheduledList.remove(task);
	}
	
	private List<Task> scheduledList = new ArrayList<Task>();
	
	public void replace(Task original, Task replacement){
		int index = this.getScheduledTasks().indexOf(original);
		this.removeTask(original);
		this.scheduledList.add(index,replacement);
	}
	
	
	public boolean areTasksPartOf(Collection<Task> tasks){
		return this.scheduledList.containsAll(tasks);
	}
	
	public Task getTaskHighestPriority(){
		return this.getScheduledTasks().get(0);
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
	
	//TODO leer iterator
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
		System.out.println("iterator");
		return null;
	}
	
}
