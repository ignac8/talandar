package gui;

import javax.swing.JTextArea;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.time.ZonedDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

public class Logger implements Runnable {

    private static final Logger INSTANCE = new Logger();
    private JTextArea loggerTextArea;
    private BlockingQueue<String> logQueue;

    private Logger() {
        logQueue = new LinkedBlockingQueue<>();
    }

    public static Logger getInstance() {
        return INSTANCE;
    }

    public void setLoggerTextArea(JTextArea loggerTextArea) {
        this.loggerTextArea = loggerTextArea;
    }

    public void log(String message) {
        logQueue.offer(message);
    }

    @Override
    public void run() {
        String message;
        String currentTime = "[" + now().format(ofPattern("HH:mm")) + "]" + ":" + " ";
        while ((message = logQueue.poll()) != null) {
            loggerTextArea.append(currentTime + message + "\r\n");
        }
    }
}