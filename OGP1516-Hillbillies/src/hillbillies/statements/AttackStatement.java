package hillbillies.statements;

import hillbillies.expressions.IUnitExpression;
import hillbillies.expressions.UnitExpression;
import hillbillies.model.Unit;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;
import hillbillies.expressions.ReadVariableExpression;

@SuppressWarnings("hiding")
public class AttackStatement<E extends UnitExpression,ReadVariableExpression> 
	extends MyStatement {
	
	private UnitExpression expressionUnit;
	private ReadVariableExpression expressionVariableUnit;

	public AttackStatement(UnitExpression unit){
		this.expressionUnit = unit;
	}
	
	public AttackStatement(ReadVariableExpression unit){
		this.expressionVariableUnit = unit;
	}

	@Override
	public void execute(TaskComponents taskComponents) throws Error {
		System.out.println("ATTACK STATAMENT");
		Unit enemy;
		if (this.expressionVariableUnit != null){
			enemy = ((IUnitExpression) this.expressionVariableUnit).evaluateUnit(taskComponents);
		}else{
			enemy = this.expressionUnit.evaluateUnit(taskComponents);
		}
		try{
			taskComponents.getUnit().fight(enemy);
			this.setExecutedState(true);
		} catch(Throwable e){
			taskComponents.getUnit().interruptTask();
			throw new Error("enemy not reachable");
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
	public boolean isExecuted() {
		return this.finished;
	}

	private boolean finished;

	@Override
	public void setExecutedState(boolean state) {
		this.finished = state;
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
