package hillbillies.expressions;

import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class ReadVariableExpression extends MyExpression {

	private SourceLocation sourceLocation;
	private String varName;

	public ReadVariableExpression(String variableName, SourceLocation sourceLocation){
		this.varName = variableName;
		this.sourceLocation = sourceLocation;
	}

	@Override
	public Object evaluate(World world, Unit unit,int[] selectedCube, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
