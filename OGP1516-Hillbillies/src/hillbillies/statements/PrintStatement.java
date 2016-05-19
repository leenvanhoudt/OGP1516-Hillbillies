package hillbillies.statements;

import hillbillies.expressions.BooleanExpression;
import hillbillies.expressions.CubePositionExpression;
import hillbillies.expressions.ReadVariableExpression;
import hillbillies.expressions.UnitExpression;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class PrintStatement extends MyStatement{
	
	private MyExpression expressionValue;

	public PrintStatement(MyExpression value){
		this.expressionValue = value;
	}

	@Override
	public void execute(TaskComponents taskComponents) throws Error {
		if (this.expressionValue instanceof BooleanExpression){
			BooleanExpression exp = (BooleanExpression) this.expressionValue;
			System.out.println(exp.evaluateBoolean(taskComponents));
		}
		else if (this.expressionValue instanceof UnitExpression){
			UnitExpression exp = (UnitExpression) this.expressionValue;
			System.out.println(exp.evaluateUnit(taskComponents));
		}
		else if (this.expressionValue instanceof CubePositionExpression){
			CubePositionExpression exp = (CubePositionExpression) this.expressionValue;
			System.out.println(exp.evaluatePosition(taskComponents));
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

}
