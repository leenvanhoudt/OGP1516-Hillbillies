package hillbillies.expressions;


import hillbillies.model.PathFinding;
import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public class EnemyExpression extends UnitExpression {

	private PathFinding pathFinding;
	
	@Override
	public Unit evaluate(TaskComponents taskComponents) throws Error{
		System.out.println("ENEMY EXP");
		//TODO dit zoekt dichtstbijzijnde any unit, not enemy
		pathFinding.setUnit(taskComponents.getUnit());
		int[] cube = pathFinding.Dijkstra(2);
		return taskComponents.getWorld().getCubeOtherUnit(cube[0], cube[1], cube[2],
				taskComponents.getUnit());
	}

	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
