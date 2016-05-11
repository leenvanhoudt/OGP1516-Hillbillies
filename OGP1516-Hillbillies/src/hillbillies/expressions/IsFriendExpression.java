package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.TaskComponents;

public class IsFriendExpression extends BooleanExpression{


	private MyExpression expressionUnit;

	public IsFriendExpression(MyExpression unit){
		this.expressionUnit = unit;
	}
	
	@Override
	public Boolean evaluate(TaskComponents taskComponents) {
		System.out.println("ISFRIEND EXP");
		// TODO Auto-generated method stub
		if (this.expressionUnit instanceof UnitExpression){
			UnitExpression hillbilly = (UnitExpression) this.expressionUnit;
			Unit hilly = hillbilly.evaluate(taskComponents);
			return hilly.getFaction() == taskComponents.getUnit().getFaction();
		}
		else{
			throw new Error("No UnitExpression");
		}
		
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionUnit.containSelectedCube();
	}

}
