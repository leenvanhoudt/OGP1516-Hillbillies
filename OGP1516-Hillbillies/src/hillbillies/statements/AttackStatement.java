package hillbillies.statements;


import hillbillies.expressions.EnemyExpression;
import hillbillies.model.Unit;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class AttackStatement extends MyStatement {
	
	private MyExpression expressionUnit;

	public AttackStatement(MyExpression unit){
		this.expressionUnit = unit;
	}

	@Override
	public void execute(TaskComponents taskComponents) throws Error {
		System.out.println("ATTACK STATAMENT");
		// TODO Auto-generated method stub
		if (this.expressionUnit instanceof EnemyExpression ){
			EnemyExpression hillbilly = (EnemyExpression) this.expressionUnit;
			Unit enemy = hillbilly.evaluate(taskComponents);
			taskComponents.getUnit().fight(enemy);
			this.setExecutedState(true);
		} else{
			throw new Error("enemy not reachable");
		}
	}

	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionUnit.containSelectedCube();
	}

	@Override
	public MyStatement getNext(TaskComponents taskComponents) {
//		MyStatement parent = this.getParent();
//		if (parent instanceof SequenceStatement && 
//			((SequenceStatement) parent).getListSequence().size()-1 >
//				((SequenceStatement) parent).getListSequence().indexOf(this)){
//			SequenceStatement parentSeq = (SequenceStatement) parent;
//			return parentSeq.getListSequence().get(parentSeq.getListSequence().indexOf(this)+1);
//		}else if(parent != null){
//			return parent.getNext();
//		}else{
//			return null;
//		}
		return null;
	}

	@Override
	public boolean isExecuted() {
		// TODO Auto-generated method stub
		return this.finished;
	}

	private boolean finished;

	@Override
	public void setExecutedState(boolean state) {
		// TODO Auto-generated method stub
		this.finished = state;
	}
}
