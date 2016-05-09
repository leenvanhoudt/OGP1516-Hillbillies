package hillbillies.expressions;

import java.util.Set;

import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class AnyExpression extends UnitExpression {


	private SourceLocation sourceLocation;

	public AnyExpression(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public Unit evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation) {
		System.out.println("ANY EXP");
		// TODO Auto-generated method stub
		Set<Unit> allUnits = world.getUnits();
		for (Unit randomUnit: allUnits){
			if (randomUnit != unit){
				return randomUnit;
			}
		}
		//TODO check exception
		throw new IndexOutOfBoundsException();
	}

}
