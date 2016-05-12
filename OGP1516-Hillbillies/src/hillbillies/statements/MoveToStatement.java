package hillbillies.statements;

import hillbillies.expressions.CubePositionExpression;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class MoveToStatement extends MyStatement {

	private MyExpression expressionPosition;

	public MoveToStatement(MyExpression position){
		this.expressionPosition = position;
	}
	
	@Override
	public void execute(TaskComponents taskComponents) throws Error{
		System.out.println("MOVE TO STATEMENT");
		// TODO Auto-generated method stub
		if (!(this.expressionPosition instanceof CubePositionExpression)){
			throw new Error("no position expression");
		}
		CubePositionExpression pos = (CubePositionExpression) this.expressionPosition;
		int[] cube = pos.evaluate(taskComponents);
		try{
			taskComponents.getUnit().moveTo(cube);
			this.setExecutedState(true);
		}catch(Throwable e){
			throw new Error("no path found");
		}
	}

	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionPosition.containSelectedCube();
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
