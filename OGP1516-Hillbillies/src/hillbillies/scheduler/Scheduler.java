package hillbillies.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.Unit;
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
		if (!this.isTerminated){
			this.terminate();
			this.scheduledList.remove(task);
		}
	}
	
	private ArrayList<Task> scheduledList = new ArrayList<Task>();
	
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
		System.out.println("get highest priority");
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
	
	/**
	 * Terminate this task.
	 *
	 * @post   This task  is terminated.
	 *       | new.isTerminated()
	 */
	 public void terminate() {
		 this.isTerminated = true;
	 }
	 
	 /**
	  * Return a boolean indicating whether or not this task
	  * is terminated.
	  */
	 @Basic @Raw
	 public boolean isTerminated() {
		 return this.isTerminated;
	 }
	 
	 /**
	  * Variable registering whether this person is terminated.
	  */
	 private boolean isTerminated = false;
	 
	
}
