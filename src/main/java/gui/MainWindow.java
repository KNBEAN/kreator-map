package gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.swingViewer.DefaultView;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.swingViewer.basicRenderer.SwingBasicGraphRenderer;
import org.graphstream.ui.view.*;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;

public class MainWindow extends JFrame implements ViewerListener {
    private JPanel mainPanel;
    private JPanel mapPanel;
    private JPanel sidePanel;
    private JButton addEdgeButton;
    private JButton removeButton;
    private JButton addNodeButton;


    private String activeNodeID;
    private MultiGraph graph;
    private ViewerPipe viewerPipe;
    private boolean loop = true;
    private static int autoId = 0;
    private String css = "graph{\n" +
            "\n" +
            "}\n" +
            "\n" +
            "node{\n" +
            "\ttext-alignment: above;\n" +
            "\ttext-size: 20;\t\n" +
            "}\n" +
            "\n" +
            "node:clicked{\n" +
            "\tfill-color: red;\n" +
            "}\n" +
            "\n" +
            "node:selected{\n" +
            "\tfill-color: blue;\n" +
            "}\n" +
            "\n" +
            "edge{}\n";
    private activeMode currentState;


    public static enum activeMode {
        NONE, NODEADDITION, EDGEADDITION, DELETION
    }

    public MainWindow(String s) throws HeadlessException {
        super(s);
        $$$setupUI$$$();
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        getContentPane().add(mainPanel);
        addMenuBar();
        addListeners();


    }

    private void addListeners() {
        addNodeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (currentState != activeMode.NODEADDITION) {
                    currentState = activeMode.NODEADDITION;
                    addNodeButton.setBorder(BorderFactory.createBevelBorder(1));
                    addEdgeButton.setBorder(null);
                    removeButton.setBorder(null);

                } else {
                    addNodeButton.setBorder(null);
                    currentState = activeMode.NONE;
                }
                activeNodeID = null;
                super.mouseClicked(mouseEvent);


            }
        });
        addEdgeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (currentState != activeMode.EDGEADDITION) {
                    currentState = activeMode.EDGEADDITION;
                    addEdgeButton.setBorder(BorderFactory.createBevelBorder(1));
                    addNodeButton.setBorder(null);
                    removeButton.setBorder(null);

                } else {
                    addEdgeButton.setBorder(null);
                    currentState = activeMode.NONE;
                }
                activeNodeID = null;
                super.mouseClicked(mouseEvent);


            }
        });
        removeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (currentState != activeMode.DELETION) {
                    currentState = activeMode.DELETION;
                    removeButton.setBorder(BorderFactory.createBevelBorder(1));
                    addEdgeButton.setBorder(null);
                    addNodeButton.setBorder(null);
                } else {
                    addEdgeButton.setBorder(null);
                    currentState = activeMode.NONE;
                }
                activeNodeID = null;
                super.mouseClicked(mouseEvent);


            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        currentState = activeMode.NONE;
        graph = new MultiGraph("MapGrah");
        graph.setAttribute("ui.stylesheet", css);
        SwingBasicGraphRenderer renderer = new SwingBasicGraphRenderer();
        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.getCamera().setViewPercent(0.01);
        viewerPipe = viewer.newViewerPipe();
        viewerPipe.addAttributeSink(graph);
        viewerPipe.addViewerListener(this);
        mapPanel = new JPanel(new GridLayout()) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1000, 600);
            }
        };


        //  mapPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 5));


        String path = "/home/anna/Dokumenty/MapCreator/kreator-map/src/main/java/res/0.png";
        // String pat = ClassLoader.getSystemResource()"/home/anna/Dokumenty/MapCreator/kreator-map/src/main/java/res/0.png";

        try {
            final Image bg = ImageIO.read(new File(path));
            view.setBackLayerRenderer((graphics, graph, px2Gu, widthPx, heightPx,
                                       minXGu, minYGu, maxXGu, maxYGu) ->
                    graphics.drawImage(bg, (int) minXGu, (int) minYGu, null));
        } catch (IOException e) {
            e.printStackTrace();
        }

       mapPanel.add(view);


        view.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                switch (currentState) {
                    case NODEADDITION: {
                        Point3 p = view.getCamera().transformPxToGu(mouseEvent.getX(), mouseEvent.getY());
                        addNode(p.x, p.y, 0);
                    }

                }


            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

                if (currentState == activeMode.DELETION) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Iterator iterator = graph.getEachNode().iterator();
                    while (iterator.hasNext()) {
                        Node node = (Node) iterator.next();
                        if (node.hasAttribute("ui.selected")) {
                            graph.removeNode((node));
                            if (activeNodeID != null && activeNodeID.equals(node)) {
                                activeNodeID = null;
                            }
                        }
                    }

                }

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {


            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        //TODO implementation of MouseWheelListener
        view.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
                System.out.println(mouseWheelEvent.getScrollAmount());
            }
        });

        new Thread(() -> {
            while (loop) {
                viewerPipe.pump();
                viewer.disableAutoLayout();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setAlignmentX(0.1f);
        mainPanel.add(mapPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(294, 200), null, 0, false));
        sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(sidePanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, 1, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(20, 200), null, 0, false));
        final Spacer spacer1 = new Spacer();
        sidePanel.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(43, 14), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        sidePanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(43, 104), null, 0, false));
        addNodeButton = new JButton();
        addNodeButton.setText("Add Node");
        panel1.add(addNodeButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addEdgeButton = new JButton();
        addEdgeButton.setText("Add Edge");
        panel1.add(addEdgeButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeButton = new JButton();
        removeButton.setText("Remove");
        panel1.add(removeButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(e -> {

        });
        fileMenu.add(openItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }


    @Override
    public void viewClosed(String s) {
        loop = false;
    }

    @Override
    public void buttonPushed(String s) {
        if (currentState == activeMode.DELETION) {
            graph.removeNode(s);
            activeNodeID = null;
        }
        if (currentState == activeMode.EDGEADDITION) {
            if (activeNodeID == null) {
                activeNodeID = s;
            } else {
                graph.addEdge(Integer.toString(autoId), s, activeNodeID);
                activeNodeID = s;
                autoId++;
            }

        }

    }

    @Override
    public void buttonReleased(String s) {
    }

    private void addNode(double x, double y, double z) {
        Node node = graph.addNode(Integer.toString(autoId));
        node.setAttribute("xyz", new double[]{x, y, z});
        node.setAttribute("ui.label", autoId);


        for (Node n : graph.getNodeSet()) {
            System.out.println(n.getId() + "  id  " + Arrays.toString((double[]) n.getAttribute("xyz")));
        }
        autoId++;
    }


}
