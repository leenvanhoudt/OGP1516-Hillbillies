package hillbillies.expressions;


import hillbillies.model.Unit;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.TaskComponents;

public class CarriesItemExpression extends BooleanExpression {

	private MyExpression expressionUnit;

	public CarriesItemExpression(MyExpression unit){
		this.expressionUnit = unit;
	}
	
	@Override
	public Boolean evaluate(TaskComponents taskComponents ) {
		System.out.println("Carriesitem EXP");
		// TODO Auto-generated method stub
		if (this.expressionUnit instanceof UnitExpression){
			UnitExpression hillbilly = (UnitExpression) this.expressionUnit;
			Unit hilly = hillbilly.evaluate(taskComponents);
			return hilly.isCarryingBoulder() || hilly.isCarryingLog();
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
