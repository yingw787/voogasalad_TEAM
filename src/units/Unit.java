package units;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import rules.Rule;

import rules.Rule;

public class Unit {
	protected Map<String, Double> myAttributes;
	protected Map<String, String> myStringAttributes;
	protected Map<String, Rule> myRules;
	protected Faction myFaction;
	protected UnitType myType;
	
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
		
		myRules = new HashMap<String, Rule>();
	}
	
	public Unit(){
		myAttributes = new HashMap<String, Double>();
		myStringAttributes = new HashMap<String, String>();
		myRules = new HashMap<String, Rule>();
		
		// Default values
		myAttributes.put("MaxHealth", 10.0);
		myAttributes.put("CollisionDamage", 0.0);
		myAttributes.put("BuyCost", 10.0);
		myAttributes.put("SellCost", 5.0);
		myStringAttributes.put("Name", "T");
		myStringAttributes.put("Image", "");
	}
	
	public Unit(Unit u) {
		myAttributes = u.myAttributes;
		myStringAttributes = u.myStringAttributes;
		myRules = u.myRules;
		myFaction = u.myFaction;
		myType = u.myType;
	}
	
	public void setRule(String key, Rule rule){
		myRules.put(key, rule);
	}
	
	public Rule getRule(String key){
		return myRules.get(key);
	}
	
	public void removeRules(String key){
		myRules.remove(key);
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
		unit.myAttributes = new HashMap<String, Double>(this.myAttributes);
		unit.myRules = new HashMap<String, Rule>(this.myRules);
		unit.myStringAttributes = new HashMap<String, String>(this.myStringAttributes);
		unit.myAttributes.put("ID", (double)IDGenerator.getID());
		unit.myType = this.myType;
		return unit;
	}

	public Faction getFaction() {
		return myFaction;
	}

	public void setFaction(Faction Faction) {
		this.myFaction = Faction;
	}

	public double getHealth() {
		// TODO Auto-generated method stub
		return myAttributes.get("Health");
	}
	
	public UnitType getType() {
		return myType;
	}
	
	public void setType(UnitType ut) {
		myType = ut;
	}
	
}
