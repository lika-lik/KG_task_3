package com.company;

import com.company.point.ScreenPoint;
import java.util.ArrayList;

public class LineTransformer {
    private ArrayList<ScreenPoint> lineFrom;
    private ArrayList<ScreenPoint> lineTo;
    private double time;

    public LineTransformer(ArrayList<ScreenPoint> lineFrom, ArrayList<ScreenPoint> lineTo, int time) {
        this.lineFrom = lineFrom;
        this.lineTo = lineTo;
        this.time = time;
        int n = Math.max(lineFrom.size(), lineTo.size());
        if (lineFrom.size() < n){
            addPointsToN(lineFrom, n);
        }else if (lineTo.size() < n)
            addPointsToN(lineTo, n);
    }

    private void addPointsToN(ArrayList<ScreenPoint> line, int n){
        int k = n - line.size();
        ScreenPoint lastPoint = line.get(line.size()-1);
        for (int i = 0; i < k; i ++)
            line.add(lastPoint);
    }

    public ArrayList<ScreenPoint> change(int timePast){
        ArrayList<ScreenPoint> lineMove = new ArrayList<>();
        ScreenPoint pFrom, pTo;
        int xMove, yMove;
        double delX, delY;
        for (int i = 0; i < lineFrom.size(); i ++) {
            pFrom = lineFrom.get(i);
            pTo = lineTo.get(i);
            delX = (pTo.getX() - pFrom.getX())/time;
            delY = (pTo.getY() - pFrom.getY())/time;
            xMove = (int)(pFrom.getX() + delX*timePast);
            yMove = (int)(pFrom.getY() + delY*timePast);
            lineMove.add(new ScreenPoint(xMove, yMove));
        }
        return lineMove;
    }
}
