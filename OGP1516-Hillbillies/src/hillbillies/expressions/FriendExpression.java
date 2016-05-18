package hillbillies.expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hillbillies.model.DijkstraPathFinding;
import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public class FriendExpression extends UnitExpression {
	
	DijkstraPathFinding dijkstra = new DijkstraPathFinding();
	
	@Override
	public Unit evaluateUnit(TaskComponents taskComponents) throws Error{
		this.dijkstra.setUnit(taskComponents.getUnit());
		int[] cube = this.dijkstra.Dijkstra(4);
		ArrayList<Unit> possibleEnemies = taskComponents.getWorld().getCubeOtherUnit(cube[0], cube[1], cube[2],
				taskComponents.getUnit());
		List<Unit> enemies = possibleEnemies.stream().
		filter(u -> taskComponents.getUnit().getFaction() == u.getFaction()).collect(Collectors.toList());
		return enemies.get(0);
	}
	
	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
