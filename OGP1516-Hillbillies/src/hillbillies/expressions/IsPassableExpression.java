package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class IsPassableExpression<E extends CubePositionExpression,ReadVariableExpression> extends BooleanExpression{


	private CubePositionExpression expressionPosition;
	private ReadVariableExpression expressionVariablePosition;

	public IsPassableExpression(CubePositionExpression position){
		this.expressionPosition = position;		
	}
	
	public IsPassableExpression(ReadVariableExpression position){
		this.expressionVariablePosition = position;		
	}
	
	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents) {
		System.out.println("ISPASSABLE EXP");
		int[] cube;
		if (this.expressionVariablePosition != null)
			cube = ((ICubePositionExpression) this.expressionVariablePosition).evaluatePosition(taskComponents);
		else
			cube = this.expressionPosition.evaluatePosition(taskComponents);
		return taskComponents.getWorld().isPassable(cube[0], cube[1], cube[2]);
	}

	@Override
	public boolean containSelectedCube() {
		if (this.expressionVariablePosition != null)
			return ((ICubePositionExpression) this.expressionVariablePosition).containSelectedCube();
		return this.expressionPosition.containSelectedCube();
	}

}
