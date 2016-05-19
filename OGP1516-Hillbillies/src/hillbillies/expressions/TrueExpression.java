package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class TrueExpression extends BooleanExpression {

	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents) {
		return true;
	}

	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
