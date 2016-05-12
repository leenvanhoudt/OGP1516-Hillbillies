package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public class IsEnemyExpression<E extends UnitExpression> extends BooleanExpression{

	private UnitExpression expressionUnit;

	public IsEnemyExpression(UnitExpression unit){
		this.expressionUnit = unit;
	}
	
	@Override
	public Boolean evaluate(TaskComponents taskComponents) {
		System.out.println("ISENEMY EXP");
		// TODO Auto-generated method stub
		Unit hilly = this.expressionUnit.evaluate(taskComponents);
		return hilly.getFaction() != taskComponents.getUnit().getFaction();		
	}

	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionUnit.containSelectedCube();
	}

}
