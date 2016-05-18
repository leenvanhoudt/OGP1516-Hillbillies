package hillbillies.expressions;

import hillbillies.scheduler.TaskComponents;

public class OrExpression<E extends BooleanExpression,ReadVariableExpression> extends BooleanExpression{

	private BooleanExpression expressionLeft;
	private BooleanExpression expressionRight;
	private ReadVariableExpression expressionVariableLeft;
	private ReadVariableExpression expressionVariableRight;

	public OrExpression(BooleanExpression left, BooleanExpression right){
		this.expressionLeft = left;
		this.expressionRight = right;
	}
	public OrExpression(ReadVariableExpression left, ReadVariableExpression right){
		this.expressionVariableLeft = left;
		this.expressionVariableRight = right;
	}
	
	public OrExpression(ReadVariableExpression left, BooleanExpression right){
		this.expressionVariableLeft = left;
		this.expressionRight = right;
	}
	
	public OrExpression(BooleanExpression left, ReadVariableExpression right){
		this.expressionLeft = left;
		this.expressionVariableRight = right;
	}
	
	@Override
	public Boolean evaluateBoolean(TaskComponents taskComponents) {
		System.out.println("OR EXP");
		boolean left;
		boolean right;
		if (this.expressionVariableLeft != null)
			left = ((IBooleanExpression) this.expressionVariableLeft).evaluateBoolean(taskComponents);
		else
			left = this.expressionLeft.evaluateBoolean(taskComponents);
		if (this.expressionVariableRight != null)
			right = ((IBooleanExpression) this.expressionVariableRight).evaluateBoolean(taskComponents);
		else
			right = this.expressionRight.evaluateBoolean(taskComponents);
		
		return left || right;
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
