package com.company;

import com.company.line.DDALineDrawer;
import com.company.line.Line;
import com.company.line.LineDrawer;
import com.company.point.RealPoint;
import com.company.point.ScreenConverter;
import com.company.point.ScreenPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {

    private ScreenConverter sc = new ScreenConverter(-2, 2, 4, 4, 800, 600);
    private ArrayList<ScreenPoint> sourcePoints1 = new ArrayList<>();
    private ArrayList<ScreenPoint> sourcePoints2 = new ArrayList<>();

    private boolean is1lineComplete = false;
    private boolean is2lineComplete = false;
    private boolean isStartMove = false;
    private  int time;

    public void setTime(int time) {
        this.time = time;
    }

    public void setStartMove(boolean startMove) {
        isStartMove = startMove;
    }

    public void setIs1lineComplete(boolean is1lineComplete) {
        this.is1lineComplete = is1lineComplete;
    }

    public void setIs2lineComplete(boolean is2lineComplete) {
        this.is2lineComplete = is2lineComplete;
    }

    public DrawPanel(){
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        sc.setsW(getWidth());
        sc.setsH(getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_BGR);
        Graphics2D gr = bi.createGraphics();
        gr.setColor(Color.white);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.dispose();
        PixelDrawer pd = new BufferedImagePixelDrawer(bi);
        LineDrawer ld = new DDALineDrawer(pd);

        BezierCurveDrawer bcd1 = new BezierCurveDrawer(ld, sc);
        bcd1.bezierDraw(sourcePoints1);

        ld.setColor(Color.black);

        BezierCurveDrawer bcd2 = new BezierCurveDrawer(ld, sc);
        bcd2.bezierDraw(sourcePoints2);

        if (isStartMove){
            ld.setColor(Color.red);
            /*MyTimerTask timerTask = new MyTimerTask(new BezierCurveDrawer(ld, sc),
                    new LineTransformer(sourcePoints1, sourcePoints2, time));
            Timer timer = new Timer();
            timer.schedule(timerTask, 100);

             */
            drawMove(new BezierCurveDrawer(ld, sc));
        }

        g.drawImage(bi, 0, 0, null);

    }

    public void drawMove(BezierCurveDrawer bcd){
        LineTransformer lt = new LineTransformer(sourcePoints1, sourcePoints2, time);
        for (int i = 0; i <= time; i ++){
            bcd.bezierDraw(lt.change(i));
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!is1lineComplete) {
            sourcePoints1.add(new ScreenPoint(e.getX(), e.getY()));
        }else if(!is2lineComplete){
            sourcePoints2.add(new ScreenPoint(e.getX(), e.getY()));
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(!is1lineComplete) {
            sourcePoints1.add(new ScreenPoint(e.getX(), e.getY()));
        }else if(!is2lineComplete){
            sourcePoints2.add(new ScreenPoint(e.getX(), e.getY()));
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private class MyTimerTask extends TimerTask {
        int i = -1;
        BezierCurveDrawer bcd;
        LineTransformer lt;

        public MyTimerTask(BezierCurveDrawer bcd, LineTransformer lt) {
            this.bcd = bcd;
            this.lt = lt;
        }

        @Override
        public void run() {
            i++;
            bcd.bezierDraw(lt.change(i));
            repaint();
        }
    }

}
