package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.TaskComponents;

public class IsSolidExpression extends BooleanExpression {
	
	private MyExpression expressionPosition;

	public IsSolidExpression(MyExpression position){
		this.expressionPosition = position;
	}

	@Override
	public Boolean evaluate(TaskComponents taskComponents) throws ClassCastException {
		System.out.println("ISSOLID EXP");
		// TODO Auto-generated method stub
		if (this.expressionPosition instanceof CubePositionExpression){
			CubePositionExpression pos = (CubePositionExpression) this.expressionPosition;
			int[] position = pos.evaluate(taskComponents);
			return !taskComponents.getWorld().isPassable(position[0], position[1], position[2]);
		}else{
			throw new ClassCastException();
		}
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionPosition.containSelectedCube();
	}


}
