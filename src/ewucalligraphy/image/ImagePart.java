/*
 * Copyright (C) 2014 David McInnis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ewucalligraphy.image;

import ewucalligraphy.gui.DisplayWindow;
import static ewucalligraphy.image.BoxState.notHighlighted;
import static ewucalligraphy.image.ImgBox.buildImgBoxes;
import static ewucalligraphy.testing.FileIO.saveToFile;
import java.awt.Graphics;
import java.awt.Point;
import static java.awt.color.ColorSpace.TYPE_GRAY;
import static java.awt.color.ColorSpace.TYPE_RGB;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author David McInnis <davidm@eagles.ewu.edu>
 */

//TODO: Declare the array as a byte-array to save memory

public final class ImagePart {
    
    private DisplayWindow myWindow;
    private BufferedImage myImage;    
    private int[] [][] imG;
    private Statistics[]  imGStats;
    private int imgHeight;
    private int imgWidth;
    private int imgDepth;
    private String myName;
    private boolean isGray;
    
    
    private LinkedList<ImgBox> foundBoxes;
    private int[][][] bigImg;
    
    public ImagePart(BufferedImage inImage, String imageName)
    {
	myImage = inImage;
	myName = imageName;
        buildIntArray();
    
        myWindow = new DisplayWindow(this, myImage);
        
        myWindow.setVisible(true);
    
    }
    
      
    public void buildBoxes(boolean findDarkest)
    {
        if(isGray)
        {
            foundBoxes = buildImgBoxes(imG[0], imGStats[0], findDarkest);

            myWindow.repaint();
        }
        else
        {
            showMessageDialog(null, "Color Images not yet stupported", "Color Images not yet stupported", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    public void exportForGnuPlot()
    {
       
        String[] data = imGStats[0].getGnuPlotVertHorizSums();
       
        saveToFile(data[0], myName + "-Vert.dat");
        saveToFile(data[1], myName + "-Horiz.dat");
        
        
    }
        

    private void buildIntArray()
    {
	Raster myTile = myImage.getTile(0, 0);
	ColorModel myColorModel = myImage.getColorModel();
	
	assert(myColorModel.hasAlpha());
        
	switch(myColorModel.getColorSpace().getType())
	{
	    case TYPE_GRAY:
		buildModel(myTile, 1); //1 color channel
                isGray = true;
		break;
	    case TYPE_RGB:
		buildModel(myTile, 3); //3 color channels
                isGray = false;
		break;
	    default:
		System.out.println("Unexpected ColorSpace Detected: " +myColorModel.getColorSpace().getType());
		break;
	}

    }

    
    
    
    //TODO: Improve efficiency for image building
    
    private void buildModel(Raster myTile, int depth) {
	imgHeight = myTile.getHeight();
	imgWidth  = myTile.getWidth();
	imgDepth  = depth;
        imGStats = new Statistics[imgDepth];
	
        imG = new int[imgDepth] [imgWidth][imgHeight];
	
	int[] myPixel = new int[imgDepth];
        int x, y, z;
	
	
	int[] intArray = null;
	
	//0 = darkest 255 = lightest
	
	for(x = 0; x < imgWidth; ++x)
	{
	    for(y = 0; y < imgHeight; ++y)
	    {
		myPixel = myTile.getPixel(x, y, intArray); //TODO: Improve efficiency by adopting a better strategy

		for(z = 0; z < imgDepth; ++z)
		{
                    imG[z] [x][y] = myPixel[z];
		}
	    }
	}
        
        for(z = 0; z < imgDepth; ++z)
        {
            imGStats[z] = new Statistics(imG[z]);
        }

    }
    
     
    public void dispose()
    {
        myWindow.dispose();
    }

    public void drawBoxes(Graphics g)
    {
        if(foundBoxes != null)
        {
            for(ImgBox curBox: foundBoxes)
            {
                curBox.drawBox(g, myWindow);
            }
        }
     }
    
    public void drawChangedBoxes(Graphics g)
    {
        for(ImgBox curBox : boxesToRedraw)
        {
            curBox.drawBox(g, myWindow);
        }
        boxesToRedraw.clear();
    }

    private final LinkedList<ImgBox> boxesToRedraw = new LinkedList<>();
    private ImgBox lastHighLightedBox, selectedBox;

    
    public boolean detectMouseOver(Point relLocation)
    {
       boolean redrawBoxes = false;
        
        if(pointIsInsideWindow(relLocation))
        {
            for(ImgBox curBox : foundBoxes) //TODO: Implement search method with improved efficiency
            {
                boolean redrawBox = curBox.detectMouseOver(relLocation);
                if(redrawBox)
                {
                    BoxState curState = curBox.getState();
                    
                    switch(curState)
                    {
                        case selected:
                            //Do nothing, do not highlight a box that's highlighted
                            break;
                        case highlighted:
                            lastHighLightedBox = curBox;
                        case notHighlighted:
                            boxesToRedraw.add(curBox);
                            redrawBoxes = true;
                            break;
                    }
                }
            } 

        }
        return redrawBoxes;
    }

    public boolean selectThisBox(Point mousePos)
    {
        boolean boxChanged = false;

        if(pointIsInsideWindow(mousePos) && lastHighLightedBox != null)
        {
            boxChanged = lastHighLightedBox.setActive(mousePos);
            if(boxChanged)
            {
                if(selectedBox != null)
                {
                    selectedBox.deselect();
                    boxesToRedraw.add(selectedBox);
                }
                boxesToRedraw.add(lastHighLightedBox);
                selectedBox = lastHighLightedBox;
            }
        }
        return boxChanged;
    }
    
    private boolean pointIsInsideWindow(Point inPt)
    {
        return         (inPt.x >= 0 &&
                        inPt.y >= 0 &&
                        inPt.x <= imgWidth &&
                        inPt.y <= imgHeight &&
                        foundBoxes != null);
    }
}
