package com.office.viewer.system;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.office.viewer.common.IOfficeToPicture;
import com.office.viewer.constant.EventConstant;
import com.office.viewer.constant.MainConstant;
import com.office.viewer.constant.wp.WPViewConstant;
import com.office.viewer.system.beans.pagelist.IPageListViewListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DarkViewer implements IMainFrame, IMainViewer {

    private Context context;

    private String filePath;

    private FrameLayout rootView;

    private MainControl control;

    private boolean writeLog = true;
    private boolean isThumbnail;
    private Object background = Color.parseColor("#000000");

    private String tempFilePath;


    public DarkViewer(Context context) {
        this.context = context;
        this.control = new MainControl(this);
        this.control.setOffictToPicture(officeToPictureCallback);
    }

    @Override
    public void loadFile(FrameLayout layout, String filePath) {
        this.rootView = layout;
        this.filePath = filePath;
        this.rootView.post(this::openFile);
    }

    private void openFile() {
        File file = new File(this.filePath);
        if (!file.exists()) {
            Toast.makeText(context, "File not found", Toast.LENGTH_SHORT).show();
            getActivity().finish();
            return;
        }
        if (SupportType.check(this.filePath)) {
            control.openFile(this.filePath);
        } else {
            Toast.makeText(context, "File type not support", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }

    private IOfficeToPicture officeToPictureCallback = new IOfficeToPicture() {

        private Bitmap bitmap;

        @Override
        public void setModeType(byte modeType) {

        }

        @Override
        public byte getModeType() {
            return VIEW_CHANGE_END;
        }

        @Override
        public Bitmap getBitmap(int componentWidth, int componentHeight) {
            if (componentWidth == 0 || componentHeight == 0) {
                return null;
            }
            if (bitmap == null || bitmap.getWidth() != componentWidth || bitmap.getHeight() != componentHeight) {
                if (bitmap != null) {
                    bitmap.recycle();
                }
                bitmap = Bitmap.createBitmap((int) (componentWidth), (int) (componentHeight), Bitmap.Config.RGB_565);
            }
            return bitmap;
        }

        @Override
        public void callBack(Bitmap bitmap) {
            saveBitmapToFile(bitmap);
        }

        @Override
        public boolean isZoom() {
            return false;
        }

        @Override
        public void dispose() {

        }
    };

    private void saveBitmapToFile(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        if (tempFilePath == null) {
            tempFilePath = context.getCacheDir().getAbsolutePath();
            File fileDir = new File(tempFilePath + File.separatorChar + "tempPic");
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            tempFilePath = fileDir.getAbsolutePath();
        }

        File file = new File(tempFilePath + File.separatorChar + "export_image.jpg");
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            System.out.println("error converting bitmap" + e.getLocalizedMessage());
        }
    }


    public boolean isShowZoomingMsg() {
        return true;
    }

    public boolean isPopUpErrorDlg() {
        return false;
    }

    public Dialog onCreateDialog(int id) {
        return control.getDialog((Activity) context, id);
    }

    @Override
    public void setFindBackForwardState(boolean state) {

    }

    public IControl getControl() {
        return this.control;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public Activity getActivity() {
        return (Activity) context;
    }

    public boolean doActionEvent(int actionID, Object obj) {
        try {
            switch (actionID) {
                case EventConstant.SYS_READER_FINSH_ID:
                    // 文件读写完成
                    break;
                default:
                    return false;
            }
        } catch (Exception e) {
            control.getSysKit().getErrorKit().writerLog(e);
        }
        return true;
    }

    public void openFileFinish() {
        View officeView = control.getView();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        rootView.addView(officeView, layoutParams);
        //全屏重设默认大小
        resetZoom();
    }

    private void resetZoom() {
        // 全屏播放放大
        float zoom = ((Float) control.getActionValue(EventConstant.APP_ZOOM_ID, null)).floatValue();
        zoom = zoom * 1.4f;
        control.actionEvent(EventConstant.APP_ZOOM_ID, new int[]{(int) (zoom * MainConstant.STANDARD_RATE), 0, 0});
    }

    @Override
    public void updateToolsbarStatus() {

    }

    public int getBottomBarHeight() {
        return 0;
    }

    public int getTopBarHeight() {
        return 0;
    }

    public boolean onEventMethod(View v, MotionEvent e1, MotionEvent e2, float xValue, float yValue, byte eventMethodType) {
        return false;
    }

    public void changePage() {
    }

    public String getAppName() {
        return "OfficeViewer";
    }

    public boolean isDrawPageNumber() {
        return false;
    }

    public boolean isTouchZoom() {
        return true;
    }

    public byte getWordDefaultView() {
        return WPViewConstant.PAGE_ROOT;
    }

    public boolean isZoomAfterLayoutForWord() {
        return true;
    }


    public void changeZoom() {
    }

    public void error(int errorCode) {
    }

    @Override
    public void fullScreen(boolean fullscreen) {

    }

    @Override
    public void showProgressBar(boolean visible) {

    }

    public String getLocalString(String resName) {
        return "正在加载文件...";
    }

    @Override
    public boolean isShowPasswordDlg() {
        return true;
    }

    @Override
    public boolean isShowProgressBar() {
        return true;
    }

    @Override
    public boolean isShowFindDlg() {
        return true;
    }

    @Override
    public boolean isShowTXTEncodeDlg() {
        return true;
    }

    public String getTXTDefaultEncode() {
        return "GBK";
    }

    @Override
    public void completeLayout() {

    }

    @Override
    public boolean isChangePage() {
        return true;
    }

    public void setWriteLog(boolean saveLog) {
        this.writeLog = saveLog;
    }

    public boolean isWriteLog() {
        return writeLog;
    }

    public void setThumbnail(boolean isThumbnail) {
        this.isThumbnail = isThumbnail;
    }

    public Object getViewBackground() {
        return background;
    }

    public void setIgnoreOriginalSize(boolean ignoreOriginalSize) {
    }

    public boolean isIgnoreOriginalSize() {
        return false;
    }

    public byte getPageListViewMovingPosition() {
        return IPageListViewListener.Moving_Horizontal;
    }

    public boolean isThumbnail() {
        return isThumbnail;
    }

    public void updateViewImages(List<Integer> viewList) {

    }

    public File getTemporaryDirectory() {
        File file = context.getExternalFilesDir(null);
        if (file != null) {
            return file;
        } else {
            return context.getFilesDir();
        }
    }

    public void dispose() {
        Object obj = control.getActionValue(EventConstant.PG_SLIDESHOW, null);
        if (obj != null && (Boolean) obj) {
            this.control.actionEvent(EventConstant.PG_SLIDESHOW_END, null);
        } else {
            if (control.getReader() != null) {
                control.getReader().abortReader();
            }
            if (control != null && control.isAutoTest()) {
                System.exit(0);
            }
        }

        if (control != null) {
            control.dispose();
            control = null;
        }

        rootView.removeAllViews();
        rootView = null;

        context = null;
    }

}
