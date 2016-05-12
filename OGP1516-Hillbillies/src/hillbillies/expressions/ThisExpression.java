package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public class ThisExpression extends UnitExpression {
	

	@Override
	public Unit evaluate(TaskComponents taskComponents) {
		System.out.println("THIS EXP");
		// TODO Auto-generated method stub
		return taskComponents.getUnit();
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return false;
	}

}
