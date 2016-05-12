package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class SelectedPositionExpression extends CubePositionExpression {
	
	@Override
	public int[] evaluate(TaskComponents taskComponents) {
		System.out.println("SELECTEDCUBE EXP");
		// TODO Auto-generated method stub
		return taskComponents.getSelectedCube();
	}

	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return true;
	}

}
