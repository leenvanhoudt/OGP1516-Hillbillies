package hillbillies.model;

import java.util.List;

import hillbillies.part3.programs.SourceLocation;

public abstract class MyStatement {
	
	public abstract void execute(World world, Unit unit,List<int[]> selectedCubes, SourceLocation sourceLocation);

}
