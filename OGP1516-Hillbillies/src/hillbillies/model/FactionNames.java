package hillbillies.model;

public enum FactionNames {
		
	GRYFFINDOR{
		
		public String getName(){
			return "Harry Potter";
		}
		
		public int getAgility(){
			return 70;
		}
		
		public int getStrength(){
			return 60;
		}
		
		public int getToughness(){
			return 50;
		}
		
		public int getWeight(){
			return 80;
		}
		
	},
	
	HUFFLEPUFF{
		
		public String getName(){
			return "Cedric Diggory";
		}
		
		public int getAgility(){
			return 30;
		}
		
		public int getStrength(){
			return 30;
		}
		
		public int getToughness(){
			return 30;
		}
		
		public int getWeight(){
			return 50;
		}
		
	},
	
	RAVENCLAW{
		
		public String getName(){
			return "Luna Lovegood";
		}
		
		public int getAgility(){
			return 50;
		}
		
		public int getStrength(){
			return 30;
		}
		
		public int getToughness(){
			return 50;
		}
		
		public int getWeight(){
			return 50;
		}
		
	},
	
	SLYTHERIN{
		
		public String getName(){
			return "Draco Malfoy";
		}
		
		public int getAgility(){
			return 30;
		}
		
		public int getStrength(){
			return 50;
		}
		
		public int getToughness(){
			return 70;
		}
		
		public int getWeight(){
			return 70;
		}
		
	},
	
	DEATH_EATERS{
		
		public String getName(){
			return "Lord Voldemort";
		}
		
		public int getAgility(){
			return 30;
		}
		
		public int getStrength(){
			return 70;
		}
		
		public int getToughness(){
			return 70;
		}
		
		public int getWeight(){
			return 70;
		}
		
	};
	
	public abstract String getName();
	
	public abstract int getAgility();
	
	public abstract int getStrength();
	
	public abstract int getToughness();
	
	public abstract int getWeight();
}
