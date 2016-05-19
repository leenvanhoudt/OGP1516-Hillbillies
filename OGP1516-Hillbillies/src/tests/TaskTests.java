package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hillbillies.expressions.FriendExpression;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.Scheduler;
import hillbillies.scheduler.Task;
import hillbillies.statements.PrintStatement;

public class TaskTests {

	@Test
	public void testPriorityMinValue() {
		String name = "name"; int priority = 5; 
		MyExpression expression = new FriendExpression();
		MyStatement activity = new PrintStatement(expression); 
		int[] selectedCubes = null;
		Task task = new Task(name, priority, activity, selectedCubes);
		task.setPriority(Integer.MIN_VALUE+1);
		assertTrue("priority is min value", task.getPriority()==Integer.MIN_VALUE);
	}

}
