package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class HerePositionExpression extends CubePositionExpression {


	@Override
	public int[] evaluate(TaskComponents taskComponents) {
		System.out.println("HERE EXP");
		// TODO Auto-generated method stub
		return taskComponents.getUnit().getCubeCoordinate();
	}

	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return false;
	}

	

}
