package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class TrueExpression extends BooleanExpression {

	@Override
	public Boolean evaluate(TaskComponents taskComponents) {
		System.out.println("TRUE EXP");
		return true;
	}

	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
