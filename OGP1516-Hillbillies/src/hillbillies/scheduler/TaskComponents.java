package hillbillies.scheduler;

import java.util.ArrayList;
import java.util.HashMap;

import hillbillies.model.Unit;
import hillbillies.model.World;

public class TaskComponents {

	private World world;
	private Unit unit;
	private int[] selectedCube;

	public TaskComponents(World world, Unit unit, int[] selectedCube){
		this.world = world;
		this.unit = unit;
		this.selectedCube = selectedCube;
	}
	
	public Unit getUnit(){
		return this.unit;
	}
	
	public World getWorld(){
		return this.world;
	}
	
	public int[] getSelectedCube(){
		return this.selectedCube;
	}
	
	private ArrayList<MyStatement> parentList = new ArrayList<MyStatement>();
	
	public ArrayList<MyStatement> getParentList(){
		return this.parentList;
	}
	
	public void addStatement(MyStatement statement){
		this.parentList.add(statement);
	}
	
	public void removeStatement(MyStatement statement){
		this.parentList.remove(statement);
	}
	
	private HashMap<String,Object> assignedVariableList = new HashMap<String, Object>();
	
	public HashMap<String,Object> getVariables(){
		return this.assignedVariableList;
	}
	
	public void addVariable(String name, Object object){
		this.assignedVariableList.put(name, object);
	}
	
	public Object getValue(String valueName){
		return this.getVariables().get(valueName);
	}
}
