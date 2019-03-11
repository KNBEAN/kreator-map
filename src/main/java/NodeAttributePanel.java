import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.graphstream.graph.Node;
import org.jdesktop.swingx.JXCollapsiblePane;

import javax.swing.*;
import java.awt.*;

public class NodeAttributePanel extends JPanel {

    private JPanel mainPanel;
    private JLabel nodeIDLabel;
    private JTextField floorTextField;
    private JComboBox hardToReachComboBox;
    private JPanel basicInfoPanel;
    private JPanel coordsInfoPanel;
    private JTextField yTextField;
    private JTextField xTextField;
    private JPanel locationInfoPanel;
    private JTextField locationIDTextField;
    private JTextField locationNameTextField;
    private JTextArea locationDescriptionTextArea;
    private JTextField locationTagTextField;
    private JButton addAttributesButton;
    private JPanel nodePanel;
    private JButton toggleButton;

    private Node nodeClicked;

    public NodeAttributePanel() {
        $$$setupUI$$$();
        hardToReachComboBox.addItem("true");
        hardToReachComboBox.addItem("false");
        addListeners();
    }

    private void addListeners() {
        addAttributesButton.addActionListener(e -> {

            int floor = 0;
            float x = 0;
            float y = 0;
            int locationID = 0;
            boolean hardToReach = false;

            try {
                floor = Integer.parseInt(floorTextField.getText());
                x = Float.parseFloat(xTextField.getText());
                y = Float.parseFloat(yTextField.getText());
                locationID = Integer.parseInt(locationIDTextField.getText());
                hardToReach = Boolean.parseBoolean((String) hardToReachComboBox.getSelectedItem());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(getParent(),
                        "Check if in all text fields are right type values",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            String locationName = locationNameTextField.getText();
            String locationDescription = locationDescriptionTextArea.getText();
            String locationTag = locationTagTextField.getText();

            if (nodeClicked != null) {
                nodeClicked.setAttribute("floor", floor);
                nodeClicked.setAttribute("x", x);
                nodeClicked.setAttribute("y", y);
                nodeClicked.setAttribute("locationID", locationID);
                nodeClicked.setAttribute("hardToReach", hardToReach);
                nodeClicked.setAttribute("locationName", locationName);
                nodeClicked.setAttribute("locationDescription", locationDescription);
                nodeClicked.setAttribute("locationTag", locationTag);
            }
        });

        Action toggleAction = nodePanel.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION);
        toggleAction.putValue(JXCollapsiblePane.COLLAPSE_ICON, UIManager.getIcon("Tree.expandedIcon"));
        toggleAction.putValue(JXCollapsiblePane.EXPAND_ICON, UIManager.getIcon("Tree.collapsedIcon"));

        toggleButton.addActionListener(e -> {
            if (((JXCollapsiblePane) nodePanel).isCollapsed())
                toggleButton.setText("Hide Node Panel");
            else
                toggleButton.setText("Show Node Panel");

        });
    }

    public void addNodeAttributes(Node node) {

        this.nodeClicked = node;

        for (String key : node.getEachAttributeKey()) {
            if (key.equals("floor"))
                floorTextField.setText(String.valueOf(node.getAttribute("floor")));
            if (key.equals("x"))
                xTextField.setText(String.valueOf(node.getAttribute("x")));
            if (key.equals("y"))
                yTextField.setText(String.valueOf(node.getAttribute("y")));
            if (key.equals("locationID"))
                locationIDTextField.setText(String.valueOf(node.getAttribute("locationID")));
            if (key.equals("hardToReach")) {
                String hardToReach = String.valueOf(node.getAttribute("hardToReach"));
                hardToReachComboBox.setSelectedItem(hardToReach);
            }
            if (key.equals("locationName"))
                locationNameTextField.setText(node.getAttribute("locationName"));
            if (key.equals("locationDescription"))
                locationDescriptionTextArea.setText(node.getAttribute("locationDescription"));
            if (key.equals("locationTag"))
                locationTagTextField.setText(node.getAttribute("locationTag"));
        }
    }

    public Node getNodeClicked() {
        return nodeClicked;
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
        mainPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        nodePanel.setLayout(new GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(nodePanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        basicInfoPanel = new JPanel();
        basicInfoPanel.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        nodePanel.add(basicInfoPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        nodeIDLabel = new JLabel();
        nodeIDLabel.setText("");
        basicInfoPanel.add(nodeIDLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(30, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Floor");
        basicInfoPanel.add(label1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        floorTextField = new JTextField();
        basicInfoPanel.add(floorTextField, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("ID:");
        basicInfoPanel.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        basicInfoPanel.add(spacer1, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        nodePanel.add(spacer2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        coordsInfoPanel = new JPanel();
        coordsInfoPanel.setLayout(new GridLayoutManager(1, 6, new Insets(0, 0, 0, 0), -1, -1));
        nodePanel.add(coordsInfoPanel, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("x:");
        coordsInfoPanel.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("y:");
        coordsInfoPanel.add(label4, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        xTextField = new JTextField();
        coordsInfoPanel.add(xTextField, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        yTextField = new JTextField();
        coordsInfoPanel.add(yTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Hard to reach");
        coordsInfoPanel.add(label5, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        hardToReachComboBox = new JComboBox();
        coordsInfoPanel.add(hardToReachComboBox, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        locationInfoPanel = new JPanel();
        locationInfoPanel.setLayout(new GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        nodePanel.add(locationInfoPanel, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("ID");
        locationInfoPanel.add(label6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        locationIDTextField = new JTextField();
        locationInfoPanel.add(locationIDTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Location name");
        locationInfoPanel.add(label7, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        locationNameTextField = new JTextField();
        locationInfoPanel.add(locationNameTextField, new GridConstraints(0, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        locationDescriptionTextArea = new JTextArea();
        Font locationDescriptionTextAreaFont = this.$$$getFont$$$(null, Font.PLAIN, 10, locationDescriptionTextArea.getFont());
        if (locationDescriptionTextAreaFont != null)
            locationDescriptionTextArea.setFont(locationDescriptionTextAreaFont);
        locationInfoPanel.add(locationDescriptionTextArea, new GridConstraints(1, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Location description");
        locationInfoPanel.add(label8, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Location tag");
        locationInfoPanel.add(label9, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        locationTagTextField = new JTextField();
        locationInfoPanel.add(locationTagTextField, new GridConstraints(2, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer3 = new Spacer();
        locationInfoPanel.add(spacer3, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        addAttributesButton = new JButton();
        addAttributesButton.setText("Add attributes");
        nodePanel.add(addAttributesButton, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        Font label10Font = this.$$$getFont$$$(null, Font.BOLD, 14, label10.getFont());
        if (label10Font != null) label10.setFont(label10Font);
        label10.setHorizontalAlignment(10);
        label10.setHorizontalTextPosition(0);
        label10.setText("Node Attribute");
        nodePanel.add(label10, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        Font label11Font = this.$$$getFont$$$(null, Font.BOLD, 12, label11.getFont());
        if (label11Font != null) label11.setFont(label11Font);
        label11.setText("Coordinates");
        nodePanel.add(label11, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        Font label12Font = this.$$$getFont$$$(null, Font.BOLD, 12, label12.getFont());
        if (label12Font != null) label12.setFont(label12Font);
        label12.setText("Location info");
        nodePanel.add(label12, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Font toggleButtonFont = this.$$$getFont$$$(null, Font.BOLD, -1, toggleButton.getFont());
        if (toggleButtonFont != null) toggleButton.setFont(toggleButtonFont);
        toggleButton.setHideActionText(false);
        toggleButton.setHorizontalAlignment(2);
        toggleButton.setText("Show Node Panel");
        mainPanel.add(toggleButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private void createUIComponents() {
        nodePanel = new JXCollapsiblePane();
        ((JXCollapsiblePane) nodePanel).setAnimated(false);
        ((JXCollapsiblePane) nodePanel).setCollapsed(true);
        toggleButton = new JButton(nodePanel.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
    }
}
