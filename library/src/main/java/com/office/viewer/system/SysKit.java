package com.office.viewer.system;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;

import com.office.viewer.common.bookmark.BookmarkManage;
import com.office.viewer.common.borders.BordersManage;
import com.office.viewer.common.bulletnumber.ListManage;
import com.office.viewer.common.hyperlink.HyperlinkManage;
import com.office.viewer.common.picture.PictureManage;
import com.office.viewer.powerpoint.animate.AnimationManager;
import com.office.viewer.powerpoint.model.PGBulletText;
import com.office.viewer.system.beans.CalloutView.CalloutManager;
import com.office.viewer.word.control.WPShapeManage;

public class SysKit {

    private ErrorUtil errorKit;
    private PictureManage pmKit;
    private HyperlinkManage hmKit;
    private ListManage lmKit;
    private PGBulletText pgLMKit;
    private BordersManage brKit;
    private WPShapeManage wpSMKit;
    private BookmarkManage bmKit;
    private AnimationManager animationMgr;
    private CalloutManager calloutMgr;
    private IControl control;
    private static ShapeDrawable pageNumberDrawable;

    public final boolean isDebug() {
        return false;
    }

    public final PictureManage getPictureManage() {
        if (this.pmKit == null) {
            this.pmKit = new PictureManage(this.control);
        }
        return this.pmKit;
    }

    public final HyperlinkManage getHyperlinkManage() {
        if (this.hmKit == null) {
            this.hmKit = new HyperlinkManage();
        }
        return this.hmKit;
    }

    public final ListManage getListManage() {
        if (this.lmKit == null) {
            this.lmKit = new ListManage();
        }
        return this.lmKit;
    }

    public final PGBulletText getPGBulletText() {
        if (this.pgLMKit == null) {
            this.pgLMKit = new PGBulletText();
        }
        return this.pgLMKit;
    }

    public final BordersManage getBordersManage() {
        if (this.brKit == null) {
            this.brKit = new BordersManage();
        }
        return this.brKit;
    }

    public final WPShapeManage getWPShapeManage() {
        if (this.wpSMKit == null) {
            this.wpSMKit = new WPShapeManage();
        }
        return this.wpSMKit;
    }

    public final BookmarkManage getBookmarkManage() {
        if (this.bmKit == null) {
            this.bmKit = new BookmarkManage();
        }
        return this.bmKit;
    }

    public final AnimationManager getAnimationManager() {
        if (this.animationMgr == null) {
            this.animationMgr = new AnimationManager(this.control);
        }
        return this.animationMgr;
    }

    public final CalloutManager getCalloutManager() {
        if (this.calloutMgr == null) {
            this.calloutMgr = new CalloutManager(this.control);
        }
        return this.calloutMgr;
    }

    public final void dispose() {
        this.control = null;
        if (this.errorKit != null) {
            errorKit.dispose();
            this.errorKit = null;
        }

        if (this.pmKit != null) {
            pmKit.dispose();
            this.pmKit = null;
        }

        if (this.hmKit != null) {
            hmKit.dispose();
            this.hmKit = null;
        }

        if (this.lmKit != null) {
            lmKit.dispose();
            this.lmKit = null;
        }

        if (this.pgLMKit != null) {
            pgLMKit.dispose();
            this.pgLMKit = null;
        }

        if (this.brKit != null) {
            brKit.dispose();
            this.brKit = null;
        }

        if (this.wpSMKit != null) {
            wpSMKit.dispose();
            this.wpSMKit = null;
        }

        if (this.bmKit != null) {
            bmKit.dispose();
            this.bmKit = null;
        }

        if (this.animationMgr != null) {
            animationMgr.dispose();
            this.animationMgr = null;
        }

        if (this.calloutMgr != null) {
            calloutMgr.dispose();
            this.calloutMgr = null;
        }

    }

    public final void setErrorKit(ErrorUtil var1) {
        this.errorKit = var1;
    }

    public final IControl getControl() {
        return this.control;
    }

    public final void setControl(IControl var1) {
        this.control = var1;
    }

    public SysKit(IControl control) {
        this.control = control;
    }

    public static final Drawable getPageNubmerDrawable() {
        if (pageNumberDrawable == null) {
            pageNumberDrawable = new ShapeDrawable(new RoundRectShape(new float[]{6f, 6f, 6f, 6f, 6f, 6f, 6f, 6f}, null, null));
            pageNumberDrawable.getPaint().setColor(-0x770077bc);
        }
        return pageNumberDrawable;
    }

    public static final boolean isValidateRect(int pageWidth, int pageHeight, int x, int y, int width, int height) {
        return x >= 0 && y >= 0 && x < pageWidth && y < pageHeight && width >= 0 && height >= 0 && x + width <= pageWidth && y + height <= pageHeight;
    }


    public final ErrorUtil getErrorKit() {
        if (errorKit == null) {
            errorKit = new ErrorUtil(this);
        }
        return errorKit;
    }

}
