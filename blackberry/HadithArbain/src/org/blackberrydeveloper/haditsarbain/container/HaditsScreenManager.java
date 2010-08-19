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

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;

public class HaditsScreenManager extends Manager {

	public HaditsScreenManager(){
		super(Manager.USE_ALL_HEIGHT|Manager.USE_ALL_WIDTH);
	}
	
	protected void sublayout(int width, int height) {
		int prefwidth = Display.getWidth();
		int prefheight = Display.getHeight();
		
		Field list = getField(0);
		setPositionChild(list, 31, 31);
		layoutChild(list, prefwidth-62, prefheight-61);
		
		setExtent(prefwidth, prefheight);
	}
	
	
	protected void paint(Graphics g){
		Bitmap leftBackground = Bitmap.getBitmapResource("image/left.png");
		Bitmap topBackground = Bitmap.getBitmapResource("image/top.png");
		Bitmap rightBackground = Bitmap.getBitmapResource("image/right.png");
		Bitmap bottomBackground = Bitmap.getBitmapResource("image/bottom.png");
		
		int width = Display.getWidth();
		int height = Display.getHeight();
		
		// left
		g.drawBitmap(0, 0, width, height, leftBackground, 0, 0);
		// right
		g.drawBitmap(width-rightBackground.getWidth(), 0, width, height, rightBackground, 0, 0);
		
		// top
		g.drawBitmap(0, 0, width, height, topBackground, 0, 0);
		// bottom
		g.drawBitmap(0, height-bottomBackground.getHeight(), width, height, bottomBackground, 0, 0);
		super.paint(g);
	}
	
}
