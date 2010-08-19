/**
 * 
 */
/*
Copyright (c) 2009-2010, hakim
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
package org.blackberrydeveloper.haditsarbain.component;


import java.util.Vector;

import org.blackberrydeveloper.haditsarbain.container.ListRowManager;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;

/**
 * @author hakim
 *
 */
public class HaditsListFieldCallback implements ListFieldCallback {

	private Vector haditsData;
	
	public HaditsListFieldCallback(){
		this.haditsData = new Vector();
	}
	
	/* (non-Javadoc)
	 * @see net.rim.device.api.ui.component.ListFieldCallback#drawListRow(net.rim.device.api.ui.component.ListField, net.rim.device.api.ui.Graphics, int, int, int)
	 */
	public void drawListRow(ListField listField, Graphics graphics, int index,
			int y, int width) {
		ListRowManager row = (ListRowManager) haditsData.elementAt(index);
		
		if (graphics.isDrawingStyleSet(Graphics.DRAWSTYLE_FOCUS) && index == listField.getSelectedIndex()){
			row.drawRow(graphics, 0, y, width, listField.getRowHeight(), true);
		}else
			row.drawRow(graphics, 0, y, width, listField.getRowHeight(), false);
	}

	/* (non-Javadoc)
	 * @see net.rim.device.api.ui.component.ListFieldCallback#get(net.rim.device.api.ui.component.ListField, int)
	 */
	public Object get(ListField listField, int index) {
		return haditsData.elementAt(index);
	}

	/* (non-Javadoc)
	 * @see net.rim.device.api.ui.component.ListFieldCallback#getPreferredWidth(net.rim.device.api.ui.component.ListField)
	 */
	public int getPreferredWidth(ListField listField) {
		// TODO Auto-generated method stub
		return listField.getPreferredWidth();
	}

	/* (non-Javadoc)
	 * @see net.rim.device.api.ui.component.ListFieldCallback#indexOfList(net.rim.device.api.ui.component.ListField, java.lang.String, int)
	 */
	public int indexOfList(ListField listField, String prefix, int start) {
		return haditsData.indexOf(prefix, start);
	}
	
	/**
	 * insert element into index vector
	 * @param haditsindex index title of hadits
	 */
	public void insert(String haditsindex){
        FontColorField hadits = new FontColorField(haditsindex, DrawStyle.LEFT | DrawStyle.VCENTER, Color.DARKOLIVEGREEN);
        hadits.setFont(Font.getDefault().derive(Font.PLAIN, 17));
        ListRowManager haditsmanager = new ListRowManager();
        haditsmanager.add(hadits);
        haditsData.addElement(haditsmanager);
	}
	
	/**
	 * remove all elements
	 */
	public void removeAllElements(){
		haditsData.removeAllElements();
	}
	
	/**
	 * number of elements
	 */
	public int size(){
		return haditsData.size();
	}

}
