package units;

public class IDGenerator {

	static private int ID;
	
	public IDGenerator(){
		ID = 0;
	}

	static public int getID(){
		ID++;
		return ID;
	}

}
