package gui;

import gui.action.Learn;
import gui.action.Replay;
import gui.component.SimulationUI;
import gui.updater.Logger;
import neuralnetwork.NeuralNetwork;
import solver.Result;
import util.FileUtils;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;

import static java.time.ZonedDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.concurrent.Executors.newScheduledThreadPool;
import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.UIManager.getSystemLookAndFeelClassName;
import static javax.swing.UIManager.setLookAndFeel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static util.FileUtils.fromJson;
import static util.FileUtils.loadFile;
import static util.FileUtils.saveFile;
import static util.FileUtils.toJson;

public class MainForm {

    private JPanel rootPanel;
    private JLabel passLimitLabel;
    private JLabel searchTimeLimitLabel;
    private JLabel populationSizeLabel;
    private JLabel tournamentSizeLabel;
    private JLabel crossoverChanceLabel;
    private JLabel mutationChanceLabel;
    private JLabel standardDeviationLabel;
    private JLabel meanLabel;
    private JLabel hiddenLayerSizeLabel;
    private JTextField passLimitTextField;
    private JTextField searchTimeLimitTextField;
    private JTextField populationSizeTextField;
    private JTextField tournamentSizeTextField;
    private JTextField crossOverChanceTextField;
    private JTextField mutationChanceTextField;
    private JTextField meanTextField;
    private JTextField hiddenLayerSizeTextField;
    private JButton loadNeuralNetworkButton;
    private JButton saveNeuralNetworkToButton;
    private SimulationUI simulationUI;
    private JButton runNeuralNetworkLearningButton;
    private JButton runSimulationButton;
    private JTextArea loggerTextArea;
    private JCheckBox withGraphicsCheckBox;
    private JButton saveGraphToFileButton;
    private JTextField standardDeviationTextField;
    private JButton resetToDefaultValuesButton;
    private ExecutorService actionExecutorService = newSingleThreadExecutor();
    private Logger logger;
    private NeuralNetwork neuralNetwork;
    private JFileChooser fileChooser = new JFileChooser();
    private DateTimeFormatter dateTimeFormatter = ofPattern("yyyy'_'MM'_'dd'_'HH'_'mm'_'ss'_'");
    private Replay replay = new Replay();
    private Learn learn = new Learn();
    private int passLimit;
    private int searchTimeLimit;
    private int populationSize;
    private int tournamentSize;
    private double crossoverChance;
    private double mutationChance;
    private double std;
    private double mean;
    private int hiddenLayerSize;

    public MainForm() {
        prepareNativeLook();
        resetToDefaultValues();
        prepareLogger();
        prepareMethodListeners();
    }

    private void prepareNativeLook() {
        try {
            setLookAndFeel(getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private void resetToDefaultValues() {
        passLimitTextField.setText(String.valueOf(1000));
        searchTimeLimitTextField.setText(String.valueOf(Integer.MAX_VALUE));
        populationSizeTextField.setText(String.valueOf(100));
        tournamentSizeTextField.setText(String.valueOf(2));
        crossOverChanceTextField.setText(String.valueOf(0.40));
        mutationChanceTextField.setText(String.valueOf(0.01));
        standardDeviationTextField.setText(String.valueOf(10.0));
        meanTextField.setText(String.valueOf(0.0));
        hiddenLayerSizeTextField.setText(String.valueOf(10));
    }

    private void prepareLogger() {
        logger = Logger.getInstance();
        logger.setLoggerTextArea(loggerTextArea);
        newScheduledThreadPool(1).scheduleAtFixedRate(logger, 0, 100, MILLISECONDS);
    }

    private void prepareMethodListeners() {
        resetToDefaultValuesButton.addActionListener(e -> resetToDefaultValues());
        loadNeuralNetworkButton.addActionListener(e -> loadNeuralNetwork());
        saveNeuralNetworkToButton.addActionListener(e -> saveNeuralNetwork());
        saveGraphToFileButton.addActionListener(e -> saveGraphToFile());
        runSimulationButton.addActionListener(e -> runSimulation());
        runNeuralNetworkLearningButton.addActionListener(e -> runNeuralNetworkLearning());
    }

    private void loadNeuralNetwork() {
        fileChooser.setCurrentDirectory(new File("").getAbsoluteFile());
        int result = fileChooser.showOpenDialog(rootPanel);
        if (result == APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                neuralNetwork = fromJson(loadFile(selectedFile.getAbsolutePath()), NeuralNetwork.class);
                if (neuralNetwork == null) {
                    throw new Exception("Neural network was null");
                } else {
                    logger.log("File loaded properly");
                }
            } catch (Exception exception) {
                logger.log("This is not a valid neural network!");
            }
        }
    }

    private void saveNeuralNetwork() {
        Result result = learn.getResult();
        if (result != null) {
            neuralNetwork = result.getIndividual().getNeuralNetwork();
        }
        if (neuralNetwork == null) {
            logger.log("There is no neural network ready yet!");
            return;
        }
        fileChooser.setCurrentDirectory(new File("").getAbsoluteFile());
        fileChooser.setSelectedFile(new File(now().format(dateTimeFormatter) + ".json"));
        int fileChooserResult = fileChooser.showSaveDialog(rootPanel);
        if (fileChooserResult == APPROVE_OPTION) {
            saveFile(fileChooser.getSelectedFile().getAbsolutePath(), toJson(neuralNetwork));
        }
    }

    private void saveGraphToFile() {
        Result result = learn.getResult();
        if (result == null) {
            logger.log("You need to learn some neural network first");
            return;
        }
        fileChooser.setCurrentDirectory(new File("").getAbsoluteFile());
        fileChooser.setSelectedFile(new File(now().format(dateTimeFormatter) + ".png"));
        int fileChooserResult = fileChooser.showSaveDialog(rootPanel);
        if (fileChooserResult == APPROVE_OPTION) {
            FileUtils.saveGraphToFile(result.getPopulationFitnessStatistics(),
                    fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void runSimulation() {
        Result result = learn.getResult();
        if (result != null) {
            neuralNetwork = result.getIndividual().getNeuralNetwork();
        }
        if (neuralNetwork == null) {
            logger.log("Please select neural network first");
            return;
        }
        replay.setNeuralNetwork(neuralNetwork);
        replay.setGraphics(withGraphicsCheckBox.isSelected());
        actionExecutorService.submit(replay);

    }

    private void runNeuralNetworkLearning() {
        if (areFieldsValid()) {
            learn.setGraphics(withGraphicsCheckBox.isSelected());
            learn.setPassLimit(passLimit);
            learn.setSearchTimeLimit(searchTimeLimit);
            learn.setPopulationSize(populationSize);
            learn.setTournamentSize(tournamentSize);
            learn.setCrossoverChance(crossoverChance);
            learn.setMutationChance(mutationChance);
            learn.setStd(std);
            learn.setMean(mean);
            learn.setHiddenLayerSize(hiddenLayerSize);
            actionExecutorService.submit(learn);
        }
    }

    private boolean areFieldsValid() {
        boolean fieldsValid = true;

        try {
            passLimit = Integer.valueOf(passLimitTextField.getText());
            if (passLimit <= 0) {
                logger.log("Pass limit is too small!");
                fieldsValid = false;
            }
        } catch (Exception exception) {
            logger.log("Invalid of format of pass limit!");
            fieldsValid = false;
        }
        try {
            searchTimeLimit = Integer.valueOf(searchTimeLimitTextField.getText());
            if (searchTimeLimit <= 0) {
                logger.log("Time limit is too small!");
                fieldsValid = false;
            }
        } catch (Exception exception) {
            logger.log("Invalid of format of search time limit!");
            fieldsValid = false;
        }
        try {
            populationSize = Integer.valueOf(populationSizeTextField.getText());
            if (populationSize <= 0) {
                logger.log("Population size is too small!");
                fieldsValid = false;
            }
        } catch (Exception exception) {
            logger.log("Invalid of format of population size!");
            fieldsValid = false;
        }
        try {
            tournamentSize = Integer.valueOf(tournamentSizeTextField.getText());
            if (tournamentSize <= 0) {
                logger.log("Tournament size is too small!");
                fieldsValid = false;
            }
        } catch (Exception exception) {
            logger.log("Invalid of format of tournament size!");
            fieldsValid = false;
        }
        try {
            crossoverChance = Double.valueOf(crossOverChanceTextField.getText());
            if (crossoverChance < 0.0) {
                logger.log("Crossover chance is too small!");
                fieldsValid = false;
            }
        } catch (Exception exception) {
            logger.log("Invalid of format of cross over chance!");
            fieldsValid = false;
        }
        try {
            mutationChance = Double.valueOf(mutationChanceTextField.getText());
            if (mutationChance < 0.0) {
                logger.log("Mutation chance is too small!");
                fieldsValid = false;
            }
        } catch (Exception exception) {
            logger.log("Invalid of format of mutation chance!");
            fieldsValid = false;
        }
        try {
            std = Double.valueOf(standardDeviationTextField.getText());
        } catch (Exception exception) {
            logger.log("Invalid of format of standard deviation!");
            fieldsValid = false;
        }
        try {
            mean = Double.valueOf(meanTextField.getText());
        } catch (Exception exception) {
            logger.log("Invalid of format of mean!");
            fieldsValid = false;
        }
        try {
            hiddenLayerSize = Integer.valueOf(hiddenLayerSizeTextField.getText());
            if (hiddenLayerSize <= 0) {
                logger.log("Hidden layer size is too small!");
                fieldsValid = false;
            }
        } catch (Exception exception) {
            logger.log("Invalid of format of hidden layer size!");
            fieldsValid = false;
        }

        return fieldsValid;
    }

    public static void main(String[] args) {
        JFrame rootFrame = new JFrame("Talandar");
        rootFrame.setContentPane(new MainForm().rootPanel);
        rootFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        rootFrame.pack();
        rootFrame.setVisible(true);
    }

    private void createUIComponents() {
        simulationUI = SimulationUI.getInstance();
    }
}
