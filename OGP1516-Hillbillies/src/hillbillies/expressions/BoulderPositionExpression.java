package hillbillies.expressions;


import hillbillies.model.DijkstraPathFinding;
import hillbillies.model.PathFinding;
import hillbillies.scheduler.TaskComponents;

public class BoulderPositionExpression extends CubePositionExpression {

	private DijkstraPathFinding dijkstra;

	@Override
	public int[] evaluatePosition(TaskComponents taskComponents) {
		System.out.println("BOULDER EXP");
		this.dijkstra.setUnit(taskComponents.getUnit());
		return this.dijkstra.Dijkstra(0);
	}


	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
