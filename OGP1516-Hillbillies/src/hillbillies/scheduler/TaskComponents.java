package hillbillies.scheduler;

import java.util.HashMap;

import hillbillies.model.Unit;
import hillbillies.model.World;

public class TaskComponents {

	private World world;
	private Unit unit;
	private int[] selectedCube;
	private double dt;

	public TaskComponents(World world, Unit unit, int[] selectedCube, double dt){
		this.world = world;
		this.unit = unit;
		this.selectedCube = selectedCube;
		this.dt = dt;
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
	
	public double getDeltaT(){
		return this.dt;
	}
	
	public void setDeltaT(double dt){
		this.dt = dt;
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
