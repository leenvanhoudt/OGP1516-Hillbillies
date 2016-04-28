package hillbillies.expressions;

import java.util.List;

import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class IsSolidExpression extends BooleanExpression {
	
	private MyExpression expressionPosition;
	private SourceLocation sourceLocation;

	public IsSolidExpression(MyExpression position, SourceLocation sourceLocation){
		this.expressionPosition = position;
		this.sourceLocation = sourceLocation;
	}

	@Override
	public Boolean evaluate(World world, Unit unit, int[] selectedCubes, SourceLocation sourceLocation) throws ClassCastException {
		// TODO Auto-generated method stub
		if (this.expressionPosition instanceof CubePositionExpression){
			CubePositionExpression pos = (CubePositionExpression) this.expressionPosition;
			int[] position = pos.getPosition(selectedCubes);
			return world.isPassable(position[0], position[1], position[2]);
		}else{
			throw new ClassCastException();
		}
	}


}
