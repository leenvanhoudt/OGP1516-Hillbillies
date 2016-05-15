package hillbillies.scheduler;


public abstract class MyStatement {
	
	public abstract void execute(TaskComponents taskComponents);
	
	public abstract boolean containSelectedCube();
	
	public abstract MyStatement getNext(TaskComponents taskComponents);
	
	public abstract boolean isExecuted();
	
	public abstract void setExecutedState(boolean state);

	
	public MyStatement getParent(){
		return this.parent;
	}
	
	public void setParent(MyStatement parent){
		this.parent = parent;
	}
	
	private MyStatement parent;
	
}
