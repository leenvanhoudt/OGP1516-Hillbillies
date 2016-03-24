package hillbillies.model;

public class Log {
	
	//NOG DOEN: soms wordt Log op vaste plaats gelegt (bij instorten Wood)
		//		maar anders random plaats dus geen coordinaten nodig
		//		--> hoe lossen we dit op?
	public Log(){
		
	}
	
	public double[] getPosition(){
		return this.position;
	}
	
	public void setPosition(int x, int y, int z){
		position = new double[]{x,y,z};
	}
	
	private double[] position;

}
