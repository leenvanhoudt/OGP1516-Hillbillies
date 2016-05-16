package hillbillies.expressions;

import hillbillies.model.DijkstraPathFinding;
import hillbillies.model.PathFinding;
import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public class AnyExpression extends UnitExpression{
	
	private DijkstraPathFinding dijkstra;
	
	@Override
	public Unit evaluateUnit(TaskComponents taskComponents) throws Error {
		//TODO return positie of unit zelf? zoek uit voor alle dijksta
		this.dijkstra.setUnit(taskComponents.getUnit());
		int[] cube = this.dijkstra.Dijkstra(2);
		return taskComponents.getWorld().getCubeOtherUnit(cube[0], cube[1], cube[2],
				taskComponents.getUnit());
	}

	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
