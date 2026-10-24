// Copyright 2002, FreeHEP.
package com.office.viewer.thirdpart.emf.data;

import android.graphics.Point;

import java.io.IOException;

import com.office.viewer.java.awt.Rectangle;
import com.office.viewer.java.awt.geom.GeneralPath;
import com.office.viewer.thirdpart.emf.EMFInputStream;
import com.office.viewer.thirdpart.emf.EMFRenderer;
import com.office.viewer.thirdpart.emf.EMFTag;

/**
 * PolylineTo TAG.
 * 
 * @author Mark Donszelmann
 * @version $Id: EMFPolygon.java 10367 2007-01-22 19:26:48Z duns $
 */
public class EMFPolygon extends AbstractPolygon {

    public EMFPolygon() {
        super(3, 1, null, 0, null);
    }

    public EMFPolygon(Rectangle bounds, int numberOfPoints, Point[] points) {
        super(3, 1, bounds, numberOfPoints, points);
    }

    protected EMFPolygon (int id, int version, Rectangle bounds, int numberOfPoints, Point[] points) {
        super(id, version, bounds, numberOfPoints, points);
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len)
            throws IOException {

        Rectangle r = emf.readRECTL();
        int n = emf.readDWORD();
        return new EMFPolygon(r, n, emf.readPOINTL(n));
    }

    /**
     * displays the tag using the renderer
     *
     * @param renderer EMFRenderer storing the drawing session data
     */
    public void render(EMFRenderer renderer) {
        Point[] points = getPoints();

        // Safety check.
        if (points.length > 1) {
            GeneralPath path = new GeneralPath(
                renderer.getWindingRule());
            path.moveTo((float)points[0].x, (float)points[0].y);
            for (int i = 1; i < points.length; i++) {
                path.lineTo((float)points[i].x, (float)points[i].y);
            }
            path.closePath();
            renderer.fillAndDrawOrAppend(path);
        }
    }
}
