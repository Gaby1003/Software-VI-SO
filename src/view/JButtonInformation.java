package view;

import javax.swing.*;
import java.awt.*;

public class JButtonInformation extends JButton {
    private int arcW;
    private int arcH;

    public JButtonInformation(int acrW, int arcH, String text, Color back, Color colFont, Font font) {
        super(text);
        this.arcH = arcH;
        this.arcW = acrW;
        this.setBackground(back);
        this.setForeground(colFont);
        this.setFont(font);
        this.setContentAreaFilled(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.setOpaque(false);
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcW, arcH);
        super.paintComponent(g2);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getBackground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcW, arcH);
    }
}
