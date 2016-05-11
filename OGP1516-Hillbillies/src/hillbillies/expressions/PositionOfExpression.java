package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.TaskComponents;

public class PositionOfExpression extends CubePositionExpression {


	private MyExpression expressionUnit;

	public PositionOfExpression(MyExpression unit){
		this.expressionUnit = unit;
	}
	
	@Override
	public int[] evaluate(TaskComponents taskComponents) {
		System.out.println("POSITIONOF EXP");
		// TODO Auto-generated method stub
		if (this.expressionUnit instanceof UnitExpression){
			UnitExpression hillbilly = (UnitExpression) this.expressionUnit;
			Unit hilly = hillbilly.evaluate(taskComponents);
			int[] position = {hilly.getCubeCoordinate()[0], hilly.getCubeCoordinate()[1], hilly.getCubeCoordinate()[2]};
			return position;
		}else{
			throw new Error("No UnitExpression");
		}
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionUnit.containSelectedCube();
	}

}
