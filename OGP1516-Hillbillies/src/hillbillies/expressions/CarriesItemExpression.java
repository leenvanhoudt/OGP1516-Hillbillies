package hillbillies.expressions;


import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public class CarriesItemExpression<E extends UnitExpression> extends BooleanExpression {

	private UnitExpression expressionUnit;

	public CarriesItemExpression(UnitExpression unit){
		System.out.println("carries item constructor");
		this.expressionUnit = unit;
	}
	
	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents ) {
		System.out.println("Carriesitem EXP");
		Unit hilly = this.expressionUnit.evaluateUnit(taskComponents);
		return hilly.isCarryingBoulder() || hilly.isCarryingLog();
	}

	@Override
	public boolean containSelectedCube() {
		return this.expressionUnit.containSelectedCube();
	}

}
