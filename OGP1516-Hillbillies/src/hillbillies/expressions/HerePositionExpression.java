package hillbillies.expressions;


import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class HerePositionExpression extends CubePositionExpression {
	
	private SourceLocation sourceLocation;

	public HerePositionExpression(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}

	@Override
	public int[] evaluate(World world, Unit unit,int[] selectedCube, SourceLocation sourceLocation) {
		System.out.println("HERE EXP");
		// TODO Auto-generated method stub
		return unit.getCubeCoordinate();
	}

	

}
