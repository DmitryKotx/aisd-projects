package com.company;


import com.util.ArrayUtils;
import com.util.JTableUtils;
import com.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JTable tableInput1;
    private JButton buttonLoadInputFromFile1;
    private JButton buttonResult;
    private JTable cardPile;
    private JButton buttonSaveOutputIntoFile;
    private JButton shake;
    private JTable cardTable;
    private JTextField textField1;
    private JButton buttonResult1;


    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;


    public FrameMain() {
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableInput1, 50, false, true, false, true);
        tableInput1.setRowHeight(25);
        JTableUtils.initJTableForArray(cardPile, 50, false, true, false, true);
        cardPile.setRowHeight(25);
        JTableUtils.initJTableForArray(cardTable, 50, false, true, false, true);
        cardTable.setRowHeight(25);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        JTableUtils.writeArrayToJTable(tableInput1, new int[][]{
        });
        this.pack();
        this.pack();

        buttonLoadInputFromFile1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        String[] arr = ArrayUtils.readLinesFromFile(fileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(tableInput1, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonSaveOutputIntoFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[] arr = JTableUtils.readIntArrayFromJTable(cardPile);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(file, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        shake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String[] arr = JTableUtils.readStringArrayFromJTable(tableInput1);
                    Random rnd = new Random();
                    for(int i = 0; i < arr.length; i++) {
                        int index = rnd.nextInt(i + 1);
                        String a = arr[index];
                        arr[index] = arr[i];
                        arr[i] = a;
                    }
                    JTableUtils.writeArrayToJTable(tableInput1, arr);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String[] arr = JTableUtils.readStringArrayFromJTable(tableInput1);
                    CardDeck[] cardDecks = new CardDeck[arr.length];
                    for (int i = 0; i < cardDecks.length; i++) {
                        cardDecks[i] = new CardDeck(arr[i].substring(0,1),arr[i].substring(1));
                    }
                    Result result = Solution.myVersion(cardDecks);
                    String[] cardT = new String[result.getTable().length];
                    for (int i = cardT.length - 1; i >= 0; i--) {
                        cardT[i] = result.getTable()[i].getValue() + result.getTable()[i].getSuit();
                    }
                    String[] cardP = new String[result.getDeck().length];
                    for (int i = 0; i < cardP.length; i++) {
                        cardP[i] = result.getDeck()[i].getValue()+ result.getDeck()[i].getSuit();
                    }
                    JTableUtils.writeArrayToJTable(cardPile, cardP);
                    JTableUtils.writeArrayToJTable(cardTable, cardT);
                    textField1.setText(Integer.toString(result.getSteps()));

                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonResult1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String[] arr = JTableUtils.readStringArrayFromJTable(tableInput1);
                    CardDeck[] cardDecks = new CardDeck[arr.length];
                    for (int i = 0; i < cardDecks.length; i++) {
                        cardDecks[i] = new CardDeck(arr[i].substring(0,1),arr[i].substring(1));
                    }
                    Result result = Solution.integrated(cardDecks);
                    String[] cardT = new String[result.getTable().length];
                    for (int i = cardT.length - 1; i >= 0; i--) {
                        cardT[i] = result.getTable()[i].getValue() + result.getTable()[i].getSuit();
                    }
                    String[] cardP = new String[result.getDeck().length];
                    for (int i = 0; i < cardP.length; i++) {
                        cardP[i] = result.getDeck()[i].getValue()+ result.getDeck()[i].getSuit();
                    }
                    JTableUtils.writeArrayToJTable(cardPile, cardP);
                    JTableUtils.writeArrayToJTable(cardTable, cardT);
                    textField1.setText(Integer.toString(result.getSteps()));
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
