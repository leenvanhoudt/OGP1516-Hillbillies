package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.TaskComponents;

public class NotExpression extends BooleanExpression{

	private MyExpression expressionExpression;

	public NotExpression(MyExpression expression){
		this.expressionExpression = expression;
	}
	
	@Override
	public Boolean evaluate(TaskComponents taskComponents) {
		System.out.println("NOT EXP");
		// TODO Auto-generated method stub
		if (!(this.expressionExpression instanceof BooleanExpression)){
			throw new IllegalStateException();
		}
		BooleanExpression exp = (BooleanExpression) this.expressionExpression;
		
		return !exp.evaluate(taskComponents);
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionExpression.containSelectedCube();
	}

}
