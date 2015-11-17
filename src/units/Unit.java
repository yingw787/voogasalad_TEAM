package units;

import java.util.HashMap;
import java.util.Map;

import rules.Rule;

public class Unit {
	protected Map<String, Double> myAttributes;
	protected Map<String, String> myStringAttributes;
	protected Map<String, Rule> myRules;
	
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
	}
	
	public Unit(){
		myAttributes = new HashMap<String, Double>();
		myStringAttributes = new HashMap<String, String>();
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
	
	public void setPoint(Point p){
		myAttributes.put("X", p.getX());
		myAttributes.put("Y", p.getY());
	}
	
	public void setHealth(double h){
		myAttributes.put("Health", h);
	}
}
