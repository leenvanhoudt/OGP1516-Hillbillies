package hillbillies.expressions;


import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class AndExpression extends BooleanExpression {

	private MyExpression expressionLeft;
	private MyExpression expressionRight;
	private SourceLocation sourceLocation;

	public AndExpression(MyExpression left, MyExpression right, SourceLocation sourceLocation){
		this.expressionLeft = left;
		this.expressionRight = right;
		this.sourceLocation = sourceLocation;
	}

	@Override
	public Boolean evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation) {
		System.out.println("AND EXP");
		// TODO Auto-generated method stub
		if (!(this.expressionLeft instanceof BooleanExpression) || !(this.expressionRight instanceof
				BooleanExpression)){
			throw new IllegalStateException();
		}
		BooleanExpression left = (BooleanExpression) this.expressionLeft;
		BooleanExpression right = (BooleanExpression) this.expressionRight;
		
		return left.evaluate(world, unit, selectedCube, sourceLocation) && 
				right.evaluate(world, unit, selectedCube, sourceLocation);
	}

}
