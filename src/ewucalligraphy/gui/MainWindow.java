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

package ewucalligraphy.gui;

import ewucalligraphy.image.WholeImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author David McInnis <davidm@eagles.ewu.edu>
 */

public class MainWindow extends javax.swing.JFrame
{
	private static final long serialVersionUID = 1L;

        private DisplayWindow windowDisplay;
	private AboutWindow windowAbout;
	private JFileChooser windowFileChooser;
	private BufferedImage fileImage;
	private WholeImage    wholeImage;
	/**
	 * Creates new form MainWindow
	 */
	public MainWindow()
	{
		initComponents();
	}

	public void start()
	{
		this.setVisible(true);
		windowAbout = new AboutWindow();
                windowDisplay = new DisplayWindow();

		windowFileChooser = new JFileChooser();
		FileNameExtensionFilter fileFilterJpeg;
		fileFilterJpeg = new FileNameExtensionFilter("JPEG Images", "jpg", "jpeg");
		windowFileChooser.setFileFilter(fileFilterJpeg);

	}


	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuFileOpen = new javax.swing.JMenuItem();
        jMenuFileExit = new javax.swing.JMenuItem();
        jMenuSegment = new javax.swing.JMenu();
        jMenuSegmentRun = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuHelpAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EwuCalligraphy");
        setFont(new java.awt.Font("Monospaced", 0, 10)); // NOI18N
        setMinimumSize(new java.awt.Dimension(50, 100));
        setName("ewuCalligraphy"); // NOI18N

        jMenuFile.setText("File");

        jMenuFileOpen.setText("Open");
        jMenuFileOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFileOpenActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuFileOpen);

        jMenuFileExit.setText("Exit");
        jMenuFileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFileExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuFileExit);

        jMenuBar1.add(jMenuFile);

        jMenuSegment.setText("Segment");
        jMenuSegment.setToolTipText("");

        jMenuSegmentRun.setText("run");
        jMenuSegmentRun.setToolTipText("");
        jMenuSegmentRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSegmentRunActionPerformed(evt);
            }
        });
        jMenuSegment.add(jMenuSegmentRun);

        jMenuBar1.add(jMenuSegment);

        jMenuHelp.setText("Help");

        jMenuHelpAbout.setText("About");
        jMenuHelpAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuHelpAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuHelpAbout);

        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuFileExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuFileExitActionPerformed
    {//GEN-HEADEREND:event_jMenuFileExitActionPerformed
		windowAbout.dispose();
                windowDisplay.dispose();
		this.dispose();
    }//GEN-LAST:event_jMenuFileExitActionPerformed

    private void jMenuHelpAboutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuHelpAboutActionPerformed
    {//GEN-HEADEREND:event_jMenuHelpAboutActionPerformed
		windowAbout.setVisible(true);
    }//GEN-LAST:event_jMenuHelpAboutActionPerformed

    private void jMenuFileOpenActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuFileOpenActionPerformed
    {//GEN-HEADEREND:event_jMenuFileOpenActionPerformed

	int returnVal = windowFileChooser.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = windowFileChooser.getSelectedFile();
			if(selectedFile.canRead() && selectedFile.isFile())
			{
			    String fileName = selectedFile.getName();
				try
				{
					fileImage = ImageIO.read(selectedFile);
					wholeImage = new WholeImage(fileImage, fileName);
					
                                        windowDisplay.setImage(fileImage);
                                        windowDisplay.setVisible(true);

				}
				catch(IOException e)
				{
					JOptionPane.showMessageDialog(this, "Image Opening Failed", "Error", JOptionPane.ERROR_MESSAGE);

				}

			}
		}
    }//GEN-LAST:event_jMenuFileOpenActionPerformed

    private void jMenuSegmentRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSegmentRunActionPerformed

	if(fileImage != null)
	{
	    wholeImage.segmentImage();
	}
    }//GEN-LAST:event_jMenuSegmentRunActionPerformed


	
	

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuFileExit;
    private javax.swing.JMenuItem jMenuFileOpen;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuHelpAbout;
    private javax.swing.JMenu jMenuSegment;
    private javax.swing.JMenuItem jMenuSegmentRun;
    // End of variables declaration//GEN-END:variables
}
