package hillbillies.statements;

import hillbillies.expressions.BooleanExpression;
import hillbillies.expressions.IBooleanExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class WhileStatement<E extends BooleanExpression,ReadVariableExpression> extends MyStatement {

	private BooleanExpression expressionCondition;
	private ReadVariableExpression expressionVariableCondition;
	private MyStatement statementBody;

	public WhileStatement(BooleanExpression condition, MyStatement body){
		this.expressionCondition = condition;
		this.statementBody = body;
		this.statementBody.setParent(this);
	}
	
	public WhileStatement(ReadVariableExpression condition, MyStatement body){
		this.expressionVariableCondition = condition;
		this.statementBody = body;
		this.statementBody.setParent(this);
	}
	
	@Override
	public void execute(TaskComponents taskComponents) {
		boolean con;
		if (this.expressionVariableCondition != null){
			con = ((IBooleanExpression) this.expressionVariableCondition).evaluateBoolean(taskComponents);
		}else{
			con = this.expressionCondition.evaluateBoolean(taskComponents);
		}
		
		if (con){
			this.statementBody.setExecutedState(false);
		}else{
			this.setExecutedState(true);
		}
	}
	

	@Override
	public boolean containSelectedCube() {
		if (this.expressionVariableCondition != null){
			return  this.statementBody.containSelectedCube() ||
					((IBooleanExpression) this.expressionVariableCondition).containSelectedCube();
		}
		return this.statementBody.containSelectedCube() ||
				this.expressionCondition.containSelectedCube();
	}

	@Override
	public MyStatement getNext(TaskComponents taskComponents) {		
		return this.statementBody;
	}
	
	@Override
	public MyStatement getNextWellFormed() {
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
		this.statementBody.setExecutedState(state);
	}

	@Override
	public boolean containReadVariableExpression() {
		return this.expressionVariableCondition != null;
	}

	@Override
	public hillbillies.expressions.ReadVariableExpression getReadVariableExpression() {
		return (hillbillies.expressions.ReadVariableExpression) this.expressionVariableCondition;
	}

}
