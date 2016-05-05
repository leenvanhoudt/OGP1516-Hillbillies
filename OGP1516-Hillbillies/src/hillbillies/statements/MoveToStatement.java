package hillbillies.statements;

import hillbillies.expressions.CubePositionExpression;
import hillbillies.model.MyExpression;
import hillbillies.model.MyStatement;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class MoveToStatement extends MyStatement {

	private MyExpression expressionPosition;
	private SourceLocation sourceLocation;

	public MoveToStatement(MyExpression position, SourceLocation sourceLocation){
		this.expressionPosition = position;
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public void execute(World world, Unit unit, int[] selectedCube,SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		if (!(this.expressionPosition instanceof CubePositionExpression)){
			throw new Error("no position expression");
		}
		CubePositionExpression pos = (CubePositionExpression) this.expressionPosition;
		int[] cube = pos.evaluate(world, unit, selectedCube, sourceLocation);
		unit.moveTo(cube);
	}

}
