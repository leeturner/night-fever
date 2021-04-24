package com.leeturner.nightfever.blinkytape;

import java.awt.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BlinkyTapeControllerIntegrationTest {

    public static final String DEFAULT_COM_PORT = "/dev/tty.usbmodem1412201";

    BlinkyTapeController controller;

    @BeforeEach
    public void initializeController(){
        controller = new BlinkyTapeController(DEFAULT_COM_PORT);
    }

    @AfterEach
    public void closeController(){
        controller.close();
    }

    public void testBlinkyTape() throws InterruptedException {

        int pause = 20;
        Color baseColor = Color.GRAY;
        Color dot = Color.BLACK;
        BlinkyTapeFrame frame = new BlinkyTapeFrame();

        for (int i = 0; i < frame.getLightCount(); i++) {
            frame.setColorOfLight(baseColor, i);
        }

        controller.renderFrame(frame);
        Thread.sleep(pause);

        for (int i = 0; i < frame.getLightCount(); i++) {
            frame.setColorOfLight(baseColor, i);
            frame.setColorOfLight(dot, i);
            if (i > 0) {
                frame.setColorOfLight(baseColor, i-1);
            }
            controller.renderFrame(frame);
            Thread.sleep(pause);
        }

        for (int i = 0; i < frame.getLightCount(); i++) {
            frame.setColorOfLight(baseColor, i);
        }

        controller.renderFrame(frame);
        Thread.sleep(pause);

        for (int i = frame.getLightCount()-1; i > -1; i--) {
            frame.setColorOfLight(baseColor, i);
            frame.setColorOfLight(dot, i);
            if (i < frame.getLightCount()-1) {
                frame.setColorOfLight(baseColor, i+1);
            }
            controller.renderFrame(frame);
            Thread.sleep(pause);
        }
    }
}
