package hillbillies.model;


import hillbillies.part3.programs.SourceLocation;

public abstract class MyStatement {
	
	public abstract void execute(World world, Unit unit, int[] selectedCube);

}
