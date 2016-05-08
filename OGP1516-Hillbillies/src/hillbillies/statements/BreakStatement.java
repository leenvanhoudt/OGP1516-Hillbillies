package hillbillies.statements;


import hillbillies.model.MyStatement;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class BreakStatement extends MyStatement{

	private SourceLocation sourceLocation;

	public BreakStatement(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public void execute(World world, Unit unit, int[] selectedCube) {
		// TODO Auto-generated method stub
		
	}

}
