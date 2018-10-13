package com.leeturner.nightfever.blinkytape;

import java.awt.*;
import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A BlinkyTapeFrame represents a single frame (ie the state of all the lights) for the whole BlinkyTape light strip
 * A single BlinkyTape has 60 lights in the strip so this object is designed to work with this as a default.  The number
 * of lights can be overridden.
 */
public class BlinkyTapeFrame {

    public static final int DEFAULT_LIGHT_COUNT = 60;
    private static final Color DEFAULT_LIGHT_COLOR = Color.BLACK;

    private Color[] lights;

    public BlinkyTapeFrame() {
        this(DEFAULT_LIGHT_COUNT);
    }

    public BlinkyTapeFrame(int lightCount) {
        checkArgument(lightCount > 0, "Invalid lightCount.  The lightCount must be greater than 0");

        this.lights = new Color[lightCount];

        // Set BLACK as the base color for all the lights to start off with (basically all lights are off)
        for (int i = 0; i < this.lights.length; i++) {
            this.lights[i] = DEFAULT_LIGHT_COLOR;
        }
    }

    public BlinkyTapeFrame(Color[] lights) {
        checkNotNull(lights, "Invalid lights array. A null lights array is not allowed");
        this.lights = lights;
    }

    public Color[] getLights() {
        return this.lights;
    }

    public void setLights(Color[] lights) {
        checkNotNull(lights, "Invalid lights array. A null lights array is not allowed");
        this.lights = lights;
    }

    public int getLightCount(){
        return this.lights.length;
    }

    public Color getColorOfLight(int lightNumber){
        this.checkLightNumberArguments(lightNumber);
        return this.lights[lightNumber];
    }

    public void setColorOfLight(Color color, int lightNumber) {
        checkNotNull(color, "Invalid color. A null color is not allowed");
        this.checkLightNumberArguments(lightNumber);
        this.lights[lightNumber] = color;
    }

    private void checkLightNumberArguments(int lightNumber) {
        checkArgument(lightNumber <= this.lights.length - 1, "Error trying to access nonexistent lightNumber (" + lightNumber + "). This BlinkyTapeFrame has %s lights indexed from 0", this.lights.length);
        checkArgument(lightNumber >= 0, "Error trying to access nonexistent lightNumber. A negative lightNumber is not allowed");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlinkyTapeFrame that = (BlinkyTapeFrame) o;
        return Arrays.equals(getLights(), that.getLights());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getLights());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BlinkyTapeFrame{");
        sb.append("lights=").append(Arrays.toString(lights));
        sb.append('}');
        return sb.toString();
    }
}
