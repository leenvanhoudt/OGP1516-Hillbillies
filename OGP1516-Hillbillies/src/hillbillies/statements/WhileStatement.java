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
		this.statementBody.setParent(this);
		if(condition.evaluate(taskComponents)){
			this.statementBody.setExecutedState(false);
		}else{
			this.setExecutedState(true);
		}
	}
	

	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.statementBody.containSelectedCube() ||
				this.expressionCondition.containSelectedCube();
	}

	@Override
	public MyStatement getNext(TaskComponents taskComponents) {
//		MyStatement parent = this.getParent();
//		if (parent instanceof SequenceStatement && 
//			((SequenceStatement) parent).getListSequence().size()-1 >
//				((SequenceStatement) parent).getListSequence().indexOf(this)){
//			SequenceStatement parentSeq = (SequenceStatement) parent;
//			return parentSeq.getListSequence().get(parentSeq.getListSequence().indexOf(this)+1);
//		}else if(parent != null){
//			return parent.getNext();
//		}else{
//			return null;
//		}
		
		return this.statementBody;
	}

	@Override
	public boolean isExecuted() {
		// TODO Auto-generated method stub
		return this.finished;
	}

	private boolean finished = false;

	@Override
	public void setExecutedState(boolean state) {
		// TODO Auto-generated method stub
		this.finished = state;
	}
}
