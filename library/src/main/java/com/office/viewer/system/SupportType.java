package com.office.viewer.system;

import java.util.ArrayList;

public class SupportType {

    private static ArrayList<String> types = new ArrayList<String>() {
        {
            add("DOC");
            add("DOCX");
            add("XLS");
            add("XLSX");
            add("PPT");
            add("PPTX");
        }
    };

    public static boolean check(String filePath) {
        int dotPosition = filePath.lastIndexOf(".");
        if (dotPosition == -1) {
            return false;
        }
        String fileExt = filePath.substring(dotPosition + 1).toUpperCase();
        if (types.contains(fileExt)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPpt(String filePath) {
        int dotPosition = filePath.lastIndexOf(".");
        if (dotPosition == -1) {
            return false;
        }
        String fileExt = filePath.substring(dotPosition + 1).toUpperCase();
        if (fileExt.equals("PPT")||fileExt.equals("PPTX")) {
            return true;
        } else {
            return false;
        }
    }

}
