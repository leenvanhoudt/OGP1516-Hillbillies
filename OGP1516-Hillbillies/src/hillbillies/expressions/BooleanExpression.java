package hillbillies.expressions;

import hillbillies.scheduler.MyExpression;

public abstract class BooleanExpression extends MyExpression implements IBooleanExpression{
	
	public abstract boolean containSelectedCube();

}
