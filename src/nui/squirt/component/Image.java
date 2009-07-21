package nui.squirt.component;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import nui.squirt.Scalable;
import processing.core.PApplet;
import processing.core.PImage;


public class Image extends Rectangle implements Scalable {
	
	private static final Color NONE = new Color(0, 0, 0, 0);
	
	private static Map<String, PImage> images = new HashMap<String, PImage>();

	private String imagePath;
	private float scale = 1;

	public Image(float x, float y, String path) {
		this(x, y, -1, -1, path);
	}
	
	public Image(float x, float y,  float width, float height, String path) {
		super(x, y, width, height);
		this.imagePath = path;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	@Override
	public void update() {
		super.update();
		
		setFillColor(NONE);
		setStrokeColor(NONE);
	}
	
	@Override
	public void preRender(PApplet p) {
		super.preRender(p);
		
		p.scale(getScale());
		
		p.imageMode(PApplet.CENTER);
		p.noTint();
	}
	
	@Override
	public void render(PApplet p) {		
		PImage i = images.get(getImagePath());
		if (i == null) {
			images.put(getImagePath(), p.loadImage(getImagePath()));
			i = images.get(getImagePath());
		}
		
		if (getWidth() >= 0 && getHeight() >= 0) {
			p.image(i, 0, 0, getWidth(), getHeight());
		}
		else p.image(i, 0, 0);
	}

}
