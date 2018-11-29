package com.hardlove.cl.fooddefender;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void gentatory() {
        int x = 1080;
        int y = 1920;
        int f = 100;
        File file = new File("base_values.xml");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            StringBuilder sb = new StringBuilder();
            String tmpx = "<dimen name=\"x_ui_px_%s\">%smm</dimen>";
            String tmpy = "<dimen name=\"y_ui_px_%s\">%smm</dimen>";
            String tmpf = "<dimen name=\"font_ui_px_%s\">%smm</dimen>";
            sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
                    .append("\n")
                    .append("<resources>").append("\n");
            for (int i = 0; i < x; i++) {
                sb.append(String.format(tmpx, i + ".0", i + ".0")).append("\n");
            }
            for (int i = 0; i < y; i++) {
                sb.append(String.format(tmpy, i + ".0", i + ".0")).append("\n");
            }
            for (int i = 0; i < f; i++) {
                sb.append(String.format(tmpf, i + ".0", i + ".0")).append("\n");
            }
            sb.append("</resources>");

            fos.write(sb.toString().getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}