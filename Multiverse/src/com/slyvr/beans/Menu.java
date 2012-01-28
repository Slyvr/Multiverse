package com.slyvr.beans;

import java.util.ArrayList;

public class Menu {

	private String name;
	private ArrayList<MenuItem> menuItems;
	
	public Menu(){
		
	}
	
	//Name
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	//MenuItems
	public ArrayList<MenuItem> getMenuItems(){
		return menuItems;
	}
	public void setMenuItems(ArrayList<MenuItem> menuItems){
		this.menuItems=menuItems;
	}
	public MenuItem getMenuItemByName(String name){
		for(int i=0; i<menuItems.size(); i++){
			if (menuItems.get(i).getName().equals(name)) return menuItems.get(i);
		}
		return null;
	}
}
