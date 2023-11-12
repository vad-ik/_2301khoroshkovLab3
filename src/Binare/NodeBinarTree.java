package Binare;

public class NodeBinarTree {
    private int inf;
    private NodeBinarTree parent;
    private NodeBinarTree lChild;
    private NodeBinarTree rChild;

    public NodeBinarTree(int inf, NodeBinarTree parent, NodeBinarTree lChild, NodeBinarTree rChild) {
        this.inf = inf;
        this.parent = parent;
        this.lChild = lChild;
        this.rChild = rChild;
    }

    public int getInf() {
        return inf;
    }

    public NodeBinarTree getParent() {
        return parent;
    }

    public NodeBinarTree getlChild() {
        return lChild;
    }

    public NodeBinarTree getrChild() {
        return rChild;
    }

    public void setInf(int inf) {
        this.inf = inf;
    }

    public void setParent(NodeBinarTree parent) {
        this.parent = parent;
        if (parent!=null) {
            if (parent.inf <this.inf) {
                parent.rChild  =(this);
            } else {
                parent.lChild =(this);
            }
        }

    }

    public void setlChild(NodeBinarTree lChild) {
        this.lChild = lChild;
        if (lChild !=null) {
            lChild.parent = this;
        }
    }

    public void setrChild(NodeBinarTree rChild) {
        this.rChild = rChild;
        if (rChild !=null) {
            rChild.parent = this;
        }
    }
    public void copyLink(NodeBinarTree node) {

        setlChild(node.getlChild());//установка всех ссылок этого элемента на ссылки принимаемого элемента
        setrChild(node.getrChild());
        setParent(node.getParent());
    }
    public boolean check(){
        boolean answer=true;
        if (rChild!=null){
            if (rChild.inf<inf){
                answer=false;
            }
        }
        if (lChild!=null){
            if (lChild.inf>inf){
                answer=false;
            }
        }
        return  answer;
    }



}
