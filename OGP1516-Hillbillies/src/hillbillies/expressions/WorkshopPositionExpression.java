package hillbillies.expressions;

import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class WorkshopPositionExpression extends CubePositionExpression{

	private SourceLocation sourceLocation;

	public WorkshopPositionExpression(){
		this.sourceLocation = sourceLocation;
	}

	@Override
	public int[] evaluate(World world, Unit unit,int[] selectedCube, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

}
