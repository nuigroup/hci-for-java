package nui.squirt.component;

import nui.squirt.LayoutManager;


public class Image extends AbstractScalable {
	
	private String imagePath;
//	private float width;
//	private float height;

//	public Image(float x, float y, String path) {
//		super(x, y);
//		this.imagePath = path;
//	}
	
//	public Image(float x, float y, String path, float width, float height) {
//		this(x, y, path);
//		this.width = width;
//		this.height = height;
//	}

//	private ImageRenderer renderer;

//	public Renderer getRenderer() {
//		return renderer;
//	}
//	
//	public void setRenderer(Renderer r) {
//		setRenderer((ImageRenderer) r);
//	}
//
//	public void setRenderer(ImageRenderer r) {
//		this.renderer = r;
//	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public LayoutManager getLayout() {
		// TODO Auto-generated method stub
		return null;
	}

//	public float getWidth() {
//		return width;
//	}
//
//	public void setWidth(float width) {
//		this.width = width;
//	}
//
//	public float getHeight() {
//		return height;
//	}
//
//	public void setHeight(float height) {
//		this.height = height;
//	}

}
