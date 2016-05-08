package hillbillies.expressions;

import hillbillies.model.PathFinding;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class WorkshopPositionExpression extends CubePositionExpression{

	private SourceLocation sourceLocation;
	private PathFinding pathfinding;

	public WorkshopPositionExpression(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}

	@Override
	public int[] evaluate(World world, Unit unit,int[] selectedCube, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		this.pathfinding.setUnit(unit);
		return this.pathfinding.Dijkstra(3);
	}

}
