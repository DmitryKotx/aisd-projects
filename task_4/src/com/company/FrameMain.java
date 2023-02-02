package com.company;

import javax.swing.Timer;

import com.util.ArrayUtils;
import com.util.JTableUtils;
import com.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JTable tableInput1;
    private JButton buttonLoadInputFromFile1;
    private JButton Start;
    private JTable result;
    private JButton Stop;
    private JTextField text_I;
    private JTextField text_J;
    private JButton backButton;
    private JButton forwardButton;
    private JButton randomButton;
    private JTextField change1;
    private JTextField compare1;
    private JTextField change2;
    private JTextField compare2;
    private JTextField textInserted;

    private int k = -1;
    private Timer timer;
    List<SortState> list = new ArrayList<>();

    StateViewer viewer;


    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;



    public FrameMain() throws ParseException {
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableInput1, 50, false, true, false, true);
        tableInput1.setRowHeight(25);
        JTableUtils.initJTableForArray(result, 50, false, true, false, true);
        result.setRowHeight(25);

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
                        int[] arr = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(tableInput1, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }

        });


       Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[] data = JTableUtils.readIntArrayFromJTable(tableInput1);
                    assert data != null;
                    list = InsertionSort.sort(data);
                    timer = new Timer(200, e -> {
                        if(timer.isRunning()) {
                            Start.setEnabled(false);
                            backButton.setEnabled(false);
                            forwardButton.setEnabled(false);
                        }
                        k++;
                        SortDemoTextRenderer.textRenderer(list, k, result, compare1, compare2, change1, change2, textInserted, text_I, text_J);
                        SortDemoCellRenderer.cellRenderer(list,k,result);

                        if (k == list.size()-1) {
                            timer.stop();
                            backButton.setEnabled(true);
                        }
                    });

                        timer.start();
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        Stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                backButton.setEnabled(true);
                forwardButton.setEnabled(true);
                Start.setEnabled(true);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (k < list.size()) {
                    forwardButton.setEnabled(true);
                    Start.setEnabled(true);
                }
                k--;
                SortDemoTextRenderer.textRenderer(list, k, result, compare1, compare2, change1, change2, textInserted, text_I, text_J);
                SortDemoCellRenderer.cellRenderer(list,k,result);
                if (k == 0) {
                    backButton.setEnabled(false);
                }
            }
        });

        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (k > 0) {
                    backButton.setEnabled(true);
                }

                k++;
                JTableUtils.writeArrayToJTable(result, list.get(k).getArr());
                SortDemoTextRenderer.textRenderer(list, k, result, compare1, compare2, change1, change2, textInserted, text_I, text_J);
                SortDemoCellRenderer.cellRenderer(list,k,result);
                if (k == list.size()-1) {
                    forwardButton.setEnabled(false);
                    Start.setEnabled(false);
                }
            }
        });
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[] arr = ArrayUtils.createRandomIntArray(tableInput1.getColumnCount(), 100);
                    JTableUtils.writeArrayToJTable(tableInput1, arr);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });


    }

    public interface StateViewer {
        void show(SortState ss);
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
