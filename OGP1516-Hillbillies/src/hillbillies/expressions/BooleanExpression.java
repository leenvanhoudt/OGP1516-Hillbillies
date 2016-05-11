package hillbillies.expressions;


import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.TaskComponents;

public abstract class BooleanExpression extends MyExpression{

	public abstract Boolean evaluate(TaskComponents taskComponents);
	
	public abstract Boolean containSelectedCube();

}
