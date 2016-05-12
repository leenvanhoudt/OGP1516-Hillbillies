package hillbillies.expressions;


import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public class IsAliveExpression<E extends UnitExpression> extends BooleanExpression{

	private UnitExpression expressionUnit;

	public IsAliveExpression(UnitExpression unit){
		this.expressionUnit = unit;
	}

	@Override
	public Boolean evaluate(TaskComponents taskComponents) {
		System.out.println("ISALIVE EXP");
		// TODO Auto-generated method stub
		Unit hilly = this.expressionUnit.evaluate(taskComponents);
		return hilly.isAlive();
	}

	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionUnit.containSelectedCube();
	}

}
