package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public interface IBooleanExpression{
	
	public abstract Boolean evaluateBoolean(TaskComponents taskComponents);
	
	public abstract boolean containSelectedCube();
}
