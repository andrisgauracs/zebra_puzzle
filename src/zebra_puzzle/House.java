package zebra_puzzle;

public class House {
	private int nr;
	private String nationality, color, drink, pet, smoke;
	
	/**
     * Constructor for the house object
     * 
     * @param nr House position
     * @param Nat Nationality
     * @param Col Color
     * @param Drink Drink
     * @param Pet Pet
     * @param Smoke Smoke
     * 
     */
	public House(int Nr, String Nat, String Col, String Drink, String Pet, String Smoke) {
		nr = Nr;
		nationality = Nat;
		color = Col;
		drink = Drink;
		pet = Pet;
		smoke = Smoke;
	}
	
	public int getPosition() {
		return nr;
	}
	
	public String getValue(String key) {
		switch (key.toLowerCase()) {
		case "color": return color;
		case "drink": return drink;
		case "nationality": return nationality;
		case "pet": return pet;
		case "smoke": return smoke;
		default: return "";
		}
	}
}
