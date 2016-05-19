package hillbillies.scheduler;

import hillbillies.expressions.ReadVariableExpression;

/**
 * @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 *
 */
public abstract class MyStatement {
	
	public abstract void execute(TaskComponents taskComponents);
	
	public abstract boolean containSelectedCube();
	
	public abstract boolean isExecuted();
	
	public abstract void setExecutedState(boolean state);
	
	public abstract MyStatement getNext(TaskComponents taskComponents);
	
	public abstract MyStatement getNextWellFormed();
	
	public abstract boolean containReadVariableExpression();
	
	public abstract ReadVariableExpression getReadVariableExpression();
	
	
	public MyStatement getParent(){
		return this.parent;
	}
	
	public void setParent(MyStatement parent){
		this.parent = parent;
	}
	
	private MyStatement parent;
	
}
