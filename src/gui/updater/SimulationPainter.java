package gui.updater;

import gui.component.SimulationUI;

public class SimulationPainter implements Runnable {

    private SimulationUI simulationUI = SimulationUI.getInstance();

    private static final SimulationPainter INSTANCE = new SimulationPainter();

    private SimulationPainter() {

    }

    public static SimulationPainter getInstance() {
        return INSTANCE;
    }

    @Override
    public void run() {
        simulationUI.repaint();
    }
}