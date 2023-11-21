

import AVL.AVLTree;
import AVL.NodeAVLTree;
import Binare.BinareTree;
import Binare.NodeBinarTree;
import RedBlack.NodeRedBlackTree;
import RedBlack.RedBlackTree;

import java.util.Arrays;
import java.util.Random;

public class Main {


    public static void main(String[] args) {
        Random random = new Random();
        int max = 100000;
        int step = 10000;
        int[] numbers = new int[max];
        for (int i = 0; i < max; i++) {
            int mem = random.nextInt();//создаем массив, чтобы деревья были в равных условиях
            if (Arrays.asList(numbers).contains(mem)) {
                i--;
            } else {
                numbers[i] = mem;
            }
        }

        AVLTree avlTree = new AVLTree();
        RedBlackTree redBlackTree = new RedBlackTree();
        Binare.BinareTree binareTree = new Binare.BinareTree();

//        redBlackTree.add(5);
//        redBlackTree.add(6);
//        redBlackTree.add(7);
//        redBlackTree.add(2);
//        redBlackTree.add(12);
//        redBlackTree.add(1);
//        redBlackTree.add(3);
//
//        System.out.println("Inorder");
//        redBlackTree.sayInorder(redBlackTree.getRoot());
//        System.out.println();
//        System.out.println("Postorder");
//        redBlackTree.sayPostorder(redBlackTree.getRoot());
//        System.out.println();
//        System.out.println("Preorder");
//        redBlackTree.sayPreorder(redBlackTree.getRoot());
//        System.out.println();
//        System.out.println("по глубине");
//        redBlackTree.sayTree(redBlackTree.getRoot(),0,0);
//
//
//
//        System.out.println();

        long startTime;
        long endTime;


        System.out.print("AVLInsert=[");
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9000) {
                    double sum = 0;
                    startTime = System.nanoTime();
                    for (int k = 0; k < 1000; k++) {
                        avlTree.insert(numbers[i + j + k]);
                    }
                    endTime = System.nanoTime();
                    sum += (endTime - startTime) / 1000.0;
                    System.out.print((int) sum + ",");

                } else {
                    avlTree.insert(numbers[i + j]);
                }

            }
        }

        System.out.println("];");
        System.out.print("AVLDel=[");
        avlTree = new AVLTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    double sum = 0;
                    for (int k = 0; k < 1000; k++) {
                        NodeAVLTree findNode = avlTree.getRandNode(avlTree.getRoot());
                        int FindNodeinf = findNode.getInf();
                        startTime = System.nanoTime();
                        avlTree.delete(findNode);
                        endTime = System.nanoTime();
                        sum += (endTime - startTime) / 1000.0;
                        avlTree.insert(FindNodeinf);

                    }
                    System.out.print((int) sum + ",");

                } else {
                    avlTree.insert(numbers[i + j]);
                }

            }
        }
        System.out.println("];");
        System.out.print("AVLDeep=[");
        avlTree = new AVLTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    System.out.print(avlTree.getDeep(avlTree.getRoot()) + ",");
                    avlTree.insert(numbers[i + j]);
                } else {
                    avlTree.insert(numbers[i + j]);
                }

            }
        }

        System.out.println("];");
        System.out.print("AVLFind=[");
        avlTree = new AVLTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    double sum = 0;
                    for (int k = 0; k < 1000; k++) {
                        int findNode = avlTree.getRandNode(avlTree.getRoot()).getInf();
                        startTime = System.nanoTime();
                        avlTree.find(avlTree.getRoot(), findNode);
                        endTime = System.nanoTime();
                        sum += (endTime - startTime) / 1000.0;
                    }
                    System.out.print((int)sum + ",");
                    avlTree.insert(numbers[i + j]);
                } else {
                    avlTree.insert(numbers[i + j]);
                }

            }
        }
        System.out.println("];");
        System.out.print("AVLInsertSortMass=[");
        avlTree = new AVLTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9000) {
                    double sum = 0;
                    startTime = System.nanoTime();
                    for (int k = 0; k < 1000; k++) {
                        avlTree.insert(i + j + k);
                    }
                    endTime = System.nanoTime();
                    sum += (endTime - startTime) / 1000.0;
                    System.out.print((int) sum + ",");

                } else {
                    avlTree.insert(i + j);
                }

            }
        }

        System.out.println("];");
        System.out.print("AVLDelSortMass=[");
        avlTree = new AVLTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    double sum = 0;
                    for (int k = 0; k < 1000; k++) {
                        NodeAVLTree findNode = avlTree.getRandNode(avlTree.getRoot());
                        int FindNodeinf = findNode.getInf();
                        startTime = System.nanoTime();
                        avlTree.delete(findNode);
                        endTime = System.nanoTime();
                        sum += (endTime - startTime) / 1000.0;
                        avlTree.insert(FindNodeinf);

                    }
                    System.out.print((int) sum + ",");
                } else {
                    avlTree.insert(i + j);
                }

            }
        }
        System.out.println("];");
        System.out.print("AVLDeepSortMass=[");
        avlTree = new AVLTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    System.out.print(avlTree.getDeep(avlTree.getRoot()) + ",");
                    avlTree.insert(i + j);
                } else {
                    avlTree.insert(i + j);
                }

            }
        }

        System.out.println("];");
        System.out.print("AVLFindSortMass=[");
        avlTree = new AVLTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    double sum = 0;
                    for (int k = 0; k < 1000; k++) {
                        int findNode = avlTree.getRandNode(avlTree.getRoot()).getInf();
                        startTime = System.nanoTime();
                        avlTree.find(avlTree.getRoot(), findNode);
                        endTime = System.nanoTime();
                        sum += (endTime - startTime) / 1000.0;
                    }
                    System.out.print((int)sum + ",");
                    avlTree.insert(i + j);
                } else {
                    avlTree.insert(i + j);
                }

            }
        }


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("];");
        System.out.print("BINAREInsert=[");
        binareTree = new BinareTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9000) {

                    double sum = 0;
                    startTime = System.nanoTime();
                    for (int k = 0; k < 1000; k++) {
                        binareTree.add(numbers[i + j + k]);
                    }
                    endTime = System.nanoTime();
                    sum += (endTime - startTime) / 1000.0;
                    System.out.print((int) sum + ",");
                    i += 1000;
                } else {
                    binareTree.add(numbers[i + j]);
                }

            }
        }

        System.out.println("];");
        System.out.print("BINAREDel=[");
        binareTree = new BinareTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    double sum = 0;
                    for (int k = 0; k < 1000; k++) {
                        NodeBinarTree findNode = binareTree.getRandNode(binareTree.getRoot());
                        int inf = findNode.getInf();

                        startTime = System.nanoTime();
                        binareTree.delete(findNode);
                        endTime = System.nanoTime();
                        sum += (endTime - startTime) / 1000.0;

                        redBlackTree.add(inf);
                    }
                    System.out.print((int) sum + ",");
                } else {
                    binareTree.add(numbers[i + j]);
                }

            }
        }
        System.out.println("];");
        System.out.print("BINAREDeep=[");
        binareTree = new BinareTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    System.out.print(binareTree.getDeep(binareTree.getRoot()) + ",");
                    binareTree.add(numbers[i + j]);
                } else {
                    binareTree.add(numbers[i + j]);
                }

            }
        }

        System.out.println("];");
        System.out.print("BINAREFind =[");
        binareTree = new BinareTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    double sum = 0;
                    for (int k = 0; k < 1000; k++) {
                        int findNode = binareTree.getRandNode(binareTree.getRoot()).getInf();
                        startTime = System.nanoTime();
                        binareTree.find(binareTree.getRoot(), findNode);
                        endTime = System.nanoTime();
                        sum += (endTime - startTime) / 1000.0;
                    }
                    System.out.print((int)sum + ",");
                    binareTree.add(numbers[i + j]);
                } else {
                    binareTree.add(numbers[i + j]);
                }

            }
        }
        System.out.println("];");
        System.out.print("BINAREInsertSortMass=[");
        binareTree = new BinareTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {

                    double sum = 0;
                    startTime = System.nanoTime();
                    for (int k = 0; k < 1000; k++) {
                        binareTree.add(i + j + k);
                    }
                    endTime = System.nanoTime();
                    sum += (endTime - startTime) / 1000.0;
                    System.out.print((int) sum + ",");
                    i += 1000;
                } else {
                    binareTree.add(i + j);
                }

            }
        }

        System.out.println("];");
        System.out.print("BINAREDelSortMass=[");
        binareTree = new BinareTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    double sum = 0;
                    for (int k = 0; k < 1000; k++) {
                        NodeBinarTree findNode = binareTree.getRoot();
                        while (findNode.getrChild() != null) {
                            findNode = findNode.getrChild();
                        }
                        int inf = findNode.getInf();
                        startTime = System.nanoTime();
                        binareTree.delete(findNode);
                        endTime = System.nanoTime();
                        sum += (endTime - startTime) / 1000.0;
                        redBlackTree.add(inf);
                    }
                    System.out.print((int) sum + ",");

                } else {
                    binareTree.add(i + j);
                }

            }
        }
        System.out.println("];");
        System.out.print("BINAREDeepSortMass=[");
        for (int i = 10000; i <= 100000; i += 10000) {
            System.out.print(i + ",");

        }

        System.out.println("];");
        System.out.print("BINAReFindSortMass=[");
        binareTree = new BinareTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    double sum = 0;
                    for (int k = 0; k < 1000; k++) {

                        int findNode = binareTree.getMax(binareTree.getRoot()).getInf();
                        startTime = System.nanoTime();
                        binareTree.find(binareTree.getRoot(), findNode);
                        endTime = System.nanoTime();
                        sum += (endTime - startTime) / 1000.0;
                    }
                    System.out.print((int)sum + ",");
                    binareTree.add(i + j);

                } else {
                    binareTree.add(i + j);
                }

            }
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////


        System.out.println("];");
        System.out.print("RedBlackTreeInsert=[");
        redBlackTree = new RedBlackTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9000) {
                    double sum = 0;
                    startTime = System.nanoTime();
                    for (int k = 0; k < 1000; k++) {
                        redBlackTree.add(numbers[i + j+k]);
                    }
                    endTime = System.nanoTime();
                    sum += (endTime - startTime) / 1000.0;
                    System.out.print((int) sum + ",");
                    i += 1000;
                } else {
                    redBlackTree.add(numbers[i + j]);
                }

            }
        }

        System.out.println("];");
        System.out.print("RedBlackTreeDel=[");
        redBlackTree = new RedBlackTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    double sum = 0;
                    int k = 0;
                    boolean flag=true;
                    try {

                        NodeRedBlackTree findNode = redBlackTree.getRandNode(redBlackTree.getRoot());
                        int inf = findNode.getInf();
                        for (k = 0; k < 1000; k++) {
                            startTime = System.nanoTime();
                            redBlackTree.delete(findNode);
                            endTime = System.nanoTime();
                            sum += (endTime - startTime) ;
                            redBlackTree.add(inf);
                        }
                    } catch (Exception e) {
                        System.out.print((int) ((sum / k)) + ",");
                        flag = false;
                    }
                    if (flag) {
                        System.out.print((int) sum/k + ",");
                    }
                } else {
                    redBlackTree.add(numbers[i + j]);
                }

            }
        }


        System.out.println("];");
        System.out.print("RedBlackTreeDeep=[");
        redBlackTree = new RedBlackTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    System.out.print(redBlackTree.getDeep(redBlackTree.getRoot()) + ",");
                    redBlackTree.add(numbers[i + j]);
                } else {
                    redBlackTree.add(numbers[i + j]);
                }

            }
        }

        System.out.println("];");
        System.out.print("RedBlackTreeFind =[");
        redBlackTree = new RedBlackTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    double sum=0;
                    for (int k = 0; k < 1000; k++) {

                    int findNode = redBlackTree.getRandNode(redBlackTree.getRoot()).getInf();
                    startTime = System.nanoTime();
                    redBlackTree.find(redBlackTree.getRoot(), findNode);
                    endTime = System.nanoTime();
                    sum+=(endTime - startTime)/1000.0;
                }
                    System.out.print((int)sum + ",");
                    redBlackTree.add(numbers[i + j]);
                } else {
                    redBlackTree.add(numbers[i + j]);
                }

            }
        }
        System.out.println("];");
        System.out.print("RedBlackTreeInsertSortMass=[");
        redBlackTree = new RedBlackTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                    if (i == 9000) {
                        double sum = 0;
                        startTime = System.nanoTime();
                        for (int k = 0; k < 1000; k++) {
                            redBlackTree.add(i + j+k);
                        }
                        endTime = System.nanoTime();
                        sum += (endTime - startTime) / 1000.0;
                        System.out.print((int) sum + ",");
                        i += 1000;
                } else {
                    redBlackTree.add(i + j);
                }

            }
        }

        System.out.println("];");
        System.out.print("RedBlackTreeDelSortMass=[");
        redBlackTree = new RedBlackTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {

                if (i == 9999) {
                    double sum = 0;
                    int k = 0;
                    boolean flag=true;
                    try {


                        for (k = 0; k < 1000; k++) {
                            NodeRedBlackTree findNode = redBlackTree.getRandNode(redBlackTree.getRoot());
                            int inf = findNode.getInf();


                            startTime = System.nanoTime();
                            redBlackTree.delete(findNode);
                            endTime = System.nanoTime();
                            sum += (endTime - startTime) / 1000.0;
                            redBlackTree.add(inf);
                        }
                    } catch (Exception e) {
                        System.out.print((int) ((sum / k) * 1000) + ",");
                        flag = false;
                    }
                    if (flag) {
                        System.out.print((int) sum + ",");
                    }
                } else {
                    redBlackTree.add(i + j);
                }

            }
        }
        System.out.println("];");
        System.out.print("RedBlackTreeDeepSortMass=[");
        redBlackTree = new RedBlackTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    System.out.print(redBlackTree.getDeep(redBlackTree.getRoot()) + ",");
                    redBlackTree.add(i + j);
                } else {
                    redBlackTree.add(i + j);
                }

            }
        }

        System.out.println("];");
        System.out.print("RedBlackTreeFindSortMass=[");
        redBlackTree = new RedBlackTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {

                    double sum = 0;
                    for (int k = 0; k < 1000; k++) {
                        int findNode = redBlackTree.getMax(redBlackTree.getRoot()).getInf();

                        startTime = System.nanoTime();
                        redBlackTree.find(redBlackTree.getRoot(), findNode);
                        endTime = System.nanoTime();
                        sum += (endTime - startTime) / 1000.0;
                    }

                    System.out.print((int) sum + ",");
                    redBlackTree.add(i + j);
                } else {
                    redBlackTree.add(i + j);
                }

            }
        }
        System.out.println("];");

    }
}

