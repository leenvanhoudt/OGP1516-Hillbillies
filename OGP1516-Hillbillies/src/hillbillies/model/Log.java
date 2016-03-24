package hillbillies.model;

import java.util.Random;

public class Log {
	
	//NOG DOEN: soms wordt Log op vaste plaats gelegt (bij instorten Wood)
		//		maar anders random plaats dus geen coordinaten nodig
		//		--> hoe lossen we dit op?
	public Log(){
		this.setLogWeight();
	}

	public int getLogWeight() {
		return this.logWeight;
	}
	
	// als error dan ligt het aan final
	private final void setLogWeight(){
		Random random = new Random();
		this.logWeight = random.nextInt(41)+10;
	}
	
	private int logWeight;
	
	public double[] getPosition(){
		return this.logPosition;
	}
	
	public void setPosition(int x, int y, int z){
		this.logPosition = new double[]{x,y,z};
	}
	
	private double[] logPosition;

}
