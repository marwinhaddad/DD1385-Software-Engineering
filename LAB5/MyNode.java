import javax.swing.tree.DefaultMutableTreeNode;

public class MyNode extends DefaultMutableTreeNode {

    private final String info;
    private final String layer;
    private final String name;

    MyNode(String layer, String name, String info) {
        super(name);
        this.info = info;
        this.layer = layer;
        this.name = name;
    }

    public MyNode getParent(){
        return (MyNode) this.parent;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getLayer() {
        return layer;
    }

    /*
    @Override
    public String toString() {
        return layer + ": " + info;
    }

 */
}
