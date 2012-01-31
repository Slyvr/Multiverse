package com.slyvr.render;

import org.newdawn.slick.Graphics;

import com.slyvr.beans.Global;
import com.slyvr.beans.Menu;
import com.slyvr.beans.MenuItem;

public class RenderMenu {

	public static void renderMenu(Graphics g, Global global){
		
		//Current Menu
		Menu currentMenu = global.getCurrent().getMenu();
		if (currentMenu!=null){
			for (int i=0; i<currentMenu.getMenuItems().size(); i++){
				MenuItem item = currentMenu.getMenuItems().get(i);
				//item.getImg().getImage().draw(item.getX(),item.getY());
				if (item.getImg()!=null) item.getImg().getImage().draw(item.getPosition().getX(),item.getPosition().getY(), item.getPosition().getWidth(),item.getPosition().getHeight());
				else if (item.getSheet() != null){
					item.getSheet().getSheet().getSubImage(item.getSubImgX(), item.getSubImgY()).draw(item.getPosition().getX(),item.getPosition().getY(), item.getPosition().getWidth(), item.getPosition().getHeight());
				}
				else if (item.getText()!= null){
					g.drawString(item.getText(), item.getPosition().getX(), item.getPosition().getY());
				}
			}
		}
		
		//Constants Menu
		Menu constant = global.getMenuByName("constant");
		if (constant!=null){
			for (int i=0; i<constant.getMenuItems().size(); i++){
				MenuItem item = constant.getMenuItems().get(i);
				item.getImg().getImage().draw(item.getPosition().getX(),item.getPosition().getY());
			}
		}
	}
}
