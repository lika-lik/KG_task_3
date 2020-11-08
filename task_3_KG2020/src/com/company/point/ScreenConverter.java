package com.company.point;

import com.company.point.RealPoint;
import com.company.point.ScreenPoint;

public class ScreenConverter {
    private double rX, rY, rW, rH;
    private int sW, sH;

    public ScreenConverter(double rX, double rY, double rW, double rH, int sW, int sH) {
        this.rX = rX;
        this.rY = rY;
        this.rW = rW;
        this.rH = rH;
        this.sW = sW;
        this.sH = sH;
    }

    public ScreenPoint r2s(RealPoint p){
        double x = (p.getX()-rX)*sW/rW;
        double y = (rY-p.getY())*sH/rH;
        return new ScreenPoint((int)x, (int)y);
    }

    public RealPoint s2r(ScreenPoint p){
        double x = p.getX() *rW/sW + rX;
        double y = rY - p.getY() *rH/sH;
        return new RealPoint(x, y);
    }

    public void setrX(double rX) {
        this.rX = rX;
    }

    public void setrY(double rY) {
        this.rY = rY;
    }

    public void setrW(double rW) {
        this.rW = rW;
    }

    public void setrH(double rH) {
        this.rH = rH;
    }

    public void setsW(int sW) {
        this.sW = sW;
    }

    public void setsH(int sH) {
        this.sH = sH;
    }

    public double getrX() {
        return rX;
    }

    public double getrY() {
        return rY;
    }

    public double getrW() {
        return rW;
    }

    public double getrH() {
        return rH;
    }

    public int getsW() {
        return sW;
    }

    public int getsH() {
        return sH;
    }
}
