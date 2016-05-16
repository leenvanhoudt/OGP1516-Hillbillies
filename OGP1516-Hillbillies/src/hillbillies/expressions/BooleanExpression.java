package hillbillies.expressions;

import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.TaskComponents;

public abstract class BooleanExpression extends MyExpression implements IBooleanExpression{

	//public abstract Boolean evaluate(TaskComponents taskComponents);
	
	public abstract boolean containSelectedCube();

}
