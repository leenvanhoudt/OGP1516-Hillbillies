package hillbillies.expressions;


import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class NextToPositionExpression extends CubePositionExpression{

	
	private MyExpression expressionPosition;
	private SourceLocation sourceLocation;

	public NextToPositionExpression(MyExpression position, SourceLocation sourceLocation){
		this.expressionPosition = position;
		this.sourceLocation = sourceLocation;
	}

	@Override
	public int[] evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

}
