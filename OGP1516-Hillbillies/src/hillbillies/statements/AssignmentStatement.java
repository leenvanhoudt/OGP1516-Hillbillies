package hillbillies.statements;

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
		Object value = this.expressionValue.evaluate(taskComponents);
		taskComponents.addVariable(this.variableName, value);
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
