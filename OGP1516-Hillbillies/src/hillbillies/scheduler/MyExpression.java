package hillbillies.scheduler;

import hillbillies.model.Unit;
import hillbillies.model.World;

public abstract class MyExpression {

	public abstract Object evaluate(TaskComponents taskComponents);
	
	public abstract Boolean containSelectedCube();
	
}
