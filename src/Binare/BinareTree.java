package Binare;


import AVL.NodeAVLTree;

import java.util.Random;

public class BinareTree {
    private NodeBinarTree root;

    public BinareTree() {
        root = null;
    }

    public NodeBinarTree getRoot() {
        return root;
    }

    public void add(int inf) {
        NodeBinarTree tmp = root;
        NodeBinarTree memery = root;
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
            tmp = new NodeBinarTree(inf, memery, null, null);
            if (memery.getInf() < inf) {
                memery.setrChild(tmp);
            } else {
                memery.setlChild(tmp);
            }
        } else {//если дерево пустое создать новый корень
            root = new NodeBinarTree(inf, null, null, null);
        }
    }

    public void sayInorder(NodeBinarTree node) {
        if (node != null) {
            sayInorder(node.getlChild());
            System.out.print(node.getInf() + " ");
            sayInorder(node.getrChild());
        }
    }
    public void sayPreorder(NodeBinarTree node) {
        if (node != null) {
            System.out.print(node.getInf() + " ");
            sayPreorder(node.getlChild());
            sayPreorder(node.getrChild());
        }
    }
    void sayPostorder(NodeBinarTree node) {
        if (node != null) {
            sayPostorder(node.getlChild());
            sayPostorder(node.getrChild());
            System.out.print(node.getInf() + " ");
        }
    }
    boolean sayTree(NodeBinarTree node, int nowLewel, int findLewel) {
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

    NodeBinarTree getMin(NodeBinarTree node) {
        if (node.getlChild() != null) {
            return (getMin(node.getlChild()));
        } else {
            return (node);
        }
    }

    public NodeBinarTree getMax(NodeBinarTree node) {
        NodeBinarTree FindNode=node;
        while (FindNode.getrChild()!=null){
            FindNode=FindNode.getrChild();
        }
        return FindNode;
    }
    public NodeBinarTree getRandNode(NodeBinarTree node) {
        Random random = new Random();
        int n = random.nextInt(100);
        if (node != null) {
            if (n > 50 && node.getrChild() != null) {
                return getRandNode( node.getrChild());
            } else if (n > 0 && node.getlChild() != null) {
                return getRandNode(node.getlChild());
            } else {
                return node;
            }
        } else {
            return null;
        }
    }
    public void delete(NodeBinarTree node) {
        if (node.getrChild() != null) {//если есть правый потомок
            NodeBinarTree newNode = getMin(node.getrChild());//ищем минимальный в правой ветке
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
    boolean check(NodeBinarTree node) {
        boolean answer=true;
        if (node != null) {
            answer&=check(node.getlChild());
            answer&=node.check();
            answer&=check(node.getrChild());
        }
        return answer;
    }
    public NodeBinarTree find(NodeBinarTree node, int inf) {
        NodeBinarTree findNode=node;
        while (findNode.getrChild()!=null&&inf>findNode.getInf()||findNode.getlChild()!=null&&inf<findNode.getInf()){
            if (inf>findNode.getInf()){
                findNode=findNode.getrChild();
            }else {
                findNode=findNode.getlChild();
            }
        }
        if (findNode.getInf()==inf){
            return node;
        }else {
            return null;
        }
    }

    void leftTurn(NodeBinarTree node) {
        if (node.getrChild() != null) {
            NodeBinarTree newNode = node.getrChild();
            if (newNode.getlChild()!=null ) {
                node.setrChild(newNode.getlChild());
            }
            newNode.setParent(node.getParent());
            newNode.setlChild(node);
            if (newNode.getParent() == null) {
                root = newNode;
            }
        }
    }

    void rightTurn(NodeBinarTree node) {
        if (node.getlChild() != null) {
            NodeBinarTree newNode = node.getlChild();
            if (newNode.getrChild()!=null ) {
                node.setlChild(newNode.getrChild());
            }
            newNode.setParent(node.getParent());
            newNode.setrChild(node);
            if (newNode.getParent() == null) {
                root = newNode;
            }
        }
    }
    public int getDeep(NodeBinarTree node){
        if (node!=null) {
            return (Math.max(getDeep(node.getrChild()), getDeep(node.getlChild()))+1);
        }else {
            return 0;
        }
    }
    public int getMinDeep(NodeBinarTree node) {
        if (node != null) {
            return (Math.min(getMinDeep(node.getrChild()), getMinDeep(node.getlChild())) + 1);
        } else {
            return 0;
        }
    }
     void fill(  int min, int max) {//сбалансированное заполнение от min до max
        if (max - min > 2) {
            add((max + min) / 2);
            fill( min, (max + min) / 2);
            fill( (max + min) / 2, max);
        }
    }

}
