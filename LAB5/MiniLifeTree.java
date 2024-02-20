import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniLifeTree extends TreeFrame {

    static String dirtxt = "liv.txt";
    static Scanner sc;

    static final Pattern openPattern = Pattern.compile("(?=.*(?<=<)(.*?)(?= ))(?=.*(?<=\")(.*?)(?=\"))(?=.*((?<=> ).*))");
    Matcher openMatcher;

    static final Pattern closePattern = Pattern.compile("(?<=</)(.*?)(?=>)");
    Matcher closeMatcher;

    @Override
    void initTree() {
        root = new DefaultMutableTreeNode("Life");
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);

        buildTree();
    }

    private void buildTree() {
        buildTree(new DefaultMutableTreeNode("Plants"), root);
        buildTree(new DefaultMutableTreeNode("Animals"), root);
        buildTree(new DefaultMutableTreeNode("Fungus"), root);
    }

    private void buildTree(DefaultMutableTreeNode child, DefaultMutableTreeNode root) {
        root.add(child);
    }

    void showDetails(TreePath p){
        if ( p == null )
            return;
        File f = new File( p.getLastPathComponent().toString() );
        JOptionPane.showMessageDialog( this, f.getPath() +
                "\n   " +
                getAttributes( f ) );
    }

    private String getAttributes( File f ) {
        String t = "";
        if ( f.isDirectory() )
            t += "Directory";
        else
            t += "Nondirectory file";
        t += "\n   ";
        if ( !f.canRead() )
            t += "not ";
        t += "Readable\n   ";
        if ( !f.canWrite() )
            t += "not ";
        t += "Writeable\n  ";
        if ( !f.isDirectory() )
            t += "Size in bytes: " + f.length() + "\n   ";
        else {
            t += "Contains files: \n     ";
            String[ ] contents = f.list();
            for ( int i = 0; i < contents.length; i++ )
                t += contents[ i ] + ", ";
            t += "\n";
        }
        return t;
    }

    public static void main(String[] args) {
        new MiniLifeTree();
    }

}
