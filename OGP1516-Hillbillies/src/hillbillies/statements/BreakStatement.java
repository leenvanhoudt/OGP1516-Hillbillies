package hillbillies.statements;

import hillbillies.expressions.ReadVariableExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class BreakStatement extends MyStatement{
		
	@Override
	public void execute(TaskComponents taskComponents) throws Error{
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
	public boolean containReadVariableExpression() {
		return false;
	}

	@Override
	public ReadVariableExpression getReadVariableExpression() {
		return null;
	}

}
