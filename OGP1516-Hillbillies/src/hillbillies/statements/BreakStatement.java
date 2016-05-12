package hillbillies.statements;

import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class BreakStatement extends MyStatement{
		
	@Override
	public void execute(TaskComponents taskComponents) throws Error{
		System.out.println("BREAK STATEMENT");
		// TODO Auto-generated method stub
		MyStatement parent = this.getParent();
		while(parent !=null && !(parent instanceof WhileStatement)){
			parent = parent.getParent();
		}
		if ((parent instanceof WhileStatement)){
			WhileStatement whileParent = (WhileStatement) parent;
			whileParent.setExecutedState(true);
			this.setExecutedState(true);
		} else{
			throw new Error("break not in a while loop");
		}
		
	}

	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return false;
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
