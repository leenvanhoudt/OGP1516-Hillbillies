package hillbillies.statements;


import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class BreakStatement extends MyStatement{
		
	@Override
	public void execute(TaskComponents taskComponents) {
		System.out.println("BREAK STATEMENT");
		// TODO Auto-generated method stub
		MyStatement parent = this.getParent();
		while(parent !=null && !(parent instanceof WhileStatement)){
			parent = parent.getParent();
		}
		if ((parent instanceof WhileStatement)){
			WhileStatement whileParent = (WhileStatement) parent;
			whileParent.setExecutionState(false);
		}
		
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return false;
	}

}
