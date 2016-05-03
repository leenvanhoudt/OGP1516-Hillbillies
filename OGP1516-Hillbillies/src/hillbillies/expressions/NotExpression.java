package hillbillies.expressions;

import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class NotExpression extends BooleanExpression{

	private MyExpression expressionExpression;
	private SourceLocation sourceLocation;

	public NotExpression(MyExpression expression, SourceLocation sourceLocation){
		this.expressionExpression = expression;
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public Boolean evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		if (!(this.expressionExpression instanceof BooleanExpression)){
			throw new IllegalStateException();
		}
		BooleanExpression exp = (BooleanExpression) this.expressionExpression;
		
		return !exp.evaluate(world, unit, selectedCube, sourceLocation);
	}

}
