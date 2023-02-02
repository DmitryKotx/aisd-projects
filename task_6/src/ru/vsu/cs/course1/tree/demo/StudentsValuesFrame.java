package ru.vsu.cs.course1.tree.demo;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class StudentsValuesFrame extends JFrame {
    private JPanel panelMain;
    private JSplitPane splitPaneMain;
    private JList<String> outputList;
    private JTextField nameField;
    private JTextField StudentListTextField;
    private JTable studentData;
    private JButton addButton;
    private JButton selectDelButton;
    private JButton deleteSubject;
    private JButton addSubject;
    private JTextField FIOField;
    private JTextField subject;
    private JTextField subjectValue;

    private final JFileChooser fileChooserOpen;
    private JMenuBar menuBarMain;
    private final JFileChooser fileChooserSave;


    private static DefaultListModel<String> listModel = new DefaultListModel<>();
    private static Map<String, Map<String, String>> student = new TreeMap<>();
    private static String cur = "";

    public static Map<String, Map<String, String>> getStudent() {
        return student;
    }

    public static String getCur() {
        return cur;
    }

    public StudentsValuesFrame() {
        this.setTitle("Журнал");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        createMenu();

        splitPaneMain.setDividerLocation(0.35);
        splitPaneMain.setBorder(null);

        JTableUtils.initJTableForArray(studentData, 40, false, false, false, false);
        listModel = new DefaultListModel<>();
        studentData.setRowHeight(25);
        studentData.setPreferredSize(new Dimension(-1, 90));
        outputList.setModel(listModel);
        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        Action action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                TableCellListener tcl = (TableCellListener) e.getSource();
                int r = tcl.getRow();
                int c = tcl.getColumn();
                String old = (String) tcl.getOldValue();
                if (c == 1) {
                    String s = (String) tcl.getNewValue();
                    String subject = (String) studentData.getValueAt(r, 0);
                    String name = nameField.getText();
                    student.get(name).put(subject, s);
                } else {
                    String s = (String) tcl.getNewValue();
                    String subjectValue = (String) studentData.getValueAt(r, 1);
                    String name = nameField.getText();
                    student.get(name).put(s, subjectValue);
                    student.get(name).remove(old);
                }
            }
        };

        nameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = nameField.getText();
                student.put(s, student.get(cur));
                student.remove(cur);
                cur = s;
                reload();
            }
        });


        new TableCellListener(studentData, action);
        outputList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (outputList.getSelectedValue() != null) {
                    String s = outputList.getSelectedValue();
                    cur = outputList.getSelectedValue();
                    nameField.setText(s);
                    /*for (Map.Entry<String, Map<String, String>> entry : student.entrySet()) {
                        int counter = 0;
                        if (Objects.equals(entry.getKey(), s)) {
                            Set<String> h = entry.getValue().keySet();
                            String[][] input = new String[h.size()][2];
                            for (Map.Entry<String, String> obj : entry.getValue().entrySet()) {
                                input[counter][0] = obj.getKey();
                                input[counter][1] = obj.getValue();
                                counter++;
                            }
                            studentData.setVisible(true);
                            nameField.setVisible(true);
                            selectDelButton.setVisible(true);
                            addSubject.setVisible(true);
                            deleteSubject.setVisible(true);
                            FIOField.setVisible(true);
                            subject.setVisible(true);
                            subjectValue.setVisible(true);
                            JTableUtils.writeArrayToJTable(studentData, input);
                        }
                    }*/

                    int counter = 0;
                    Map<String, String> marks = student.get(s);
                    Set<String> h = marks.keySet();
                    String[][] input = new String[h.size()][2];
                    for (Map.Entry<String, String> obj : marks.entrySet()) {
                        input[counter][0] = obj.getKey();
                        input[counter][1] = obj.getValue();
                        counter++;
                    }
                    studentData.setVisible(true);
                    nameField.setVisible(true);
                    selectDelButton.setVisible(true);
                    addSubject.setVisible(true);
                    deleteSubject.setVisible(true);
                    FIOField.setVisible(true);
                    subject.setVisible(true);
                    subjectValue.setVisible(true);
                    JTableUtils.writeArrayToJTable(studentData, input);
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame jFrame = new JFrame();
                    JDialog jd = new JDialog(jFrame);
                    jd.setLayout(new FlowLayout());
                    jd.setTitle("Добавить студента");
                    jd.setBounds(500, 300, 400, 150);
                    TextField textField = new TextField(30);
                    JLabel jLabel = new JLabel("Введите ФИО студента:");
                    textField.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                if (!textField.getText().equals("")) {
                                    if (!StudentsValuesFrame.getStudent().containsKey(textField.getText())) {
                                        StudentsValuesFrame.getStudent().put(textField.getText(), new TreeMap<>());
                                        StudentsValuesFrame.getStudent().get(textField.getText()).put("Empty", "Empty");
                                    } else {
                                        try {
                                            throw new Exception("Такой студент уже существует");
                                        } catch (Exception ex) {
                                            SwingUtils.showErrorMessageBox(ex);
                                        }
                                    }
                                    StudentsValuesFrame.reload();
                                    jd.setVisible(false);
                                }
                            }
                        }
                    });

                    jd.add(jLabel);
                    jd.add(textField);
                    jd.setVisible(true);

                } catch (Exception ex) {
                    SwingUtils.showErrorMessageBox(ex);
                }
            }
        });
        selectDelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (outputList.getSelectedValue() != null) {
                        cur = outputList.getSelectedValue();
                    }
                    StudentsValuesFrame.getStudent().remove(cur);
                    studentData.setVisible(false);
                    nameField.setVisible(false);
                    selectDelButton.setVisible(false);
                    addSubject.setVisible(false);
                    deleteSubject.setVisible(false);
                    FIOField.setVisible(false);
                    subject.setVisible(false);
                    subjectValue.setVisible(false);
                    reload();
                } catch (Exception ex) {
                    try {
                        throw new Exception("Такого студента нет");
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            }
        });

        deleteSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (outputList.getSelectedValue() != null) {
                        cur = outputList.getSelectedValue();
                    }
                    String out = (String) studentData.getValueAt(studentData.getSelectedRow(), 0);
                    StudentsValuesFrame.getStudent().get(cur).remove(out);
                    if (StudentsValuesFrame.getStudent().get(cur).size() == 0) {
                        StudentsValuesFrame.getStudent().get(cur).put("Empty", "Empty");
                    }
                    nameField.setText(cur);
                    for (Map.Entry<String, Map<String, String>> entry : student.entrySet()) {
                        int counter = 0;
                        if (Objects.equals(entry.getKey(), cur)) {
                            Set<String> h = entry.getValue().keySet();
                            String[][] input = new String[h.size()][2];
                            for (Map.Entry<String, String> obj : entry.getValue().entrySet()) {
                                input[counter][0] = obj.getKey();
                                input[counter][1] = obj.getValue();
                                counter++;
                            }
                            studentData.setVisible(true);
                            nameField.setVisible(true);
                            selectDelButton.setVisible(true);
                            addSubject.setVisible(true);
                            deleteSubject.setVisible(true);
                            FIOField.setVisible(true);
                            JTableUtils.writeArrayToJTable(studentData, input);
                            if (input.length == 1) {
                                subject.setVisible(false);
                                subjectValue.setVisible(false);
                            }
                        }
                    }
                    reload();
                } catch (Exception ex) {
                    try {
                        throw new Exception("Такого предмета нет");
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            }
        });
        addSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SubjectFrame frame = new SubjectFrame();
                    SwingUtils.setDefaultFont("Arial", 20);
                    frame.setVisible(true);

                } catch (Exception ex) {
                    SwingUtils.showErrorMessageBox(ex);
                }
            }
        });
    }

    public static void reload() {
        listModel.clear();
        List<String> s = new ArrayList<>(StudentsValuesFrame.student.keySet());
        Collections.sort(s);
        for (int i = 0; i < s.size(); i++) {
            listModel.addElement(s.get(i));
        }
    }


    private void createMenu() {
        JMenu menuTesting = new JMenu("Управление");
        String addBaseStudents = "Загрузить базу студентов";
        String buttonSaveInputInfoFile = "Сохранить базу студентов в файл";
        JMenuItem menuItem = new JMenuItem(addBaseStudents);
        JMenuItem menuItem2 = new JMenuItem(buttonSaveInputInfoFile);

        menuItem.addActionListener(actionEvent -> {//addB
            try {
                EventQueue.invokeLater(() -> {
                    try {
                        if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                            String[] str = ArrayUtils.readLinesFromFile(fileChooserOpen.getSelectedFile().getPath());
                            for (int i = 0; i < str.length; i++) {
                                String[] elements = str[i].split(";");
                                if (student.containsKey(elements[1])) {
                                    student.get(elements[1]).put(elements[0], elements[2]);
                                } else
                                    student.put(elements[1], new TreeMap<>());
                                student.get(elements[1]).put(elements[0], elements[2]);
                            }
                            List<String> s = new ArrayList<>(student.keySet());
                            listModel.clear();
                            for (int i = 0; i < s.size(); i++) {
                                listModel.addElement(s.get(i));
                            }
                        }
                        reload();
                    } catch (Exception ex) {
                        SwingUtils.showErrorMessageBox(ex);
                    }
                });
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        menuItem2.addActionListener(actionEvent -> {//saveB
            try {
                EventQueue.invokeLater(() -> {
                    try {
                        if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                            String file = fileChooserSave.getSelectedFile().getPath();
                            FileWriter write_to_file = new FileWriter(file, true);
                            PrintWriter print = new PrintWriter(write_to_file);
                            for (Map.Entry<String, Map<String, String>> entry : student.entrySet()) {
                                for (Map.Entry<String, String> entry1 : entry.getValue().entrySet()) {
                                    print.print(entry1.getKey() + ";" + entry.getKey() + ";" + entry1.getValue() + "\n");
                                }
                            }
                            print.close();
                        }
                    } catch (Exception ex) {
                        SwingUtils.showErrorMessageBox(ex);
                    }
                });
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        menuTesting.add(menuItem);
        menuTesting.add(menuItem2);

        menuBarMain = new JMenuBar();
        menuBarMain.add(menuTesting);
        setJMenuBar(menuBarMain);
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


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 0, 0, true, true));
        panelMain.setBackground(new Color(-12828863));
        panelMain.setEnabled(true);
        panelMain.setMinimumSize(new Dimension(450, 118));
        panelMain.setPreferredSize(new Dimension(1150, 424));
        splitPaneMain = new JSplitPane();
        panelMain.add(splitPaneMain, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        splitPaneMain.setLeftComponent(panel1);
        StudentListTextField = new JTextField();
        StudentListTextField.setEditable(false);
        StudentListTextField.setText("Список студентов:");
        panel1.add(StudentListTextField, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        outputList = new JList();
        panel1.add(outputList, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addButton = new JButton();
        addButton.setText("Добавить студента");
        panel2.add(addButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        splitPaneMain.setRightComponent(panel3);
        studentData = new JTable();
        studentData.setShowHorizontalLines(true);
        studentData.setShowVerticalLines(true);
        studentData.setVisible(false);
        panel3.add(studentData, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        selectDelButton = new JButton();
        selectDelButton.setText("Удалить студента");
        selectDelButton.setVisible(false);
        panel4.add(selectDelButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteSubject = new JButton();
        deleteSubject.setText("Удалить предмет");
        deleteSubject.setVisible(false);
        panel4.add(deleteSubject, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addSubject = new JButton();
        addSubject.setText("Добавить предмет");
        addSubject.setVisible(false);
        panel4.add(addSubject, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setVisible(true);
        panel3.add(panel5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        FIOField = new JTextField();
        FIOField.setEditable(false);
        FIOField.setText("ФИО студента:");
        FIOField.setVisible(false);
        panel5.add(FIOField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        nameField = new JTextField();
        nameField.setEditable(true);
        nameField.setText("");
        nameField.setVisible(false);
        panel5.add(nameField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        subject = new JTextField();
        subject.setEditable(false);
        subject.setText("Предмет");
        subject.setVisible(false);
        panel5.add(subject, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        subjectValue = new JTextField();
        subjectValue.setEditable(false);
        subjectValue.setText("Оценка");
        subjectValue.setVisible(false);
        panel5.add(subjectValue, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}
