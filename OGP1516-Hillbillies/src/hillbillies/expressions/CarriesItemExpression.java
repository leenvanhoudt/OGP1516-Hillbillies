package hillbillies.expressions;


import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public class CarriesItemExpression<E extends UnitExpression> extends BooleanExpression {

	private UnitExpression expressionUnit;

	public CarriesItemExpression(UnitExpression unit){
		this.expressionUnit = unit;
	}
	
	@Override
	public Boolean evaluate(TaskComponents taskComponents ) {
		System.out.println("Carriesitem EXP");
		Unit hilly = this.expressionUnit.evaluate(taskComponents);
		return hilly.isCarryingBoulder() || hilly.isCarryingLog();
	}

	@Override
	public boolean containSelectedCube() {
		return this.expressionUnit.containSelectedCube();
	}

}
