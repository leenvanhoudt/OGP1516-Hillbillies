package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class SelectedPositionExpression extends CubePositionExpression {
	
	@Override
	public int[] evaluatePosition(TaskComponents taskComponents) {
		System.out.println("SELECTEDCUBE EXP");
		return taskComponents.getSelectedCube();
	}

	@Override
	public boolean containSelectedCube() {
		return true;
	}

}
