package hillbillies.expressions;

import hillbillies.model.PathFinding;
import hillbillies.scheduler.TaskComponents;

public class WorkshopPositionExpression extends CubePositionExpression{

	private PathFinding pathfinding;

	@Override
	public int[] evaluate(TaskComponents taskComponents) {
		System.out.println("WORKSHOP EXP");
		this.pathfinding.setUnit(taskComponents.getUnit());
		return this.pathfinding.Dijkstra(3);
	}

	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
