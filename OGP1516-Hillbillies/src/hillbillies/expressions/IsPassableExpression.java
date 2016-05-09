package hillbillies.expressions;

import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class IsPassableExpression extends BooleanExpression{


	private MyExpression expressionPosition;
	private SourceLocation sourceLocation;

	public IsPassableExpression(MyExpression position, SourceLocation sourceLocation){
		this.expressionPosition = position;
		this.sourceLocation = sourceLocation;
		
	}
	
	@Override
	public Boolean evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation) {
		System.out.println("ISPASSABLE EXP");
		// TODO Auto-generated method stub
		if(this.expressionPosition instanceof CubePositionExpression){
			CubePositionExpression pos = (CubePositionExpression) this.expressionPosition;
			int[] cube = pos.evaluate(world, unit, selectedCube, sourceLocation);
			return world.isPassable(cube[0], cube[1], cube[2]);
		}
		else{
			throw new Error("No CubePositionExpression");
		}
	}

}
