package hillbillies.statements;

import java.util.List;

import hillbillies.model.MyStatement;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class SequenceStatement extends MyStatement {

	private List<MyStatement> statementList;
	private SourceLocation sourceLocation;

	public SequenceStatement(List<MyStatement> statements, SourceLocation sourceLocation){
		this.statementList = statements;
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public void execute(World world, Unit unit, int[] selectedCube) {
		for (int k = 0; k<this.statementList.size(); k++){
			this.statementList.get(k).execute(world, unit, selectedCube);
		}
	}

}
