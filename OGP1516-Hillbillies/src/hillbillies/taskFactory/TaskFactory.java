package hillbillies.taskFactory;

import java.util.List;

import hillbillies.expressions.AndExpression;
import hillbillies.expressions.AnyExpression;
import hillbillies.expressions.BooleanExpression;
import hillbillies.expressions.BoulderPositionExpression;
import hillbillies.expressions.CarriesItemExpression;
import hillbillies.expressions.CubePositionExpression;
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
import hillbillies.expressions.UnitExpression;
import hillbillies.expressions.WorkshopPositionExpression;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.scheduler.CreateTasks;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
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
		return new CreateTasks(name, priority, activity, selectedCubes).tasks();
	}

	@Override
	public MyStatement createAssignment(String variableName, MyExpression value, SourceLocation sourceLocation) {
		return new AssignmentStatement(variableName, value);
	}

	@Override
	public MyStatement createWhile(MyExpression condition, MyStatement body, SourceLocation sourceLocation) {
		if (condition instanceof ReadVariableExpression){
			return new WhileStatement<BooleanExpression, ReadVariableExpression>((ReadVariableExpression)condition,body);
		}
		return new WhileStatement<BooleanExpression,ReadVariableExpression>((BooleanExpression)condition, body);
	}

	@Override
	public MyStatement createIf(MyExpression condition, MyStatement ifBody, MyStatement elseBody,
			SourceLocation sourceLocation) {
		if (condition instanceof ReadVariableExpression){
			return new IfStatement<BooleanExpression, ReadVariableExpression>((ReadVariableExpression)condition,ifBody,elseBody);
		}
		return new IfStatement<BooleanExpression,ReadVariableExpression>((BooleanExpression)condition, ifBody, elseBody);
	}

	@Override
	public MyStatement createBreak(SourceLocation sourceLocation) {
		return new BreakStatement();
	}

	@Override
	public MyStatement createPrint(MyExpression value, SourceLocation sourceLocation) {
		return new PrintStatement(value);
	}

	@Override
	public MyStatement createSequence(List<MyStatement> statements, SourceLocation sourceLocation) {
		return new SequenceStatement(statements);
	}

	@Override
	public MyStatement createMoveTo(MyExpression position, SourceLocation sourceLocation){
		if (position instanceof ReadVariableExpression)
			return new MoveToStatement<CubePositionExpression,ReadVariableExpression>((ReadVariableExpression)position);
		return new MoveToStatement<CubePositionExpression,ReadVariableExpression>((CubePositionExpression)position);
	}

	@Override
	public MyStatement createWork(MyExpression position, SourceLocation sourceLocation) {
		if (position instanceof ReadVariableExpression)
			return new WorkStatement<CubePositionExpression,ReadVariableExpression>((ReadVariableExpression)position);
		return new WorkStatement<CubePositionExpression,ReadVariableExpression>((CubePositionExpression)position);
	}

	@Override
	public MyStatement createFollow(MyExpression unit, SourceLocation sourceLocation) {
		if (unit instanceof ReadVariableExpression)
			return new FollowStatement<UnitExpression,ReadVariableExpression>((ReadVariableExpression)unit);
		return new FollowStatement<UnitExpression,ReadVariableExpression>((UnitExpression)unit);
	}

	@Override
	public MyStatement createAttack(MyExpression unit, SourceLocation sourceLocation) {
		if (unit instanceof ReadVariableExpression)
			return new AttackStatement<UnitExpression,ReadVariableExpression>((ReadVariableExpression)unit);
		return new AttackStatement<UnitExpression,ReadVariableExpression>((UnitExpression)unit);
	}

	@Override
	public MyExpression createReadVariable(String variableName, SourceLocation sourceLocation) {
		return new ReadVariableExpression(variableName);
	}

	@Override
	public MyExpression createIsSolid(MyExpression position, SourceLocation sourceLocation) {
		if (position instanceof ReadVariableExpression)
			return new IsSolidExpression<CubePositionExpression,ReadVariableExpression>((ReadVariableExpression)position);
		return new IsSolidExpression<CubePositionExpression,ReadVariableExpression>((CubePositionExpression)position);
	}

	@Override
	public MyExpression createIsPassable(MyExpression position, SourceLocation sourceLocation) {
		if (position instanceof ReadVariableExpression)
			return new IsPassableExpression<CubePositionExpression,ReadVariableExpression>((ReadVariableExpression)position);
		return new IsPassableExpression<CubePositionExpression,ReadVariableExpression>((CubePositionExpression)position);
	}

	@Override
	public MyExpression createIsFriend(MyExpression unit, SourceLocation sourceLocation) {
		if (unit instanceof ReadVariableExpression)
			return new IsFriendExpression<UnitExpression,ReadVariableExpression>((ReadVariableExpression)unit);
		return new IsFriendExpression<UnitExpression,ReadVariableExpression>((UnitExpression)unit);
	}

	@Override
	public MyExpression createIsEnemy(MyExpression unit, SourceLocation sourceLocation) {
		if (unit instanceof ReadVariableExpression)
			return new IsEnemyExpression<UnitExpression,ReadVariableExpression>((ReadVariableExpression)unit);
		return new IsEnemyExpression<UnitExpression,ReadVariableExpression>((UnitExpression)unit);
	}

	@Override
	public MyExpression createIsAlive(MyExpression unit, SourceLocation sourceLocation) {
		if (unit instanceof ReadVariableExpression)
			return new IsAliveExpression<UnitExpression,ReadVariableExpression>((ReadVariableExpression)unit);
		return new IsAliveExpression<UnitExpression,ReadVariableExpression>((UnitExpression)unit);
	}

	@Override
	public MyExpression createCarriesItem(MyExpression unit, SourceLocation sourceLocation) {
		if (unit instanceof ReadVariableExpression)
			return new CarriesItemExpression<UnitExpression,ReadVariableExpression>((ReadVariableExpression)unit);
		return new CarriesItemExpression<UnitExpression,ReadVariableExpression>((UnitExpression)unit);
	}

	@Override
	public MyExpression createNot(MyExpression expression, SourceLocation sourceLocation) {
		if (expression instanceof ReadVariableExpression)
			return new NotExpression<BooleanExpression,ReadVariableExpression>((ReadVariableExpression)expression);
		return new NotExpression<BooleanExpression,ReadVariableExpression>((BooleanExpression)expression);
	}

	@Override
	public MyExpression createAnd(MyExpression left, MyExpression right, SourceLocation sourceLocation) {
		if (left instanceof BooleanExpression && right instanceof BooleanExpression){
			return new AndExpression<BooleanExpression,ReadVariableExpression>((BooleanExpression)left, (BooleanExpression)right);
		} else if (left instanceof ReadVariableExpression && right instanceof ReadVariableExpression){
			return new AndExpression<BooleanExpression,ReadVariableExpression>((ReadVariableExpression)left, (ReadVariableExpression)right);
		} else if (left instanceof ReadVariableExpression && right instanceof BooleanExpression){
			return new AndExpression<BooleanExpression,ReadVariableExpression>((ReadVariableExpression)left, (BooleanExpression)right);
		} else if (left instanceof BooleanExpression && right instanceof ReadVariableExpression){
			return new AndExpression<BooleanExpression,ReadVariableExpression>((BooleanExpression)left, (ReadVariableExpression)right);
		}
		throw new Error();
	}

	@Override
	public MyExpression createOr(MyExpression left, MyExpression right, SourceLocation sourceLocation) {
		if (left instanceof BooleanExpression && right instanceof BooleanExpression){
			return new OrExpression<BooleanExpression,ReadVariableExpression>((BooleanExpression)left, (BooleanExpression)right);
		} else if (left instanceof ReadVariableExpression && right instanceof ReadVariableExpression){
			return new OrExpression<BooleanExpression,ReadVariableExpression>((ReadVariableExpression)left, (ReadVariableExpression)right);
		} else if (left instanceof ReadVariableExpression && right instanceof BooleanExpression){
			return new OrExpression<BooleanExpression,ReadVariableExpression>((ReadVariableExpression)left, (BooleanExpression)right);
		} else if (left instanceof BooleanExpression && right instanceof ReadVariableExpression){
			return new OrExpression<BooleanExpression,ReadVariableExpression>((BooleanExpression)left, (ReadVariableExpression)right);
		}
		throw new Error();
	}

	@Override
	public MyExpression createHerePosition(SourceLocation sourceLocation) {
		return new HerePositionExpression();
	}

	@Override
	public MyExpression createLogPosition(SourceLocation sourceLocation) {
		return new LogPositionExpression();
	}

	@Override
	public MyExpression createBoulderPosition(SourceLocation sourceLocation) {
		return new BoulderPositionExpression();
	}

	@Override
	public MyExpression createWorkshopPosition(SourceLocation sourceLocation) {
		return new WorkshopPositionExpression();
	}

	@Override
	public MyExpression createSelectedPosition(SourceLocation sourceLocation) {
		return new SelectedPositionExpression();
	}

	@Override
	public MyExpression createNextToPosition(MyExpression position, SourceLocation sourceLocation) {
		if (position instanceof ReadVariableExpression)
			return new NextToPositionExpression<CubePositionExpression,ReadVariableExpression>((ReadVariableExpression)position);
		return new NextToPositionExpression<CubePositionExpression,ReadVariableExpression>((CubePositionExpression)position);
	}

	@Override
	public MyExpression createPositionOf(MyExpression unit, SourceLocation sourceLocation) {
		if (unit instanceof ReadVariableExpression)
			return new PositionOfExpression<UnitExpression,ReadVariableExpression>((ReadVariableExpression)unit);
		return new PositionOfExpression<UnitExpression,ReadVariableExpression>((UnitExpression)unit);
	}

	@Override
	public MyExpression createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		return new LiteralPositionExpression(x, y, z);
	}

	@Override
	public MyExpression createThis(SourceLocation sourceLocation) {
		return new ThisExpression();
	}

	@Override
	public MyExpression createFriend(SourceLocation sourceLocation) {
		return new FriendExpression();
	}

	@Override
	public MyExpression createEnemy(SourceLocation sourceLocation) {
		return new EnemyExpression();
	}

	@Override
	public MyExpression createAny(SourceLocation sourceLocation) {
		return new AnyExpression();
	}

	@Override
	public MyExpression createTrue(SourceLocation sourceLocation) {
		return new TrueExpression();
	}

	@Override
	public MyExpression createFalse(SourceLocation sourceLocation) {
		return new FalseExpression();
	}

	
	
}