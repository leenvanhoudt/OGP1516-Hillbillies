package hillbillies.statements;


import hillbillies.expressions.BooleanExpression;
import hillbillies.expressions.IBooleanExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class IfStatement<E extends BooleanExpression,ReadVariableExpression> extends MyStatement {

	private BooleanExpression expressionCondition;
	private ReadVariableExpression expressionVariableCondition;
	private MyStatement statementIfBody;
	private MyStatement statementElseBody;

	public IfStatement(BooleanExpression condition, MyStatement ifBody, MyStatement elseBody){
		this.expressionCondition = condition;
		this.statementIfBody = ifBody;
		this.statementElseBody = elseBody;
		this.statementIfBody.setParent(this);
		if (this.statementElseBody != null){
			this.statementElseBody.setParent(this);}
	}
	
	public IfStatement(ReadVariableExpression condition, MyStatement ifBody, MyStatement elseBody){
		this.expressionVariableCondition = condition;
		this.statementIfBody = ifBody;
		this.statementElseBody = elseBody;
		this.statementIfBody.setParent(this);
		if (elseBody != null)
			this.statementElseBody.setParent(this);
	}
	
	@Override
	public void execute(TaskComponents taskComponents) {
		this.setExecutedState(true);
	}

	@Override
	public boolean containSelectedCube() {
		if (this.expressionVariableCondition != null){
			return ((IBooleanExpression) this.expressionVariableCondition).containSelectedCube() ||
					this.statementIfBody.containSelectedCube() ||
					(this.statementElseBody != null && this.statementElseBody.containSelectedCube());
		}
		return this.expressionCondition.containSelectedCube() ||
				this.statementIfBody.containSelectedCube() ||
				(this.statementElseBody != null && this.statementElseBody.containSelectedCube());
	}


	@Override
	public MyStatement getNext(TaskComponents taskComponents) {	
		boolean bool;
		if (this.expressionVariableCondition != null){
			bool = ((IBooleanExpression) this.expressionVariableCondition).evaluateBoolean(taskComponents);
		}else{
			bool = this.expressionCondition.evaluateBoolean(taskComponents);
		}
		
		if (bool){
			return this.statementIfBody;
		}else if(this.statementElseBody != null){
			return this.statementElseBody;
		}
		return null;
		
	}
	
	@Override
	public MyStatement getNextWellFormed() {
		if (!this.statementIfBody.isExecuted())
			return this.statementIfBody;
		else if (this.statementElseBody != null && !this.statementElseBody.isExecuted()){
			return this.statementElseBody;
		}
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
		this.statementIfBody.setExecutedState(state);
		if (this.statementElseBody != null)
			this.statementElseBody.setExecutedState(state);
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
