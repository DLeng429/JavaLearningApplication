package org.qmbupt.grp88.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableCellEditorButton extends DefaultCellEditor {

    private JButton button;

    public TableCellEditorButton() {
        super(new JTextField());
        this.setClickCountToStart(1);
        this.initButton();
    }

    private void initButton() {
        this.button = new JButton("show image");
        this.button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableCellEditorButton.this.fireEditingCanceled();
            }
        });

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return this.button;
    }
}
