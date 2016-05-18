package hillbillies.expressions;

import hillbillies.scheduler.MyExpression;

public abstract class UnitExpression extends MyExpression implements IUnitExpression{
		
	public abstract boolean containSelectedCube();
}
