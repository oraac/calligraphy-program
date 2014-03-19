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

        jButtonFileOpen = new javax.swing.JButton();
        jButtonPlusBox = new javax.swing.JButton();
        jButtonMinusBox = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuFileExit = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuHelpAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EwuCalligraphy");
        setFont(new java.awt.Font("Monospaced", 0, 10)); // NOI18N
        setMinimumSize(new java.awt.Dimension(50, 100));
        setName("ewuCalligraphy"); // NOI18N

        jButtonFileOpen.setText("Open");
        jButtonFileOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFileOpenActionPerformed(evt);
            }
        });

        jButtonPlusBox.setText("+ Box");
        jButtonPlusBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlusBoxActionPerformed(evt);
            }
        });

        jButtonMinusBox.setText("- Box");
        jButtonMinusBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinusBoxActionPerformed(evt);
            }
        });

        jMenuFile.setText("File");

        jMenuFileExit.setText("Exit");
        jMenuFileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFileExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuFileExit);

        jMenuBar1.add(jMenuFile);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonFileOpen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(jButtonPlusBox))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonMinusBox)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addComponent(jButtonMinusBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFileOpen)
                    .addComponent(jButtonPlusBox))
                .addContainerGap())
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

    private void jButtonFileOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFileOpenActionPerformed
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
                                        windowDisplay.wipeLines();
                                        windowDisplay.repaint();
                                        
                                        
                                        wholeImage.segmentImage(windowDisplay);

				}
				catch(IOException e)
				{
					JOptionPane.showMessageDialog(this, "Image Opening Failed", "Error", JOptionPane.ERROR_MESSAGE);

				}

			}
		}
    }//GEN-LAST:event_jButtonFileOpenActionPerformed

    private void jButtonPlusBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlusBoxActionPerformed
        wholeImage.growImage(windowDisplay);
    }//GEN-LAST:event_jButtonPlusBoxActionPerformed

    private void jButtonMinusBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMinusBoxActionPerformed
        wholeImage.shrinkImage(windowDisplay);        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonMinusBoxActionPerformed


	
	

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFileOpen;
    private javax.swing.JButton jButtonMinusBox;
    private javax.swing.JButton jButtonPlusBox;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuFileExit;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuHelpAbout;
    // End of variables declaration//GEN-END:variables
}
