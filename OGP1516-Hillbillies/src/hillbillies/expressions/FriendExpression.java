package hillbillies.expressions;

import java.util.Set;

import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class FriendExpression extends UnitExpression {


	private SourceLocation sourceLocation;

	public FriendExpression(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public Unit evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		Set<Unit> allUnits = world.getUnits();
		for (Unit randomUnit: allUnits){
			if (randomUnit.getFaction() == unit.getFaction()){
				return randomUnit;
			}
		}
		//TODO check exception
		throw new IndexOutOfBoundsException();
	}

}
