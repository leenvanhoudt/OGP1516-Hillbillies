package hillbillies.expressions;

import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.TaskComponents;

public abstract class CubePositionExpression extends MyExpression
	implements ICubePositionExpression {

	//public abstract int[] evaluate(TaskComponents taskComponents);
	
	public abstract boolean containSelectedCube();

}
