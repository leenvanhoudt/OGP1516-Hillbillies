package hillbillies.statements;


import hillbillies.model.MyExpression;
import hillbillies.model.MyStatement;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class AssignmentStatement extends MyStatement {
	
	private String variableName;
	private MyExpression expressionValue;
	private SourceLocation sourceLocation;

	public AssignmentStatement(String variableName, MyExpression value, SourceLocation sourceLocation){
		this.variableName = variableName;
		this.expressionValue = value;
		this.sourceLocation = sourceLocation;
	}


	@Override
	public void execute(World world, Unit unit, int[] selectedCube) {
		// TODO Auto-generated method stub
		
	}

}
