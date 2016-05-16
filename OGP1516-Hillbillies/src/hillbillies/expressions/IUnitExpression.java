package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public abstract interface IUnitExpression{
	
	public abstract Unit evaluateUnit(TaskComponents taskComponents);
	
	public abstract boolean containSelectedCube();

}
