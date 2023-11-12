package RedBlack;

import java.util.Random;

public class RedBlackTree {
    boolean black = true;
    boolean red = false;
    private NodeRedBlackTree root;

    public RedBlackTree() {
        root = null;
    }

    public NodeRedBlackTree getRoot() {
        return root;
    }

    public void add(int inf) {
        NodeRedBlackTree tmp = root;
        NodeRedBlackTree memery = root;
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
            tmp = new NodeRedBlackTree(inf, memery, null, null, red);
            if (memery.getInf() < inf) {
                memery.setrChild(tmp);
            } else {
                memery.setlChild(tmp);
            }
            insertionFix(tmp);
        } else {//если дерево пустое создать новый корень
            root = new NodeRedBlackTree(inf, null, null, null, black);
        }

    }

    public boolean check(NodeRedBlackTree node) {

        boolean answer = true;
        if (node == root) {//проверить что все пути до листьев имеют одинаковую черную высоту
            if (checBlackDeep(root) == -1) {
                answer = false;
            }
        }
        if (answer && node != null) {
            answer &= check(node.getlChild());
            answer &= node.check();
            if (answer) {//если уже нарушили нет смысла проверять дальше
                answer &= check(node.getrChild());
            }
        }
        return answer;
    }

    void sayInorder(NodeRedBlackTree node) {
        if (node != null) {
            sayInorder(node.getlChild());
            System.out.print(node.getInf() + " ");
            sayInorder(node.getrChild());
        }
    }

    void sayPreorder(NodeRedBlackTree node) {
        if (node != null) {
            System.out.print(node.getInf() + " ");
            sayPreorder(node.getlChild());
            sayPreorder(node.getrChild());
        }
    }

    void sayPostorder(NodeRedBlackTree node) {
        if (node != null) {
            sayPostorder(node.getlChild());
            sayPostorder(node.getrChild());
            System.out.print(node.getInf() + " ");
        }
    }

    public boolean sayTree(NodeRedBlackTree node, int nowLewel, int findLewel) {
        boolean answer = false;
        if (node != null) {//еще есть элементы

            if (nowLewel == findLewel) {//дошли до нужного уровня

                if (node.getColor()) {//выделяем красные узлы для наглядности
                    System.out.printf(node.getInf() + " ");
                } else {
                    System.out.printf("\u001B[31m" + node.getInf() + "\u001B[0m" + " ");
                }

                answer = true;
            }

            if (nowLewel == 0) {//на первом уровне вызываем поиск всех уровней, пока есть куда спускаться

                boolean deeper;
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

    NodeRedBlackTree getMin(NodeRedBlackTree node) {
        if (node.getlChild() != null) {
            return (getMin(node.getlChild()));
        } else {
            return (node);
        }
    }

    public NodeRedBlackTree getMax(NodeRedBlackTree node) {
        if (node.getrChild() != null) {
            return (getMax(node.getrChild()));
        } else {
            return (node);
        }
    }

    public void delete(NodeRedBlackTree node) {
        boolean flag=true;
        NodeRedBlackTree movedUpNode;
        boolean deletedNodeColor;

        // Node has zero or one child
        if (node.getlChild() == null || node.getrChild() == null) {
            movedUpNode = deleteNodeWithZeroOrOneChild(node);
            deletedNodeColor = node.getColor();
            if (movedUpNode!=null&&movedUpNode.getColor()!=deletedNodeColor&&!movedUpNode.getColor()){
                movedUpNode.switchColor();
                flag=false;
            }
        }

        // Node has two children
        else {
            // Find minimum node of right subtree ("inorder successor" of current node)
            NodeRedBlackTree inOrderSuccessor = getMin(node.getrChild());

            // Copy inorder successor's data to current node (keep its color!)
            if(inOrderSuccessor==node.getrChild()){
                node.setInf(inOrderSuccessor.getInf());
                inOrderSuccessor.setInf(inOrderSuccessor.getInf()+1);
            }else {
                node.setInf(inOrderSuccessor.getInf());
            }

            // Delete inorder successor just as we would delete a node with 0 or 1 child
            deletedNodeColor = inOrderSuccessor.getColor();
            movedUpNode = deleteNodeWithZeroOrOneChild(inOrderSuccessor);
        }

        if (deletedNodeColor && movedUpNode != null) {
            if (flag) {
                fixRedBlackPropertiesAfterDelete(movedUpNode);
            }
            if (movedUpNode.getClass() == NilNode.class) {
                if (root != null) {
                    if (movedUpNode.getParent().getrChild() == movedUpNode) {
                        movedUpNode.getParent().setrChild(null);
                    } else {
                        movedUpNode.getParent().setlChild(null);
                    }
                }
            }
        }
    }

    private NodeRedBlackTree deleteNodeWithZeroOrOneChild(NodeRedBlackTree node) {

        if (node.getrChild() != null) {//если есть правый потомок
            node.getrChild().setParent(node.getParent());//устанавливаем правого потомка на это место

            if (node.getParent() == null) {//если удален корень, заменить указатель на него
                root = node.getrChild();
            }
            return node.getrChild();
        } else if (node.getlChild() != null) {//нет правого потомка, но есть левый
            node.getlChild().setParent(node.getParent());//устанавливаем левого потомка на это место

            if (node.getParent() == null) {//если удален корень, заменить указатель на него
                root = node.getlChild();
            }
            return node.getlChild();
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
            NodeRedBlackTree newChild = (node.getColor() == black ? new NilNode() : null);
            if (newChild != null) {
                if (node.getParent() != null) {
                    if (node.isRightChild()) {
                        node.getParent().setrChild(newChild);
                    } else {
                        node.getParent().setlChild(newChild);
                    }
                    newChild.setInf(node.getInf());
                } else {
                    newChild = null;
                }

            }
            return newChild;//если удаляем черный узел будет нарушена черная длина
        }
    }

    void fixRedBlackPropertiesAfterDelete(NodeRedBlackTree node) {

        if (node == root) {//1случ
            node.setBlackColor();
            if (node.getrChild() != null && node.getlChild() != null &&
                    !node.getrChild().getColor() && !node.getlChild().getColor()) {
                node.getlChild().switchColor();
                node.getrChild().switchColor();
            }
        } else if (node.getParent() != null && node.getParent().getrChild() == node && node.getParent().getlChild() != null && !node.getParent().getlChild().getColor()) {
            //есть брат и он красный 2случ
            node.getParent().getlChild().switchColor();
            node.getParent().switchColor();

            rightTurn(node.getParent());
            if (node.getParent().getlChild()!=null&&node.getParent().getlChild().getColor()){
                node.getParent().switchColor();
                node.getParent().getlChild().switchColor();
            }
            if (node.getParent().getlChild().hedOneRedChild()){
                if (node.getParent().getlChild().getlChild()!=null&&!node.getParent().getlChild().getlChild().getColor()){
                    insertionFix(node.getParent().getlChild().getlChild());
                }else {
                    insertionFix(node.getParent().getlChild().getrChild());
                }
            }else if (node.getParent().getlChild().hedRedChild()){
                node.getParent().getlChild().getlChild().switchColor();
                rightTurn(node.getParent());
            }
        } else if (node.getParent() != null && node.getParent().getlChild() == node && node.getParent().getrChild() != null && !node.getParent().getrChild().getColor()) {
            //есть брат и он красный 2случ
            node.getParent().getrChild().switchColor();
            node.getParent().switchColor();
            leftTurn(node.getParent());
            if (node.getParent().getrChild()!=null&&node.getParent().getrChild().getColor()){
                node.getParent().switchColor();
                node.getParent().getrChild().switchColor();
            }
            if (node.getParent().getrChild().hedOneRedChild()){
                if (node.getParent().getrChild().getrChild()!=null&&!node.getParent().getrChild().getrChild().getColor()){
                    insertionFix(node.getParent().getrChild().getrChild());
                }else {
                    insertionFix(node.getParent().getrChild().getlChild());
                }
            }else if (node.getParent().getrChild().hedRedChild()){
                node.getParent().getrChild().getrChild().switchColor();
                leftTurn(node.getParent());
            }
        } else if (node.getParent() != null && node.getParent().getlChild() == node && node.getParent().getrChild() != null && node.getParent().getrChild().getColor() && !node.getParent().getColor()) {
            //3 случ
            node.getParent().getrChild().switchColor();
            node.getParent().switchColor();
            checDoublRed(node.getParent().getrChild(), 'l');

        } else if (node.getParent() != null && node.getParent().getrChild() == node && node.getParent().getlChild() != null && node.getParent().getlChild().getColor() && !node.getParent().getColor()) {
            //3 случ
            node.getParent().getlChild().switchColor();
            node.getParent().switchColor();

            checDoublRed(node.getParent().getlChild(), 'r');

        } else if (node.getParent() != null && node.getParent().getlChild() == node && node.getParent().getrChild() != null && node.getParent().getrChild().getColor() && node.getParent().getColor()) {
            //4 случ
            if (node.getParent().getrChild().hedOneRedChild()) {
                node.getParent().getrChild().switchColor();
                checDoublRed(node.getParent().getrChild(), 'l');

                if (!node.getParent().getParent().getColor()&&node.getParent().getParent().hedTwoBlackChild()){
                    node.getParent().getParent().switchColor();
                }else {
                    fixRedBlackPropertiesAfterDelete(node.getParent().getParent());
                }
            } else if (node.getParent().getrChild().noHedRedChild()) {
                node.getParent().getrChild().switchColor();
                fixRedBlackPropertiesAfterDelete(node.getParent());
            } else {
                node.getParent().getrChild().switchColor();
                node.getParent().getrChild().getrChild().switchColor();
                leftTurn(node.getParent());
                fixRedBlackPropertiesAfterDelete(node.getParent().getParent());
            }


        } else if (node.getParent() != null && node.getParent().getrChild() == node && node.getParent().getlChild() != null && node.getParent().getlChild().getColor() && node.getParent().getColor()) {
            //4 случ
            if (node.getParent().getlChild().hedOneRedChild()) {
                node.getParent().getlChild().switchColor();
                checDoublRed(node.getParent().getlChild(), 'r');

                if (!node.getParent().getParent().getColor()&&node.getParent().getParent().hedTwoBlackChild()){
                    node.getParent().getParent().switchColor();
                }else {
                    fixRedBlackPropertiesAfterDelete(node.getParent().getParent());
                }
            } else if (node.getParent().getlChild().noHedRedChild()) {
                node.getParent().getlChild().switchColor();
                fixRedBlackPropertiesAfterDelete(node.getParent());
            } else {
                node.getParent().getlChild().switchColor();
                node.getParent().getlChild().getlChild().switchColor();
                rightTurn(node.getParent());
                fixRedBlackPropertiesAfterDelete(node.getParent().getParent());
            }
        }
    }


    void checDoublRed(NodeRedBlackTree node, char direction) {
        if (node.getrChild() != null && !node.getrChild().getColor()
                && node.getlChild() == null || (
                node.getlChild() != null &&
                node.getlChild().getClass() == NilNode.class)) {

            if (direction == 'l') {
                node.getrChild().switchColor();
                leftTurn(node.getParent());
            } else {
                node.switchColor();
                leftTurn(node);
                rightTurn(node.getParent().getParent());
            }
        } else if (node.getlChild() != null && !node.getlChild().getColor()
                && node.getrChild() == null || (
                node.getlChild() != null && node.getrChild().getClass() == NilNode.class)) {

            if (direction == 'l') {
                node.switchColor();
                rightTurn(node);
                leftTurn(node.getParent().getParent());
            } else {

                node.getlChild().switchColor();
                rightTurn(node.getParent());
            }
        } else if (node.getlChild() != null && !node.getlChild().getColor()
                && node.getrChild() != null && !node.getrChild().getColor()) {

            if (direction == 'l') {
                node.getrChild().switchColor();
                leftTurn(node.getParent());
            } else {
                node.getlChild().switchColor();
                rightTurn(node.getParent());
            }
        }
    }

    public NodeRedBlackTree getRandNode(NodeRedBlackTree node) {
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

    public NodeRedBlackTree find(NodeRedBlackTree node, int inf) {
        if (node != null) {
            if (inf == node.getInf()) {
                return node;
            } else if (inf < node.getInf()) {
                return find(node.getlChild(), inf);
            } else {
                return find(node.getrChild(), inf);
            }
        } else {
            return null;
        }
    }

    void leftTurn(NodeRedBlackTree node) {
        NodeRedBlackTree parent = node.getParent();
        NodeRedBlackTree rightChild = node.getrChild();

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
    }

    void rightTurn(NodeRedBlackTree node) {
        NodeRedBlackTree parent = node.getParent();
        NodeRedBlackTree leftChild = node.getlChild();

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
    }

    public int getDeep(NodeRedBlackTree node) {
        if (node != null) {
            return (Math.max(getDeep(node.getrChild()), getDeep(node.getlChild())) + 1);
        } else {
            return 0;
        }
    }
    public int getMinDeep(NodeRedBlackTree node) {
        if (node != null) {
            return (Math.min(getMinDeep(node.getrChild()), getMinDeep(node.getlChild())) + 1);
        } else {
            return 0;
        }
    }
    int getBlackDeep(NodeRedBlackTree node) {
        if (node != null) {

            if (node.getColor()) {
                return (getBlackDeep(node.getlChild()) + 1);
            } else {
                return (getBlackDeep(node.getlChild()));//не добовляю в красных узлах
            }

        } else {
            return 0;
        }
    }

    public int getTheNumberOfElements(NodeRedBlackTree node) {
        if (node != null) {

            return (getTheNumberOfElements(node.getlChild()) + getTheNumberOfElements(node.getrChild()) + 1);

        } else {
            return 0;
        }
    }

    int checBlackDeep(NodeRedBlackTree node) {
        if (node != null) {
            int lDeep = checBlackDeep(node.getlChild());
            int rDeep = checBlackDeep(node.getrChild());
            if (lDeep == rDeep && lDeep != -1) {
                if (node.getColor()) {
                    return (lDeep + 1);
                } else {
                    return (lDeep);//не добовляю в красных узлах
                }
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

    void insertionFix(NodeRedBlackTree node) {


        if (!node.getColor()) {
            if (node.getParent() != null) {
                if (!node.getParent().getColor()) {

                    if (node.getParent().getParent() != null) {
                        if (node.getParent().getParent().getrChild() != null && !node.getParent().getParent().getrChild().getColor()
                                && node.getParent().getParent().getlChild() != null && !node.getParent().getParent().getlChild().getColor()) {//есть красный дядя

                            if (node.getParent().getParent().getlChild() == node.getParent()) {//родитель левый потомок деда
                                node.getParent().switchColor();
                                node.getParent().getParent().getrChild().switchColor();
                                node.getParent().getParent().switchColor();
                                insertionFix(node.getParent().getParent());
                            } else {
                                node.getParent().switchColor();
                                node.getParent().getParent().getlChild().switchColor();
                                node.getParent().getParent().switchColor();
                                insertionFix(node.getParent().getParent());
                            }


                        } else {//нет красного дяди
                            boolean swap = false;
                            if (node.getParent().getParent().getlChild() == node.getParent()) {

                                if (node.getParent().getrChild() == node) {
                                    leftTurn(node.getParent());
                                    swap = true;
                                }
                                if (swap) {
                                    node.switchColor();
                                    node.getParent().switchColor();

                                    rightTurn(node.getParent());
                                } else {
                                    node.getParent().getParent().switchColor();
                                    node.getParent().switchColor();
                                    rightTurn(node.getParent().getParent());
                                }
                            } else {

                                if (node.getParent().getlChild() == node) {
                                    rightTurn(node.getParent());
                                    swap = true;
                                }
                                if (swap) {
                                    node.switchColor();
                                    node.getParent().switchColor();

                                    leftTurn(node.getParent());
                                } else {
                                    node.getParent().getParent().switchColor();
                                    node.getParent().switchColor();
                                    leftTurn(node.getParent().getParent());
                                }


                            }
                        }

                    }
                }
            }
        }
        root.setBlackColor();

    }

    void fill(int min, int max) {//сбалансированное заполнение от min до max
        if (max - min > 2) {
            add((max + min) / 2);
            fill(min, (max + min) / 2);
            fill((max + min) / 2, max);
        }
    }
}
