package units;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import actions.DisappearAction;
import actions.IAction;
import conditions.CheckAttributeCondition;
import conditions.ICondition;
import rules.Rule;

public class Unit {
	protected Map<String, Double> myAttributes;
	protected Map<String, String> myStringAttributes;
	protected Map<String, Rule> myRules;
	protected Faction myFaction;
	protected final static String DEFAULTS_FILE = "resources/DefaultUnit.properties";
//	private double mySpeed;
	
	/**  Constructor superclass for Tower and Troop objects
	 *   @params Attributes of Unit object
	 **/
	public Unit(String name, double health, double cd, String img, 
			Point p, int ID, int bc, int sc){		
		myAttributes = new HashMap<String, Double>();
		myAttributes.put("Health", health);
		myAttributes.put("MaxHealth", health);
		myAttributes.put("CollisionDamage", cd);
		myAttributes.put("ID", (double) ID);
		myAttributes.put("BuyCost", (double)bc);
		myAttributes.put("SellCost", (double)sc);
		myAttributes.put("X", (double)p.getX());
		myAttributes.put("Y", (double)p.getY());
		myStringAttributes = new HashMap<String, String>();
		myStringAttributes.put("Name", name);
		myStringAttributes.put("Image", img);
		myAttributes.put("Speed", 1.0);
		
		myRules = new HashMap<String, Rule>();
		
		addDefaultRule(); 
//		mySpeed = 1.0;
	}
	
	private void addDefaultRule() {
		ICondition  ic = new CheckAttributeCondition("Health",0,myAttributes.get("MaxHealth"));
		IAction ia = new DisappearAction();
		Rule rule = new Rule(ic,ia);
		myRules.put("Default Disappear Rule", rule);
	}

	public Unit(){
		this(DEFAULTS_FILE);
	}
	
	
	/**  Constructor for default Unit object
	 * Reads in default values from resource file
	 *   @params Attributes of default Unit object
	 **/
	public Unit(String filePath){
		myAttributes = new HashMap<String, Double>();
		myStringAttributes = new HashMap<String, String>();
		myRules = new HashMap<String, Rule>();
		myStringAttributes.put("Name", "T");
		myStringAttributes.put("Image", "");
		// Read in defaults from a resource file
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = getClass().getClassLoader().getResourceAsStream(filePath);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filePath);
				return;
			}
			prop.load(input);
			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				myAttributes.put(key, Double.parseDouble(value));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
		addDefaultRule(); 
	}
	
	/**  Constructor for Unit object clone
	 *   @params Unit Unit object to clone
	 **/
	public Unit(Unit u) {
		myAttributes = u.myAttributes;
		myStringAttributes = u.myStringAttributes;
		myRules = u.myRules;
		myFaction = u.myFaction;
		if(!myRules.containsKey("Default Disappear Rule"))
			addDefaultRule();
	}
	
	public void setRule(String key, Rule rule){
		myRules.put(key, rule);
		IAction a = rule.getAction();
		if(a.getRange()!=0)
			myAttributes.put("Range", a.getRange());
	}
	
	public Rule getRule(String key){
		return myRules.get(key);
	}
	
	public void removeRules(String key){
		myRules.remove(key);
	}
	
	public void removeRules(Rule rule) {
		myRules.values().remove(this);
	}
	
	public Set<String> getRuleSet(){
		return myRules.keySet();
	}
	
	public Collection<Rule> getRules() {
		return myRules.values();
	}
	
	public Set<String> getStringAttributeSet(){
		return myStringAttributes.keySet();
	}
	
	public Set<String> getAttributeSet(){
		return myAttributes.keySet();
	}
	
	public String[] getAttributeArray(){
		String[] arr = new String[myAttributes.keySet().size()];
		int i=0;
		for(String attr : myAttributes.keySet()){
			arr[i++] = attr;
		}
		return arr;
	}
	
	public double getAttribute(String attribute){
		return myAttributes.get(attribute);
	}
	
	public String getStringAttribute(String attribute){
		return myStringAttributes.get(attribute);
	}
	
	public void setAttribute(String attribute, double value){
		myAttributes.put(attribute, value);
	}
	
	public void setAttribute(String attribute, String value){
		myStringAttributes.put(attribute, value);
	}
	
	public void removeAttribute(String attribute){
		myAttributes.remove(attribute);
	}
	
	public void setPoint(Point p){
		myAttributes.put("X", p.getX());
		myAttributes.put("Y", p.getY());
	}
	
	public Point getPoint() {
		Point p = new Point(myAttributes.get("X"), myAttributes.get("Y"));
		return p;
	}
	
	public void setHealth(double h){
		myAttributes.put("Health", h);
	}
	
	public int getID(){
		return (new Double(myAttributes.get("ID"))).intValue();
	}
	
	public void setID(int id){
		myAttributes.put("ID", (double) id);
	}
	
	public Unit clone(){
		Unit unit = new Unit();
		unit.myAttributes = (Map<String, Double>) ((HashMap<String, Double>)(this.myAttributes)).clone();
		
		unit.myRules = new HashMap<String,Rule>();
		
		for(Entry<String, Rule> e : this.myRules.entrySet()){
			unit.myRules.put(e.getKey(),e.getValue().clone());
		}
		
		unit.myStringAttributes = (Map<String, String>) ((HashMap<String, String>)(this.myStringAttributes)).clone();
		unit.myAttributes.put("ID", (double)IDGenerator.getID());
		return unit;
	}

	public Faction getFaction() {
		return myFaction;
	}

	public void setFaction(Faction Faction) {
		this.myFaction = Faction;
	}

	public double getHealth() {
		return myAttributes.get("Health");
	}

	public double getSpeed() {
		// TODO Auto-generated method stub
		return myAttributes.get("Speed");
	}
	
	public void setSpeed(double s) {
		myAttributes.put("Speed", s);
	}

}
