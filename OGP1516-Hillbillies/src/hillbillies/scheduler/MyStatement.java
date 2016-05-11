package hillbillies.scheduler;

import hillbillies.model.Unit;
import hillbillies.model.World;

public abstract class MyStatement {
	
	public abstract void execute(TaskComponents taskComponents);
	
	public abstract Boolean containSelectedCube();
	
	public MyStatement getParent(){
		return this.parent;
	}
	
	public void setParent(MyStatement parent){
		this.parent = parent;
	}
	
	private MyStatement parent;

}
