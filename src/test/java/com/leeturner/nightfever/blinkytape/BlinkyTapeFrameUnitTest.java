package com.leeturner.nightfever.blinkytape;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("unit")
public class BlinkyTapeFrameUnitTest {

    private Color[] colours;

    @BeforeEach
    public void setup() {
        this.colours = new Color[10];
        colours[0] = Color.BLACK;
        colours[1] = Color.BLUE;
        colours[2] = Color.CYAN;
        colours[3] = Color.GREEN;
        colours[4] = Color.MAGENTA;
        colours[5] = Color.ORANGE;
        colours[6] = Color.RED;
        colours[7] = Color.PINK;
        colours[8] = Color.YELLOW;
        colours[9] = Color.WHITE;
    }

    @Test
    public void whenTheDefaultConstructorIsUsedTheNumberOfLightsIsSetToTheDefaultNumberOfLights() {
        BlinkyTapeFrame frame = new BlinkyTapeFrame();
        assertThat(frame.getLightCount()).isEqualTo(BlinkyTapeFrame.DEFAULT_LIGHT_COUNT);
    }

    @Test
    public void whenTheDefaultConstructorIsUsedAllTheLightsAreSetToTheDefaultColor() {
        BlinkyTapeFrame frame = new BlinkyTapeFrame();
        assertThat(frame.getLights()).isNotEmpty();
        for (int i = 0; i < frame.getLightCount(); i++) {
            assertThat(frame.getColorOfLight(i)).isEqualTo(Color.BLACK);
        }
    }

    @Test
    public void whenTheLightCountConstructorIsUsedAllTheLightsAreSetToTheDefaultColor() {
        BlinkyTapeFrame frame = new BlinkyTapeFrame(10);
        assertThat(frame.getLights()).isNotEmpty();
        for (int i = 0; i < frame.getLightCount(); i++) {
            assertThat(frame.getColorOfLight(i)).isEqualTo(Color.BLACK);
        }
    }

    @Test
    public void whenTheNumberOfLightsAreSpecifiedInTheConstructorTheNumberOfLightsIsSetToTheSameNumberOfLights() {
        BlinkyTapeFrame frame = new BlinkyTapeFrame(4);
        assertThat(frame.getLightCount()).isEqualTo(4);

        frame = new BlinkyTapeFrame(45);
        assertThat(frame.getLightCount()).isEqualTo(45);

        frame = new BlinkyTapeFrame(450);
        assertThat(frame.getLightCount()).isEqualTo(450);
    }

    @Test
    @Tag("exception")
    public void whenTheNumberOfLightsAreSpecifiedInTheConstructorSpecifyingZeroLightsAnInvalidArgumentExceptionIsThrown() {
        IllegalArgumentException expected = assertThrows(IllegalArgumentException.class, () -> new BlinkyTapeFrame(0));
        assertThat(expected).hasMessageThat().isEqualTo("Invalid lightCount.  The lightCount must be greater than 0");
    }

    @Test
    @Tag("exception")
    public void whenTheNumberOfLightsAreSpecifiedInTheConstructorSpecifyingANegativeNumberAnInvalidArgumentExceptionIsThrown() {
        IllegalArgumentException expected = assertThrows(IllegalArgumentException.class, () -> new BlinkyTapeFrame(-1));
        assertThat(expected).hasMessageThat().isEqualTo("Invalid lightCount.  The lightCount must be greater than 0");
    }

    @Test
    public void whenColoursAreSpecifiedInTheConstructorTheSameColoursAreReturnedFromGetLights() {
        BlinkyTapeFrame frame = new BlinkyTapeFrame(this.colours);
        assertThat(frame.getLights()).isNotNull();
        assertThat(frame.getLights()).isNotEmpty();
        assertThat(frame.getLights()).isEqualTo(this.colours);
        assertThat(frame.getLightCount()).isEqualTo(this.colours.length);
    }

    @Test
    @Tag("exception")
    public void whenColoursAreSpecifiedInTheConstructorButNullIsPassedANullPointerExceptionIsThrown() {
        NullPointerException expected = assertThrows(NullPointerException.class, () -> new BlinkyTapeFrame(null));
        assertThat(expected).hasMessageThat().isEqualTo("Invalid lights array. A null lights array is not allowed");
    }

    @Test
    public void whenColorsAreSetUsingSetColoursTheSameColoursAreReturnedFromGetLights() {
        BlinkyTapeFrame frame = new BlinkyTapeFrame();
        assertThat(frame.getLights()).isNotNull();
        assertThat(frame.getLights()).isNotEmpty();
        assertThat(frame.getLights()).hasLength(60);
        assertThat(frame.getLightCount()).isEqualTo(60);

        frame.setLights(this.colours);
        assertThat(frame.getLights()).isNotNull();
        assertThat(frame.getLights()).isNotEmpty();
        assertThat(frame.getLights()).hasLength(10);
        assertThat(frame.getLightCount()).isEqualTo(10);

        assertThat(frame.getLights()).isEqualTo(this.colours);
    }

    @Test
    @Tag("exception")
    public void whenNullIsPassedToSetColorsANullPointerExceptionIsThrown() {
        BlinkyTapeFrame frame = new BlinkyTapeFrame();
        NullPointerException expected = assertThrows(NullPointerException.class, () -> frame.setLights(null));
        assertThat(expected).hasMessageThat().isEqualTo("Invalid lights array. A null lights array is not allowed");
    }

    @Test
    public void whenAValidLightNumberIsPassedGetColorOfLightReturnTheCorrectColour() {
        BlinkyTapeFrame frame = new BlinkyTapeFrame(this.colours);
        assertThat(frame.getColorOfLight(0)).isEqualTo(Color.BLACK);
        assertThat(frame.getColorOfLight(1)).isEqualTo(Color.BLUE);
        assertThat(frame.getColorOfLight(2)).isEqualTo(Color.CYAN);
        assertThat(frame.getColorOfLight(3)).isEqualTo(Color.GREEN);
        assertThat(frame.getColorOfLight(4)).isEqualTo(Color.MAGENTA);
        assertThat(frame.getColorOfLight(5)).isEqualTo(Color.ORANGE);
        assertThat(frame.getColorOfLight(6)).isEqualTo(Color.RED);
        assertThat(frame.getColorOfLight(7)).isEqualTo(Color.PINK);
        assertThat(frame.getColorOfLight(8)).isEqualTo(Color.YELLOW);
        assertThat(frame.getColorOfLight(9)).isEqualTo(Color.WHITE);
    }


    @Test
    @Tag("exception")
    public void whenALightNumberGreaterThanTheNumberOfLightsIsPassedGetColorOfLightThrowsAnInValidArgumentException() {
        BlinkyTapeFrame frame = new BlinkyTapeFrame(this.colours);
        IllegalArgumentException expected = assertThrows(IllegalArgumentException.class, () -> frame.getColorOfLight(10));
        assertThat(expected).hasMessageThat().isEqualTo("Error trying to access nonexistent lightNumber (10). This BlinkyTapeFrame has 10 lights indexed from 0");
    }

    @Test
    @Tag("exception")
    public void whenALightNumberLessThanZeroIsPassedGetColorOfLightThrowsAnInValidArgumentException() {
        BlinkyTapeFrame frame = new BlinkyTapeFrame(this.colours);
        IllegalArgumentException expected = assertThrows(IllegalArgumentException.class, () -> frame.getColorOfLight(-1));
        assertThat(expected).hasMessageThat().isEqualTo("Error trying to access nonexistent lightNumber. A negative lightNumber is not allowed");
    }

    @Test
    public void whenSetColorIsCalledTheCorrectLightInTheFrameIsSet() {
        BlinkyTapeFrame frame = new BlinkyTapeFrame(this.colours);
        assertThat(frame.getColorOfLight(0)).isEqualTo(Color.BLACK);
        assertThat(frame.getColorOfLight(1)).isEqualTo(Color.BLUE);
        assertThat(frame.getColorOfLight(2)).isEqualTo(Color.CYAN);
        assertThat(frame.getColorOfLight(3)).isEqualTo(Color.GREEN);
        assertThat(frame.getColorOfLight(4)).isEqualTo(Color.MAGENTA);
        assertThat(frame.getColorOfLight(5)).isEqualTo(Color.ORANGE);
        assertThat(frame.getColorOfLight(6)).isEqualTo(Color.RED);
        assertThat(frame.getColorOfLight(7)).isEqualTo(Color.PINK);
        assertThat(frame.getColorOfLight(8)).isEqualTo(Color.YELLOW);
        assertThat(frame.getColorOfLight(9)).isEqualTo(Color.WHITE);

        frame.setColorOfLight(Color.LIGHT_GRAY, 1);
        frame.setColorOfLight(Color.LIGHT_GRAY, 3);
        frame.setColorOfLight(Color.LIGHT_GRAY, 5);
        frame.setColorOfLight(Color.LIGHT_GRAY, 7);
        frame.setColorOfLight(Color.LIGHT_GRAY, 9);

        assertThat(frame.getColorOfLight(0)).isEqualTo(Color.BLACK);
        assertThat(frame.getColorOfLight(1)).isEqualTo(Color.LIGHT_GRAY);
        assertThat(frame.getColorOfLight(2)).isEqualTo(Color.CYAN);
        assertThat(frame.getColorOfLight(3)).isEqualTo(Color.LIGHT_GRAY);
        assertThat(frame.getColorOfLight(4)).isEqualTo(Color.MAGENTA);
        assertThat(frame.getColorOfLight(5)).isEqualTo(Color.LIGHT_GRAY);
        assertThat(frame.getColorOfLight(6)).isEqualTo(Color.RED);
        assertThat(frame.getColorOfLight(7)).isEqualTo(Color.LIGHT_GRAY);
        assertThat(frame.getColorOfLight(8)).isEqualTo(Color.YELLOW);
        assertThat(frame.getColorOfLight(9)).isEqualTo(Color.LIGHT_GRAY);
    }

    @Test
    @Tag("exception")
    public void whenALightNumberGreaterThanTheNumberOfLightsIsPassedSetColorOfLightThrowsAnInValidArgumentException() {
        BlinkyTapeFrame frame = new BlinkyTapeFrame(this.colours);
        IllegalArgumentException expected = assertThrows(IllegalArgumentException.class, () -> frame.setColorOfLight(Color.RED, 10));
        assertThat(expected).hasMessageThat().isEqualTo("Error trying to access nonexistent lightNumber (10). This BlinkyTapeFrame has 10 lights indexed from 0");
    }

    @Test
    @Tag("exception")
    public void whenANullColorIsPassedToSetColorOfLightANullPointerExceptionIsThrown() {
        BlinkyTapeFrame frame = new BlinkyTapeFrame(this.colours);
        NullPointerException expected = assertThrows(NullPointerException.class, () -> frame.setColorOfLight(null, 1));
        assertThat(expected).hasMessageThat().isEqualTo("Invalid color. A null color is not allowed");
    }

    @Test
    @Tag("exception")
    public void whenALightNumberLessThanZeroIsPassedSetColorOfLightThrowsAnInValidArgumentException() {
        BlinkyTapeFrame frame = new BlinkyTapeFrame(this.colours);
        IllegalArgumentException expected = assertThrows(IllegalArgumentException.class, () -> frame.setColorOfLight(Color.RED, -1));
        assertThat(expected).hasMessageThat().isEqualTo("Error trying to access nonexistent lightNumber. A negative lightNumber is not allowed");
    }
}
