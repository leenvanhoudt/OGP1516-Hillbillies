package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class IsSolidExpression<E extends CubePositionExpression> extends BooleanExpression {
	
	private CubePositionExpression expressionPosition;

	public IsSolidExpression(CubePositionExpression position){
		this.expressionPosition = position;
	}

	@Override
	public Boolean evaluate(TaskComponents taskComponents) throws ClassCastException {
		System.out.println("ISSOLID EXP");
		// TODO Auto-generated method stub
		int[] position = this.expressionPosition.evaluate(taskComponents);
		return !taskComponents.getWorld().isPassable(position[0], position[1], position[2]);
	}

	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionPosition.containSelectedCube();
	}


}
