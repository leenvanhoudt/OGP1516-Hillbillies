package hillbillies.expressions;


import hillbillies.model.DijkstraPathFinding;
import hillbillies.model.PathFinding;
import hillbillies.scheduler.TaskComponents;

public class LogPositionExpression extends CubePositionExpression {

	private DijkstraPathFinding dijkstra;

	@Override
	public int[] evaluatePosition(TaskComponents taskComponents) {
		System.out.println("LOG EXP");
		this.dijkstra.setUnit(taskComponents.getUnit());
		return this.dijkstra.Dijkstra(1);
	}


	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
