package Program.demo;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import Program.Graph;
import Program.myProgram;
import util.DrawUtils;

public class GraphDemoFrame extends JFrame {
    private Graph<Integer> graph = new Graph<>();
    private static boolean deleteVertex;
    private static boolean deleteEdge;
    private static PaintTools.Connection choose;
    private static boolean motion;
    private PaintTools.Circle movableVertex;
    private PaintTools.Connection connection;
    private JPanel panelMain;
    private JButton solutionButton;
    private JTextField textField;
    private javax.swing.JPanel JPanel;

    private JFileChooser fileChooserTxtOpen;
    private JFileChooser fileChooserDotOpen;
    private JFileChooser fileChooserTxtSave;
    private JFileChooser fileChooserDotSave;
    private JFileChooser fileChooserImgSave;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private static class GraphPanel extends JPanel {
        List<PaintTools.Circle> vertexes = new ArrayList<>();
        List<PaintTools.Connection> edges = new ArrayList<>();

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (PaintTools.Connection l : edges) {
                Graphics2D g2d = (Graphics2D) g;
                if (motion) {
                    PaintTools.Circle begin = l.getBeginCircle();
                    PaintTools.Circle end = l.getEndCircle();
                    l.setPosition(begin.getXCenter(), begin.getYCenter(), end.getXCenter(), end.getYCenter());
                }
                g2d.setStroke(new BasicStroke(4));
                g2d.setColor(l.getLineColor());
                g2d.drawLine(l.getXBegin(), l.getYBegin(), l.getXEnd(), l.getYEnd());
            }
            for (PaintTools.Circle c : vertexes) {
                g.setColor(c.getBorderColor());
                g.fillOval(c.getXExternal(), c.getYExternal(), 2 * c.getExRadius(), 2 * c.getExRadius());
                g.setColor(c.getFillColor());
                g.fillOval(c.getXInternal(), c.getYInternal(), 2 * c.getInRadius(), 2 * c.getInRadius());
                g.setColor(c.getValueColor());
                DrawUtils.drawStringInCenter(g, c.getValueFont(), Integer.toString(c.getValue()),
                        c.getXValue(), c.getYValue(), c.getExRadius(), c.getExRadius());
            }
        }
    }


    public GraphDemoFrame() {
        this.setTitle("Графы");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


        connection = new PaintTools.Connection();

        deleteVertex = false;

        deleteEdge = false;

        motion = false;

        movableVertex = null;

        fileChooserTxtOpen = new JFileChooser();
        fileChooserDotOpen = new JFileChooser();
        fileChooserTxtSave = new JFileChooser();
        fileChooserDotSave = new JFileChooser();
        fileChooserImgSave = new JFileChooser();
        fileChooserTxtOpen.setCurrentDirectory(new File("./files/input"));
        fileChooserDotOpen.setCurrentDirectory(new File("./files/input"));
        fileChooserTxtSave.setCurrentDirectory(new File("./files/input"));
        fileChooserDotSave.setCurrentDirectory(new File("./files/input"));
        fileChooserImgSave.setCurrentDirectory(new File("./files/output"));
        FileFilter txtFilter = new FileNameExtensionFilter("Text files (*.txt)", "txt");
        FileFilter dotFilter = new FileNameExtensionFilter("DOT files (*.dot)", "dot");
        FileFilter svgFilter = new FileNameExtensionFilter("SVG images (*.svg)", "svg");
        //FileFilter pngFilter = new FileNameExtensionFilter("PNG images (*.png)", "png");

        fileChooserTxtOpen.addChoosableFileFilter(txtFilter);
        fileChooserDotOpen.addChoosableFileFilter(dotFilter);
        fileChooserTxtSave.addChoosableFileFilter(txtFilter);
        fileChooserDotSave.addChoosableFileFilter(dotFilter);
        fileChooserImgSave.addChoosableFileFilter(svgFilter);
        //fileChooserImgSave.addChoosableFileFilter(pngFilter);

        fileChooserTxtSave.setAcceptAllFileFilterUsed(false);
        fileChooserTxtSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserTxtSave.setApproveButtonText("Save");
        fileChooserDotSave.setAcceptAllFileFilterUsed(false);
        fileChooserDotSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserDotSave.setApproveButtonText("Save");
        fileChooserImgSave.setAcceptAllFileFilterUsed(false);
        fileChooserImgSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserImgSave.setApproveButtonText("Save");

        JPanel.setLayout(new BorderLayout());
        GraphPanel panelGraph = new GraphPanel();
        JPanel.add(new JScrollPane(panelGraph));


        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (deleteVertex && !deleteEdge) {
                    int code = e.getKeyCode();
                    if (code == KeyEvent.VK_BACK_SPACE) {
                        PaintTools.Circle circle = connection.getBeginCircle();
                        panelGraph.vertexes.remove(circle);
                        graph.removeNode(circle.getValue());
                        panelGraph.edges.removeIf(c -> c.getBeginCircle() == circle || c.getEndCircle() == circle);
                        connection.clear();
                        for (int i = 0; i < panelGraph.vertexes.size(); i++) {
                            panelGraph.vertexes.get(i).setValue(i);
                        }
                        deleteVertex = false;
                        repaint();
                    }
                } else if (deleteEdge && !deleteVertex) {
                    int code = e.getKeyCode();
                    if (code == KeyEvent.VK_BACK_SPACE) {
                        panelGraph.edges.remove(choose);
                        graph.disconnect(choose.getBeginCircle().getValue(), choose.getEndCircle().getValue());
                        choose = null;
                        deleteEdge = false;
                        repaint();
                    }
                }
                return false;
            }
        });
        panelGraph.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (e.getClickCount() == 1 && !deleteEdge) {
                        for (PaintTools.Circle c : panelGraph.vertexes) {
                            int xCenter = c.getXExternal() + c.getExRadius();
                            int yCenter = c.getYExternal() + c.getExRadius();
                            double segment = Math.sqrt((x - xCenter) * (x - xCenter) + (y - yCenter) * (y - yCenter));
                            if (segment <= c.getExRadius() && !motion) {
                                if (connection.isConfirmed()) {
                                    connection.getBeginCircle().setColors(connection.getBeginCircle().getFillColor(), Color.BLACK, Color.BLACK);
                                }
                                if (connection.getBeginCircle() != c) {
                                    connection.setBeginCircle(c);
                                    connection.setConfirmed(true);
                                    deleteVertex = true;
                                    c.setColors(c.getFillColor(), Color.RED, c.getValueColor());
                                } else {
                                    connection.clear();
                                    deleteVertex = false;
                                }
                                repaint();
                                break;
                            }
                        }
                    }
                    if (!deleteVertex) {
                        for (PaintTools.Connection l : panelGraph.edges) {
                            if (l.isClickPointBelongsArea(x, y)) {
                                if (!deleteEdge) {
                                    choose = l;
                                    choose.setLineColor(Color.RED);
                                    deleteEdge = true;
                                } else if (l == choose) {
                                    choose.setLineColor(Color.BLACK);
                                    choose = null;
                                    deleteEdge = false;
                                } else {
                                    choose.setLineColor(Color.BLACK);
                                    choose = l;
                                    choose.setLineColor(Color.RED);
                                }
                                repaint();
                                return;
                            }
                        }
                    }
                } else {
                    if (deleteEdge) {
                        return;
                    }
                    if (e.getClickCount() == 1) {
                        int offset = 100;
                        boolean flag = true;
                        for (PaintTools.Circle c : panelGraph.vertexes) {
                            int xCenter = c.getXExternal() + c.getExRadius();
                            int yCenter = c.getYExternal() + c.getExRadius();
                            double segment = Math.sqrt((x - xCenter) * (x - xCenter) + (y - yCenter) * (y - yCenter));
                            if (segment <= c.getExRadius() && c != movableVertex) {
                                if (connection.isConfirmed()) {
                                    connection.setEndCircle(c);
                                    flag = false;
                                    break;
                                } else if (!motion && !connection.isConfirmed()) {
                                    motion = true;
                                    movableVertex = c;
                                    movableVertex.setColors(c.getFillColor(), Color.BLUE, c.getValueColor());
                                    flag = false;
                                    break;
                                }
                            }
                            if (segment <= c.getExRadius() + offset && c != movableVertex) {
                                flag = false;
                            }
                        }
                        if (motion && !connection.isConfirmed() && flag) {
                            flag = false;
                            movableVertex.setColors(movableVertex.getFillColor(), Color.BLACK, Color.BLACK);
                            motion = false;
                            movableVertex = null;
                            repaint();
                        }
                        if (flag && !connection.isConfirmed()) {
                            panelGraph.vertexes.add(new PaintTools.Circle(x, y, panelGraph.vertexes.size()));
                            graph.addNode(graph.vertexCount);
                            for (PaintTools.Circle c : panelGraph.vertexes) {
                                c.setColors(Color.WHITE, Color.BLACK, Color.BLACK);
                            }
                            panelGraph.repaint();
                        } else if (!flag && connection.isConfirmed() && connection.getBeginCircle() != connection.getEndCircle()) {
                            for (PaintTools.Connection v : panelGraph.edges) {
                                if (connection.isEqual(v)) {
                                    panelGraph.edges.remove(v);
                                    break;
                                }
                            }
                            if (connection.getEndCircle() != null) {
                                deleteVertex = false;
                                JTextField txt = new JTextField(10);
                                txt.setFocusable(true);
                                panelGraph.edges.add(connection);
                                graph.connectNodes(connection.getBeginCircle().getValue(), connection.getEndCircle().getValue());
                                connection.getBeginCircle().setColors(Color.WHITE, Color.BLACK, Color.BLACK);
                                panelGraph.repaint();
                                connection = new PaintTools.Connection();
                            }
                        }
                    }
                }
            }
        });

        panelGraph.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                if (motion) {
                    int x = e.getX();
                    int y = e.getY();
                    movableVertex.setPosition(x, y);
                    repaint();
                }
            }
        });
        solutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean[] color = new boolean[graph.vertexCount];
                textField.setText(Boolean.valueOf(myProgram.check(graph, graph.getNodes().get(0), color)).toString());
                if (myProgram.check(graph, graph.getNodes().get(0), color)) {
                    for (PaintTools.Circle c : panelGraph.vertexes) {
                        if (color[c.getValue()]) {
                            c.setColors(Color.YELLOW, Color.BLACK, Color.BLACK);
                        } else {
                            c.setColors(Color.GREEN, Color.BLACK, Color.BLACK);
                        }
                    }
                } else {
                    for (PaintTools.Circle c : panelGraph.vertexes) {
                        c.setColors(Color.WHITE, Color.BLACK, Color.BLACK);
                    }
                }
                repaint();
            }
        });
    }


    /**
     * Преобразование dot-записи в svg-изображение (с помощью Graphviz)
     *
     * @param dotSrc dot-запись
     * @return svg
     * @throws IOException
     */

}
