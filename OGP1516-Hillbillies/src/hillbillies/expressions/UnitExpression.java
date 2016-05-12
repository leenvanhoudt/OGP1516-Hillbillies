package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.TaskComponents;

public abstract class UnitExpression extends MyExpression{
	
	public abstract Unit evaluate(TaskComponents taskComponents);
	
	public abstract Boolean containSelectedCube();
}
