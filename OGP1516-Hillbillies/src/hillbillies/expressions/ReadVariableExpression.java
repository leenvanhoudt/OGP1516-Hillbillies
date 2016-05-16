package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.TaskComponents;

public class ReadVariableExpression extends MyExpression implements IBooleanExpression,
	ICubePositionExpression, IUnitExpression{

	private String varName;

	public ReadVariableExpression(String variableName){
		this.varName = variableName;
	}
	
	@Override
	public Unit evaluateUnit(TaskComponents taskComponents) {
		System.out.println("READVARIABLE EXP");
		return (Unit) taskComponents.getValue(this.varName);
	}
	
	@Override
	public int[] evaluatePosition(TaskComponents taskComponents) {
		System.out.println("READVARIABLE EXP");
		return (int[]) taskComponents.getValue(this.varName);
	}
	
	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents) {
		System.out.println("READVARIABLE EXP");
		return (Boolean) taskComponents.getValue(this.varName);
	}
	
	@Override
	public boolean containSelectedCube() {
		return false;
	}


}
