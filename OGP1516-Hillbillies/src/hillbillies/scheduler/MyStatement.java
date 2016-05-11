package hillbillies.scheduler;

import hillbillies.model.Unit;
import hillbillies.model.World;

public abstract class MyStatement {
	
	public abstract void execute(TaskComponents taskComponents);
	
	public abstract Boolean containSelectedCube();

}
