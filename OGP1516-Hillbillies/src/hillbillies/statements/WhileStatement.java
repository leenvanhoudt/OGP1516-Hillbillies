package hillbillies.statements;

import hillbillies.expressions.BooleanExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class WhileStatement<E extends BooleanExpression> extends MyStatement {

	private BooleanExpression expressionCondition;
	private MyStatement statementBody;

	public WhileStatement(BooleanExpression condition, MyStatement body){
		System.out.println("WHILE CONSTRUCTOR");
		this.expressionCondition = condition;
		this.statementBody = body;
		this.statementBody.setParent(this);
	}
	
	@Override
	public void execute(TaskComponents taskComponents) {
		System.out.println("WHILE STATEMENT");
		if(this.expressionCondition.evaluate(taskComponents)){
			this.statementBody.setExecutedState(false);
		}else{
			this.setExecutedState(true);
		}
	}
	

	@Override
	public boolean containSelectedCube() {
		return this.statementBody.containSelectedCube() ||
				this.expressionCondition.containSelectedCube();
	}

	@Override
	public MyStatement getNext(TaskComponents taskComponents) {		
		return this.statementBody;
	}

	@Override
	public boolean isExecuted() {
		return this.finished;
	}

	private boolean finished = false;

	@Override
	public void setExecutedState(boolean state) {
		this.finished = state;
		if (state == false){
			this.statementBody.setExecutedState(false);
		}
	}
}
