package org.blackberrydeveloper.haditsarbain.component;

import net.rim.device.api.ui.Color; //import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.LabelField;

/**
 * LabelField dengan warna custom
 */
public class FontColorField extends LabelField {

	private int color = Color.BLACK;

	/**
	 * labelfield with custom color
	 * @param text label to be displayed
	 * @param style field style
	 * @param color label color
	 * */
	public FontColorField(String text, long style, int color) {
		super(text, style);
		this.color = color;
		int height = Font.getDefault().getHeight() - 3;
		setFont(Font.getDefault().derive(Font.PLAIN, height));
	}

	public FontColorField(String text, long style, int color, Font font) {
		super(text, style);
		this.color = color;
		setFont(font);
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	public void paint(Graphics g) {
		// g.setBackgroundColor(0x00359AFF);
		// g.clear();
		g.setColor(color);
		super.paint(g);
	}
}
