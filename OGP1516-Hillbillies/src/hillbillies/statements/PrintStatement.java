package hillbillies.statements;

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
		System.out.println("PRINT STATEMENT");
		// TODO Auto-generated method stub
		try{
			System.out.println(this.expressionValue.evaluate(taskComponents));
			this.setExecutedState(true);
		} catch(Throwable e){
			throw new Error("Can not print");
		}
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
