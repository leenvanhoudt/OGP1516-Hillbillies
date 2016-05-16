package hillbillies.expressions;

import hillbillies.model.DijkstraPathFinding;
import hillbillies.scheduler.TaskComponents;

public class WorkshopPositionExpression extends CubePositionExpression{

	private DijkstraPathFinding dijkstra;

	@Override
	public int[] evaluatePosition(TaskComponents taskComponents) {
		System.out.println("WORKSHOP EXP");
		this.dijkstra.setUnit(taskComponents.getUnit());
		return this.dijkstra.Dijkstra(3);
	}

	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
