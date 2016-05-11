package hillbillies.statements;


import hillbillies.expressions.EnemyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class AttackStatement extends MyStatement {
	
	private MyExpression expressionUnit;

	public AttackStatement(MyExpression unit){
		this.expressionUnit = unit;
	}

	@Override
	public void execute(TaskComponents taskComponents) {
		System.out.println("ATTACK STATAMENT");
		// TODO Auto-generated method stub
		if (this.expressionUnit instanceof EnemyExpression ){
			EnemyExpression hillbilly = (EnemyExpression) this.expressionUnit;
			Unit enemy = hillbilly.evaluate(taskComponents);
			taskComponents.getUnit().fight(enemy);
		} else{
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionUnit.containSelectedCube();
	}

}
