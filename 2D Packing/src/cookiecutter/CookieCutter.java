package cookiecutter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import NMCS.InputOutput;
import Objects.BoardCuts;
import Objects.Item;
import Objects.Cell;

public class CookieCutter {

//    static boolean[][][][] cutHistory = new boolean[21][20][20][3];
    static List<BoardCuts> cutHistory;
    static JFrame frame1 = new JFrame("Fill");
    public static JPanel bigPanel = new JPanel();
    public static JPanel room = new JPanel();
    public static JPanel sliderPanel = new JPanel();
    public static JLabel label = new JLabel();
    public static JLabel label1 = new JLabel();
    public static JButton forward = new JButton("Next Cut");
    public static JButton back = new JButton("Prior Cut");
    public static JButton forward1 = new JButton("Next Sheet");
    public static JButton back1 = new JButton("Prior Sheet");
    public static JSlider slider1;
    public static int sheet;
    public static int cutNum;
    
    public static JFrame frame = new JFrame("");
    public static JPanel labelPane = new JPanel();
    public static JPanel JustinPanel = new JPanel();
    public static JButton submit = new JButton("Add");
    public static JTextField quant = new JTextField(3);
    public static int rows = 9;
    public static int col = 9;
    public static JButton[][] buttonChecks = new JButton[rows][col];
    public static ArrayList<Item> listOfItems = new ArrayList<>();
    public static JSlider slider = new JSlider(3, 9, 9);
    public static JButton finalSubmission = new JButton("Submit");
    public static JLabel shitty = new JLabel("");

    public static boolean checkBlack(boolean[][][][] cutHistory, int cutNumber, int boardNumber) {
            for (int j = 0; j < cutHistory[0].length; j++) {
                for (int k = 0; k < cutHistory[0][0].length; k++) {
                        if (cutHistory[cutNumber][j][k][boardNumber]) {
                            return false;
                        }
                }
        }
        return true;
    }
    
    public static void createBoard(int x, int y) {
        JustinPanel.removeAll();
        
        for (int row = 0; row < x; row++) {
            for (int columns = 0; columns < y; columns++) {
                JButton coorButton = new JButton();
                coorButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                coorButton.addActionListener(new ButtonPressed());
                coorButton.setPreferredSize(new Dimension(520 / rows, 520 / col));
                coorButton.setActionCommand(row + " " + columns);
                buttonChecks[row][columns] = coorButton;
                JustinPanel.add(coorButton);
            }
        }
        shitty.setOpaque(false);
        JustinPanel.add(shitty);
        JustinPanel.add(submit);
        JustinPanel.add(finalSubmission);
        JustinPanel.add(slider);
        JustinPanel.add(quant);
        JustinPanel.revalidate();
        JustinPanel.repaint();
    }
    
    public static class ButtonPressed implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            if (command.equals("add")) {
                Item submitted = new Item();
                for (int i = 0; i < rows; i++) {
                    for (int q = 0; q < col; q++) {
                        if (buttonChecks[i][q].getBackground() == Color.RED) {
                            submitted.addCell(new Cell(i, q));
                        }
                    }
                }
                try {
                    for (int i = 0; i < Integer.parseInt(quant.getText()); i++) {
                        listOfItems.add(submitted);
                    }
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(frame, "You caused an error: " + exception.getMessage());
                }
                System.out.println(listOfItems.size());
                createBoard(rows, col);
            } else if (command.equals("submit")) {
                System.out.println(listOfItems);
//                cutHistory = new ArrayList(InputOutput.cutHistory(listOfItems));
                cutHistory = InputOutput.cutHistory(new ArrayList(listOfItems));
                display(cutHistory, 0, 0);
                frame.setVisible(false);
            } else {
                JButton button = (JButton) e.getSource();
                button.setBackground(button.getBackground() == Color.RED ? new JButton().getBackground() : Color.RED);
            }
        }
    }
    
    public static class SliderChange implements ChangeListener {
        
        @Override
        public void stateChanged(ChangeEvent event) {
            JSlider source = (JSlider) event.getSource();
            if (!source.getValueIsAdjusting()) {
                rows = (int) source.getValue();
                col = (int) source.getValue();
                buttonChecks = new JButton[rows][col];
                createBoard(rows, col);
            }
        }
    }
    
    private static class UserInput implements ChangeListener {
        
        public void stateChanged(ChangeEvent e) {
            JSlider slider1 = (JSlider) e.getSource();
            if (!slider1.getValueIsAdjusting()) {
                bigPanel.removeAll();
                labelPane.removeAll();
                room.removeAll();
                sliderPanel.removeAll();
                display(cutHistory, slider1.getValue(), sheet);
                bigPanel.revalidate();
                bigPanel.repaint();
                room.revalidate();
                room.repaint();
                sliderPanel.revalidate();
                sliderPanel.repaint();
            }
        }
    }
    
    private static class ButtonAction implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("forward")) {
                slider1.setValue(slider1.getValue() + 1);
            } else if (e.getActionCommand().equals("back")) {
                slider1.setValue(slider1.getValue() - 1);
            } else if (e.getActionCommand().equals("forward1")) {
                slider1.setValue(0);
                labelPane.removeAll();
                bigPanel.removeAll();
                room.removeAll();
                sliderPanel.removeAll();
                display(cutHistory, cutNum, sheet + 1);
                labelPane.revalidate();
                labelPane.repaint();
                bigPanel.revalidate();
                bigPanel.repaint();
                room.revalidate();
                room.repaint();
                sliderPanel.revalidate();
                sliderPanel.repaint();
            } else {
                slider1.setValue(0);
                labelPane.removeAll();
                bigPanel.removeAll();
                room.removeAll();
                sliderPanel.removeAll();
                display(cutHistory, cutNum, sheet - 1);
                bigPanel.revalidate();
                bigPanel.repaint();
                labelPane.revalidate();
                labelPane.repaint();
                room.revalidate();
                room.repaint();
                sliderPanel.revalidate();
                sliderPanel.repaint();
            }
        }
    }
    
    public static void display(List<BoardCuts> cutHistory, int cutNumber, int boardNumber) {
//        for (int p = 0; p < cutHistory[0][0][0].length; p++) {
//
//            for (int i = 0; i < cutHistory.length; i++) {
//                for (int j = 0; j < cutHistory[0].length; j++) {
//                    for (int k = 0; k < cutHistory[0][0].length; k++) {
//                        System.out.println("Row: " + i + " Col: " + j + " CutNum: " + k + " Sheet: " + p + cutHistory[i][j][k][p]);
//                    }
//                }
//
//            }
//        }

//        System.out.println(cutHistory.get(0).size());
//        System.out.println(cutHistory.get(1).size());
//        System.exit(0);
        boolean[][] booleanGrid = cutHistory.get(boardNumber).get(cutNumber);

        int roomRow = booleanGrid.length;
        int roomCol = booleanGrid.length;
        int numberOfSheets = cutHistory.size();
        int[] numberOfSteps = new int[numberOfSheets];
//        int steps;
//        for (int i = 0; i < numberOfSheets; i++) {
//            steps = 1;
//            for (int j = 0; j < cutHistory.length; j++) {
//                if (!checkBlack(cutHistory,j,i)) {
//                    steps++;
//                }
//            }
//            numberOfSteps[i] = steps;
//        }
        label.setText("Cut " + Integer.toString(cutNumber));
        label1.setText("Sheet " + Integer.toString(boardNumber));
        sheet = boardNumber;
        cutNum = cutNumber;
        
        forward.setEnabled(true);
        if (cutNumber == numberOfSteps[boardNumber] - 1) {
            forward.setEnabled(false);
        }
        
        back.setEnabled(true);
        if (cutNumber == 0) {
            back.setEnabled(false);
        }
        
        forward1.setEnabled(true);
        if (boardNumber == numberOfSheets - 1) {
            forward1.setEnabled(false);
        }
        
        back1.setEnabled(true);
        if (boardNumber == 0) {
            back1.setEnabled(false);
        }
        
        slider1 = new JSlider(0, cutHistory.get(boardNumber).size() - 1, cutNumber);
        slider1.setMajorTickSpacing(5);
        slider1.setMinorTickSpacing(1);
        slider1.setPaintTicks(true);
        slider1.setPaintLabels(true);
        slider1.addChangeListener(new UserInput());
        sliderPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        sliderPanel.add(label1);
        sliderPanel.add(label);
        sliderPanel.add(slider1);
        sliderPanel.add(back);
        sliderPanel.add(forward);
        sliderPanel.add(back1);
        sliderPanel.add(forward1);
        
        int maxDimension = Math.max(roomRow, roomCol);
        boolean rowBig = false;
        if (maxDimension == roomRow) {
            rowBig = true;
        }
        
        GridLayout layout = new GridLayout(roomRow, roomCol);
        room.setLayout(layout);
        room.setPreferredSize(new Dimension(700, 700));
        
        if (rowBig) {
            frame1.setPreferredSize(new Dimension(700 * roomCol / roomRow, 800));
        } else {
            frame1.setPreferredSize(new Dimension(700, 700 * roomRow / roomCol + 100));
        }
        
        Dimension CELL_SIZE = new Dimension(700 / maxDimension * 4 / 5, 700 / maxDimension * 4 / 5);
        
        JLabel[][] labels = new JLabel[roomRow][roomCol];

//        for (int i = 0; i < numberOfSheets; i++) {
//            for (int j = 0; j < numberOfSteps[boardNumber]; j++) {
//                for (int k = 0; k < roomRow; k++) {
//                    for (int p = 0; p < roomCol; p++) {
//                        System.out.println("Row: " + k + " Col: " + p + " CutNum: " + j + " Sheet: " + i + cutHistory[j][k][p][i]);
//                    }
//                }
//            }
//        }
        
        for (int i = 0; i < roomRow; i++) {
            for (int j = 0; j < roomCol; j++) {
                labels[i][j] = new JLabel("");
                labels[i][j].setPreferredSize(CELL_SIZE);
                labels[i][j].setOpaque(true);

                //System.out.println(cutHistory[cutNumber][i][j]);
                if (booleanGrid[i][j]) {
                    labels[i][j].setBackground(new Color(255, 255, 255));
                } else {
                    labels[i][j].setBackground(new Color(0, 0, 0));
                }
                JPanel labelPane = new JPanel();
                labelPane.add(labels[i][j]);
                labelPane.setPreferredSize(CELL_SIZE);
                room.add(labelPane);
            }
        }
        bigPanel.add(sliderPanel);
        bigPanel.add(room);
        frame1.add(bigPanel);
        frame1.pack();
        frame1.setResizable(false);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        quant.setText("1");
        submit.setActionCommand("add");
        submit.addActionListener(new ButtonPressed());
        quant.setHorizontalAlignment(SwingConstants.RIGHT);
        finalSubmission.setActionCommand("submit");
        finalSubmission.addActionListener(new ButtonPressed());
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.addChangeListener(new SliderChange());
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(3);
        slider.setMinorTickSpacing(1);
        
        frame.pack();
        JustinPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JustinPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JustinPanel.setPreferredSize(new Dimension(900, 730));
        createBoard(rows, col);
        frame.setContentPane(JustinPanel);
        frame.setSize(600, 730);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        
//        Random random = new Random();
//
//        for (int sheet = 0; sheet < 3; sheet++) {
//            for (int cut = 0; cut < 21; cut++) {
//                for (int col = 0; col < 20; col++) {
//                    for (int row = 0; row < 20; row++) {
//                        cutHistory[cut][row][col][sheet] = random.nextBoolean();
//                    }
//                }
//            }
//        }
        forward.addActionListener(new ButtonAction());
        forward.setActionCommand("forward");
        back.addActionListener(new ButtonAction());
        back.setActionCommand("back");
        forward1.addActionListener(new ButtonAction());
        forward1.setActionCommand("forward1");
        back1.addActionListener(new ButtonAction());
        back1.setActionCommand("back1");
    }
}
