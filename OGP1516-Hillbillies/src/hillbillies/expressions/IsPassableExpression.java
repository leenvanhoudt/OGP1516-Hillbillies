package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.TaskComponents;

public class IsPassableExpression extends BooleanExpression{


	private MyExpression expressionPosition;

	public IsPassableExpression(MyExpression position){
		this.expressionPosition = position;		
	}
	
	@Override
	public Boolean evaluate(TaskComponents taskComponents) {
		System.out.println("ISPASSABLE EXP");
		// TODO Auto-generated method stub
		if(this.expressionPosition instanceof CubePositionExpression){
			CubePositionExpression pos = (CubePositionExpression) this.expressionPosition;
			int[] cube = pos.evaluate(taskComponents);
			return taskComponents.getWorld().isPassable(cube[0], cube[1], cube[2]);
		}
		else{
			throw new Error("No CubePositionExpression");
		}
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionPosition.containSelectedCube();
	}

}
