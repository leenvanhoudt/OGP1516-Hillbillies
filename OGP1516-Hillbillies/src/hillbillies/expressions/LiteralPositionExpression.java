package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.TaskComponents;

public class LiteralPositionExpression extends CubePositionExpression {

	private int xcoordinate;
	private int zcoordinate;
	private int ycoordinate;

	public LiteralPositionExpression(int x, int y, int z){
		this.xcoordinate = x;
		this.ycoordinate = y;
		this.zcoordinate = z;
	}
	
	@Override
	public int[] evaluate(TaskComponents taskComponents) {
		System.out.println("LITERALPOSITION EXP");
		// TODO Auto-generated method stub
		int [] position = new int[] {this.xcoordinate, this.ycoordinate, this.zcoordinate};
		return position;
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return false;
	}

}
