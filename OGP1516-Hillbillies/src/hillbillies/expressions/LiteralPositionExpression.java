package hillbillies.expressions;

import java.util.List;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class LiteralPositionExpression extends CubePositionExpression {

	private int xcoordinate;
	private int zcoordinate;
	private int ycoordinate;
	private SourceLocation sourceLocation;

	public LiteralPositionExpression(int x, int y, int z, SourceLocation sourceLocation){
		this.xcoordinate = x;
		this.ycoordinate = y;
		this.zcoordinate = z;
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public int[] evaluate(World world, Unit unit, int[] selectedCubes, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new int[] {this.xcoordinate, this.ycoordinate, this.zcoordinate};
	}

}
