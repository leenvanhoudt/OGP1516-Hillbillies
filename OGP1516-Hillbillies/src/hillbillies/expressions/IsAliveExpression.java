package hillbillies.expressions;


import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class IsAliveExpression extends BooleanExpression{

	private MyExpression expressionUnit;
	private SourceLocation sourceLocation;

	public IsAliveExpression(MyExpression unit, SourceLocation sourceLocation){
		this.expressionUnit = unit;
		this.sourceLocation = sourceLocation;
	}

	@Override
	public Boolean evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation) {
		System.out.println("ISALIVE EXP");
		// TODO Auto-generated method stub
		if (this.expressionUnit instanceof UnitExpression){
			UnitExpression hillbilly = (UnitExpression) this.expressionUnit;
			Unit hilly = hillbilly.evaluate(world, unit, selectedCube, sourceLocation);
			return hilly.isAlive();
		}
		else{
			throw new Error("No UnitExpression");
		}
		
	}

}
