package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class FalseExpression extends BooleanExpression{
	
	@Override
	public Boolean evaluate(TaskComponents taskComponents) {
		System.out.println("FALSE EXP");
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return false;
	}

}
