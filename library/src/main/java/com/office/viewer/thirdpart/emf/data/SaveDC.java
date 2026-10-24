// Copyright 2002, FreeHEP.
package com.office.viewer.thirdpart.emf.data;

import java.io.IOException;

import com.office.viewer.thirdpart.emf.EMFInputStream;
import com.office.viewer.thirdpart.emf.EMFRenderer;
import com.office.viewer.thirdpart.emf.EMFTag;

/**
 * SaveDC TAG.
 * 
 * @author Mark Donszelmann
 * @version $Id: SaveDC.java 10367 2007-01-22 19:26:48Z duns $
 */
public class SaveDC extends EMFTag {

    public SaveDC() {
        super(33, 1);
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len)
            throws IOException {

        return this;
    }

    /**
     * displays the tag using the renderer
     *
     * @param renderer EMFRenderer storing the drawing session data
     */
    public void render(EMFRenderer renderer) {
        renderer.saveDC();
    }
}
