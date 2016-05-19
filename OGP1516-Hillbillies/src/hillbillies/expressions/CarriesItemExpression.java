package hillbillies.expressions;


import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

@SuppressWarnings("hiding")
public class CarriesItemExpression<E extends UnitExpression,ReadVariableExpression> 
	extends BooleanExpression {

	private UnitExpression expressionUnit;
	private ReadVariableExpression expressionVariableUnit;

	public CarriesItemExpression(UnitExpression unit){
		this.expressionUnit = unit;
	}
	
	public CarriesItemExpression(ReadVariableExpression unit){
		this.expressionVariableUnit = unit;
	}
	
	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents ) {
		Unit hilly;
		if (this.expressionVariableUnit != null)
			hilly = ((IUnitExpression) this.expressionVariableUnit).evaluateUnit(taskComponents);
		else
			hilly = this.expressionUnit.evaluateUnit(taskComponents);
		return hilly.isCarryingBoulder() || hilly.isCarryingLog();
	}

	@Override
	public boolean containSelectedCube() {
		if (this.expressionVariableUnit != null)
			return ((IUnitExpression) this.expressionVariableUnit).containSelectedCube();
		return this.expressionUnit.containSelectedCube();
	}

}
