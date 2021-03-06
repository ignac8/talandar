package sandbox;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Sandbox {
    public static void main(String... args) {

        class BeeperControl {
            private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

            public void beepForAnHour() {
                final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(() -> System.out.println("beep"), 10, 10, SECONDS);
                scheduler.schedule(() -> {
                    beeperHandle.cancel(true);
                }, 60 * 60, SECONDS);
            }
        }
        BeeperControl beeperControl = new BeeperControl();
        beeperControl.beepForAnHour();
    }
}
