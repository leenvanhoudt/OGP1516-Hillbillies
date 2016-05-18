package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.TaskComponents;

public class ReadVariableExpression extends MyExpression implements IBooleanExpression,
	ICubePositionExpression, IUnitExpression{

	private String varName;

	public ReadVariableExpression(String variableName){
		System.out.println("readvar");
		this.varName = variableName;
		System.out.println(this.varName);
	}
	
	@Override
	public Unit evaluateUnit(TaskComponents taskComponents) throws Error {
		System.out.println("READVARIABLE UNIT EXP");
		try{
			return (Unit) taskComponents.getValue(this.varName);
		} catch (Throwable e){
			throw new Error("can not cast to unit");
		}
	}
	
	@Override
	public int[] evaluatePosition(TaskComponents taskComponents) throws Error{
		System.out.println("READVARIABLE Position EXP");
		try{
			return (int[]) taskComponents.getValue(this.varName);
		} catch(Throwable e){
			System.out.println("error bij readvar");
			throw new Error("can not cast to position");
		}
	}
	
	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents) throws Error {
		System.out.println("READVARIABLE Boolean EXP");
		try{
			return (Boolean) taskComponents.getValue(this.varName);
		} catch (Throwable e){
			throw new Error("can not cast to boolean");
		}
	}
	
	@Override
	public boolean containSelectedCube() {
		return false;
	}

	public String getVariableName(){
		return this.varName;
	}

}
