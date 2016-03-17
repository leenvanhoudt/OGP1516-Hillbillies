package hillbillies.model;

import java.util.Random;
import java.util.Set;

public class Boulder {
	
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
		return null;
	}
	
	public void advanceTime(double dt){
		// TODO schrijf deze methode
	}
	

	
}
