package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public class ThisExpression extends UnitExpression {
	

	@Override
	public Unit evaluateUnit(TaskComponents taskComponents) {
		return taskComponents.getUnit();
	}

	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
