package hillbillies.model;

import java.util.Random;
import java.util.Set;

public class Boulder {
	
	//NOG DOEN: soms wordt Boulder op vaste plaats gelegt (bij instorten Rock)
	//		maar anders random plaats dus geen coordinaten nodig
	//		--> hoe lossen we dit op?
	public Boulder(){
		this.setBoulderWeight();
	}

	public int getBoulderWeight() {
		return this.boulderWeight;
	}
	
	// als error dan ligt het aan final
	private final void setBoulderWeight(){
		Random random = new Random();
		this.boulderWeight = random.nextInt(41)+10;
	}
	
	private int boulderWeight;
	
	public double[] getPosition(){
		// TODO schrijf deze methode
		return this.position;
	}
	
	public void setPosition(int x, int y, int z){
		position = new double[]{x,y,z};
	}
	
	private double[] position;
	
	public void advanceTime(double dt){
		// TODO schrijf deze methode
	}
	

	
}
