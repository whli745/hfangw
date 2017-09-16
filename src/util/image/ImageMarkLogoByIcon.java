package util.image;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 图片加水印，设置透明度 http://blog.csdn.net/hfmbook
 * 
 * @author Gary 创建日期：2014年12月16日 22:45:17
 */
public class ImageMarkLogoByIcon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String srcImgPath = "f:/d2b4f0cc-d3e0-4310-978b-97a8792c8e08.jpg";
		String iconPath = "f:/logo.png";
		String targerPath = "f:/d2b4f0cc-d3e0-4310-978b-97a8792c8e08.jpg";
		// 给图片添加水印
		ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath, targerPath,
				null);
	}

	/**
	 * 给图片添加水印
	 * 
	 * @param iconPath
	 *            水印图片路径
	 * @param srcImgPath
	 *            源图片路径
	 * @param targerPath
	 *            目标图片路径
	 */
	public static void markImageByIcon(String iconPath, String srcImgPath,
			String targerPath) {
		markImageByIcon(iconPath, srcImgPath, targerPath, null);
	}

	/**
	 * 给图片添加水印、可设置水印图片旋转角度
	 * 
	 * @param iconPath
	 *            水印图片路径
	 * @param srcImgPath
	 *            源图片路径
	 * @param targerPath
	 *            目标图片路径
	 * @param degree
	 *            水印图片旋转角度
	 */
	public static void markImageByIcon(String iconPath, String srcImgPath,
			String targerPath, Integer degree) {
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));
			int width = srcImg.getWidth(null);
			int height = srcImg.getHeight(null);
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象
			// Graphics g= buffImg.getGraphics();
			Graphics2D g = buffImg.createGraphics();

			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			g.drawImage(
					srcImg.getScaledInstance(srcImg.getWidth(null),
							srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
					null);

			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree),
						(double) buffImg.getWidth() / 2,
						(double) buffImg.getHeight() / 2);
			}
			// 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
			ImageIcon imgIcon = new ImageIcon(iconPath);
			// 得到Image对象。
			Image img = imgIcon.getImage();
			float alpha = 0.8f; // 透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			int width_biao = img.getWidth(null);
			int height_biao = img.getHeight(null);
			double rate = 0.12;
			double rate_1 =  width * rate/width_biao;
			if(rate_1 > 1){
				
			}else{
				width_biao = (int)(width * rate);
				height_biao = (int)(height_biao * rate_1);
				imgIcon.setImage(imgIcon.getImage().getScaledInstance(width_biao,height_biao,Image.SCALE_SMOOTH ));
			}
			// 如果水印图片高或者宽大于目标图片是做的处理,使其水印宽或高等于目标图片的宽高，并且等比例缩放
			int x = width - width_biao - 35;
			int y = height - height_biao - 35;
			
			
			// 表示水印图片的位置
			g.drawImage(imgIcon.getImage(), x, y, null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}