package hillbillies.statements;


import hillbillies.expressions.UnitExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class FollowStatement extends MyStatement {
	
	private MyExpression expressionUnit;

	public FollowStatement(MyExpression unit){
		this.expressionUnit = unit;
	}

	@Override
	public void execute(TaskComponents taskComponents) throws Error{
		System.out.println("FOLLOW STATEMENT");
		// TODO Auto-generated method stub
		if (!(this.expressionUnit instanceof UnitExpression))
			throw new Error("no unit expression");
		UnitExpression hillbilly = (UnitExpression) this.expressionUnit;
		Unit followed = hillbilly.evaluate(taskComponents);
		try{
			taskComponents.getUnit().follow(followed);
			this.setExecutedState(true);
		} catch (Throwable e){
			throw new Error("no path to follow unit");
		}
	}

	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionUnit.containSelectedCube();
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
