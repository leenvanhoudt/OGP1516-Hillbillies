package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class AndExpression<E extends BooleanExpression> extends BooleanExpression implements
	IBooleanExpression{

	private BooleanExpression expressionLeft;
	private BooleanExpression expressionRight;

	public AndExpression(BooleanExpression left, BooleanExpression right){
		this.expressionLeft = left;
		this.expressionRight = right;
	}

	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents) {
		System.out.println("AND EXP");
		return this.expressionLeft.evaluateBoolean(taskComponents) && 
				this.expressionRight.evaluateBoolean(taskComponents);
	}

	@Override
	public boolean containSelectedCube() {
		return this.expressionLeft.containSelectedCube() || 
				this.expressionRight.containSelectedCube();
	}

}
