package hillbillies.expressions;


import hillbillies.model.Log;
import hillbillies.model.MyExpression;
import hillbillies.model.PathFinding;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class LogPositionExpression extends CubePositionExpression {

	private SourceLocation sourceLocation;
	private PathFinding pathFinding;

	public LogPositionExpression(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}

	@Override
	public int[] evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation) {
		System.out.println("LOG EXP");
		// TODO Auto-generated method stub
		this.pathFinding.setUnit(unit);
		return this.pathFinding.Dijkstra(1);
	}

}
