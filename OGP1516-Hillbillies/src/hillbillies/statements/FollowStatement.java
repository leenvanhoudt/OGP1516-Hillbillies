package hillbillies.statements;

import hillbillies.expressions.IUnitExpression;
import hillbillies.expressions.UnitExpression;
import hillbillies.model.Unit;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class FollowStatement<E extends UnitExpression,ReadVariableExpression> extends MyStatement {
	
	private UnitExpression expressionUnit;
	private ReadVariableExpression expressionVariableUnit;

	public FollowStatement(UnitExpression unit){
		this.expressionUnit = unit;
	}
	
	public FollowStatement(ReadVariableExpression unit){
		this.expressionVariableUnit = unit;
	}

	@Override
	public void execute(TaskComponents taskComponents) throws Error{
		Unit followed;
		if (this.expressionVariableUnit != null){
			followed = ((IUnitExpression) this.expressionVariableUnit).evaluateUnit(taskComponents);
		}else{
			followed = this.expressionUnit.evaluateUnit(taskComponents);
		}
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
		if (this.expressionVariableUnit != null){
			return ((IUnitExpression) this.expressionVariableUnit).containSelectedCube();
		}
		return this.expressionUnit.containSelectedCube();
	}

	@Override
	public MyStatement getNext(TaskComponents taskComponents) {
		return null;
	}
	
	@Override
	public MyStatement getNextWellFormed() {
		return null;
	}


	@Override
	public boolean containReadVariableExpression() {
		return this.expressionVariableUnit != null;
	}

	@Override
	public hillbillies.expressions.ReadVariableExpression getReadVariableExpression() {
		return (hillbillies.expressions.ReadVariableExpression) this.expressionVariableUnit;
	}

}
