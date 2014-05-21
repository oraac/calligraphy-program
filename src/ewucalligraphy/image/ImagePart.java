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
import static ewucalligraphy.image.ImgBox.buildImgBoxes;
import static ewucalligraphy.testing.FileIO.saveToFile;
import java.awt.Rectangle;
import static java.awt.color.ColorSpace.TYPE_GRAY;
import static java.awt.color.ColorSpace.TYPE_RGB;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.util.ArrayList;

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
    
    
    private ArrayList<ImgBox> foundBoxes;
    private ArrayList<ImagePart> childBoxes;
    private int[][][] bigImg;
    
    public ImagePart(BufferedImage inImage, String imageName)
    {
	myImage = inImage;
	myName = imageName;
        buildIntArray();
    
        myWindow = new DisplayWindow(myImage);
        
        myWindow.setVisible(true);
    
    }
    
    public ImagePart(ImgBox cropBox, int[][][] bigImg, BufferedImage bigImage)
    {
       Rectangle myRect = cropBox.getRectangel();

           myImage = bigImage.getSubimage(myRect.x, myRect.y, myRect.width, myRect.height);
           buildIntArray();  //TODO:  Super inefficient here, use bigImg instead!
       

            
            myWindow = new DisplayWindow(myImage);
            myWindow.setVisible(true);
    }
      
    public BufferedImage getImage()
    {
	return myImage;
    }
    
    public String getName()
    {
	return myName;
    }
    
    public void setImage(BufferedImage inImage)
    {
	myImage = inImage;
    }
    
    public void setName(String newName)
    {
	myName = newName;
    }

    

    
    public void buildBoxes(boolean findDarkest)
    {
        foundBoxes = buildImgBoxes(imG[0], imGStats[0], findDarkest);
        for(ImgBox curBox: foundBoxes)
        {
            curBox.drawBox(myWindow);
        }
        
        myWindow.repaint();
    }
        
    /*
    NOTE:

        childBoxes = new ArrayList<>();
                
        for(ImgBox curBox: foundBoxes)
        {
            childBoxes.add(new ImagePart(curBox, imG, myImage));
        }        
               
     */  
        
   
    public void exportForGnuPlot()
    {
       
        String[] data = imGStats[0].getGnuPlotVertHorizSums();
       
        saveToFile(data[0], myName + "-Vert.dat");
        saveToFile(data[1], myName + "-Horiz.dat");
        
        
    }
        

        
    
    private void buildIntArray()
    {
	//NOTE: There seem to be just 1 tile for jpg's < 8Mb
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

    
    
    
    //Note: This is only used to build the base image
    
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
		myPixel = myTile.getPixel(x, y, intArray); //Slow, but flexible

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
    
    public boolean isGray()
    {
        return isGray;
    }

   
    public BufferedImage zoomImages() {
         //Todo:  Create New Image Selected Area
        // First we will simply try to make a copy of the existing image
        
       Rectangle myRect = foundBoxes.get(0).getRectangel();
                
       
       
       return myImage.getSubimage(myRect.x, myRect.y, myRect.width, myRect.height);
        
    }


}