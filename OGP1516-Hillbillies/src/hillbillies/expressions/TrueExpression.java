package hillbillies.expressions;

import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class TrueExpression extends BooleanExpression {

	private SourceLocation sourceLocation;

	public TrueExpression(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}

	@Override
	public Boolean evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return true;
	}

}
