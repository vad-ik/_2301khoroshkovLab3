package RedBlack;

public class NodeRedBlackTree {

    private int inf;
    private NodeRedBlackTree parent;
    private NodeRedBlackTree lChild;
    private NodeRedBlackTree rChild;
    private boolean color;

    public NodeRedBlackTree(int inf, NodeRedBlackTree parent, NodeRedBlackTree lChild, NodeRedBlackTree rChild,boolean color) {
        this.inf = inf;
        this.parent = parent;
        this.lChild = lChild;
        this.rChild = rChild;
        this.color=color;
    }

    public int getInf() {
        return inf;
    }
public boolean getColor(){return color;}
    public void setBlackColor(){color=true;}
    public NodeRedBlackTree getParent() {
        return parent;
    }

    public NodeRedBlackTree getlChild() {
        return lChild;
    }

    public NodeRedBlackTree getrChild() {
        return rChild;
    }

    public void setInf(int inf) {
        this.inf = inf;
    }

    public void switchColor(){
        color=!color;
    }
    public void setParent(NodeRedBlackTree parent) {
        this.parent = parent;
        if (parent != null) {
            if (parent.inf < this.inf) {
                parent.rChild = (this);
            } else {
                parent.lChild = (this);
            }
        }

    }

    public void setlChild(NodeRedBlackTree lChild) {
        this.lChild = lChild;
        if (lChild != null) {
            lChild.parent = this;
        }
    }

    public void setrChild(NodeRedBlackTree rChild) {
        this.rChild = rChild;
        if (rChild != null) {
            rChild.parent = this;
        }
    }

    public void copyLink(NodeRedBlackTree node) {

        setlChild(node.getlChild());//установка всех ссылок этого элемента на ссылки принимаемого элемента
        setrChild(node.getrChild());
        setParent(node.getParent());
    }
boolean isRightChild(){
        if (this.getParent()!=null&&this.getParent().getInf()<this.getInf()){
            return true;
        }else return false;
}
    public boolean check() {
        boolean answer = true;
        if (rChild != null) {
            if (rChild.getInf()  < inf) {
                answer = false;
            }
        }
        if (lChild != null) {
            if (lChild.getInf() > inf) {
                answer = false;
            }
        }
        if(!getColor()){
            if (rChild != null) {
                if (!rChild.getColor()) {
                    answer = false;
                }
            }
            if (lChild != null) {
                if (!lChild.getColor()) {
                    answer = false;
                }
            }
        }
        return answer;
    }

    public boolean hedTwoBlackChild() {
        return(this.getrChild()!=null&&this.getlChild()!=null&&this.getlChild().getColor()&&this.getrChild().getColor());
    }
    public boolean hedOneRedChild() {
       return(this.getrChild()==null&&this.getlChild()!=null&&!this.getlChild().getColor()||
                this.getrChild()!=null&&this.getlChild()==null&&!this.getrChild().getColor());
    }
    public boolean hedRedChild() {
        return(this.getlChild()!=null&&!this.getlChild().getColor()||
                this.getrChild()!=null&&!this.getrChild().getColor());
    }
    public boolean hedoRedChild() {
        return(this.getrChild()==null&&this.getlChild()!=null&&!this.getlChild().getColor()||
                this.getrChild()!=null&&this.getlChild()==null&&!this.getrChild().getColor()||
                this.getrChild()!=null&&this.getlChild()!=null&&
                        (this.getrChild().getColor()&&!this.getlChild().getColor()||this.getlChild().getColor()&&!this.getrChild().getColor())
                );
    }
    public boolean noHedChild() {
        return(this.getrChild()==null&&this.getlChild()==null);
    }
    public boolean noHedRedChild() {
        boolean n=true;
        if (this.getrChild()!=null&&!this.getrChild().getColor()){
            n=false;
        }
        if (this.getlChild()!=null&&!this.getlChild().getColor()){
            n=false;
        }
        return(n);
    }
}
