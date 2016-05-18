package hillbillies.expressions;

import hillbillies.model.DijkstraPathFinding;
import hillbillies.scheduler.TaskComponents;

public class WorkshopPositionExpression extends CubePositionExpression{

	private DijkstraPathFinding dijkstra = new DijkstraPathFinding();

	@Override
	public int[] evaluatePosition(TaskComponents taskComponents)throws Error {
		System.out.println("WORKSHOP EXP");
		try{
			this.dijkstra.setUnit(taskComponents.getUnit());
			return this.dijkstra.Dijkstra(2);
		} catch(Throwable e){
			System.out.println("error finding workshop");
			throw new Error("no workshop found");
		}
	}

	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
