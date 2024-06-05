package threading;

public class Main {
    public static void main(String[] args) {
        // 0. init objects
        RpmChannel rpmChannel = new RpmChannel();
        SystemUnderTest s = new SystemUnderTest(rpmChannel, 0);
        SystemMonitor sm = new SystemMonitor(rpmChannel, 0, 1000);

        // 1. start both threads
        Thread ts = new Thread(s);
        ts.start();
        Thread tsm = new Thread(sm);
        tsm.start();

        try {
            // 2. after 500ms: set rpm to 700
            Thread.sleep(500);
            s.setTargetRpm(700);

            // 3. after another 2s: rpm to 300
            Thread.sleep(2000);
            s.setTargetRpm(300);

            // 4. after another 3s: stop SystemMonitor thread
            Thread.sleep(3000);
            sm.setRunning(false);

            // 5. after another 100ms: Stop SystemUnderTest Thread
            Thread.sleep(100);
            s.setRunning(false);

            // 6. we are still waiting for both Threads to completely finish their work
            ts.join();
            tsm.join();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
