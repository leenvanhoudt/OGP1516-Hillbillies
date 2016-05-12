package hillbillies.statements;


import hillbillies.expressions.BooleanExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class IfStatement extends MyStatement {

	private MyExpression expressionCondition;
	private MyStatement statementIfBody;
	private MyStatement statementElseBody;

	public IfStatement(MyExpression condition, MyStatement ifBody, MyStatement elseBody){
		this.expressionCondition = condition;
		this.statementIfBody = ifBody;
		this.statementElseBody = elseBody;
	}
	
	
	@Override
	public void execute(TaskComponents taskComponents) {
		System.out.println("IF STATEMENT");
		// TODO Auto-generated method stub
//		if (!(this.expressionCondition instanceof BooleanExpression)){
//			throw new Error("geen boolean expression");
//		}
//		BooleanExpression con = (BooleanExpression) this.expressionCondition;
//		
//		if (con.evaluate(taskComponents)){
//			this.statementIfBody.setParent(this);
//			this.statementIfBody.execute(taskComponents);
//		}else if(this.statementElseBody != null){
//			System.out.println("ELSE BODY STATEMENT");
//			this.statementElseBody.setParent(this);
//			this.statementElseBody.execute(taskComponents);
//		}
		this.setExecutedState(true);
	}

	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionCondition.containSelectedCube() ||
				this.statementIfBody.containSelectedCube() ||
				(this.statementElseBody != null && this.statementElseBody.containSelectedCube());
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
		
		if (!(this.expressionCondition instanceof BooleanExpression)){
			throw new Error("geen boolean expression");
		}
		BooleanExpression con = (BooleanExpression) this.expressionCondition;
		if (con.evaluate(taskComponents)){
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
		// TODO Auto-generated method stub
		return this.finished;
	}
	
	private boolean finished;

	@Override
	public void setExecutedState(boolean state) {
		// TODO Auto-generated method stub
		this.finished = state;
	}
	
}
