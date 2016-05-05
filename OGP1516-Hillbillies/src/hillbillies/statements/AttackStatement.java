package hillbillies.statements;


import hillbillies.expressions.EnemyExpression;
import hillbillies.model.MyExpression;
import hillbillies.model.MyStatement;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class AttackStatement extends MyStatement {
	
	private MyExpression expressionUnit;
	private SourceLocation sourceLocation;

	public AttackStatement(MyExpression unit, SourceLocation sourceLocation){
		this.expressionUnit = unit;
		this.sourceLocation = sourceLocation;
	}

	@Override
	public void execute(World world, Unit unit,int[] selectedCube, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		if (this.expressionUnit instanceof EnemyExpression ){
			EnemyExpression hillbilly = (EnemyExpression) this.expressionUnit;
			Unit enemy = hillbilly.evaluate(world, unit, selectedCube, sourceLocation);
			unit.fight(enemy);
		} else{
			throw new IllegalArgumentException();
		}
		
		
	}

}
