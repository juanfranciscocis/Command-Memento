package command;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;

/**
 * @author dfellig
 */
public interface CmdInterface {
    public int getX();

    public int getY();


    public int getDx();

    public int getDy();

    public Color getColor();


    public void execute();

    public void unDo();
}
