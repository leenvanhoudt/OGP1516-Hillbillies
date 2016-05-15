package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class HerePositionExpression extends CubePositionExpression {


	@Override
	public int[] evaluate(TaskComponents taskComponents) {
		System.out.println("HERE EXP");
		return taskComponents.getUnit().getCubeCoordinate();
	}

	@Override
	public boolean containSelectedCube() {
		return false;
	}

	

}
