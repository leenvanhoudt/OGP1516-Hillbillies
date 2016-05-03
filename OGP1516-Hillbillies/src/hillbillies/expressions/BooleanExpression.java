package hillbillies.expressions;


import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public abstract class BooleanExpression extends MyExpression{

	public abstract Boolean evaluate(World world, Unit unit, int[] selectedCube,SourceLocation sourceLocation);

}
