package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

@SuppressWarnings("hiding")
public class AndExpression<E extends BooleanExpression,ReadVariableExpression> 
	extends BooleanExpression{

	private BooleanExpression expressionLeft;
	private BooleanExpression expressionRight;
	private ReadVariableExpression expressionVariableLeft;
	private ReadVariableExpression expressionVariableRight;
	
	public AndExpression(BooleanExpression left, BooleanExpression right){
		this.expressionLeft = left;
		this.expressionRight = right;
	}
	
	public AndExpression(ReadVariableExpression left, ReadVariableExpression right){
		this.expressionVariableLeft = left;
		this.expressionVariableRight = right;
	}
	
	public AndExpression(ReadVariableExpression left, BooleanExpression right){
		this.expressionVariableLeft = left;
		this.expressionRight = right;
	}
	
	public AndExpression(BooleanExpression left, ReadVariableExpression right){
		this.expressionLeft = left;
		this.expressionVariableRight = right;
	}

	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents) {
		boolean left;
		boolean right;
		if (this.expressionVariableLeft != null)
			left = ((IBooleanExpression) this.expressionVariableLeft)
				.evaluateBoolean(taskComponents);
		else
			left = this.expressionLeft.evaluateBoolean(taskComponents);
		if (this.expressionVariableRight != null)
			right = ((IBooleanExpression) this.expressionVariableRight)
				.evaluateBoolean(taskComponents);
		else
			right = this.expressionRight.evaluateBoolean(taskComponents);
		
		return left && right;
	}

	@Override
	public boolean containSelectedCube() {
		boolean left;
		boolean right;
		if (this.expressionVariableLeft != null)
			left = ((IBooleanExpression) this.expressionVariableLeft).containSelectedCube();
		else
			left = this.expressionLeft.containSelectedCube();
		if (this.expressionVariableRight != null)
			right = ((IBooleanExpression) this.expressionVariableRight).containSelectedCube();
		else
			right = this.expressionRight.containSelectedCube();
		
		return left || right;
	}

}
