package hillbillies.scheduler;
import java.util.HashSet;
import java.util.Set;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.Unit;

/**
 * A class that make a task and check its wellformedness. Tasks can be assigned to units and
 * can be terminated.
 *  
 * @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 *
 */
public class Task {
	
	/**
	 * Create task with a certain name, priority, the activity to be exicuted and the cube
	 * on which the task has to be executed.
	 * 
	 * @param name
	 * 		The name of the task.
	 * @param priority
	 * 		The priority of the task.
	 * @param activity
	 * 		The activity of the task.
	 * @param selectedCube
	 * 		The cube on which the task will be executed.
	 * @post ...
	 * 		| the name of the task is set to the given name.
	 * 		| new.nameTask == name
	 * @post ...
	 * 		| the priority of the task is set to the given priority.
	 * 		| new.priority == priority
	 * @post ...
	 * 		| the activity of the task is set to the given activity.
	 * 		| new.statementactivity == activity
	 * @post ...
	 * 		| the selectedCube of the task is set to the given selectedCube.
	 * 		| new.selectedCube == selectedCube
	 * @throws IllegalArgumentException ...
	 * 		| Throw an error if the task is not wellformed.
	 * 		| !this.isWellFormed()
	 */
	public Task(String name, int priority, MyStatement activity, int[] selectedCube)throws IllegalArgumentException{
		this.nameTask = name;
		this.priority = priority;
		this.statementactivity = activity;
		this.selectedCube = selectedCube;	
		if (!this.isWellFormed())
			throw new IllegalArgumentException();
	}
	
	/**
	 * Variables referencing to the name, the priority, the activity and the selectedCube of
	 * the task.
	 */
	private String nameTask;
	private int priority;
	private MyStatement statementactivity;
	private int[] selectedCube;
	
	/**
	 * Return true if the task is wellformed. That is if each break is wrapped in
	 * a WhileStatement and each variable used in ReadVariable is assigned first.
	 * 
	 * @return ...
	 * 		| Return true if the task is wellformed.
	 * 		| result == this.isWellFormed.CheckWellFormedness()
	 */
	public boolean isWellFormed(){
		return this.isWellFormed.CheckWellFormedness();
	}
	
	/**
	 * Initialize an object of the class IsWellFormed.
	 */
	private IsWellFormed isWellFormed = new IsWellFormed(this);
	
	/**
	 * Return all schedulers wherein this task is scheduled.
	 * 
	 * @return ...
	 * 		| Return the set of schedulers which contain this task.
	 * 		| result == this.setOfSchedulers
	 */
	@Basic
	public Set<Scheduler> getSchedulersForTask(){
		return this.setOfSchedulers;
	}
	
	/**
	 * Add a scheduler to the set which contains the schedulers who contain this task.
	 * 
	 * @param scheduler
	 * 		The scheduler which contain this task.
	 * @effect ...
	 * 		| add the scheduler to the set.
	 * 		| this.setOfSchedulers.add(scheduler)
	 */
	public void addScheduler(Scheduler scheduler){
		this.setOfSchedulers.add(scheduler);
	}
	
	/**
	 * Set that registers the schedulers containing this task.
	 */
	private Set<Scheduler> setOfSchedulers = new HashSet<Scheduler>();
	
	/**
	 * Return the unit assigned to this task.
	 */
	@Basic
	public Unit getAssignedUnit(){
		return this.assignedUnit;
	}
	
	/**
	 * Set the unit that has to be assigned to this task to the given unit.
	 * 
	 * @param unit
	 * 		The unit to be assigned to this task.
	 * @post ...
	 * 		| The unit is assigned to the given unit.
	 * 		| new.assignedUnit == unit
	 */
	public void setAssignedUnit(Unit unit){
		this.assignedUnit = unit;
	}
	
	/**
	 * Variable registering the assignedUnit.
	 */
	private Unit assignedUnit;
	/**
	 * Return the name of the task.
	 */
	@Basic
	public String getName(){
		return this.nameTask;
	}
	
	/**
	 * Return the priority of the task.
	 */
	@Basic
	public int getPriority(){
		return this.priority;
	}
	
	/**
	 * Set the priority to the given value. If the priority reached the minimum integer value,
	 * it will be set to that minimum value.
	 * 
	 * @param priority
	 * 		The given value to which the priority has to be set.
	 * @post ...
	 * 		| The priority is set to the given value.
	 * 		| new.priority == priority
	 */
	public void setPriority(int priority){
		if (priority > Integer.MIN_VALUE+100)
			this.priority = priority; 
		else{
			this.priority = Integer.MIN_VALUE;
		}
	}
	
	/**
	 * Return the activity of a task.
	 */
	@Basic
	public MyStatement getActivity(){
		return this.statementactivity;
	}
	
	/**
	 * Return the selectedCube of the task.
	 */
	@Basic
	public int[] getSelectedCube(){
		return this.selectedCube;
	}
	
	/**
	 * Terminate this task.
	 *
	 * @post ...
	 * 		| This task  is terminated.
	 *      | new.isTerminated()
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