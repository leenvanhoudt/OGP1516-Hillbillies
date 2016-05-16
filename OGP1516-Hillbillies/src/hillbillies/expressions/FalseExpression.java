package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class FalseExpression extends BooleanExpression{
	
	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents) {
		System.out.println("FALSE EXP");
		return false;
	}

	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
