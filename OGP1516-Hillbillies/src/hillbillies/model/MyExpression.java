package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class MyExpression {

	public abstract Object evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation);
	
}
