package hillbillies.statements;

import java.util.List;

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
	public void execute(World world, Unit unit, List<int[]> selectedCubes, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		
	}

}
