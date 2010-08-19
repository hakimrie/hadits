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
import java.io.InputStreamReader;
import java.util.Vector;

import org.blackberrydeveloper.haditsarbain.HaditsArbainApplication;
import org.blackberrydeveloper.haditsarbain.component.FontColorField;
import org.blackberrydeveloper.haditsarbain.container.HaditsDisplayManager;
import org.blackberrydeveloper.haditsarbain.object.Hadits;
import org.blackberrydeveloper.haditsarbain.util.FileUtils;
import org.blackberrydeveloper.haditsarbain.util.StringUtils;


import net.rim.device.api.system.Characters;
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
import net.rim.device.api.ui.component.TextField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;

/**
 * Screen to display hadits
 * */
public class HaditsDisplayScreen extends MainScreen {
	private Hadits hadits;
	private String title;
	private TextField haditstext;
	private TextField haditstrans;
	
	/**
	 * Constructor hadits Display screen viewer
	 * @param index index hadits
	 * @param title title of the hadits
	 * */
	public HaditsDisplayScreen(final int index, String title){
		this.title = title;
		
		initialize();
		this.addKeyListener(new KeyEventListener());
		this.addMenuItem(menuAbout);
		// start thread
		new Thread(){
			public void run(){
				updateScreen(index);
			}
		}.start();
	}
	
	private MenuItem menuAbout = new MenuItem("About", 10,100){
		public void run(){
			Dialog.inform("Hadith Arbain 1.0\nDeveloped By Hakim (hakimrie@gmail.com)");
		}
	};
	
	/**
	 * initialize attributes
	 * */
	private void initialize(){
		this.hadits = new Hadits();
		HaditsDisplayManager haditsManager = new HaditsDisplayManager();
		FontColorField haditstitle = new FontColorField(title, DrawStyle.HCENTER, Color.DARKOLIVEGREEN);
		haditstitle.setFont(Font.getDefault().derive(Font.PLAIN, HaditsArbainApplication.fontsize));
		haditsManager.add(haditstitle);
		
		VerticalFieldManager vfm = new VerticalFieldManager(Manager.VERTICAL_SCROLL|Manager.NO_HORIZONTAL_SCROLL);
		haditstext = new TextField(TextField.READONLY){
			public void paint(Graphics g){
				g.setColor(Color.DARKOLIVEGREEN);
				super.paint(g);
			}
		};
		haditstext.setText("Loading doa....");
		haditstext.setFont(Font.getDefault().derive(Font.PLAIN, HaditsArbainApplication.fontsize));
		vfm.add(haditstext);
		haditstrans = new TextField(TextField.READONLY){
			public void paint(Graphics g){
				g.setColor(Color.DARKOLIVEGREEN);
				super.paint(g);
			}
		};
		haditstrans.setText("Loading translation");
		haditstrans.setFont(Font.getDefault().derive(Font.PLAIN, HaditsArbainApplication.fontsize));
		vfm.add(haditstrans);
		haditsManager.add(vfm);
		haditsManager.setBackground(BackgroundFactory.createSolidBackground(Color.LIGHTGOLDENRODYELLOW));
		
		add(haditsManager);
	}
	
	/**
	 * pop current screen, with transition animation
	 * */
	private void popThisScreen(){
		TransitionContext tc = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
		tc.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_RIGHT);
		tc.setIntAttribute(TransitionContext.ATTR_KIND, TransitionContext.KIND_OUT);
		
        UiApplication.getUiApplication().getUiEngineInstance().setTransition(this, null,UiEngineInstance.TRIGGER_POP, tc);
		UiApplication.getUiApplication().popScreen(this);
	}
	
	/**
	 * Update di attribute value, display hadits
	 * @param index index of the hadits to be displayed
	 * */
	private void updateScreen(int index){
		// get hadits
		Vector vhadits = new FileUtils().lineReader("/text/hadits.txt", "utf-8");
		hadits.setHadits(vhadits.elementAt(index).toString());
		
		// get translation
		Vector vtranslation = new FileUtils().lineReader("/text/trans_id.txt","ASCII");
		hadits.setTranslation(vtranslation.elementAt(index).toString());
		
        // update ui
        UiApplication.getUiApplication().invokeLater(new Runnable(){
        	public void run(){
        		haditstext.setText(hadits.getHadits());
        		haditstrans.setText(StringUtils.replaceAll(hadits.getTranslation(),"\\n","\n"));
        	}
        });
	}
	
	private class KeyEventListener implements KeyListener {
		public boolean keyChar(char key, int status, int time) {
			if (key == Characters.ESCAPE) {
				popThisScreen();
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
