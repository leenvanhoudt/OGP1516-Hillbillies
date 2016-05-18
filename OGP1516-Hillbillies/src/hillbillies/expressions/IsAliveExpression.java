package hillbillies.expressions;


import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public class IsAliveExpression<E extends UnitExpression, ReadVariableExpression> extends BooleanExpression{

	private UnitExpression expressionUnit;
	private ReadVariableExpression expressionVariableUnit;

	public IsAliveExpression(UnitExpression unit){
		this.expressionUnit = unit;
	}
	
	public IsAliveExpression(ReadVariableExpression unit){
		this.expressionVariableUnit = unit;
	}

	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents) {
		System.out.println("ISALIVE EXP");
		Unit hilly;
		if (this.expressionVariableUnit != null)
			hilly = ((IUnitExpression) this.expressionVariableUnit).evaluateUnit(taskComponents);
		else
			hilly = this.expressionUnit.evaluateUnit(taskComponents);
		return hilly.isAlive();
	}

	@Override
	public boolean containSelectedCube() {
		if (this.expressionVariableUnit != null)
			return ((IUnitExpression) this.expressionVariableUnit).containSelectedCube();
		return this.expressionUnit.containSelectedCube();
	}

}
