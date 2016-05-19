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
		System.out.println("assign constructor");
		this.variableName = variableName;
		this.expressionValue = value;
	}

	@Override
	public void execute(TaskComponents taskComponents) throws Error {
		System.out.println("ASSIGN STATEMENT");	
		if (this.expressionValue instanceof BooleanExpression){
			System.out.println("assign boolean");
			BooleanExpression exp = (BooleanExpression) this.expressionValue;
			boolean value = exp.evaluateBoolean(taskComponents);
			taskComponents.addVariable(this.variableName, value);
		}
		else if (this.expressionValue instanceof UnitExpression){
			System.out.println("assign unit");
			UnitExpression exp = (UnitExpression) this.expressionValue;
			Unit value = exp.evaluateUnit(taskComponents);
			taskComponents.addVariable(this.variableName, value);
		}
		else if (this.expressionValue instanceof CubePositionExpression){
			System.out.println("assign position");
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
	public boolean isExecuted() {
		return this.finished;
	}
	
	private boolean finished;

	@Override
	public void setExecutedState(boolean state) {
		this.finished = state;
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
