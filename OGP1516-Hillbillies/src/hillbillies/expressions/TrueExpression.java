package hillbillies.expressions;

import java.util.List;

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
	public Boolean evaluate(World world, Unit unit, int[] selectedCubes, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return true;
	}

}
