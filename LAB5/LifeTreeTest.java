import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LifeTreeTest extends TreeFrame {

    // Default file if no file is specified in terminal
    static String dir = "liv.txt";
    static Scanner sc;

    static final Pattern patternOpen = Pattern.compile("(?=.*(?<=<)(.*?)(?= ))(?=.*(?<=\")(.*?)(?=\"))(?=.*((?<=> ).*))");
    static Matcher matcherOpen = null;

    static final Pattern patternClose = Pattern.compile("(?<=</)(.*?)(?=>)");
    static Matcher matcherClose = null;

    // Keeps track of layers
    static LinkedList<String> stack = new LinkedList<>();

    // Overrides method in TreeFrame
    void initTree() {
        try {
            root = createNode();
            treeModel = new DefaultTreeModel(root);
            tree = new JTree(treeModel);
            buildTree((MyNode) root);

            /*
             If stack is not empty at the end of the run it means that some layer(s) are still open
             */
            if (!stack.isEmpty()) {
                throw new IncorrectTreeSyntaxException(String.format("Finished reading from file before closing all layers. Layer that are still open: %s", stack));
            }
            // System.out.println(stack);
        }catch (IncorrectTreeSyntaxException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    // New method
    private void buildTree(MyNode parent) throws IncorrectTreeSyntaxException {
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

    /**
     * Recursive function that gets the info of a node and its parent(s)
     * as long as the current node is not null
     * @param node Current node
     * @return String value of the info of all nodes from current node to root
     */
    String getAllDetails(MyNode node) {
        if (node != null) {
            return " är " + node.getName() + getAllDetails(node.getParent());
        }
        return ".";
    }

    /**
     * Retrieves node information with the use of regex pattern and creates node
     * If closing pattern found; return null
     * @return A ny node to add to the tree or null
     * @throws IncorrectTreeSyntaxException if xml syntax is formatted incorrectly
     */
    private MyNode createNode() throws IncorrectTreeSyntaxException {
            // System.out.println(stack);
            if (sc.hasNextLine()) {
                String str = sc.nextLine();
                matcherOpen = patternOpen.matcher(str);
                matcherClose = patternClose.matcher(str);

                if (matcherOpen.find()) {
                    String layer = matcherOpen.group(1);
                    String name = matcherOpen.group(2);
                    String info = matcherOpen.group(3);

                    /*
                    If the layer of the new node is already open and not the same as the current layer
                    it means we are trying to open a layer that is already open
                     */
                    if (stack.contains(layer) && !Objects.equals(stack.peekFirst(), layer)) {
                        throw new IncorrectTreeSyntaxException(String.format("The layer '%s' is already present above, cannot add the same layer twice.", layer));
                    }

                    stack.push(layer);

                    return new MyNode(layer, name, info);

                } else if (matcherClose.find()) {
                    String layer = matcherClose.group();
                    if (stack.peekFirst() != null && Objects.equals(stack.peekFirst(), layer)) {
                        stack.pop();
                        return null;
                    } else {
                        /*
                        If the closing layer does not match the current open layer we are trying to close the wrong layer
                         */
                        throw new IncorrectTreeSyntaxException(String.format("Cannot close the layer '%s' because it is not currently open.", layer));
                    }
                }
            }
        return null;
    }

    public static void main(String[] args) {
        try {
            /*
            If file name is not specified open default file "Liv.txt"
             */
            if (args.length == 0) {
                sc = new Scanner(new File(dir));
            } else {
                sc = new Scanner(new File(args[0]));
            }
            new LifeTreeTest();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Custom exception that is thrown when reading file is not formatted correctly
 */
class IncorrectTreeSyntaxException extends Exception {
    public IncorrectTreeSyntaxException(String e) {
        super(e);
    }
}
