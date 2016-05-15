package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public class PositionOfExpression<E extends UnitExpression> extends CubePositionExpression {


	private UnitExpression expressionUnit;

	public PositionOfExpression(UnitExpression unit){
		this.expressionUnit = unit;
	}
	
	@Override
	public int[] evaluate(TaskComponents taskComponents) {
		System.out.println("POSITIONOF EXP");
		Unit hilly = this.expressionUnit.evaluate(taskComponents);
		return new int[]{hilly.getCubeCoordinate()[0], hilly.getCubeCoordinate()[1],
				hilly.getCubeCoordinate()[2]};
	}

	@Override
	public boolean containSelectedCube() {
		return this.expressionUnit.containSelectedCube();
	}

}
