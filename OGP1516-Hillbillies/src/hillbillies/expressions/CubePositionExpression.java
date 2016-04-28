package hillbillies.expressions;

import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public abstract class CubePositionExpression extends MyExpression {

	public abstract int[] evaluate(World world, Unit unit, int[] selectedCubes, SourceLocation sourceLocation);

	public int[] getPosition(int[] selectedCubes){
		return selectedCubes;
	}
}
