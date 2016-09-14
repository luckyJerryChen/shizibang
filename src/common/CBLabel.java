package common;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CBLabel extends JLabel{
	public CBLabel(String name){
		super(name);
	}
	
	public void setImage(Image image){
		ImageIcon lineImage = new ImageIcon(); 
		lineImage.setImage(image.getScaledInstance(CrossBand.frameWidth/2,CrossBand.frameHeight,Image.SCALE_DEFAULT)); 
	    setIcon(lineImage);
	}
}
