package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public class ThisExpression extends UnitExpression {
	

	@Override
	public Unit evaluate(TaskComponents taskComponents) {
		System.out.println("THIS EXP");
		return taskComponents.getUnit();
	}

	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
