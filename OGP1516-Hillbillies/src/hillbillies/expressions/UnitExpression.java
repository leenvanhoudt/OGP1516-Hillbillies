package hillbillies.expressions;

import java.util.List;

import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public abstract class UnitExpression extends MyExpression{
	
	public abstract Unit evaluate(World world, Unit unit, int[] selectedCubes, SourceLocation sourceLocation);
	
}
