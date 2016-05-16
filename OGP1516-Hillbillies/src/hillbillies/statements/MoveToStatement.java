package hillbillies.statements;

import hillbillies.expressions.CubePositionExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class MoveToStatement<E extends CubePositionExpression> extends MyStatement {

	private CubePositionExpression expressionPosition;

	public MoveToStatement(CubePositionExpression position){
		this.expressionPosition = position;
	}
	
	@Override
	public void execute(TaskComponents taskComponents) throws Error{
		System.out.println("MOVE TO STATEMENT");
		int[] cube = this.expressionPosition.evaluatePosition(taskComponents);
		try{
			taskComponents.getUnit().moveTo(cube);
			this.setExecutedState(true);
		}catch(Throwable e){
			taskComponents.getUnit().interruptTask();
			throw new Error("no path found");
		}
	}

	@Override
	public boolean containSelectedCube() {
		return this.expressionPosition.containSelectedCube();
	}

	@Override
	public MyStatement getNext(TaskComponents taskComponents) {
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

}
