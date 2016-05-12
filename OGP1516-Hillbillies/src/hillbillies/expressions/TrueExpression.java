package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class TrueExpression extends BooleanExpression {

	@Override
	public Boolean evaluate(TaskComponents taskComponents) {
		System.out.println("TRUE EXP");
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return false;
	}

}
