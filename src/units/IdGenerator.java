package units;

public class IdGenerator {

	
	static private int ID = 0;

	static public int getID(){
		ID++;
		return ID;
	}

}
