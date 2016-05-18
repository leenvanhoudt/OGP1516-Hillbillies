package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class IsSolidExpression<E extends CubePositionExpression, ReadVariableExpression> extends BooleanExpression {
	
	private CubePositionExpression expressionPosition;
	private ReadVariableExpression expressionVariablePosition;

	public IsSolidExpression(CubePositionExpression position){
		this.expressionPosition = position;
	}
	
	public IsSolidExpression(ReadVariableExpression position){
		this.expressionVariablePosition = position;
	}

	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents) throws ClassCastException {
		System.out.println("ISSOLID EXP");
		int[] position;
		if (this.expressionVariablePosition != null)
			position = ((ICubePositionExpression) this.expressionVariablePosition).evaluatePosition(taskComponents);
		else
			position = this.expressionPosition.evaluatePosition(taskComponents);
		return !taskComponents.getWorld().isPassable(position[0], position[1], position[2]);
	}

	@Override
	public boolean containSelectedCube() {
		if (this.expressionVariablePosition != null){
			return ((ICubePositionExpression) this.expressionVariablePosition).containSelectedCube();
		}
		return this.expressionPosition.containSelectedCube();
	}


}
