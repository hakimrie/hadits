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
package org.blackberrydeveloper.haditsarbain.ui;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import org.blackberrydeveloper.haditsarbain.component.FontColorField;
import org.blackberrydeveloper.haditsarbain.component.HaditsListFieldCallback;
import org.blackberrydeveloper.haditsarbain.container.HaditsScreenManager;
import org.blackberrydeveloper.haditsarbain.container.ListRowManager;
import org.blackberrydeveloper.haditsarbain.util.FileUtils;

import net.rim.device.api.io.LineReader;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Characters;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.KeyListener;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;

/**
 * Screen to display list of do'as
 * */
public class HaditsListScreen extends MainScreen {
	private ListField haditsList;
	private Vector haditsData;
	private HaditsListFieldCallback haditslistCallback;
	
	public HaditsListScreen(){
		super(DEFAULT_CLOSE|DEFAULT_MENU);
		//this.setBackground(BackgroundFactory.createSolidBackground(Color.LIGHTGOLDENRODYELLOW));
		initAttributes();
		
		HaditsScreenManager haditsmanager = new HaditsScreenManager();
		haditsmanager.setBackground(BackgroundFactory.createSolidBackground(Color.LIGHTGOLDENRODYELLOW));
		VerticalFieldManager vfm = new VerticalFieldManager(Manager.NO_HORIZONTAL_SCROLL|Manager.VERTICAL_SCROLL|Manager.USE_ALL_HEIGHT|Manager.USE_ALL_WIDTH);

		vfm.add(haditsList);
		haditsmanager.add(vfm);
		add(haditsmanager);
	
		this.addKeyListener(new KeyEventListener());
		this.addMenuItem(menuAbout);
	}
	
	private MenuItem menuAbout = new MenuItem("About", 10,100){
		public void run(){
			Dialog.inform("Hadith Arbain 1.0\nDeveloped By Hakim (hakimrie@gmail.com)");
		}
	};
	
	/**
	 * initialize all private attributes
	 * */
	private void initAttributes(){
		haditslistCallback = new HaditsListFieldCallback();
		
		haditsList = new ListField(){
			// draw focus, do nothing
			public void drawFocus(Graphics g, boolean on){
				// don't draw focus
				g.setDrawingStyle(Graphics.DRAWSTYLE_FOCUS, on);
				paint(g);
				//super.drawFocus(g, false);
			}
			
			// trackwheel
			protected boolean trackwheelClick(int status, int time) {
				int index = getSelectedIndex();
				pushNewsScreen(index);
				return true;
			}
		};
		
		haditsList.setRowHeight(50);
		haditsList.setEmptyString("Bismillah", DrawStyle.HCENTER);
		haditsList.setCallback(haditslistCallback);
		
		populateHadits();
	}
	
	private void populateHadits(){
		haditslistCallback.removeAllElements();
		haditsList.setSize(0);
		
		haditsData = new FileUtils().lineReader("/text/index_id.txt", "ascii");
		// insert callback
		for(int i=0; i< haditsData.size(); i++){
			haditslistCallback.insert(haditsData.elementAt(i).toString());
		}
		
		haditsList.setSize(haditslistCallback.size());
		
	}
	
	private void pushNewsScreen(int index){
		TransitionContext tc = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
		tc.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_LEFT);
		tc.setIntAttribute(TransitionContext.ATTR_KIND, TransitionContext.STYLE_OVER);
		
		HaditsDisplayScreen ns = new HaditsDisplayScreen(index, haditsData.elementAt(index).toString());
        UiApplication.getUiApplication().getUiEngineInstance().setTransition(getScreen(), ns,UiEngineInstance.TRIGGER_PUSH, tc);
		UiApplication.getUiApplication().pushScreen(ns);
	}
	
	private class KeyEventListener implements KeyListener {
		public boolean keyChar(char key, int status, int time) {
			if (key == Characters.ENTER) {
				//close();
				int index = haditsList.getSelectedIndex();
				pushNewsScreen(index);
				
			} else if (key == Characters.ESCAPE) {
				close();
			}else if (key == Characters.CONTROL_MENU){
				return false;
			}
			return true;
		}

		public boolean keyDown(int keycode, int time) {
			return false;
		}

		public boolean keyUp(int keycode, int time) {
			return false;
		}

		public boolean keyRepeat(int keycode, int time) {
			return false;
		}

		public boolean keyStatus(int keycode, int time) {
			return false;
		}
	}
}
