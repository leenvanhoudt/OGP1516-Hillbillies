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
		// TODO Auto-generated method stub
		Object value = this.expressionValue.evaluate(taskComponents);
		taskComponents.addVariable(this.variableName, value);
		this.setExecutedState(true);
	}


	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionValue.containSelectedCube();
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
