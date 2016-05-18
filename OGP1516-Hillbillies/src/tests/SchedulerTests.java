package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

import hillbillies.expressions.FriendExpression;
import hillbillies.model.Unit;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.Scheduler;
import hillbillies.scheduler.Task;
import hillbillies.statements.PrintStatement;

public class SchedulerTests {

	@Test
	public void testAddAndRemoveTask() {
		Scheduler scheduler = new Scheduler();
		String name = "name"; int priority = 5; 
		MyExpression expression = new FriendExpression();
		MyStatement activity = new PrintStatement(expression); 
		int[] selectedCubes = null;
		Task task = new Task(name, priority, activity, selectedCubes);
		scheduler.schedule(task);
		assertTrue("task added", scheduler.getScheduledTasks().size()==1);
		scheduler.removeTask(task);
		assertTrue("task removed", scheduler.getScheduledTasks().size()==0);
	}
	
	@Test
	public void testReplaceTask() {
		Scheduler scheduler = new Scheduler();
		String name = "name"; String nameTwo = "name2"; int priority = 5; 
		MyExpression expression = new FriendExpression();
		MyStatement activity = new PrintStatement(expression); 
		int[] selectedCubes = null;
		Task task = new Task(name, priority, activity, selectedCubes);
		Task task2 = new Task(nameTwo, priority+5, activity, selectedCubes);
		scheduler.schedule(task);
		assertTrue("task added", scheduler.getScheduledTasks().get(0)==task);
		scheduler.replace(task, task2);
		assertTrue("task removed", scheduler.getScheduledTasks().get(0)==task2);
	}
	
	@Test
	public void testTasksArePartOf() {
		Scheduler scheduler = new Scheduler();
		String name = "name"; int priority = 5; 
		MyExpression expression = new FriendExpression();
		MyStatement activity = new PrintStatement(expression); 
		int[] selectedCubes = null;
		Task task = new Task(name, priority, activity, selectedCubes);
		scheduler.schedule(task);
		Collection<Task> tasks = new ArrayList<Task>();
		tasks.add(task);
		assertTrue("task added", scheduler.areTasksPartOf(tasks));
	}
	
	@Test
	public void testTaskHighestPriority() {
		Scheduler scheduler = new Scheduler();
		String name = "name"; String nameTwo = "name2"; int priority = 5; 
		MyExpression expression = new FriendExpression();
		MyStatement activity = new PrintStatement(expression); 
		int[] selectedCubes = null;
		Task task = new Task(name, priority, activity, selectedCubes);
		Task task2 = new Task(nameTwo, priority+5, activity, selectedCubes);
		scheduler.schedule(task);
		scheduler.schedule(task2);
		assertTrue("task highest priority", scheduler.getTaskHighestPriority()==task2);
	}
	
	@Test
	public void testAssignTask() {
		Scheduler scheduler = new Scheduler();
		String name = "name"; int priority = 5; 
		MyExpression expression = new FriendExpression();
		MyStatement activity = new PrintStatement(expression); 
		int[] selectedCubes = null;
		Task task = new Task(name, priority, activity, selectedCubes);
		scheduler.schedule(task);
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		scheduler.assignTaskToUnit(task, unit);
		assertTrue("unit assigned to task", task.getAssignedUnit()==unit);
		assertTrue("task assigned to unit", unit.getAssignedTask()==task);
	}
	
	@Test
	public void testResetTask() {
		Scheduler scheduler = new Scheduler();
		String name = "name"; int priority = 5; 
		MyExpression expression = new FriendExpression();
		MyStatement activity = new PrintStatement(expression); 
		int[] selectedCubes = null;
		Task task = new Task(name, priority, activity, selectedCubes);
		scheduler.schedule(task);
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		scheduler.assignTaskToUnit(task, unit);
		scheduler.reset(task, unit);
		assertTrue("no unit assigned to task", task.getAssignedUnit()==null);
		assertTrue("no task assigned to unit", unit.getAssignedTask()==null);
	}
	
	@Test
	public void testIteratorHasNextAndNext() {
		Scheduler scheduler = new Scheduler();
		String name = "name"; String nameTwo = "name2"; int priority = 5; 
		MyExpression expression = new FriendExpression();
		MyStatement activity = new PrintStatement(expression); 
		int[] selectedCubes = null;
		Task task = new Task(name, priority, activity, selectedCubes);
		Task task2 = new Task(nameTwo, priority+5, activity, selectedCubes);
		scheduler.schedule(task);
		scheduler.schedule(task2);
		Iterator<Task> iterator = scheduler.getAllTasksIterator();
		assertTrue("has next", iterator.hasNext());
		assertTrue("next is task2", iterator.next()==task2);
	}

}
