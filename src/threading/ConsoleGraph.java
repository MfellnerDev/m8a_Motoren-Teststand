package threading;

/**
 * A class for printing pretty graphs on the console: each line is a data point
 * and the column within the line represents the value at that point.
 *
 * @author Clemens Koza
 */
public class ConsoleGraph {
    // the formatting depends on this width & offset;
    // for simplicity, they can not be changed
    private static final int WIDTH = 80;
    private static final int OFFSET = 8;

    private final float min, max;
    private final char grid, data;

    /**
     * Creates a ConsoleGraph instance for the given range of values
     *
     * @param min the minimum value that can be plotted
     * @param max the maximum value that can be plotted
     */
    public ConsoleGraph(float min, float max) {
        this(min, max, '.', 'o');
    }

    /**
     * Creates a ConsoleGraph instance for the given range of values
     *
     * @param min the minimum value that can be plotted
     * @param max the maximum value that can be plotted
     * @param grid the character to print as a "grid line"
     * @param data the character to print as a data point
     */
    public ConsoleGraph(float min, float max, char grid, char data) {
        this.min = min;
        this.max = max;
        this.grid = grid;
        this.data = data;
    }

    /**
     * Prints a "legend": a line with evenly spaced values where grid lines go.
     * For example, if the range is -10 to 10, the legend would have -10 to the
     * far left, 0 in the middle, and 10 to the far right. The legend will
     * consist of a total of 11 numbers, i.e. in the example all the even
     * numbers from -10 to 10 would be printed.
     */
    public void printLegend() {
        for (int x = 0; x <= WIDTH; x += OFFSET) {
            float value = (max - min) / WIDTH * x + min;
            System.out.printf("%-" + OFFSET + "s", Math.round(value));
        }
        System.out.println();
    }

    /**
     * Prints a datapoint line containing the given value. There will be a
     * "grid" of characters (a total of 11 evenly spaced over the width of the
     * line), and somewhere on the line will be the datapoint. If the value is
     * outside the (min, max) range, that will be indicating by arrows pointing
     * left/right.
     *
     * @param value the value of the datapoint to print
     */
    public void printDatapoint(float value) {
        // fill sb so that it hase WIDTH+1 characters
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x <= WIDTH; x++) {
            sb.append(x % OFFSET == 0 ? grid : ' ');
        }

        if (value < min) {
            // show that the value is off the charts to the left
            sb.setCharAt(0, '<');
            sb.setCharAt(1, '<');
        } else if (value > max) {
            // show that the value is off the charts to the right
            sb.setCharAt(WIDTH - 1, '>');
            sb.setCharAt(WIDTH, '>');
        } else {
            // the value, normalized to the range 0-1
            float normalizedValue = (value - min) / (max - min);
            sb.setCharAt(Math.round(normalizedValue * WIDTH), data);
        }
        System.out.println(sb);
    }
}
