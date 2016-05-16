package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class IsPassableExpression<E extends CubePositionExpression> extends BooleanExpression{


	private CubePositionExpression expressionPosition;

	public IsPassableExpression(CubePositionExpression position){
		this.expressionPosition = position;		
	}
	
	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents) {
		System.out.println("ISPASSABLE EXP");
		int[] cube = this.expressionPosition.evaluatePosition(taskComponents);
		return taskComponents.getWorld().isPassable(cube[0], cube[1], cube[2]);
	}

	@Override
	public boolean containSelectedCube() {
		return this.expressionPosition.containSelectedCube();
	}

}
