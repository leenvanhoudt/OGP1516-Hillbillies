package hillbillies.statements;

import hillbillies.expressions.BooleanExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class WhileStatement extends MyStatement {

	private MyExpression expressionCondition;
	private MyStatement statementBody;

	public WhileStatement(MyExpression condition, MyStatement body){
		this.expressionCondition = condition;
		this.statementBody = body;
	}
	
	@Override
	public void execute(TaskComponents taskComponents) {
		System.out.println("WHILE STATEMENT");
		// TODO Auto-generated method stub
		if (!(this.expressionCondition instanceof BooleanExpression)){
			throw new Error("no boolean expression");
		}
		BooleanExpression condition = (BooleanExpression) this.expressionCondition;
		this.setExecutionState(true);
		this.statementBody.setParent(this);
		while(condition.evaluate(taskComponents) && this.getExecutionState() == true){
			this.statementBody.execute(taskComponents);
		}
		this.setExecutionState(false);
	}
	
	public Boolean getExecutionState(){
		return this.executing;
	}
	
	public void setExecutionState(Boolean executing){
		this.executing = executing;
	}
	
	private Boolean executing;
	
	public MyStatement getParent(){
		return this.parent;
	}
	
	public void setParent(MyStatement parent){
		this.parent = parent;
	}

	private MyStatement parent;

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.statementBody.containSelectedCube() ||
				this.expressionCondition.containSelectedCube();
	}

}
