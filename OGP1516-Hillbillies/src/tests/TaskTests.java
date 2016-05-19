package tests;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import hillbillies.expressions.FriendExpression;
import hillbillies.model.CubeType;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.programs.TaskParser;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.Task;
import hillbillies.statements.PrintStatement;
import hillbillies.taskFactory.TaskFactory;
import ogp.framework.util.ModelException;

public class TaskTests {

	@Test
	public void testPriorityMinValue() throws ModelException{
		String name = "name"; int priority = 5; 
		MyExpression expression = new FriendExpression();
		MyStatement activity = new PrintStatement(expression); 
		int[] selectedCubes = null;
		Task task = new Task(name, priority, activity, selectedCubes);
		task.setPriority(Integer.MIN_VALUE+1);
		assertTrue("priority is min value", task.getPriority()==Integer.MIN_VALUE);
	}
	
	//TODO: fix
	@Test //(expected=IllegalArgumentException.class)
	public void testNotWellFormedBreakBeforeWhile() throws ModelException{
		int[][][] types = new int[6][6][6];
		types[5][5][1] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		world.addUnit(unit);
		TaskFactory factory = new TaskFactory();
		
		Boolean exception = false;
		
		try{
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: break;\n while is_passable here do\n work here; \n done",
				factory, Collections.singletonList(null));
		}catch(Throwable e){
			exception = true;
		}
		
		assertTrue("exception", exception);
		
	}

}
