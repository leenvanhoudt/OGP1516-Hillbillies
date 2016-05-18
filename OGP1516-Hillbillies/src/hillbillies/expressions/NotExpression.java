package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

@SuppressWarnings("hiding")
public class NotExpression<E extends BooleanExpression, ReadVariableExpression> 
	extends BooleanExpression{

	private BooleanExpression expressionExpression;
	private ReadVariableExpression expressionVariableExpression;

	public NotExpression(BooleanExpression expression){
		this.expressionExpression = expression;
	}
	
	public NotExpression(ReadVariableExpression expression){
		this.expressionVariableExpression = expression;
	}
	
	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents) {
		System.out.println("NOT EXP");
		if (this.expressionVariableExpression != null)
			return !((IBooleanExpression) this.expressionVariableExpression)
					.evaluateBoolean(taskComponents);
		return !this.expressionExpression.evaluateBoolean(taskComponents);
	}

	@Override
	public boolean containSelectedCube() {
		if (this.expressionVariableExpression != null)
			return ((BooleanExpression) this.expressionVariableExpression)
					.containSelectedCube();
		return this.expressionExpression.containSelectedCube();
	}

}
