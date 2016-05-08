package hillbillies.taskFactory;

import java.util.List;

import hillbillies.expressions.AndExpression;
import hillbillies.expressions.AnyExpression;
import hillbillies.expressions.BoulderPositionExpression;
import hillbillies.expressions.CarriesItemExpression;
import hillbillies.expressions.EnemyExpression;
import hillbillies.expressions.FalseExpression;
import hillbillies.expressions.FriendExpression;
import hillbillies.expressions.HerePositionExpression;
import hillbillies.expressions.IsAliveExpression;
import hillbillies.expressions.IsEnemyExpression;
import hillbillies.expressions.IsFriendExpression;
import hillbillies.expressions.IsPassableExpression;
import hillbillies.expressions.IsSolidExpression;
import hillbillies.expressions.LiteralPositionExpression;
import hillbillies.expressions.LogPositionExpression;
import hillbillies.expressions.NextToPositionExpression;
import hillbillies.expressions.NotExpression;
import hillbillies.expressions.OrExpression;
import hillbillies.expressions.PositionOfExpression;
import hillbillies.expressions.ReadVariableExpression;
import hillbillies.expressions.SelectedPositionExpression;
import hillbillies.expressions.ThisExpression;
import hillbillies.expressions.TrueExpression;
import hillbillies.expressions.WorkshopPositionExpression;
import hillbillies.model.MyExpression;
import hillbillies.model.MyStatement;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.scheduler.CreateTasks;
import hillbillies.scheduler.Scheduler;
import hillbillies.scheduler.Task;
import hillbillies.statements.AssignmentStatement;
import hillbillies.statements.AttackStatement;
import hillbillies.statements.BreakStatement;
import hillbillies.statements.FollowStatement;
import hillbillies.statements.IfStatement;
import hillbillies.statements.MoveToStatement;
import hillbillies.statements.PrintStatement;
import hillbillies.statements.SequenceStatement;
import hillbillies.statements.WhileStatement;
import hillbillies.statements.WorkStatement;

public class TaskFactory implements ITaskFactory<MyExpression,MyStatement,Task>{

	@Override
	public List<Task> createTasks(String name, int priority, MyStatement activity, List<int[]> selectedCubes) {
		// TODO Auto-generated method stub
		return new CreateTasks(name, priority, activity, selectedCubes).tasks();
	}

	@Override
	public MyStatement createAssignment(String variableName, MyExpression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new AssignmentStatement(variableName, value, sourceLocation);
	}

	@Override
	public MyStatement createWhile(MyExpression condition, MyStatement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new WhileStatement(condition, body, sourceLocation);
	}

	@Override
	public MyStatement createIf(MyExpression condition, MyStatement ifBody, MyStatement elseBody,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new IfStatement(condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public MyStatement createBreak(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new BreakStatement(sourceLocation);
	}

	@Override
	public MyStatement createPrint(MyExpression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new PrintStatement(value, sourceLocation);
	}

	@Override
	public MyStatement createSequence(List<MyStatement> statements, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new SequenceStatement(statements, sourceLocation);
	}

	@Override
	public MyStatement createMoveTo(MyExpression position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new MoveToStatement(position, sourceLocation);
	}

	@Override
	public MyStatement createWork(MyExpression position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new WorkStatement(position, sourceLocation);
	}

	@Override
	public MyStatement createFollow(MyExpression unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new FollowStatement(unit, sourceLocation);
	}

	@Override
	public MyStatement createAttack(MyExpression unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new AttackStatement(unit, sourceLocation);
	}

	@Override
	public MyExpression createReadVariable(String variableName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new ReadVariableExpression(variableName, sourceLocation);
	}

	@Override
	public MyExpression createIsSolid(MyExpression position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new IsSolidExpression(position, sourceLocation);
	}

	@Override
	public MyExpression createIsPassable(MyExpression position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new IsPassableExpression(position, sourceLocation);
	}

	@Override
	public MyExpression createIsFriend(MyExpression unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new IsFriendExpression(unit, sourceLocation);
	}

	@Override
	public MyExpression createIsEnemy(MyExpression unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new IsEnemyExpression(unit, sourceLocation);
	}

	@Override
	public MyExpression createIsAlive(MyExpression unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new IsAliveExpression(unit, sourceLocation);
	}

	@Override
	public MyExpression createCarriesItem(MyExpression unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new CarriesItemExpression(unit, sourceLocation);
	}

	@Override
	public MyExpression createNot(MyExpression expression, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new NotExpression(expression, sourceLocation);
	}

	@Override
	public MyExpression createAnd(MyExpression left, MyExpression right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new AndExpression(left, right, sourceLocation);
	}

	@Override
	public MyExpression createOr(MyExpression left, MyExpression right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new OrExpression(left, right, sourceLocation);
	}

	@Override
	public MyExpression createHerePosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new HerePositionExpression(sourceLocation);
	}

	@Override
	public MyExpression createLogPosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new LogPositionExpression(sourceLocation);
	}

	@Override
	public MyExpression createBoulderPosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new BoulderPositionExpression(sourceLocation);
	}

	@Override
	public MyExpression createWorkshopPosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new WorkshopPositionExpression(sourceLocation);
	}

	@Override
	public MyExpression createSelectedPosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new SelectedPositionExpression(sourceLocation);
	}

	@Override
	public MyExpression createNextToPosition(MyExpression position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new NextToPositionExpression(position, sourceLocation);
	}

	@Override
	public MyExpression createPositionOf(MyExpression unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new PositionOfExpression(unit, sourceLocation);
	}

	@Override
	public MyExpression createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new LiteralPositionExpression(x, y, z, sourceLocation);
	}

	@Override
	public MyExpression createThis(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new ThisExpression(sourceLocation);
	}

	@Override
	public MyExpression createFriend(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new FriendExpression(sourceLocation);
	}

	@Override
	public MyExpression createEnemy(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new EnemyExpression(sourceLocation);
	}

	@Override
	public MyExpression createAny(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new AnyExpression(sourceLocation);
	}

	@Override
	public MyExpression createTrue(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new TrueExpression(sourceLocation);
	}

	@Override
	public MyExpression createFalse(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new FalseExpression(sourceLocation);
	}

	
	
}