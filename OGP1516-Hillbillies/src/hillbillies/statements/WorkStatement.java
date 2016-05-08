package hillbillies.statements;

import hillbillies.expressions.CubePositionExpression;
import hillbillies.model.MyExpression;
import hillbillies.model.MyStatement;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class WorkStatement extends MyStatement{

	private MyExpression expressionPosition;
	private SourceLocation sourceLocation;

	public WorkStatement(MyExpression position, SourceLocation sourceLocation){
		this.expressionPosition = position;
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public void execute(World world, Unit unit, int[] selectedCube) {
		// TODO Auto-generated method stub
		if (!(this.expressionPosition instanceof CubePositionExpression))
			throw new Error("no position expression");
		CubePositionExpression pos = (CubePositionExpression) this.expressionPosition;
		int[] position = pos.evaluate(world, unit, selectedCube, this.sourceLocation);
		unit.workAt(position[0], position[1], position[2]);
	}

}
