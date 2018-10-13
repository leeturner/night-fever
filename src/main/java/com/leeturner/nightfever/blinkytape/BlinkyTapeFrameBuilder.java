package com.leeturner.nightfever.blinkytape;

import java.awt.*;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A Builder that helps you build different BlinkyTapeFame patterns.
 */
public class BlinkyTapeFrameBuilder {

    private BlinkyTapeFrame frame = new BlinkyTapeFrame();

    /**
     * Sets the number lights that the generated BlinkyTapeFrame has.  It is important to note
     * that this method will reset the whole frame so any previously set lights will be reset.
     * @param lightCount the number of lights in this frame
     * @return the updated BlinkyTapeFrameBuilder
     */
    public BlinkyTapeFrameBuilder withALightCountOf(int lightCount) {
        this.frame = new BlinkyTapeFrame(lightCount);
        return this;
    }

    /**
     * Sets all the lights in the frame to the specified color
     * @param color the color to set the frame to
     * @return the updated BlinkyTapeFrameBuilder
     */
    public BlinkyTapeFrameBuilder withAllLightsSetToColor(Color color) {
        for (int i = 0; i < frame.getLightCount(); i++) {
            frame.setColorOfLight(color, i);
        }
        return this;
    }

    /**
     * Sets a specific light in this frame to the specified color
     * @param color the color to set the light to
     * @param lightNumber the index of the light to be set
     * @return the updated BlinkyTapeFrameBuilder
     */
    public BlinkyTapeFrameBuilder withSpecificLightSetToColor(Color color, int lightNumber) {
        frame.setColorOfLight(color, lightNumber);
        return this;
    }

    /**
     * Sets a specific set of lights in this frame to the specified color
     * @param color the color to set the lights to
     * @param lightNumbers an array of light numbers to be set
     * @return the updated BlinkyTapeFrameBuilder
     */
    public BlinkyTapeFrameBuilder withSpecificLightsSetToColor(Color color, int... lightNumbers) {
        for (int i = 0; i < lightNumbers.length; i++) {
            frame.setColorOfLight(color, lightNumbers[i]);
        }
        return this;
    }

    public BlinkyTapeFrameBuilder withRangeOfLightsSetToColor(Color color, int start, int end) {
        checkArgument(end > start, "Error setting range of colors. The end range must be after the start");
        for (int i = start; i <= end; i++) {
            frame.setColorOfLight(color, i);
        }
        return this;
    }

    /**
     * Builds this BlinkyTapeFrame and returns it
     * @return the configured BlinkyTapeFrame
     */
    public BlinkyTapeFrame build() {
        return frame;
    }
}
