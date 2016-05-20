package hillbillies.statements;

import hillbillies.expressions.BooleanExpression;
import hillbillies.expressions.CubePositionExpression;
import hillbillies.expressions.ReadVariableExpression;
import hillbillies.expressions.UnitExpression;
import hillbillies.model.Unit;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class AssignmentStatement extends MyStatement {
	
	private String variableName;
	private MyExpression expressionValue;

	public AssignmentStatement(String variableName, MyExpression value){
		this.variableName = variableName;
		this.expressionValue = value;
	}

	@Override
	public void execute(TaskComponents taskComponents) throws Error {
		if (this.expressionValue instanceof BooleanExpression){
			BooleanExpression exp = (BooleanExpression) this.expressionValue;
			boolean value = exp.evaluateBoolean(taskComponents);
			taskComponents.addVariable(this.variableName, value);
		}
		else if (this.expressionValue instanceof UnitExpression){
			UnitExpression exp = (UnitExpression) this.expressionValue;
			Unit value = exp.evaluateUnit(taskComponents);
			taskComponents.addVariable(this.variableName, value);
		}
		else if (this.expressionValue instanceof CubePositionExpression){
			CubePositionExpression exp = (CubePositionExpression) this.expressionValue;
			try{
				int[] value = exp.evaluatePosition(taskComponents);
				taskComponents.addVariable(this.variableName, value);
			} catch(Throwable e){
				throw new Error("eRRor");
			}
		}
		this.setExecutedState(true);
	}

	@Override
	public boolean containSelectedCube() {
		return this.expressionValue.containSelectedCube();
	}

	@Override
	public MyStatement getNext(TaskComponents taskComponents) {
		return null;
	}
	
	@Override
	public MyStatement getNextWellFormed() {
		return null;
	}


	@Override
	public boolean containReadVariableExpression() {
		if (this.expressionValue instanceof ReadVariableExpression)
			return true;
		return false;
	}

	@Override
	public ReadVariableExpression getReadVariableExpression() {
		if (this.expressionValue instanceof ReadVariableExpression)
			return (ReadVariableExpression) this.expressionValue;
		return null;
	}

	public String getVarName(){
		return this.variableName;
	}
}
