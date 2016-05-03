package hillbillies.expressions;

import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class IsEnemyExpression extends BooleanExpression{

	private MyExpression expressionUnit;
	private SourceLocation sourceLocation;

	public IsEnemyExpression(MyExpression unit, SourceLocation sourceLocation){
		this.expressionUnit = unit;
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public Boolean evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		if (this.expressionUnit instanceof UnitExpression){
			UnitExpression hillbilly = (UnitExpression) this.expressionUnit;
			Unit hilly = hillbilly.evaluate(world, unit, selectedCube, sourceLocation);
			return hilly.getFaction() != unit.getFaction();
		}
		else{
			throw new Error("No UnitExpression");
		}
		
	}

}
