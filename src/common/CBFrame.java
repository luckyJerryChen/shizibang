package common;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class CBFrame extends JFrame{
	public  static JPanel imagePanel;
	public  static JPanel linePanel;
	public  static JLabel imageLabel;
	public  static CBLabel lineLabel;
	public CBFrame(int width,int height){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    setSize(width, height);
	    setLocationRelativeTo(null);  
	    setVisible(true);  
	    setLocation(80, 80);
	    this.imagePanel=new JPanel();
	    this.imagePanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    this.linePanel=new JPanel();
	    getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
	    
	    getContentPane().add(this.imagePanel);
	    imagePanel.setLayout(new GridLayout(1, 0, 0, 0));
	    getContentPane().add(this.linePanel);
	    linePanel.setLayout(new GridLayout(1, 0, 0, 0));

	}
	public void setImage(Image image2,Image lineImage2){
		this.imageLabel=new JLabel("image");
		this.imageLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.imagePanel.add(this.imageLabel);
	    ImageIcon image = new ImageIcon(); 
	    image.setImage(image2.getScaledInstance(getWidth()/2,getHeight(),Image.SCALE_DEFAULT)); 
		this.imageLabel.setIcon(image);

		
		this.lineLabel=new CBLabel("line");
		this.lineLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.linePanel.add(this.lineLabel);
		ImageIcon lineImage = new ImageIcon(); 
		lineImage.setImage(lineImage2.getScaledInstance(getWidth()/2,getHeight(),Image.SCALE_DEFAULT)); 
	    this.lineLabel.setIcon(lineImage);

	}
}
