package gui.updater;

import gui.component.SimulationUI;

public class SimulationPainter implements Runnable {

    private static final SimulationPainter INSTANCE = new SimulationPainter();
    private SimulationUI simulationUI = SimulationUI.getInstance();

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