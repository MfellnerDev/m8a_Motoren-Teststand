package threading;

/**
 * Prints out the results from the SystemUnderTest simulations
 *
 * @author Manuel Fellner
 * @version 03.06.2024
 */
public class SystemMonitor implements Runnable {
    private RpmChannel rpmChannel;
    private boolean running;

    private ConsoleGraph consoleGraph;

    public SystemMonitor(RpmChannel rpmChannel, int minLegendVal, int maxLegendVal) {
        this.consoleGraph = new ConsoleGraph(minLegendVal, maxLegendVal);
        this.rpmChannel = rpmChannel;
        running = true;
    }

    @Override
    public void run()  {
        // print the legend
        this.consoleGraph.printLegend();
        while (running)  {
            for (int i = 0; i < this.rpmChannel.getRpmChannelLength(); i++)  {
                try  {
                    Thread.sleep(20);
                    float currentActualRpm = this.rpmChannel.getRpm();
                    this.consoleGraph.printDatapoint(currentActualRpm);
                } catch (Exception ex)  {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void setRunning(boolean running)  {
        this.running = running;
    }
}
