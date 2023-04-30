package command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author dfellig
 */
public class CommandPattern {

    JLabel jlab;
    PaintPanel pp;
    HashMap<String, CmdInterface> allCmds = new HashMap<>();
    ArrayList<CmdInterface> drawLinesCmd = new ArrayList<>();
    ArrayList<CmdInterface> redoLinesCmd = new ArrayList<>();

    CommandPattern() {
        JFrame jfrm = new JFrame("Command Pattern");

        jfrm.setLocation(300, 200);

        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout ppLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();


        pp = new PaintPanel();
        pp.setLayout(ppLayout);

        LinesStateManager lineStateManager = new LinesStateManager();
        //lineStateManager.setActiveCmds(drawLinesCmd);
        lineStateManager.setPaintPanel(pp);

        gbc.gridx = 0;
        gbc.gridy = 10;

        gbc.insets = new Insets(350, 10, 10, 50);
        jfrm.add(pp);

        Button btnRed = new Button("Red");
        Button btnBlue = new Button("Blue");
        Button btnUndo = new Button("Undo");
        Button btnTakeSnapShot = new Button("Take Snapshot");
        Button btnRenderSnapShot = new Button("Render Snapshot");

        Draw drawCommand = new Draw(pp);
        pp.setDrawList(drawLinesCmd);

        btnRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CmdDrawRedLine cmdDrawRedLine = new CmdDrawRedLine(drawCommand, pp, Color.RED);
                drawLinesCmd.add(cmdDrawRedLine);
                pp.setDrawList(drawLinesCmd);

                cmdDrawRedLine.execute();
            }
        });

        btnBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CmdDrawBlueLine cmdDrawBlueLinenew = new CmdDrawBlueLine(drawCommand, pp, Color.BLUE);
                drawLinesCmd.add(cmdDrawBlueLinenew);
                pp.setDrawList(drawLinesCmd);
                cmdDrawBlueLinenew.execute();
            }
        });

        btnUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (drawLinesCmd.size() > 0){
                    CmdInterface cmd = drawLinesCmd.remove(drawLinesCmd.size() - 1);
                    redoLinesCmd.add(cmd);
                    pp.setDrawList(drawLinesCmd);
                    cmd.unDo();
                }

            }
        });


        btnTakeSnapShot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lineStateManager.getMemento(drawLinesCmd);

            }
        });

        btnRenderSnapShot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lineStateManager.setMemento();
            }
        });


        pp.add(btnUndo, gbc);
        gbc.gridx = 2;
        pp.add(btnRed, gbc);
        gbc.gridx = 3;
        pp.add(btnBlue, gbc);
        gbc.gridx = 4;
        pp.add(btnTakeSnapShot, gbc);
        gbc.gridx = 5;
        gbc.insets = new Insets(350, 10, 10, 10);
        pp.add(btnRenderSnapShot, gbc);
        jfrm.pack();
        jfrm.setVisible(true);
    }

    public static void main(String args[]) {
    // Create the frame on the event dispatching thread.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CommandPattern();
            }
        });
    }
}
