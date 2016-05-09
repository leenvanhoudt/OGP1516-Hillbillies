package hillbillies.statements;


import hillbillies.expressions.UnitExpression;
import hillbillies.model.MyExpression;
import hillbillies.model.MyStatement;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class FollowStatement extends MyStatement {
	
	private MyExpression expressionUnit;
	private SourceLocation sourceLocation;

	public FollowStatement(MyExpression unit, SourceLocation sourceLocation){
		this.expressionUnit = unit;
		this.sourceLocation = sourceLocation;
	}

	@Override
	public void execute(World world, Unit unit,int[] selectedCube) {
		System.out.println("FOLLOW STATEMENT");
		// TODO Auto-generated method stub
		if (!(this.expressionUnit instanceof UnitExpression))
			throw new Error("no unit expression");
		UnitExpression hillbilly = (UnitExpression) this.expressionUnit;
		Unit followed = hillbilly.evaluate(world, unit, selectedCube, sourceLocation);
		unit.follow(followed);
	}

}
