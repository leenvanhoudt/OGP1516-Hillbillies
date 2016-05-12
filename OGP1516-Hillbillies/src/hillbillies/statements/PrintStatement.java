package hillbillies.statements;

import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class PrintStatement extends MyStatement{
	
	private MyExpression expressionValue;

	public PrintStatement(MyExpression value){
		this.expressionValue = value;
	}

	@Override
	public void execute(TaskComponents taskComponents) throws Error {
		System.out.println("PRINT STATEMENT");
		try{
			System.out.println(this.expressionValue.evaluate(taskComponents));
			this.setExecutedState(true);
		} catch(Throwable e){
			throw new Error("Can not print");
		}
	}

	@Override
	public boolean containSelectedCube() {
		return this.expressionValue.containSelectedCube();
	}

	@Override
	public MyStatement getNext(TaskComponents taskComponents) {
		return null;
	}

	@Override
	public boolean isExecuted() {
		return this.finished;
	}
	
	private boolean finished;

	@Override
	public void setExecutedState(boolean state) {
		this.finished = state;
	}

}
