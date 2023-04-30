package command;

import java.awt.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author dfellig
 */
public class Draw {
    /**/
    private int x, y, dx = 20, dy;
    int instX, instY, instDx, instDy;
    private Color color;
    private boolean justClicked = false;
    private PaintPanel pp;
    private  int panelWidth;
    private  boolean isAbleToGenLine;
    private  boolean isAbleToUnGenLine;

    public Draw(PaintPanel pp) {
        this.pp = pp;

        pp.setDraw(this);
    }

    private void generateLineParams() {
        if (isLineWithinPanelOnRightSide()) {
            x += 20;
            dx += 20;
            dy = 340;
        }
    }



    public void justClicked(boolean clicked) {
        justClicked = clicked;
    }

    private void unGenerateLineParams() {
        if (isLineWithinPanelOnLeftSide()) {
            x -= 20;
            dx -= 20;
            dy = 340;
        }
    }


    public boolean isLineWithinPanelOnRightSide() {
        panelWidth = pp.getWidth();
        if (dx + 40 < panelWidth) {
            isAbleToGenLine = true;
        } else {
            isAbleToGenLine = false;
        }
        return isAbleToGenLine;
    }

    public boolean isLineWithinPanelOnLeftSide() {
        panelWidth = pp.getWidth();
        if (dx - 20 > 0) {
            isAbleToUnGenLine = true;
        } else {
            isAbleToUnGenLine = false;
        }
        return isAbleToUnGenLine;
    }


    public void undoLineParams() {
        x -= 20;
        dx -= 20;
        isAbleToGenLine = true;
    }


    public void unCreateLines() {
        if (instX == 0 || justClicked) {
            unGenerateLineParams();
            this.instX = x;
            this.instY = y;
            this.instDx = dx;
            this.instDy = dy;
        }

    }

    public void createLines() {
        if (instX == 0 || justClicked) {
            generateLineParams();
            this.instX = x;
            this.instY = y;
            this.instDx = dx;
            this.instDy = dy;
        }

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


    public void unDo() {

        undoLineParams();
    }
}
