package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

@SuppressWarnings("hiding")
public class PositionOfExpression<E extends UnitExpression, ReadVariableExpression> 
	extends CubePositionExpression {


	private UnitExpression expressionUnit;
	private ReadVariableExpression expressionVariableUnit;

	public PositionOfExpression(UnitExpression unit){
		this.expressionUnit = unit;
	}
	
	public PositionOfExpression(ReadVariableExpression unit){
		this.expressionVariableUnit = unit;
	}
	
	@Override
	public int[] evaluatePosition(TaskComponents taskComponents) {
		Unit hilly;
		if (this.expressionVariableUnit != null){
			hilly = ((IUnitExpression) this.expressionVariableUnit).evaluateUnit(taskComponents);
		} else{
			hilly = this.expressionUnit.evaluateUnit(taskComponents);
		}
		return new int[]{hilly.getCubeCoordinate()[0], hilly.getCubeCoordinate()[1],
				hilly.getCubeCoordinate()[2]};
	}

	@Override
	public boolean containSelectedCube() {
		if (this.expressionVariableUnit != null)
			return ((IUnitExpression) this.expressionVariableUnit).containSelectedCube();
		return this.expressionUnit.containSelectedCube();
	}

}
