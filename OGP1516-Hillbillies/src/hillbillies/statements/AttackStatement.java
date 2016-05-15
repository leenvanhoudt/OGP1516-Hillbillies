package hillbillies.statements;

import hillbillies.expressions.UnitExpression;
import hillbillies.model.Unit;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class AttackStatement<E extends UnitExpression> extends MyStatement {
	
	private UnitExpression expressionUnit;

	public AttackStatement(UnitExpression unit){
		this.expressionUnit = unit;
	}

	@Override
	public void execute(TaskComponents taskComponents) throws Error {
		System.out.println("ATTACK STATAMENT");
		Unit enemy = this.expressionUnit.evaluate(taskComponents);
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
