package AVL;

public class NodeAVLTree {
    private int inf;
    private NodeAVLTree parent;
    private NodeAVLTree lChild;
    private NodeAVLTree rChild;
    private int height;
    public NodeAVLTree(int inf, NodeAVLTree parent, NodeAVLTree lChild, NodeAVLTree rChild) {
        this.inf = inf;
        this.parent = parent;
        this.lChild = lChild;
        this.rChild = rChild;
        height=1;
    }

    public int getInf() {
        return inf;
    }

    public NodeAVLTree getParent() {
        return parent;
    }

    public NodeAVLTree getlChild() {
        return lChild;
    }

    public NodeAVLTree getrChild() {
        return rChild;
    }

    public void setInf(int inf) {
        this.inf = inf;
    }

    public void setParent(NodeAVLTree parent) {
        this.parent = parent;
        if (parent!=null) {
            if (parent.inf <this.inf) {
                parent.rChild  =this;
            } else {
                parent.lChild =this;
            }
        }

    }

    public void setlChild(NodeAVLTree lChild) {
        this.lChild = lChild;
        if (lChild !=null) {
            lChild.parent = this;
        }
    }

    public void setrChild(NodeAVLTree rChild) {
        this.rChild = rChild;
        if (rChild !=null) {
            rChild.parent = this;
        }
    }
    public void copyLink(NodeAVLTree node) {

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

int getBalance(){
        int answer=0;
    if (this.rChild!=null){
        answer+=this.rChild.height;
    }
    if (this.lChild!=null){
        answer-=this.lChild.height;
    }
    return answer;
}
    void fixheight()
    {
         int heightL =  this.lChild==null?0:this.lChild.height;
         int heightR = this.rChild==null?0:this.rChild.height;
        this.height = Math.max(heightL, heightR)+1;
    }

    public int getHeight() {
        return height;
    }
}
