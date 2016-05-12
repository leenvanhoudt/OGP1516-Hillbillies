package hillbillies.statements;

import hillbillies.expressions.CubePositionExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class WorkStatement<E extends CubePositionExpression> extends MyStatement{

	private CubePositionExpression expressionPosition;

	public WorkStatement(CubePositionExpression position){
		this.expressionPosition = position;
	}
	
	@Override
	public void execute(TaskComponents taskComponents) throws Error {
		System.out.println("WORK STATEMENT");
		int[] position = this.expressionPosition.evaluate(taskComponents);
		try{
			taskComponents.getUnit().workAt(position[0], position[1], position[2]);
			this.setExecutedState(true);
		} catch (Throwable e){
			taskComponents.getUnit().interruptTask();
			throw new Error("Can not work on that cube");
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
	
	private boolean finished = false;

	@Override
	public void setExecutedState(boolean state) {
		this.finished = state;
	}

}
