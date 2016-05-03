package hillbillies.expressions;

import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class PositionOfExpression extends CubePositionExpression {


	private MyExpression expressionUnit;
	private SourceLocation sourceLocation;

	public PositionOfExpression(MyExpression unit, SourceLocation sourceLocation){
		this.expressionUnit = unit;
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public int[] evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		if (this.expressionUnit instanceof UnitExpression){
			UnitExpression hillbilly = (UnitExpression) this.expressionUnit;
			Unit hilly = hillbilly.evaluate(world, unit, selectedCube, sourceLocation);
			int[] position = {hilly.getCubeCoordinate()[0], hilly.getCubeCoordinate()[1], hilly.getCubeCoordinate()[2]};
			return position;
		}else{
			throw new Error("No UnitExpression");
		}
	}

}
