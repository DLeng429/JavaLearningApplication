package org.qmbupt.grp88.UI;

import javax.swing.*;

public class ImageDialog extends JDialog {

    private JLabel label;

    public ImageDialog(JPanel owner, String title, String imagePath) {
        //super(owner, title);
        ImageIcon imageIcon = new ImageIcon(imagePath);
        label = new JLabel(imageIcon);
        int iconHeight = imageIcon.getIconHeight();
        int iconWidth = imageIcon.getIconWidth();
        this.setSize(iconWidth, iconHeight);
        this.add(label);
        this.setLocationRelativeTo(owner);
    }
}
