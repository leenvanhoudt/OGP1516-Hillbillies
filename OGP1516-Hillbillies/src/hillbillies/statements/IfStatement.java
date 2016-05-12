package hillbillies.statements;


import hillbillies.expressions.BooleanExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class IfStatement<E extends BooleanExpression> extends MyStatement {

	private BooleanExpression expressionCondition;
	private MyStatement statementIfBody;
	private MyStatement statementElseBody;

	public IfStatement(BooleanExpression condition, MyStatement ifBody, MyStatement elseBody){
		this.expressionCondition = condition;
		this.statementIfBody = ifBody;
		this.statementElseBody = elseBody;
	}
	
	
	@Override
	public void execute(TaskComponents taskComponents) {
		System.out.println("IF STATEMENT");
		this.setExecutedState(true);
	}

	@Override
	public boolean containSelectedCube() {
		return this.expressionCondition.containSelectedCube() ||
				this.statementIfBody.containSelectedCube() ||
				(this.statementElseBody != null && this.statementElseBody.containSelectedCube());
	}


	@Override
	public MyStatement getNext(TaskComponents taskComponents) {		
		if (this.expressionCondition.evaluate(taskComponents)){
			this.statementIfBody.setParent(this);
			return this.statementIfBody;
		}else if(this.statementElseBody != null){
			System.out.println("ELSE BODY STATEMENT");
			this.statementElseBody.setParent(this);
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
		if (state == false){
			this.statementIfBody.setExecutedState(false);
			if (this.statementElseBody != null)
				this.statementElseBody.setExecutedState(false);
		}
	}
	
}
