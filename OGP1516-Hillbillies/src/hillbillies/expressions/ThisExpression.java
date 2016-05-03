package hillbillies.expressions;

import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class ThisExpression extends UnitExpression {
	
	private SourceLocation sourceLocation;

	public ThisExpression(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}

	@Override
	public Unit evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return unit;
	}

}
