package org.qmbupt.grp88.UI;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableCellRendererButton implements TableCellRenderer {
    private JButton button;

    public TableCellRendererButton() {
        this.initButton();
    }

    private void initButton() {
        this.button = new JButton("show image");

    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
                                                   int column) {
        return this.button;
    }

}