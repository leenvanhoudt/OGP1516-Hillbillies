package hillbillies.expressions;

import java.util.Set;

import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public class EnemyExpression extends UnitExpression {

	
	@Override
	public Unit evaluate(TaskComponents taskComponents) throws Error{
		System.out.println("ENEMY EXP");
		// TODO Auto-generated method stub
		Set<Unit> allUnits = taskComponents.getWorld().getUnits();
		for (Unit randomUnit: allUnits){
			if (randomUnit.getFaction() != taskComponents.getUnit().getFaction()){
				return randomUnit;
			}
		}
		//TODO check exception
		throw new Error("no enemy found");
	}

	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return false;
	}

}
