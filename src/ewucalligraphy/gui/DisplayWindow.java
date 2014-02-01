
package ewucalligraphy.gui;

/**
 *
 * @author David McInnis <davidm@eagles.ewu.edu>
 */

import ewucalligraphy.image.WholeImage;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;


public class DisplayWindow extends javax.swing.JFrame
{
    private BufferedImage fileImage;
    private final int[]   imageSize = new int[2];
    /**
     * Creates new form DisplayWindow
     */
    public DisplayWindow()
    {
        initComponents();
    }
    
    public void setImage(BufferedImage IfileImage)
    {
        fileImage = IfileImage;
        
        imageSize[0] = fileImage.getHeight();
	imageSize[1] = fileImage.getWidth();
    }
    
    	private final int oldWindowSize[] = new int[2];
	private final int newWindowSize[] = new int[2];

	private final int edgeOffset = 10;
	private final int topOffset = 30;
	private boolean drawed = false;
    
    	@Override
	public void paint(Graphics g)
	{
		super.paint(g);

                //This part scales the image to fit within the window

		if(fileImage != null)
		{
			newWindowSize[0] = this.getHeight();
			newWindowSize[1] = this.getWidth();

			boolean windowChanged = (newWindowSize[0] != oldWindowSize[0]) ||
									(newWindowSize[1] != oldWindowSize[1]);

			if(windowChanged || !drawed)
			{
				oldWindowSize[0] = newWindowSize[0];
				oldWindowSize[1] = newWindowSize[1];

				int windowRatio = newWindowSize[0] * imageSize[1];
				int picRatio    = imageSize[0] * newWindowSize[1];

				int newImageSizeWidth, newImageSizeLength;
				newImageSizeWidth = 0; newImageSizeLength = 0;

				if(windowRatio < picRatio)
				{
					//window not long enough
					newImageSizeLength = newWindowSize[0] - edgeOffset - topOffset;
					newImageSizeWidth = (newImageSizeLength * imageSize[1]) / imageSize[0];
				}
				else
				{
					//window not wide enough
					newImageSizeWidth = newWindowSize[1] - edgeOffset * 2;
					newImageSizeLength = (newImageSizeWidth * imageSize[0]) / imageSize[1];
				}




				if((newImageSizeWidth > 0 && newImageSizeLength > 0) || !drawed)
				{
					Image scaledImage = fileImage.getScaledInstance(newImageSizeWidth, newImageSizeLength, Image.SCALE_FAST);
					drawed = g.drawImage(scaledImage, edgeOffset, topOffset, newImageSizeWidth, newImageSizeLength, null);
				}
			}
		}
                
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMinimumSize(new java.awt.Dimension(300, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm




    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
