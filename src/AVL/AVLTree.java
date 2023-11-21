package AVL;


import Binare.NodeBinarTree;
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


//    public void insertRec(NodeAVLTree node, int inf) // вставка ключа k в дерево с корнем p
//    {
//        if (root == null) {
//            root = new NodeAVLTree(inf, null, null, null);
//            ;
//
//        } else if (inf < node.getInf()) {
//            if (node.getlChild() == null) {
//                NodeAVLTree newNode = new NodeAVLTree(inf, null, null, null);
//                node.setlChild(newNode);
//            } else {
//                insertRec(node.getlChild(), inf);
//            }
//            balance(node);
//        } else {
//            if (node.getrChild() == null) {
//                NodeAVLTree newNode = new NodeAVLTree(inf, null, null, null);
//                node.setrChild(newNode);
//            } else {
//                insertRec(node.getrChild(), inf);
//            }
//            balance(node);
//        }
//    }

    public void insert(int inf) {
        NodeAVLTree node = root;
        if (node == null) {
            root = new NodeAVLTree(inf, null, null, null);
        } else {
            while (node.getInf() != inf) {//вставить элемент
                if (node.getInf() > inf) {
                    if (node.getlChild() != null) {
                        node = node.getlChild();
                    } else {
                        node.setlChild(new NodeAVLTree(inf, node, null, null));
                        break;
                    }

                } else {
                    if (node.getrChild() != null) {
                        node = node.getrChild();
                    } else {
                        node.setrChild(new NodeAVLTree(inf, node, null, null));
                        break;
                    }
                }
            }
            while (node != null) {
                balance(node);
                if (node.getParent()==null&& root!= node){
                    root= node;
                }
                node = node.getParent();
            }

        }
    }



    public void sayInorder(NodeAVLTree node) {
        if (node != null) {
            sayInorder(node.getlChild());
            System.out.print(node.getInf() + " ");
            sayInorder(node.getrChild());
        }
    }



    public void sayPreorder(NodeAVLTree node) {
        if (node != null) {
            System.out.print(node.getInf() + " ");
            sayPreorder(node.getlChild());
            sayPreorder(node.getrChild());
        }
    }

    public void sayPostorder(NodeAVLTree node) {
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
        NodeAVLTree tmp=node;
       while (tmp.getlChild()!=null){
            tmp=tmp.getlChild();
       }
       return tmp;
    }

    public NodeAVLTree getMax(NodeAVLTree node) {
        if (node.getrChild() != null) {
            return (getMax(node.getrChild()));
        } else {
            return (node);
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
    boolean check(NodeAVLTree node) {
        boolean answer = true;
        if (node != null) {
            answer &= check(node.getlChild());
            answer &= node.check();
            answer &= check(node.getrChild());
        }
        return answer;
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
            insert((max + min) / 2);
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
                return getRandNode(node.getrChild());
            } else if (n > 0 && node.getlChild() != null) {
                return getRandNode(node.getlChild());
            } else {
                return node;
            }
        } else {
            return null;
        }
    }



    public void remove(int inf) // удаление ключа k из дерева p
    {
        NodeAVLTree node = root;

        while (node != null && inf != node.getInf()) {//дойти до нужного эл
            if (inf < node.getInf()) {
                node = node.getlChild();
            } else if (inf > node.getInf()) {
                node = node.getrChild();
            }
        }

        if (node != null && node.getInf() == inf) {//есть удаляемый эл

            NodeAVLTree leftChild = node.getlChild();
            NodeAVLTree rightChild = node.getrChild();
            //удаление эл
            if (rightChild != null) {//если есть правый потомок, заменить на минимальный справа

                NodeAVLTree min = getMin(rightChild);
                System.out.println(min==rightChild);
                System.out.println(min);
                if (min.getParent().getlChild() == min) {
                    min.getParent().setlChild(min.getrChild());
                } else {
                    min.getParent().setrChild(min.getrChild());
                }
                NodeAVLTree tmp=min.getParent();
               min.copyLink(node);
                node=tmp;

            } else {
                if (root == node) {
                    root = leftChild;
                } else {
                    if (node.getParent().getlChild() == node) {
                        node.getParent().setlChild(leftChild);
                    } else {
                        node.getParent().setrChild(leftChild);
                    }
                }
            }
            while (node != null) {//балансировка
                if (node.getParent() == null) {
                    root = balance(node);
                } else {
                    if (node.getParent().getlChild() == node) {
                        node.getParent().setlChild(balance(node));
                    } else {
                        node.getParent().setrChild(balance(node));
                    }
                }
                node = node.getParent();

            }
        }
    }


    public void delete(NodeAVLTree node) {
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
        while (node.getParent() != null) {//балансировка

            node = node.getParent();
            node = balance(node);


        }
    }

}

