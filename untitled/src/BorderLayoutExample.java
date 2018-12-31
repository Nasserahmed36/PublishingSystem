import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class BorderLayoutExample extends JFrame {

    public static void main(String[] args) {
        BorderLayoutExample bs = new BorderLayoutExample();
        bs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = bs.getContentPane();
        pane.setLayout(new BorderLayout());
        JLabel label = new JLabel("North", JLabel.CENTER);
        label.setFont(new Font("Courier", Font.BOLD, 36));
        label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        JPanel northJPanel = new JPanel();




        northJPanel.setLayout(new FlowLayout(FlowLayout.LEFT));




        northJPanel.add(label);
        pane.add(northJPanel, BorderLayout.NORTH);
        label = new JLabel("South", JLabel.CENTER);
        label.setFont(new Font("Courier", Font.BOLD, 36));
        label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        pane.add(label, BorderLayout.SOUTH);
        label = new JLabel("East", JLabel.CENTER);
        label.setFont(new Font("Courier", Font.BOLD, 36));
        label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        pane.add(label, BorderLayout.EAST);
        label = new JLabel("West", JLabel.CENTER);
        label.setFont(new Font("Courier", Font.BOLD, 36));
        label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        pane.add(label, BorderLayout.WEST);
        label = new JLabel("Center", JLabel.CENTER);
        label.setFont(new Font("Courier", Font.BOLD, 36));
        label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        pane.add(label, BorderLayout.CENTER);
        bs.setSize(400, 300);
        bs.setVisible(true);
    }

}