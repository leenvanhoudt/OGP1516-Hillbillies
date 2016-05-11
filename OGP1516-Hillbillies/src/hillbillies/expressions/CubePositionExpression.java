package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.TaskComponents;

public abstract class CubePositionExpression extends MyExpression {

	public abstract int[] evaluate(TaskComponents taskComponents);
	
	public abstract Boolean containSelectedCube();

}
