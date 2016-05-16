package hillbillies.expressions;

import java.util.Set;

import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public class FriendExpression extends UnitExpression {
	
	@Override
	public Unit evaluateUnit(TaskComponents taskComponents) throws Error{
		System.out.println("FRIEND EXP");
		// TODO zoek dichtst bijzijnde
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
		return false;
	}

}
