package hillbillies.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import hillbillies.model.Unit;
import hillbillies.statements.SequenceStatement;
import ogp.framework.util.ModelException;

public class Scheduler {

	
	public List<Task> getScheduledTasks(){
		System.out.println("get scheduled tasks" + this.scheduledList.size());
		return this.scheduledList;
	}
		
	public void schedule(Task task){
		System.out.println("schedule");
		this.scheduledList.add(task);
	}
	
	public void removeTask(Task task){
		System.out.println("remove");
		this.scheduledList.remove(task);
	}
	
	private ArrayList<Task> scheduledList = new ArrayList<Task>();
	
	public void replace(Task original, Task replacement){
		System.out.println("replace");
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
		System.out.println("get highest priority");
		for (Task task: this.getScheduledTasks()){
			if (task.getAssignedUnit() == null)
				return task;
		}
		return null;
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
		//TODO is scheduledlist gesorteerd? zo niet verander gethighestpriority
		//System.out.println("iterator");
		ArrayList<Task> tasksToSort = this.scheduledList;
		Collections.sort(tasksToSort, new TaskComparator());
		Iterator<Task> taskIterator = this.scheduledList.iterator();
		return taskIterator;
	}
	
}
