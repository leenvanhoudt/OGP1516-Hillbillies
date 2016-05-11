package hillbillies.statements;

import hillbillies.expressions.CubePositionExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class WorkStatement extends MyStatement{

	private MyExpression expressionPosition;

	public WorkStatement(MyExpression position){
		this.expressionPosition = position;
	}
	
	@Override
	public void execute(TaskComponents taskComponents) {
		System.out.println("WORK STATEMENT");
		// TODO Auto-generated method stub
		if (!(this.expressionPosition instanceof CubePositionExpression))
			throw new Error("no position expression");
		CubePositionExpression pos = (CubePositionExpression) this.expressionPosition;
		int[] position = pos.evaluate(taskComponents);
		taskComponents.getUnit().workAt(position[0], position[1], position[2]);
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionPosition.containSelectedCube();
	}

}
