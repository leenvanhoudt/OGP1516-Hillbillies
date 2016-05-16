package hillbillies.statements;

import hillbillies.expressions.BooleanExpression;
import hillbillies.expressions.CubePositionExpression;
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
	public void execute(TaskComponents taskComponents) {
		System.out.println("ASSIGN STATEMENT");	
		
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
			int[] value = exp.evaluatePosition(taskComponents);
			taskComponents.addVariable(this.variableName, value);
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
	public boolean isExecuted() {
		return this.finished;
	}
	
	private boolean finished;

	@Override
	public void setExecutedState(boolean state) {
		this.finished = state;
	}

}
