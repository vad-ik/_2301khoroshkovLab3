package AVL;


import RedBlack.NodeRedBlackTree;

import java.util.Random;

public class AVLTree {
    private NodeAVLTree root;

    public AVLTree() {
        root = null;
    }

    public NodeAVLTree getRoot() {
        return root;
    }

    public void add(int inf) {
        NodeAVLTree tmp = root;
        NodeAVLTree memery = root;
        while (tmp != null) {//дойти но места, где должен лежать новый элемент
            if (tmp.getInf() < inf) {
                memery = tmp;
                tmp = tmp.getrChild();
            } else {
                memery = tmp;
                tmp = tmp.getlChild();
            }
        }
        if (memery != null) {//если дерево не пустое, вставить элемент
            tmp = new NodeAVLTree(inf, memery, null, null);
            if (memery.getInf() < inf) {
                memery.setrChild(tmp);
            } else {
                memery.setlChild(tmp);
            }
        } else {//если дерево пустое создать новый корень
            root = new NodeAVLTree(inf, null, null, null);
        }
    }

    public void insert(NodeAVLTree node, int inf) // вставка ключа k в дерево с корнем p
    {
        if (root == null) {
            root = new NodeAVLTree(inf, null, null, null);
            ;

        } else if (inf < node.getInf()) {
            if (node.getlChild() == null) {
                NodeAVLTree newNode = new NodeAVLTree(inf, null, null, null);
                node.setlChild(newNode);
            } else {
                insert(node.getlChild(), inf);
            }
            balance(node);
        } else {
            if (node.getrChild() == null) {
                NodeAVLTree newNode = new NodeAVLTree(inf, null, null, null);
                node.setrChild(newNode);
            } else {
                insert(node.getrChild(), inf);
            }
            balance(node);
        }


    }

    public boolean check(NodeAVLTree node) {
        boolean answer = true;
        if (node != null) {
            answer &= check(node.getlChild());
            answer &= node.check();
            answer &= check(node.getrChild());
        }
        return answer;
    }

    void sayInorder(NodeAVLTree node) {
        if (node != null) {
            sayInorder(node.getlChild());
            System.out.print(node.getInf() + " ");
            sayInorder(node.getrChild());
        }
    }
    public void sayInorderBalance(NodeAVLTree node) {
        if (node != null) {
            sayInorderBalance(node.getlChild());
            System.out.print(node.getBalance()+ " ");
            sayInorderBalance(node.getrChild());
        }
    }

    void sayPreorder(NodeAVLTree node) {
        if (node != null) {
            System.out.print(node.getInf() + " ");
            sayPreorder(node.getlChild());
            sayPreorder(node.getrChild());
        }
    }

    void sayPostorder(NodeAVLTree node) {
        if (node != null) {
            sayPostorder(node.getlChild());
            sayPostorder(node.getrChild());
            System.out.print(node.getInf() + " ");
        }
    }

    public boolean sayTree(NodeAVLTree node, int nowLewel, int findLewel) {
        boolean answer = false;
        if (node != null) {//еще есть элементы
            if (nowLewel == findLewel) {//дошли до нужного уровня

                System.out.print(node.getInf() + " ");
                answer = true;
            }
            if (nowLewel == 0) {//на первом уровне вызываем поиск всех уровней, пока есть куда спускаться

                boolean deeper = false;
                int i = 1;
                do {
                    deeper = false;
                    System.out.println();
                    deeper |= sayTree(node.getlChild(), nowLewel + 1, i);
                    deeper |= sayTree(node.getrChild(), nowLewel + 1, i);
                    i++;
                } while (deeper);
            } else if (nowLewel < findLewel) {//ищем нужный уровень
                answer |= sayTree(node.getlChild(), nowLewel + 1, findLewel);
                answer |= sayTree(node.getrChild(), nowLewel + 1, findLewel);
                int n = 0;
            }
        }
        return answer;
    }

    NodeAVLTree getMin(NodeAVLTree node) {
        if (node.getlChild() != null) {
            return (getMin(node.getlChild()));
        } else {
            return (node);
        }
    }

    public NodeAVLTree getMax(NodeAVLTree node) {
        if (node.getrChild() != null) {
            return (getMax(node.getrChild()));
        } else {
            return (node);
        }
    }

    void delete(NodeAVLTree node) {
        if (node.getrChild() != null) {//если есть правый потомок
            NodeAVLTree newNode = getMin(node.getrChild());//ищем минимальный в правой ветке
            if (newNode != node.getrChild()) {//если он левый потомок
                newNode.getParent().setlChild(newNode.getrChild());//устанавливаем на его место его правого потомка
                newNode.copyLink(node);//копируем newNode на место node
                if (newNode.getParent() == null) {//если удален корень, заменить указатель на него
                    root = newNode;
                }
            } else {
                node.setrChild(newNode.getrChild());//изменяем правого потомка, чтобы избежать указание на самого себя
                newNode.copyLink(node);//копируем newNode на место node
                if (newNode.getParent() == null) {//если удален корень, заменить указатель на него
                    root = newNode;
                }
            }
        } else if (node.getlChild() != null) {//нет правого потомка, но есть левый
            node.getlChild().setParent(node.getParent());//устанавливаем левого потомка на это место

            if (node.getParent() == null) {//если удален корень, заменить указатель на него
                root = node.getlChild();
            }
        } else {//нет потомков
            if (node.getParent() != null) {//это не корень
                if (node.getParent().getrChild() == node) {//удаляем указатель на удаляемый элемент
                    node.getParent().setrChild(null);
                } else {
                    node.getParent().setlChild(null);
                }
            } else {//это единственный элемент
                root = null;
            }
        }
    }

    public NodeAVLTree find(NodeAVLTree node, int inf) {
        if (node == null) {
            return null;
        } else {
            if (inf == node.getInf()) {
                return node;
            } else if (inf < node.getInf()) {
                return find(node.getlChild(), inf);
            } else {
                return find(node.getrChild(), inf);
            }
        }
    }

    void leftTurn(NodeAVLTree node) {

            NodeAVLTree parent = node.getParent();
            NodeAVLTree rightChild = node.getrChild();

            node.setrChild(rightChild.getlChild());


            rightChild.setlChild(node);
            node.setParent(rightChild);
            rightChild.setParent(parent);
            if (parent != null) {
                if (parent.getParent() == null) {
                    root = parent;
                }
                parent.setParent(parent.getParent());
            } else {
                root = rightChild;
            }

            node.fixheight();
            rightChild.fixheight();

    }

    void rightTurn(NodeAVLTree node) {

            NodeAVLTree parent = node.getParent();
            NodeAVLTree leftChild = node.getlChild();

            node.setlChild(leftChild.getrChild());
            leftChild.setrChild(node);
            node.setParent(leftChild);
            leftChild.setParent(parent);
            if (parent != null) {
                if (parent.getParent() == null) {
                    root = parent;
                }
                parent.setParent(parent.getParent());
            } else {
                root = leftChild;
            }
            node.fixheight();
            leftChild.fixheight();

    }

    public int getDeep(NodeAVLTree node) {
        if (node != null) {
            return (Math.max(getDeep(node.getrChild()), getDeep(node.getlChild())) + 1);
        } else {
            return 0;
        }
    }

    void fill(int min, int max) {//сбалансированное заполнение от min до max
        if (max - min > 2) {
            add((max + min) / 2);
            fill(min, (max + min) / 2);
            fill((max + min) / 2, max);
        }
    }

    public int getMinDeep(NodeAVLTree node) {
        if (node != null) {
            return (Math.min(getMinDeep(node.getrChild()), getMinDeep(node.getlChild())) + 1);
        } else {
            return 0;
        }
    }

    NodeAVLTree balance(NodeAVLTree node) {


        node.fixheight();
        if (node.getBalance() == 2) {
            if (node.getrChild() != null && node.getrChild().getBalance() < 0) {

                rightTurn(node.getrChild());
            }
            leftTurn(node);

            return node.getParent();
        }
        if (node.getBalance() == -2) {
            if (node.getlChild() != null && node.getlChild().getBalance() > 0) {

                leftTurn(node.getlChild());
            }
            rightTurn(node);
            return node.getParent();
        }
        return node;
    }
    public NodeAVLTree getRandNode(NodeAVLTree node) {
        Random random = new Random();
        int n = random.nextInt(10);
        if (node != null) {
            if (n > 5 && node.getrChild() != null) {
                return node.getrChild();
            } else if (n > 0 && node.getlChild() != null) {
                return node.getlChild();
            } else {
                return node;
            }
        } else {
            return null;
        }
    }
    NodeAVLTree removemin(NodeAVLTree node) // удаление узла с минимальным ключом из дерева p
    {
        if( node.getlChild()==null )
            return node.getrChild();
        node.setlChild( removemin(node.getlChild()));
        return balance(node);
    }

    public NodeAVLTree  remove(NodeAVLTree node, int k) // удаление ключа k из дерева p
    {
        if( node==null ) {return null;}
        if( k < node.getInf()  ){
            node.setlChild(  remove(node.getlChild() ,k));}
        else if( k > node.getInf() ){
            node.setrChild(  remove(node.getrChild(),k));}
        else
        {
            NodeAVLTree leftChild = node.getlChild() ;
            NodeAVLTree rightChild = node.getrChild();

            if( rightChild==null ){ return leftChild;}
            NodeAVLTree min = getMin(rightChild);
            min.setrChild(removemin(rightChild));
            min.setlChild(  leftChild);
            return balance(min);
        }
        return balance(node);
    }
}

