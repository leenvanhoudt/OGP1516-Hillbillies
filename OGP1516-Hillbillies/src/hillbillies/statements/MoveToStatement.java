package hillbillies.statements;

import hillbillies.expressions.CubePositionExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class MoveToStatement extends MyStatement {

	private MyExpression expressionPosition;

	public MoveToStatement(MyExpression position){
		this.expressionPosition = position;
	}
	
	@Override
	public void execute(TaskComponents taskComponents) throws Error{
		System.out.println("MOVE TO STATEMENT");
		// TODO Auto-generated method stub
		if (!(this.expressionPosition instanceof CubePositionExpression)){
			throw new Error("no position expression");
		}
		CubePositionExpression pos = (CubePositionExpression) this.expressionPosition;
		int[] cube = pos.evaluate(taskComponents);
		try{
			taskComponents.getUnit().moveTo(cube);
		}catch(Throwable e){
			throw new Error("no path found");
		}
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionPosition.containSelectedCube();
	}

}
