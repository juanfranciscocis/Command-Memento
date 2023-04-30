package command;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author dfellig
 */
public class PaintPanel extends JPanel {

    private int x, y, dx, dy;
    ArrayList<CmdInterface> drawList;
    Draw draw;

    public void setDraw(Draw draw) {
        this.draw = draw;
    }

    public void setDrawList(ArrayList drawList) {
        this.drawList = drawList;
    }


    private void drawLines(Graphics g) {


        for (CmdInterface cmd : drawList) {
            g.setColor(cmd.getColor());
            x = cmd.getX();
            y = cmd.getY();
            dx = cmd.getDx();
            dy = cmd.getDy();
            g.drawLine(x, y, dx, dy);

        }
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        drawLines(g);
    }

}
