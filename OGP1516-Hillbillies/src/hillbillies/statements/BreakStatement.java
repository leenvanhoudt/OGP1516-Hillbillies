package hillbillies.statements;


import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class BreakStatement extends MyStatement{
	
	@Override
	public void execute(TaskComponents taskComponents) {
		System.out.println("BREAK STATEMENT");
		// TODO Auto-generated method stub
		MyStatement Parent = null;
		while(Parent !=null && !(Parent instanceof WhileStatement))
		
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return false;
	}

}
