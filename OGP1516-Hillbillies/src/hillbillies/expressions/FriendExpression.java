package hillbillies.expressions;

import java.util.Set;

import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public class FriendExpression extends UnitExpression {
	
	@Override
	public Unit evaluate(TaskComponents taskComponents) throws Error{
		System.out.println("FRIEND EXP");
		// TODO Auto-generated method stub
		Set<Unit> allUnits = taskComponents.getWorld().getUnits();
		for (Unit randomUnit: allUnits){
			if (randomUnit.getFaction() == taskComponents.getUnit().getFaction()){
				return randomUnit;
			}
		}
		throw new Error("no friend found");
	}

	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return false;
	}

}
