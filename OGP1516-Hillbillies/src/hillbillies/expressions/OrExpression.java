package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class OrExpression<E extends BooleanExpression> extends BooleanExpression{

	private BooleanExpression expressionLeft;
	private BooleanExpression expressionRight;

	public OrExpression(BooleanExpression left, BooleanExpression right){
		this.expressionLeft = left;
		this.expressionRight = right;
	}
	
	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents) {
		System.out.println("OR EXP");
		return this.expressionLeft.evaluateBoolean(taskComponents) || 
				this.expressionRight.evaluateBoolean(taskComponents);
	}

	@Override
	public boolean containSelectedCube() {
		return this.expressionLeft.containSelectedCube() || this.expressionRight.containSelectedCube();
	}

}
