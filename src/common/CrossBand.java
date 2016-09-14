package common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import utils.BufferedImageBuilder;
import utils.Draw;
import utils.Trans;

public class CrossBand {
	public static int frameWidth = 1800;
	public static int frameHeight = 900;
	public static int pointNum = 128;
	public static float pointR = 10;
	public static int lineImageWidth = 1800;
	public static int lineImageheight = 1800;
	public static float[][] pointPX;
	public static int m = 3;
	public static String photoPath = "9999.jpg";

	public static void main(String [] args){
		BufferedImage image=null ;
		BufferedImage lineImage=new BufferedImage(CrossBand.lineImageWidth, CrossBand.lineImageheight, BufferedImage.TYPE_BYTE_GRAY);
    	for(int i=0;i<lineImage.getWidth();++i){
			for(int j=0;j<lineImage.getHeight();++j){
				int aa=255;
				lineImage.setRGB(i, j, 0xff000000 | (aa << 16) | (aa << 8) | aa);
			}
		}
		try{
			image= ImageIO.read(new FileInputStream(photoPath)); 
		}catch(Exception e){
			e.printStackTrace();
		}
		
		BufferedImage grayImage=new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		BufferedImage grayImage2=new BufferedImage(image.getWidth()/m, image.getHeight()/m, BufferedImage.TYPE_4BYTE_ABGR);
    	for(int i=0;i<grayImage.getWidth();++i){
			for(int j=0;j<grayImage.getHeight();++j){	
				int rgb=image.getRGB(i, j);
		        int a = rgb & 0xff000000;//锟斤拷锟斤拷锟轿伙拷锟�4-31锟斤拷锟斤拷锟斤拷息锟斤拷alpha通锟斤拷锟斤拷锟芥储锟斤拷a锟斤拷锟斤拷
		        int r = (rgb >> 16) & 0xff;//取锟斤拷锟轿革拷位锟斤拷16-23锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
		        int g = (rgb >> 8) & 0xff;//取锟斤拷锟斤拷位锟斤拷8-15锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
		        int b = rgb & 0xff;//取锟斤拷锟斤拷位锟斤拷0-7锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
		        int gray = (r * 77 + g * 151 + b * 28) >> 8;    // NTSC luma锟斤拷锟斤拷锟斤拷叶锟街�
		        int newRgb= a | (gray << 16) | (gray << 8) | gray;//锟斤拷锟揭讹拷值锟斤拷锟斤拷锟斤拷锟斤拷锟缴拷锟斤拷锟�
		        if(g!=(int)0xff){
			        grayImage.setRGB(i,j,newRgb);
		        }else{
			        grayImage.setRGB(i,j,rgb);    
		        }
			}
		}
		ImageIcon imageIcon = new ImageIcon(); 
		imageIcon.setImage(grayImage.getScaledInstance(grayImage.getWidth()/m,grayImage.getHeight()/m,Image.SCALE_DEFAULT)); 
		grayImage2=Trans.toBufferedImage(imageIcon.getImage());
		CBFrame frame=new CBFrame(CrossBand.frameWidth,CrossBand.frameHeight);

		frame.setImage(grayImage2,lineImage);
		int time=5000;
		Draw.drawpoint(lineImage, pointNum,pointR);

		boolean stop=false;
		while(stop==false){
			float minX1=-1;
			float minY1=-1;
			float minX2=-1;
			float minY2=-1;
			long minGray=Long.MAX_VALUE;
			for(int i=0;i<CrossBand.pointNum;++i){
				for(int j=0;j<CrossBand.pointNum;++j){
					if(i!=j){
                        float x1=CrossBand.pointPX[i][0];
                        float y1=CrossBand.pointPX[i][1];
                        float x2=CrossBand.pointPX[j][0];
                        float y2=CrossBand.pointPX[j][1];
						long gray=Draw.caculateBlackThickness(grayImage2, x1/m, y1/m, x2/m, y2/m);
						if(gray<minGray){
							minGray=gray;
							minX1=x1;
							minY1=y1;
							minX2=x2;
							minY2=y2;
						}
					}
				}
			}

            if(minGray>230){
                System.out.print("out "+minGray);
                break;
            }
			Draw.drawLine(grayImage2,lineImage,  minX1, minY1, minX2, minY2);
			if(time==0)
				stop=true;
			time--;
		}
		
	}
}
