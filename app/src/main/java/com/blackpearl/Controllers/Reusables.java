package com.blackpearl.Controllers;

public class Reusables {

    public static boolean isNotNullAndEmpty(String text) {

        try {
            if (text != null) {
                if (!text.isEmpty()) {
                    return true;
                }
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    public static void waitForSeconds(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
