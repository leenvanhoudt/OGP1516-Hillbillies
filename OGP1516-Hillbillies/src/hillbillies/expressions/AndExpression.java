package hillbillies.expressions;


import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.TaskComponents;

public class AndExpression extends BooleanExpression {

	private MyExpression expressionLeft;
	private MyExpression expressionRight;

	public AndExpression(MyExpression left, MyExpression right){
		this.expressionLeft = left;
		this.expressionRight = right;
	}

	@Override
	public Boolean evaluate(TaskComponents taskComponents) {
		System.out.println("AND EXP");
		// TODO Auto-generated method stub
		if (!(this.expressionLeft instanceof BooleanExpression) || !(this.expressionRight instanceof
				BooleanExpression)){
			throw new IllegalStateException();
		}
		BooleanExpression left = (BooleanExpression) this.expressionLeft;
		BooleanExpression right = (BooleanExpression) this.expressionRight;
		
		return left.evaluate(taskComponents) && 
				right.evaluate(taskComponents);
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionLeft.containSelectedCube() || this.expressionRight.containSelectedCube();
	}

}
