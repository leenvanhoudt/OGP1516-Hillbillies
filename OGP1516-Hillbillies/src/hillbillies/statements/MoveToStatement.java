package hillbillies.statements;

import hillbillies.expressions.CubePositionExpression;
import hillbillies.expressions.ICubePositionExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class MoveToStatement<E extends CubePositionExpression,ReadVariableExpression> extends MyStatement {

	private CubePositionExpression expressionPosition;
	private ReadVariableExpression expressionVariablePosition;

	public MoveToStatement(CubePositionExpression position){
		this.expressionPosition = position;
	}
	
	public MoveToStatement(ReadVariableExpression position){
		this.expressionVariablePosition = position;
	}
	
	@Override
	public void execute(TaskComponents taskComponents) throws Error{
		int[] cube = new int[]{};
		if (this.expressionVariablePosition != null){
			cube  = ((ICubePositionExpression) this.expressionVariablePosition).evaluatePosition(taskComponents);
		}else{
			cube = this.expressionPosition.evaluatePosition(taskComponents);
		}
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
	
	private boolean finished;

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
