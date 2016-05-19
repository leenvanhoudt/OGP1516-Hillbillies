package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class HerePositionExpression extends CubePositionExpression {


	@Override
	public int[] evaluatePosition(TaskComponents taskComponents) {
		return taskComponents.getUnit().getCubeCoordinate();
	}

	@Override
	public boolean containSelectedCube() {
		return false;
	}

	

}
