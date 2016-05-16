package hillbillies.statements;

import hillbillies.expressions.ReadVariableExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class BreakStatement extends MyStatement{
		
	@Override
	public void execute(TaskComponents taskComponents) throws Error{
		System.out.println("BREAK STATEMENT");
		MyStatement parent = this.getParent();
		while(parent !=null && !(parent instanceof WhileStatement)){
			parent = parent.getParent();
		}
		if ((parent instanceof WhileStatement)){
			parent.setExecutedState(true);
			this.setExecutedState(true);
		} else{
			throw new Error("break not in a while loop");
		}
		
	}

	@Override
	public boolean containSelectedCube() {
		return false;
	}

	@Override
	public MyStatement getNext(TaskComponents taskComponents) {
		return null;
	}
	
	@Override
	public MyStatement getNextWellFormed() {
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
	}

	@Override
	public boolean containReadVariableExpression() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ReadVariableExpression getReadVariableExpression() {
		// TODO Auto-generated method stub
		return null;
	}

}
