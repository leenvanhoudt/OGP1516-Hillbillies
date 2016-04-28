package hillbillies.statements;

import java.util.List;

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
	public void execute(World world, Unit unit,List<int[]> selectedCubes, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		if (this.expressionUnit instanceof EnemyExpression ){
			EnemyExpression enemy = (EnemyExpression) this.expressionUnit;
			//haal enemy unit uit expression
			//unit.fight(defender);
		} else{
			throw new IllegalArgumentException();
		}
		
		
	}

}
