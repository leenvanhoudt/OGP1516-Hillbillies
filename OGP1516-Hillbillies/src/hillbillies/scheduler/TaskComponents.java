package hillbillies.scheduler;

import java.util.HashMap;

import be.kuleuven.cs.som.annotate.Basic;
import hillbillies.model.Unit;
import hillbillies.model.World;
 /**
  * Class saving the components, needed to execute a task.
  * 
  * @author Laura Vranken & Leen Van Houdt, 
  * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
  * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
  *
  */
public class TaskComponents {
	
	/**
	 * Contains the parameters needed to execute a task. 
	 * 
	 * @param world
	 * 		The world in which the task will be executed.
	 * @param unit
	 * 		The unit who will execute the task.
	 * @param selectedCube
	 * 		The cube on which the task will be executed.
	 */
	public TaskComponents(World world, Unit unit, int[] selectedCube){
		this.world = world;
		this.unit = unit;
		this.selectedCube = selectedCube;
	}
	
	/**
	 * Initializing Objects of the class World and Unit, and an int[]. 
	 */
	private World world;
	private Unit unit;
	private int[] selectedCube;
	
	/**
	 * Return the unit that will execute the task.
	 */
	@Basic
	public Unit getUnit(){
		return this.unit;
	}
	
	/**
	 * Return the world in which the task will be executed.
	 */
	@Basic
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Return the cube on which the task will be executed.
	 */
	@Basic
	public int[] getSelectedCube(){
		return this.selectedCube;
	}
	
	/**
	 * HashMap for registering the name of the assigned variables and their Expression.
	 */
	private HashMap<String,Object> assignedVariableList = new HashMap<String, Object>();
	
	/**
	 * Return the HashMap, containing all assigned variables.
	 */
	@Basic
	public HashMap<String,Object> getVariables(){
		return this.assignedVariableList;
	}
	
	/**
	 * Add a variable to the HashMap.
	 * 
	 * @param name
	 * 		The name of the variable that has to be saved.
	 * @param object
	 * 		The expression belonging to the variable name.
	 * @effect ...
	 * 		| The name and its value are added to the assignedVariableList.
	 * 		| this.assignedVariableList.put(name, object)
	 */
	public void addVariable(String name, Object object){
		this.assignedVariableList.put(name, object);
	}
	
	/**
	 * Return the value belonging to the given name.
	 * 
	 * @param valueName
	 * 		The name for which the value must be returned.
	 * @return
	 * 		| Return the value belonging to the given name.
	 */
	@Basic
	public Object getValue(String valueName){
		return this.getVariables().get(valueName);
	}
}
