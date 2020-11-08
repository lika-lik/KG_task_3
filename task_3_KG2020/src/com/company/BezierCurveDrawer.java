package com.company;

import com.company.line.LineDrawer;
import com.company.point.RealPoint;
import com.company.point.ScreenConverter;
import com.company.point.ScreenPoint;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class BezierCurveDrawer {
    LineDrawer ld;
    ScreenConverter sc;

    public BezierCurveDrawer(LineDrawer ld, ScreenConverter sc) {
        this.ld = ld;
        this.sc = sc;
    }

    public void bezierDraw(ArrayList<ScreenPoint> sourcePoints){
        ArrayList<ScreenPoint> finalPoints = new ArrayList<>();

        for (double t=0; t<=1; t += 0.01)
            finalPoints.add(calculateBezierFunction(t, sourcePoints));
        drawCurve(finalPoints);
    }

    private ScreenPoint calculateBezierFunction(double t, ArrayList<ScreenPoint> srcPoints)
    {   double x = 0;
        double y = 0;

        int n = srcPoints.size() - 1;
        for (int i=0; i <= n; i++)
        {
            x += fact(n)/(fact(i)*fact(n-i)) * srcPoints.get(i).getX() * Math.pow(t, i) * Math.pow(1-t, n-i);
            y += fact(n)/(fact(i)*fact(n-i)) * srcPoints.get(i).getY() * Math.pow(t, i) * Math.pow(1-t, n-i);
        }
        return new ScreenPoint((int)x, (int)y);
    }

    private double fact(double arg){
        if (arg == 0) return 1;

        double result = 1;
        for (int i=1; i<=arg; i++)
            result *= i;
        return result;
    }

    private void drawCurve(ArrayList<ScreenPoint> points){
        for (int i = 1; i < points.size(); i++)
        {
            int x1 = (points.get(i-1).getX());
            int y1 = (points.get(i-1).getY());
            int x2 = (points.get(i).getX());
            int y2 = (points.get(i).getY());
            ld.drawLine(new ScreenPoint(x1, y1), new ScreenPoint(x2, y2));

        }
    }
}
