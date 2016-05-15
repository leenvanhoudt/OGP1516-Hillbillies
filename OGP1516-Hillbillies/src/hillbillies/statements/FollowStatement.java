package hillbillies.statements;

import hillbillies.expressions.UnitExpression;
import hillbillies.model.Unit;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class FollowStatement<E extends UnitExpression> extends MyStatement {
	
	private UnitExpression expressionUnit;

	public FollowStatement(UnitExpression unit){
		this.expressionUnit = unit;
	}

	@Override
	public void execute(TaskComponents taskComponents) throws Error{
		System.out.println("FOLLOW STATEMENT");
		Unit followed = this.expressionUnit.evaluate(taskComponents);
		try{
			taskComponents.getUnit().follow(followed);
			this.setExecutedState(true);
		} catch (Throwable e){
			taskComponents.getUnit().interruptTask();
			throw new Error("no path to follow unit");
		}
	}

	@Override
	public boolean containSelectedCube() {
		return this.expressionUnit.containSelectedCube();
	}

	@Override
	public MyStatement getNext(TaskComponents taskComponents) {
		return null;
	}

	@Override
	public boolean isExecuted() {
		return this.finished;
	}
	
	private boolean finished;

	@Override
	public void setExecutedState(boolean state) {
		this.finished = state;
	}

}
