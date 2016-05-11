package hillbillies.statements;


import hillbillies.expressions.UnitExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class FollowStatement extends MyStatement {
	
	private MyExpression expressionUnit;

	public FollowStatement(MyExpression unit){
		this.expressionUnit = unit;
	}

	@Override
	public void execute(TaskComponents taskComponents) {
		System.out.println("FOLLOW STATEMENT");
		// TODO Auto-generated method stub
		if (!(this.expressionUnit instanceof UnitExpression))
			throw new Error("no unit expression");
		UnitExpression hillbilly = (UnitExpression) this.expressionUnit;
		Unit followed = hillbilly.evaluate(taskComponents);
		taskComponents.getUnit().follow(followed);
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionUnit.containSelectedCube();
	}

}
