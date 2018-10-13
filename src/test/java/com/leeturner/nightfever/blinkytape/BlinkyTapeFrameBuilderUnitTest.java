package com.leeturner.nightfever.blinkytape;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BlinkyTapeFrameBuilderUnitTest {

    @Test
    public void whenADefaultBlinkyTapeFrameIsBuiltItUsesTheDefaultNumberOfLights() {
        BlinkyTapeFrame frame = new BlinkyTapeFrameBuilder().build();

        assertThat(frame.getLightCount()).isEqualTo(BlinkyTapeFrame.DEFAULT_LIGHT_COUNT);
    }

    @Test
    public void whenADefaultBlinkyTapeFrameIsBuiltItUsesTheDefaultColorForAllLights() {
        BlinkyTapeFrame frame = new BlinkyTapeFrameBuilder().build();

        for (int i = 0; i < frame.getLightCount(); i++) {
            assertThat(frame.getColorOfLight(i)).isEqualTo(Color.BLACK);
        }
    }

    @Test
    public void whenWithALightCountOfIsCalledWithAValidNumberTheGeneratedFrameHasTheSpecifiedNumberOfLights() {
        BlinkyTapeFrame frame = new BlinkyTapeFrameBuilder().withALightCountOf(10).build();

        assertThat(frame.getLightCount()).isEqualTo(10);
        for (int i = 0; i < frame.getLightCount(); i++) {
            assertThat(frame.getColorOfLight(i)).isEqualTo(Color.BLACK);
        }
    }

    @Test
    public void whenWithALightCountOfIsCalledSpecifyingZeroLightsAnInValidArgumentExceptionIsThrown() {
        IllegalArgumentException expected = assertThrows(IllegalArgumentException.class, () -> new BlinkyTapeFrameBuilder().withALightCountOf(0).build());
        assertThat(expected).hasMessageThat().isEqualTo("Invalid lightCount.  The lightCount must be greater than 0");
    }

    @Test
    public void whenWithALightCountOfIsCalledSpecifyingANegativeNumberAnInValidArgumentExceptionIsThrown() {
        IllegalArgumentException expected = assertThrows(IllegalArgumentException.class, () -> new BlinkyTapeFrameBuilder().withALightCountOf(-1).build());
        assertThat(expected).hasMessageThat().isEqualTo("Invalid lightCount.  The lightCount must be greater than 0");
    }

    @ParameterizedTest
    @MethodSource("createColors")
    public void whenWithAllLightsSetToColorIsCalledWithAValidColorAllLightsAreSetToThatColor(Color color) {
        BlinkyTapeFrame frame = new BlinkyTapeFrameBuilder().withAllLightsSetToColor(color).build();

        assertThat(frame.getLightCount()).isEqualTo(BlinkyTapeFrame.DEFAULT_LIGHT_COUNT);
        for (int i = 0; i < frame.getLightCount(); i++) {
            assertThat(frame.getColorOfLight(i)).isEqualTo(color);
        }
    }

    /** Parameters for whenWithAllLightsSetToColorIsCalledWithAValidColorAllLightsAreSetToThatColor **/
    private static Stream<Color> createColors() {
        return Stream.of(Color.RED, Color.GREEN, Color.BLUE, Color.PINK);
    }

    @Test
    public void whenWithAllLightsSetToColorIsCalledWithANullColorANullPointerExceptionIsThrown() {
        NullPointerException expected = assertThrows(NullPointerException.class, () -> new BlinkyTapeFrameBuilder().withAllLightsSetToColor(null).build());
        assertThat(expected).hasMessageThat().isEqualTo("Invalid color. A null color is not allowed");
    }

    @ParameterizedTest
    @MethodSource("createColorsAndLightNumbers")
    public void whenWithSpecificLightSetToColorIsCalledWithAValidLightNumberAndColorTheCorrectLightIsSetToThatColor(Color color, int lightNumber) {
        BlinkyTapeFrame frame = new BlinkyTapeFrameBuilder().withSpecificLightSetToColor(color, lightNumber).build();

        for (int i = 0; i < frame.getLightCount(); i++) {
            if (i == lightNumber) {
                assertThat(frame.getColorOfLight(i)).isEqualTo(color);
            }
            else {
                assertThat(frame.getColorOfLight(i)).isEqualTo(Color.BLACK);
            }
        }
    }

    /** Parameters for whenWithSpecificLightSetToColorIsCalledWithAValidLightNumberAndColorTheCorrectLightIsSetToThatColor **/
    private static Stream<Arguments> createColorsAndLightNumbers() {
        return Stream.of(
                Arguments.of(Color.ORANGE, 0),
                Arguments.of(Color.RED, 5),
                Arguments.of(Color.GREEN, 34),
                Arguments.of(Color.BLUE, 50),
                Arguments.of(Color.GRAY, 59),
                Arguments.of(Color.PINK, 47));
    }

    @Test
    public void whenWithSpecificLightSetToColorIsCalledWithANullColorANullPointerExceptionIsThrown() {
        NullPointerException expected = assertThrows(NullPointerException.class, () -> new BlinkyTapeFrameBuilder().withSpecificLightSetToColor(null, 1).build());
        assertThat(expected).hasMessageThat().isEqualTo("Invalid color. A null color is not allowed");
    }

    @Test
    public void whenWithSpecificLightSetToColorIsCalledWithAValidColorAndANegativeLightNumberAnIllegalArgumentExceptionIsThrown() {
        IllegalArgumentException expected = assertThrows(IllegalArgumentException.class, () -> new BlinkyTapeFrameBuilder().withSpecificLightSetToColor(Color.RED, -1).build());
        assertThat(expected).hasMessageThat().isEqualTo("Error trying to access nonexistent lightNumber. A negative lightNumber is not allowed");
    }

    @Test
    public void whenWithSpecificLightSetToColorIsCalledWithAValidColorAndALightNumberGreaterThanTheNumberOfLightsAnIllegalArgumentExceptionIsThrown() {
        IllegalArgumentException expected = assertThrows(IllegalArgumentException.class, () -> new BlinkyTapeFrameBuilder().withSpecificLightSetToColor(Color.RED, 70).build());
        assertThat(expected).hasMessageThat().isEqualTo("Error trying to access nonexistent lightNumber (70). This BlinkyTapeFrame has 60 lights indexed from 0");
    }

    @ParameterizedTest
    @MethodSource("createColorsAndLightNumberArray")
    public void whenWithSpecificLightsSetToColorIsCalledWithAValidLightNumberArrayAndColorTheCorrectLightsAreSetToThatColor(Color color, int... lightNumbers) {
        BlinkyTapeFrame frame = new BlinkyTapeFrameBuilder().withSpecificLightsSetToColor(color, lightNumbers).build();

        for (int i = 0; i < frame.getLightCount(); i++) {
            final int match = i;
            if (IntStream.of(lightNumbers).anyMatch(x -> x == match)) {
                assertThat(frame.getColorOfLight(i)).isEqualTo(color);
            }
            else {
                assertThat(frame.getColorOfLight(i)).isEqualTo(Color.BLACK);
            }
        }
    }

    /** Parameters for whenWithSpecificLightsSetToColorIsCalledWithAValidLightNumberArrayAndColorTheCorrectLightsAreSetToThatColor **/
    private static Stream<Arguments> createColorsAndLightNumberArray() {
        return Stream.of(
                Arguments.of(Color.RED, new int[]{2, 5}),
                Arguments.of(Color.GREEN, new int[]{}), // make sure we test an empty array of lights
                Arguments.of(Color.PINK, new int[]{4, 8, 23, 59, 10}),
                Arguments.of(Color.YELLOW, new int[]{13, 50, 14, 20, 52, 30, 40}),
                Arguments.of(Color.ORANGE, new int[]{17, 35, 56, 45, 34, 23, 45}));
    }

    @Test
    public void whenWithARangeOfLightsSetToColorIsCalledWithAnEndRangeBeforeTheStartAnIllegalArgumentExceptionIsThrown() {
        IllegalArgumentException expected = assertThrows(IllegalArgumentException.class, () -> new BlinkyTapeFrameBuilder().withRangeOfLightsSetToColor(Color.GREEN, 5, 2).build());
        assertThat(expected).hasMessageThat().isEqualTo("Error setting range of colors. The end range must be after the start");
    }
}
