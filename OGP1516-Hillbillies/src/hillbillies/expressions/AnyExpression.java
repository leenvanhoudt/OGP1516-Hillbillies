package hillbillies.expressions;

import java.util.ArrayList;

import hillbillies.model.DijkstraPathFinding;
import hillbillies.model.Unit;
import hillbillies.scheduler.TaskComponents;

public class AnyExpression extends UnitExpression{
	
	private DijkstraPathFinding dijkstra;
	
	@Override
	public Unit evaluateUnit(TaskComponents taskComponents) throws Error {
		try{
			this.dijkstra.setUnit(taskComponents.getUnit());
			int[] cube = this.dijkstra.Dijkstra(3);
			ArrayList<Unit> possibleUnits = taskComponents.getWorld()
					.getCubeOtherUnit(cube[0], cube[1], cube[2], taskComponents.getUnit());
			return possibleUnits.get(0);
		} catch(Throwable e){
			throw new Error("no other unit found");
		}
		
	}

	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
