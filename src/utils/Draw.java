package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import common.CBFrame;
import common.CrossBand;

public class Draw {
	public static void drawpoint(Image image, int pointNum, float pointR) {
		CrossBand.pointPX = new float[CrossBand.pointNum][2];

		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(1));

		for (int i = 0; i < pointNum; ++i) {
			g.fillOval(
					(int) (0.5f * CrossBand.lineImageWidth
							+ Math.sin(((float) 360 / CrossBand.pointNum) * i * Math.PI / 180)
									* CrossBand.lineImageWidth * 0.46f
							- CrossBand.pointR),
					(int) (0.5f * CrossBand.lineImageheight
							- Math.cos(((float) 360 / CrossBand.pointNum) * i * Math.PI / 180)
									* CrossBand.lineImageheight * 0.46f
							- CrossBand.pointR),
					(int) CrossBand.pointR * 2, (int) CrossBand.pointR * 2);
			CrossBand.pointPX[i][0] = (float) (0.5f * CrossBand.lineImageWidth
					+ Math.sin(((float) 360 / CrossBand.pointNum) * i * Math.PI / 180) * CrossBand.lineImageWidth
							* 0.46f);
			CrossBand.pointPX[i][1] = (float) (0.5f * CrossBand.lineImageheight
					- Math.cos(((float) 360 / CrossBand.pointNum) * i * Math.PI / 180) * CrossBand.lineImageheight
							* 0.46f);

            //System.out.println("p"+i+":  "+CrossBand.pointPX[i][0]+"  "+CrossBand.pointPX[i][1]);
 		}

		CBFrame.lineLabel.setImage(image);
	}

	public static void drawLine(BufferedImage grayImage,Image image, float x1, float y1, float x2, float y2) {
		Graphics2D g = (Graphics2D) image.getGraphics();
        Color color2=new Color(0,0,0,29);
        g.setColor(color2);
		g.setStroke(new BasicStroke(3));

		g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
		//System.out.println(((BufferedImage) image).getWidth() + "  " + ((BufferedImage) image).getHeight());
		//System.out.println(x1 + "  " + y1);
		//System.out.println(x2 + "  " + y2);
		CBFrame.lineLabel.setImage(image);
		
		x1=x1/CrossBand.m;
		y1=y1/CrossBand.m;
		x2=x2/CrossBand.m;
		y2=y2/CrossBand.m;

		if(Math.abs(x1-x2)>Math.abs(y1-y2)){
			float k = (y2 - y1) / (x2 - x1);
			float b = y1 - x1 * (y2 - y1) / (x2 - x1);
			if (x1 < x2) {
				for (int x = (int) x1; x < (int) x2; ++x) {
					int y = (int) (k * x + b);
				//	 System.out.println(x+"...."+y);
					int rgb = grayImage.getRGB(x, y);
			        int a = rgb & 0xff000000;//锟斤拷锟斤拷锟轿伙拷锟�4-31锟斤拷锟斤拷锟斤拷息锟斤拷alpha通锟斤拷锟斤拷锟芥储锟斤拷a锟斤拷锟斤拷
			        int r = (rgb >> 16) & 0xff;//取锟斤拷锟轿革拷位锟斤拷16-23锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
			   //     System.out.println(Integer.toBinaryString((int)rgb));
			        int bb = rgb & 0xff;//取锟斤拷锟斤拷位锟斤拷0-7锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
					int gray = (rgb & 0xff) ;
					int gg = (rgb >> 8) & 0xff;
					// 锟斤拷锟斤拷叶锟�
					if (bb!=0||r!=0||gg!=0xff) {
						gray=gray+256/(CrossBand.m*CrossBand.m)<=255?gray+256/(CrossBand.m*CrossBand.m):255;
						int newRgb= a | (gray << 16) | (gray << 8) | gray;//
						grayImage.setRGB(x, y, newRgb);

					} else {
						// System.out.println("123123123");
					}

				}
			}else{
				for (int x = (int) x2; x < (int) x1; ++x) {
					int y = (int) (k * x + b);
					// System.out.println(x+"...."+y);
					int rgb = grayImage.getRGB(x, y);
			        int a = rgb & 0xff000000;//锟斤拷锟斤拷锟轿伙拷锟�4-31锟斤拷锟斤拷锟斤拷息锟斤拷alpha通锟斤拷锟斤拷锟芥储锟斤拷a锟斤拷锟斤拷
			        int r = (rgb >> 16) & 0xff;//取锟斤拷锟轿革拷位锟斤拷16-23锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
			        //System.out.println(Integer.toBinaryString((int)0xff));
			        int bb = rgb & 0xff;//取锟斤拷锟斤拷位锟斤拷0-7锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
					int gray = (rgb & 0xff) ;
					int gg = (rgb >> 8) & 0xff;
					// 锟斤拷锟斤拷叶锟�
					if (bb!=0||r!=0||gg!=0xff) {
						gray=gray+256/(CrossBand.m*CrossBand.m)<=255?gray+256/(CrossBand.m*CrossBand.m):255;
						int newRgb= a | (gray << 16) | (gray << 8) | gray;//
						grayImage.setRGB(x, y, newRgb);

					} else {
						// System.out.println("123123123");
					}

				}
			}
		}else{
			float k = (x2 - x1) / (y2 - y1);
			float b = x1 - y1 * (x2 - x1) / (y2 - y1);
           // System.out.println(y1+"###222222222###"+y2);
			if (y1 < y2) {
				for (int y = (int) y1; y < (int) y2; ++y) {
					int x = (int) (k * y + b);
					// System.out.println(x+"...."+y);
					int rgb = grayImage.getRGB(x, y);
			        int r = (rgb >> 16) & 0xff;//取锟斤拷锟轿革拷位锟斤拷16-23锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
			        //System.out.println(Integer.toBinaryString((int)0xff));
			        int bb = rgb & 0xff;//取锟斤拷锟斤拷位锟斤拷0-7锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
					int a = rgb & 0xff000000;
					int gray = (rgb & 0xff) ;
					int gg = (rgb >> 8) & 0xff;
					// 锟斤拷锟斤拷叶锟�
					if (bb!=0||r!=0||gg!=0xff) {
						gray=gray+256/(CrossBand.m*CrossBand.m)<=255?gray+256/(CrossBand.m*CrossBand.m):255;
						int newRgb= a | (gray << 16) | (gray << 8) | gray;//
						grayImage.setRGB(x, y, newRgb);

					} else {
						// System.out.println("123123123");
					}

				}
			}else{
				for (int y = (int) y2; y < (int) y1; ++y) {
					int x =  (int) (k * y + b);
					//System.out.println(x+"...."+y);
					int rgb = grayImage.getRGB(x, y);
			        int a = rgb & 0xff000000;//锟斤拷锟斤拷锟轿伙拷锟�4-31锟斤拷锟斤拷锟斤拷息锟斤拷alpha通锟斤拷锟斤拷锟芥储锟斤拷a锟斤拷锟斤拷
			        int r = (rgb >> 16) & 0xff;//取锟斤拷锟轿革拷位锟斤拷16-23锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
			        //System.out.println(Integer.toBinaryString((int)0xff));
			        int bb = rgb & 0xff;//取锟斤拷锟斤拷位锟斤拷0-7锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
					int gray = (rgb & 0xff) ;
					int gg = (rgb >> 8) & 0xff;
					// 锟斤拷锟斤拷叶锟�
					if (bb!=0||r!=0||gg!=0xff) {
						gray=gray+256/(CrossBand.m*CrossBand.m)<=255?gray+256/(CrossBand.m*CrossBand.m):255;
						int newRgb= a | (gray << 16) | (gray << 8) | gray;
                        //System.out.println(Integer.toBinaryString(newRgb));
						grayImage.setRGB(x, y, newRgb);

					} else {
						// System.out.println("123123123");
					}

				}
			}
		}

		ImageIcon lineImage = new ImageIcon(); 
		lineImage.setImage(grayImage.getScaledInstance(CrossBand.frameWidth/2,CrossBand.frameHeight,Image.SCALE_DEFAULT));
		CBFrame.imageLabel.setIcon(lineImage);
	}

	public static long caculateBlackThickness(BufferedImage image, float x1, float y1, float x2, float y2) {
		

		long sumBlack = 0;
		int sum=0;
		if(Math.abs(x1-x2)>Math.abs(y1-y2)){
			float k = (y2 - y1) / (x2 - x1);
			float b = y1 - x1 * (y2 - y1) / (x2 - x1);
			if (x1 < x2) {
				for (int x = (int) x1; x < (int) x2; ++x) {
					int y = (int) (k * x + b);
					//System.out.println("x="+x+"  y="+y);

					int rgb = image.getRGB(x, y);
			        int a = rgb & 0xff000000;//锟斤拷锟斤拷锟轿伙拷锟�4-31锟斤拷锟斤拷锟斤拷息锟斤拷alpha通锟斤拷锟斤拷锟芥储锟斤拷a锟斤拷锟斤拷
			        int r = (rgb >> 16) & 0xff;//取锟斤拷锟轿革拷位锟斤拷16-23锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
			        int g = (rgb >> 8) & 0xff;//取锟斤拷锟斤拷位锟斤拷8-15锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
			        //System.out.println(Integer.toBinaryString((int)0xff));
			        int bb = rgb & 0xff;//取锟斤拷锟斤拷位锟斤拷0-7锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
					int gray = (rgb & 0xff) ;

					
					// 锟斤拷锟斤拷叶锟�
					if (bb!=0||r!=0||g!=0xff) {

						sumBlack += gray;
						sum++;
					} else {
						// System.out.println("123123123");
					}

				}
			}else{
				for (int x = (int) x2; x < (int) x1; ++x) {
					int y = (int) (k * x + b);
					//image.setRGB(x, y, 0xffff0000);
					//System.out.println("x="+x+"  y="+y);

					int rgb = image.getRGB(x, y);

			        int a = rgb & 0xff000000;//锟斤拷锟斤拷锟轿伙拷锟�4-31锟斤拷锟斤拷锟斤拷息锟斤拷alpha通锟斤拷锟斤拷锟芥储锟斤拷a锟斤拷锟斤拷
			        int r = (rgb >> 16) & 0xff;//取锟斤拷锟轿革拷位锟斤拷16-23锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
			        int g = (rgb >> 8) & 0xff;//取锟斤拷锟斤拷位锟斤拷8-15锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
			        //System.out.println(Integer.toBinaryString((int)0xff));
			        int bb = rgb & 0xff;//取锟斤拷锟斤拷位锟斤拷0-7锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
					int gray = (rgb & 0xff) ;

					// 锟斤拷锟斤拷叶锟�
                   // System.out.println(Integer.toBinaryString(rgb));
					if (bb!=0||r!=0||g!=0xff) {
					//	System.out.println(gray);
						sumBlack += gray;
						sum++;
					} else {
						// System.out.println("123123123");
					}

				}
			}
		}
		else{
			float k = (x2 - x1) / (y2 - y1);
			float b = x1 - y1 * (x2 - x1) / (y2 - y1);
			if (y1 < y2) {
				for (int y = (int) y1; y < (int) y2; ++y) {
					int x = (int) (k * y + b);
				//	System.out.println("x="+x+"  y="+y);

					int rgb = image.getRGB(x, y);
			        int a = rgb & 0xff000000;//锟斤拷锟斤拷锟轿伙拷锟�4-31锟斤拷锟斤拷锟斤拷息锟斤拷alpha通锟斤拷锟斤拷锟芥储锟斤拷a锟斤拷锟斤拷
			        int r = (rgb >> 16) & 0xff;//取锟斤拷锟轿革拷位锟斤拷16-23锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
			        int g = (rgb >> 8) & 0xff;//取锟斤拷锟斤拷位锟斤拷8-15锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
			        //System.out.println(Integer.toBinaryString((int)0xff));
			        int bb = rgb & 0xff;//取锟斤拷锟斤拷位锟斤拷0-7锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
					int gray = (rgb & 0xff) ;
					

					// 锟斤拷锟斤拷叶锟�
					if (bb!=0||r!=0||g!=0xff) {

						sumBlack += gray;
						sum++;
					} else {
						// System.out.println("123123123");
					}

				}
			}else{
				for (int y = (int) y2; y < (int) y1; ++y) {
					int x = (int) (k * y + b);
					
					//System.out.println("x="+x+"  y="+y);

					int rgb = image.getRGB(x, y);
			        int a = rgb & 0xff000000;//锟斤拷锟斤拷锟轿伙拷锟�4-31锟斤拷锟斤拷锟斤拷息锟斤拷alpha通锟斤拷锟斤拷锟芥储锟斤拷a锟斤拷锟斤拷
			        int r = (rgb >> 16) & 0xff;//取锟斤拷锟轿革拷位锟斤拷16-23锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
			        int g = (rgb >> 8) & 0xff;//取锟斤拷锟斤拷位锟斤拷8-15锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
			        //System.out.println(Integer.toBinaryString((int)0xff));
			        int bb = rgb & 0xff;//取锟斤拷锟斤拷位锟斤拷0-7锟斤拷锟斤拷色锟斤拷锟斤拷锟斤拷锟斤拷息
					int gray = (rgb & 0xff) ;

					// 锟斤拷锟斤拷叶锟�
					if (bb!=0||r!=0||g!=0xff) {
						sumBlack += gray;
						sum++;
					} else {
						// System.out.println("123123123");
					}

				}
			}
		
		}

		// System.out.println("sumGray="+sumBlack);
		//System.out.println("x1=" + x1 + "  " + "y1=" + y1 + "   " + "x2=" + x2 + "   " + "y2=" + y2);
		if(sum!=0)
			return sumBlack/(sum);
		else
			return Long.MAX_VALUE;
	}
}
