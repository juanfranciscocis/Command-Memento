package command;

import java.awt.*;
import java.util.ArrayList;

public class CmdDrawRedLine implements CmdInterface {
    private int instX, instY, instDx, instDy;
    protected PaintPanel pp;
    Draw receiver;


    public CmdDrawRedLine(Draw receiver, PaintPanel pp, Color color) {
        this.receiver = receiver;
        this.pp = pp;
    }

    @Override
    public void execute() {
        receiver.justClicked(true);

        receiver.createLines();
        instX = receiver.getX();
        instY = receiver.getY();
        instDx = receiver.getDx();
        instDy = receiver.getDy();

        pp.repaint();
    }

    public int getX() {
        return this.instX;
    }

    public int getY() {
        return this.instY;
    }


    public int getDx() {
        return this.instDx;
    }

    public int getDy() {
        return this.instDy;
    }

    public Color getColor() {
        return Color.RED;
    }

    @Override
    public void unDo() {
        receiver.unCreateLines();
        pp.repaint();
    }
}
