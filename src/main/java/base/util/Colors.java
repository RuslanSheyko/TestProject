package base.util;

import base.driver.InitialDriver;

/**
 * Class handler for processing colors
 * Needed because Firefox and Chrome return colors in different formats (rgb and rgba), respectively
 * From the user of the class, you need to add a new color to the Enum
 */
public enum Colors {
    GREEN(new ColorRGBA(0, 128, 0, 1), "Зеленый"),
    BLUE(new ColorRGBA(0, 136, 204, 1), "Синий"),
    GREY(new ColorRGBA(128, 128, 128, 1), "Серый"),
    LIGHT_BLUE(new ColorRGBA(122, 191, 224, 1), "Голубой"),
    WHITE(new ColorRGBA(255, 255, 255, 1), "Белый"),
    DARK_GREY(new ColorRGBA(118,131,167,1),"Темный");

    /**
     * Constructor
     *
     * @param rgba - the object class containing the RGBA color
     * @param rusName - a clear name for the color to be displayed in the report
     */
    Colors(ColorRGBA rgba, String rusName) {
        this.rgba = rgba;
        this.russianName = rusName;
    }

    /**
     * Get color string depending on browser name
     *
     * @return returns a string
     */
    public String getRGB() {
        if (new InitialDriver().getInstance().getBrowserExecuted().equals("firefox")) {
            return rgba.toRGB();
        } else return rgba.toRGBA();
    }

    /**
     * Field containing RGBA object
     */
    private ColorRGBA rgba;
    /**
     * A field containing a human-readable name
     */
    private String russianName;

    /**
     * Method that returns a friendly name for the report
     *
     * @return string value of color name
     */
    @Override
    public String toString() {
        return russianName;
    }

    /**
     * Representative color class
     */
    static class ColorRGBA {
        int red;
        int green;
        int blue;
        int a;

        /**
         * Class constructor
         *
         * @param red - red
         * @param green - green
         * @param blue - blue
         * @param a - transparency
         */
        ColorRGBA(int red, int green, int blue, int a) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.a = a;
        }

        /**
         * @return returns an RGBA color representation string
         */
        public String toRGBA() {
            return "rgba(" + red + ", " + green + ", " + blue + ", " + a + ")";
        }

        /**
         * @return returns an RGB color representation string
         */
        public String toRGB() {
            return "rgb(" + red + ", " + green + ", " + blue + ")";
        }
    }
}

