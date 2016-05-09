package hillbillies.expressions;


import hillbillies.model.Boulder;
import hillbillies.model.PathFinding;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class BoulderPositionExpression extends CubePositionExpression {

	private SourceLocation sourceLocation;
	private PathFinding pathFinding;

	public BoulderPositionExpression(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public int[] evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation) {
		System.out.println("BOULDER EXP");
		// TODO Auto-generated method stub
		this.pathFinding.setUnit(unit);
		return this.pathFinding.Dijkstra(0);
	}

}
