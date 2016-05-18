package hillbillies.expressions;

import hillbillies.scheduler.MyExpression;

public abstract class CubePositionExpression extends MyExpression
	implements ICubePositionExpression {
	
	public abstract boolean containSelectedCube();

}
