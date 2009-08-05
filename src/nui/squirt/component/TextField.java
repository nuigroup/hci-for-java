package nui.squirt.component;

import java.awt.Color;

import nui.squirt.NUIController;
import nui.squirt.event.KeyEvent;
import nui.squirt.listener.KeyListener;

import processing.core.PApplet;
import processing.core.PGraphics;

public class TextField extends Rectangle implements KeyListener {
	
	private static final Color TEXT_FIELD_FILL_COLOR = Color.WHITE;
	private static final Color TEXT_FIELD_BORDER_COLOR = Color.BLACK;
	private static final Color TEXT_FIELD_TEXT_COLOR = Color.BLACK;
	
	private static final int TEXT_FIELD_BORDER_WIDTH = 2;
	private static final int TEXT_FIELD_TEXT_PADDING = 4;
	
	private String text = new String();
	
	private int timer = 0;
	private int caretIndex = 0;
	
	private float textOffset = 0;

	public TextField(float x, float y, float w) {
		super(x, y, w, 0);
	}
	
	public TextField(float x, float y, float w, String initialText) {
		this(x, y, w);
		this.text = initialText;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getCaretIndex() {
		return caretIndex;
	}

	public void setCaretIndex(int caretIndex) {
		if (caretIndex < 0) caretIndex = 0;
		if (caretIndex > getText().length()) caretIndex = getText().length();
		this.caretIndex = caretIndex;
	}

	@Override
	public Color getFillColor() {
		return TEXT_FIELD_FILL_COLOR;
	}
	
	@Override
	public Color getStrokeColor() {
		return TEXT_FIELD_BORDER_COLOR;
	}
	
	@Override
	public float getStrokeWeight() {
		return TEXT_FIELD_BORDER_WIDTH;
	}
	
	@Override
	public void preRender(PApplet p) {
		super.preRender(p);
		setHeight(p.textAscent()+p.textDescent()+2*TEXT_FIELD_TEXT_PADDING);
	}
	
	@Override
	public void render(PApplet p) {
		super.render(p);
		
		PGraphics g = p.createGraphics((int) getWidth()-2*TEXT_FIELD_TEXT_PADDING, (int) getHeight()-2*TEXT_FIELD_TEXT_PADDING, PApplet.JAVA2D);
		g.beginDraw();
		g.stroke(TEXT_FIELD_TEXT_COLOR.getRGB());
		g.fill(TEXT_FIELD_TEXT_COLOR.getRGB());
		g.textAlign(PApplet.LEFT, PApplet.TOP);
		g.textFont(NUIController.getInstance().getFont());
		g.rectMode(PApplet.CORNER);
		
		g.background(TEXT_FIELD_FILL_COLOR.getRGB());
		float caretPos = g.textWidth(getText().substring(0, getCaretIndex()));
		if (caretPos + textOffset > g.width) {
			textOffset -= caretPos + textOffset - g.width + 2;
		}
		if (caretPos + textOffset < 0) {
			textOffset += -(caretPos + textOffset) + 2;
		}
		g.text(getText(), textOffset, 0);
		if (++timer%30 < 15) {
			g.line(caretPos + textOffset, 0, caretPos + textOffset, g.height);
		}
		g.endDraw();
		
		p.image(g, 0, 0);
	}

	public void keyPressed(KeyEvent e) {
		if (e.isCoded()) {
			switch (e.getKeyCode()) {
				case KeyEvent.LEFT:
					setCaretIndex(getCaretIndex()-1);
					break;
				case KeyEvent.RIGHT:
					setCaretIndex(getCaretIndex()+1);
					break;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		if (!e.isCoded()) {
			if (e.getKey() == KeyEvent.BACKSPACE && getCaretIndex() > 0) {
				setText(getText().substring(0, getCaretIndex()-1) + getText().substring(getCaretIndex()));
				setCaretIndex(getCaretIndex()-1);
			}
			else if (e.getKey() == KeyEvent.DELETE && getCaretIndex() < getText().length()) {
				setText(getText().substring(0, getCaretIndex()) + getText().substring(getCaretIndex()+1));
			}
			else if (e.getKey() != KeyEvent.DELETE && e.getKey() != KeyEvent.BACKSPACE) {
				setText(getText().substring(0, getCaretIndex()) + e.getKey() + getText().substring(getCaretIndex()));
				setCaretIndex(getCaretIndex()+1);
			}
		}
	}

}
