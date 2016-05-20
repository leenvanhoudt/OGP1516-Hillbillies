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
	
	public abstract MyStatement getNext(TaskComponents taskComponents);
	
	public abstract MyStatement getNextWellFormed();
	
	public abstract boolean containReadVariableExpression();
	
	public abstract ReadVariableExpression getReadVariableExpression();
	
	public boolean isExecuted(){
		return this.finished;
	}
	
	public void setExecutedState(boolean state){
		this.finished = state;
	}
	
	private boolean finished;
	
	public MyStatement getParent(){
		return this.parent;
	}
	
	public void setParent(MyStatement parent){
		this.parent = parent;
	}
	
	private MyStatement parent;
	
}
