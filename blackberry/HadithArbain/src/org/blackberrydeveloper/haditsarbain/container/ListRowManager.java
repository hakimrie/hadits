/*
Copyright (c) 2009-2010, i-moov
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice,
          this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
          this list of conditions and the following disclaimer in the documentation
          and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
OF SUCH DAMAGE.
 */
package org.blackberrydeveloper.haditsarbain.container;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;

public class ListRowManager extends Manager{

	public ListRowManager(){
		super(Manager.NO_HORIZONTAL_SCROLL | Manager.NO_VERTICAL_SCROLL);
	}
	
	/**
	 * Causes the fields within this row manager to be layed out then painted
	 * @param g graphics object to draw
	 * @param x x coordinate location
	 * @param y y coordinate location
	 * @param width row width
	 * @param height row height
	 * @param selected row status: selected or not selected
	 * */
	public void drawRow(Graphics g, int x, int y, int width, int height,boolean selected) {
		// Arrange the cell fields within this row manager.
		layout(width, height);

		// Place this row manager within its enclosing list.
		setPosition(x, y);

		// Apply a translating/clipping transformation to the graphics
		// context so that this row paints in the right area.
		g.pushRegion(getExtent());

		// Paint this manager's controlled fields.
		subpaint(g);

		if (selected){
			// draw background
			g.setGlobalAlpha(50);
			g.setColor(Color.LIGHTBLUE);
			g.fillRect(0, 0, width-1, height);
			
			g.setGlobalAlpha(100);
			g.setColor(Color.DARKOLIVEGREEN);
			g.drawRoundRect(0, 0, width, height, 6, 6);
		}
		// g.drawLine(10, 0, 10, getPreferredHeight());

		// Restore the graphics context.
		g.popContext();
	}

	protected void sublayout(int width, int height) {
		Field title = getField(0);
		setPositionChild(title, 7,0);
		layoutChild(title,getPreferredWidth(),getPreferredHeight());
		
		setExtent(getPreferredWidth(), getPreferredHeight());
	}
	
	// The preferred width of a row is defined by the list renderer.
	public int getPreferredWidth() {
		return Display.getWidth() - 61;
	}

	// The preferred height of a row is the "row height" as defined in the
	// enclosing list.
	public int getPreferredHeight() {
		return 50;
	}
}
