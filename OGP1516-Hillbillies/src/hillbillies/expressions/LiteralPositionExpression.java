package hillbillies.expressions;

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
	public int[] evaluatePosition(TaskComponents taskComponents) {
		System.out.println("LITERALPOSITION EXP");
		int [] position = new int[] {this.xcoordinate, this.ycoordinate, this.zcoordinate};
		return position;
	}

	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
