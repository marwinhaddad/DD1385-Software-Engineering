
import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LifeTree extends TreeFrame {

    static String dir = "liv.txt";
    static Scanner sc;

    static final Pattern pattern = Pattern.compile("(?=.*(?<=<)(.*?)(?= ))(?=.*(?<=\")(.*?)(?=\"))(?=.*((?<=> ).*))");
    static Matcher matcher = null;

    // Overrides method in TreeFrame
    void initTree() {
        root = createNode();
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        buildTree();
    }

    // New method
    private void buildTree() {
        buildTree((MyNode) root);
    }

    // New method
    private void buildTree(MyNode parent) {
        MyNode child = createNode();

        if (child == null) {
            if (sc.hasNextLine()) {
                buildTree(parent.getParent());
            }
        }else {
            parent.add(child);
            if (sc.hasNextLine()) {
                buildTree(child);
            }
        }
    }

    // Overrides method in TreeFrame
    void showDetails(TreePath p){
        if (p != null) {
            MyNode node = (MyNode) p.getLastPathComponent();
            String message = getAllDetails(node);
            JOptionPane.showMessageDialog(this,
                                                node.getLayer() + ": " + node.getInfo() +
                                                        "\nMen allt som" + message);
        }
    }

    String getAllDetails(MyNode node) {
        if (node != null) {
            return " är " + node.getName() + getAllDetails(node.getParent());
        }
        return ".";
    }

    private MyNode createNode() {
        String str = sc.nextLine();
        matcher = pattern.matcher(str);

        if (matcher.find()) {
            return new MyNode(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            sc = new Scanner(new File(dir));
            new LifeTree();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
