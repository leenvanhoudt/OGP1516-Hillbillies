package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public interface ICubePositionExpression {

	public abstract int[] evaluatePosition(TaskComponents taskComponents);
	
	public abstract boolean containSelectedCube();

}
