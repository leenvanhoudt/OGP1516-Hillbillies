package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.TaskComponents;

//TODO fout bij extends myexpression => te breed voor generisch
public class ReadVariableExpression extends MyExpression implements IBooleanExpression,
	ICubePositionExpression, IUnitExpression{

	private String varName;

	public ReadVariableExpression(String variableName){
		System.out.println("readvar");
		this.varName = variableName;
		System.out.println(this.varName);
	}
	
	@Override
	public Unit evaluateUnit(TaskComponents taskComponents) {
		System.out.println("READVARIABLE UNIT EXP");
		return (Unit) taskComponents.getValue(this.varName);
	}
	
	@Override
	public int[] evaluatePosition(TaskComponents taskComponents) {
		System.out.println("READVARIABLE Position EXP");
		try{
			return (int[]) taskComponents.getValue(this.varName);
		} catch(Throwable e){
			System.out.println("error bij readvar");
			throw new Error("error");
		}
	}
	
	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents) {
		System.out.println("READVARIABLE Boolean EXP");
		return (Boolean) taskComponents.getValue(this.varName);
	}
	
	//TODO als variabele selected is moet wel true geven :(
	@Override
	public boolean containSelectedCube() {
		return false;
	}


}
