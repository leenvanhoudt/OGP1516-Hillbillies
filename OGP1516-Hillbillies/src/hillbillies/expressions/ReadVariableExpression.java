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
	public Unit evaluateUnit(TaskComponents taskComponents) throws Error {
		try{
			return (Unit) taskComponents.getValue(this.varName);
		} catch (Throwable e){
			throw new Error("can not cast to unit");
		}
	}
	
	@Override
	public int[] evaluatePosition(TaskComponents taskComponents) throws Error{
		try{
			return (int[]) taskComponents.getValue(this.varName);
		} catch(Throwable e){
			throw new Error("can not cast to position");
		}
	}
	
	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents) throws Error {
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
