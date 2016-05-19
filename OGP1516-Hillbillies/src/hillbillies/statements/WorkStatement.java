package hillbillies.statements;

import hillbillies.expressions.CubePositionExpression;
import hillbillies.expressions.ICubePositionExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class WorkStatement<E extends CubePositionExpression,ReadVariableExpression> extends MyStatement{

	private CubePositionExpression expressionPosition;
	private ReadVariableExpression expressionVariablePosition;

	public WorkStatement(CubePositionExpression position){
		this.expressionPosition = position;
	}
	
	public WorkStatement(ReadVariableExpression position){
		this.expressionVariablePosition = position;
	}
	
	
	@Override
	public void execute(TaskComponents taskComponents) throws Error {
		int[] position = new int[]{};
		if (this.expressionVariablePosition != null){
			position = ((ICubePositionExpression) this.expressionVariablePosition).evaluatePosition(taskComponents);
		}else{
			position = this.expressionPosition.evaluatePosition(taskComponents);
		}
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
		if (this.expressionVariablePosition != null){
			return ((ICubePositionExpression) this.expressionVariablePosition).containSelectedCube();
		}else{
			return this.expressionPosition.containSelectedCube();
		}
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
	
	private boolean finished = false;

	@Override
	public void setExecutedState(boolean state) {
		this.finished = state;
	}

	@Override
	public boolean containReadVariableExpression() {
		return this.expressionVariablePosition != null;
	}

	@Override
	public hillbillies.expressions.ReadVariableExpression getReadVariableExpression() {
		return (hillbillies.expressions.ReadVariableExpression) this.expressionVariablePosition;
	}

}
