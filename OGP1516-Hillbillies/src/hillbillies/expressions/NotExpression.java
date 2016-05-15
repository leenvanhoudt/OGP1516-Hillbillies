package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class NotExpression<E extends BooleanExpression> extends BooleanExpression{

	private BooleanExpression expressionExpression;

	public NotExpression(BooleanExpression expression){
		this.expressionExpression = expression;
	}
	
	@Override
	public Boolean evaluate(TaskComponents taskComponents) {
		System.out.println("NOT EXP");
		return !this.expressionExpression.evaluate(taskComponents);
	}

	@Override
	public boolean containSelectedCube() {
		return this.expressionExpression.containSelectedCube();
	}

}
