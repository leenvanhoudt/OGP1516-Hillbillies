package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class AndExpression<E extends BooleanExpression> extends BooleanExpression {

	private BooleanExpression expressionLeft;
	private BooleanExpression expressionRight;

	public AndExpression(BooleanExpression left, BooleanExpression right){
		this.expressionLeft = left;
		this.expressionRight = right;
	}

	@Override
	public Boolean evaluate(TaskComponents taskComponents) {
		System.out.println("AND EXP");
		// TODO Auto-generated method stub		
		return this.expressionLeft.evaluate(taskComponents) && 
				this.expressionRight.evaluate(taskComponents);
	}

	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionLeft.containSelectedCube() || 
				this.expressionRight.containSelectedCube();
	}

}
