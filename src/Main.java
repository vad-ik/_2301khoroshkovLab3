

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

        long startTime;
        long endTime;
try {
    System.out.print("AVLInsert=[");
    for (int j = 0; j < max - 1; j += step) {
        for (int i = 0; i < step; i++) {
            if (i == 9000) {
                float sum=0;
                for (int k = 0; k < 1000; k++) {


                startTime = System.nanoTime();
                avlTree.insert(avlTree.getRoot(), numbers[i + j]);
                endTime = System.nanoTime();
                sum+=(endTime - startTime)/1000.0;
                }
                System.out.print((int)sum + ",");
                i+=1000;
            } else {
                avlTree.insert(avlTree.getRoot(), numbers[i + j]);
            }

        }
    }

    System.out.println("];");
    System.out.print("AVLDel=[");
    avlTree = new AVLTree();
    for (int j = 0; j < max - 1; j += step) {
        for (int i = 0; i < step; i++) {
            if (i == 9999) {
                float sum=0;
                for (int k = 0; k < 1000; k++) {
                    int FindNode = avlTree.getRandNode(avlTree.getRoot()).getInf();

                    startTime = System.nanoTime();
                    avlTree.remove(avlTree.getRoot(), FindNode);
                    endTime = System.nanoTime();
                    sum+=(endTime - startTime)/1000.0;
                    redBlackTree.add(FindNode);
                }
                System.out.print((int)sum + ",");

            } else {
                avlTree.insert(avlTree.getRoot(), numbers[i + j]);
            }

        }
    }
    System.out.println("];");
    System.out.print("AVLMaxDeep=[");
    avlTree = new AVLTree();
    for (int j = 0; j < max - 1; j += step) {
        for (int i = 0; i < step; i++) {
            if (i == 9999) {
                System.out.print(avlTree.getDeep(avlTree.getRoot()) + ",");
                avlTree.insert(avlTree.getRoot(), numbers[i + j]);
            } else {
                avlTree.insert(avlTree.getRoot(), numbers[i + j]);
            }

        }
    }
    System.out.println("];");
    System.out.print("AVLminDeep=[");
    avlTree = new AVLTree();
    for (int j = 0; j < max - 1; j += step) {
        for (int i = 0; i < step; i++) {
            if (i == 9999) {
                System.out.print(avlTree.getMinDeep(avlTree.getRoot()) + ",");
                avlTree.insert(avlTree.getRoot(), numbers[i + j]);
            } else {
                avlTree.insert(avlTree.getRoot(), numbers[i + j]);
            }

        }
    }
    System.out.println("];");
    System.out.print("AVLFind=[");
    avlTree = new AVLTree();
    for (int j = 0; j < max - 1; j += step) {
        for (int i = 0; i < step; i++) {
            if (i == 9999) {
                int FindNode = avlTree.getRandNode(avlTree.getRoot()).getInf();
                startTime = System.nanoTime();
                avlTree.find(avlTree.getRoot(), FindNode);
                endTime = System.nanoTime();
                System.out.print(endTime - startTime + ",");
                avlTree.insert(avlTree.getRoot(), numbers[i + j]);
            } else {
                avlTree.insert(avlTree.getRoot(), numbers[i + j]);
            }

        }
    }
    System.out.println("];");
    System.out.print("AVLInsertSortMass=[");
    avlTree = new AVLTree();
    for (int j = 0; j < max - 1; j += step) {
        for (int i = 0; i < step; i++) {
            if (i == 9999) {
                startTime = System.nanoTime();
                avlTree.insert(avlTree.getRoot(), i + j);
                endTime = System.nanoTime();
                System.out.print(endTime - startTime + ",");
            } else {
                avlTree.insert(avlTree.getRoot(), i + j);
            }

        }
    }

    System.out.println("];");
    System.out.print("AVLDelSortMass=[");
    avlTree = new AVLTree();
    for (int j = 0; j < max - 1; j += step) {
        for (int i = 0; i < step; i++) {
            if (i == 9999) {
                int FindNode = avlTree.getMax(avlTree.getRoot()).getInf();
                startTime = System.nanoTime();
                avlTree.remove(avlTree.getRoot(), FindNode);
                endTime = System.nanoTime();
                System.out.print(endTime - startTime + ",");
            } else {
                avlTree.insert(avlTree.getRoot(), i + j);
            }

        }
    }
    System.out.println("];");
    System.out.print("AVLMaxDeepSortMass=[");
    avlTree = new AVLTree();
    for (int j = 0; j < max - 1; j += step) {
        for (int i = 0; i < step; i++) {
            if (i == 9999) {
                System.out.print(avlTree.getDeep(avlTree.getRoot()) + ",");
                avlTree.insert(avlTree.getRoot(), i + j);
            } else {
                avlTree.insert(avlTree.getRoot(), i + j);
            }

        }
    }
    System.out.println("];");
    System.out.print("AVLMinDeepSortMass=[");
    avlTree = new AVLTree();
    for (int j = 0; j < max - 1; j += step) {
        for (int i = 0; i < step; i++) {
            if (i == 9999) {
                System.out.print(avlTree.getMinDeep(avlTree.getRoot()) + ",");
                avlTree.insert(avlTree.getRoot(), i + j);
            } else {
                avlTree.insert(avlTree.getRoot(), i + j);
            }

        }
    }
    System.out.println("];");
    System.out.print("AVLFindSortMass=[");
    avlTree = new AVLTree();
    for (int j = 0; j < max - 1; j += step) {
        for (int i = 0; i < step; i++) {
            if (i == 9999) {
                int FindNode = avlTree.getMax(avlTree.getRoot()).getInf();
                startTime = System.nanoTime();
                avlTree.find(avlTree.getRoot(), FindNode);
                endTime = System.nanoTime();
                System.out.print(endTime - startTime + ",");
                avlTree.insert(avlTree.getRoot(), i + j);
            } else {
                avlTree.insert(avlTree.getRoot(), i + j);
            }

        }
    }
}catch (Exception e){
    System.out.println("авл дерево переполнило стэк");
}

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("];");
        System.out.print("BINAREInsert=[");
        binareTree = new BinareTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9000) {

                    float sum=0;
                    for (int k = 0; k < 1000; k++) {


                        startTime = System.nanoTime();
                        binareTree.add(numbers[i + j]);
                        endTime = System.nanoTime();
                        sum+=(endTime - startTime)/1000.0;
                    }
                    System.out.print((int)sum + ",");
                    i+=1000;
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
                     float sum=0;
                    for (int k = 0; k < 1000; k++) {
                        NodeBinarTree FindNode=binareTree.getRandNode(binareTree.getRoot());
int inf= FindNode.getInf();

                        startTime = System.nanoTime();
                        binareTree.delete( FindNode);
                        endTime = System.nanoTime();
                        sum+=(endTime - startTime)/1000.0;

                        redBlackTree.add(inf);
                    }
                    System.out.print((int)sum + ",");
                } else {
                    binareTree.add(numbers[i + j]);
                }

            }
        }
        System.out.println("];");
        System.out.print("BINAREMaxDeep=[");
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
        System.out.print("BINAREMinDeep=[");
        binareTree = new BinareTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    System.out.print(binareTree.getMinDeep(binareTree.getRoot()) + ",");
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
                    int FindNode=binareTree.getRandNode(binareTree.getRoot()).getInf();
                    startTime = System.nanoTime();
                    binareTree.find(binareTree.getRoot(),FindNode );
                    endTime = System.nanoTime();
                    System.out.print(endTime - startTime + ",");
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
                    startTime = System.nanoTime();
                    binareTree.add(i + j);
                    endTime = System.nanoTime();
                    System.out.print(endTime - startTime + ",");
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
                    NodeBinarTree FindNode=binareTree.getRoot();
                    while (FindNode.getrChild()!=null){
                        FindNode=FindNode.getrChild();
                    }
                    startTime = System.nanoTime();
                    binareTree.delete(FindNode );
                    endTime = System.nanoTime();
                    System.out.print(endTime - startTime + ",");
                } else {
                    binareTree.add(i + j);
                }

            }
        }
        System.out.println("];");
        System.out.print("BINAREMaxDeepSortMass=[");
        for (int i = 10000; i <=100000; i+=10000) {
            System.out.print(i + ",");

        }

        System.out.println("];");
        System.out.print("BINAREMinDeepSortMass=[");
         for (int i = 10000;i <=100000; i+=10000) {
            System.out.print(1 + ",");

        }
        System.out.println("];");
        System.out.print("BINAReFindSortMass=[");
        binareTree = new BinareTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    int FindNode=binareTree.getMax(binareTree.getRoot()).getInf();
                    startTime = System.nanoTime();
                    binareTree.find(binareTree.getRoot(),FindNode );
                    endTime = System.nanoTime();
                    System.out.print(endTime - startTime + ",");
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


                    float sum=0;
                    for (int k = 0; k < 1000; k++) {


                        startTime = System.nanoTime();
                        redBlackTree.add(numbers[i + j]);
                        endTime = System.nanoTime();
                        sum+=(endTime - startTime)/1000.0;
                    }
                    System.out.print((int)sum + ",");
                    i+=1000;
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
                     float sum=0;
                    int k=0;
                    try {


                   for ( k = 0; k < 1000; k++) {
                        NodeRedBlackTree FindNode=redBlackTree.getRandNode(redBlackTree.getRoot());
                        int inf=FindNode.getInf();


                        startTime = System.nanoTime();
                        redBlackTree.delete(FindNode);
                        endTime = System.nanoTime();
                        sum+=(endTime - startTime)/1000.0;
                        redBlackTree.add(inf);
                    }
                    }catch (Exception e){
                        System.out.print((int)((sum/k)*1000) + ",,");
                    }

                    System.out.print((int)sum + ",");
                } else {
                    redBlackTree.add(numbers[i + j]);
                }

            }
        }
        System.out.println("];");
        System.out.print("RedBlackTreeMaxDeep=[");
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
        System.out.print("RedBlackTreeMinDeep=[");
        redBlackTree = new RedBlackTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    System.out.print(redBlackTree.getMinDeep(redBlackTree.getRoot()) + ",");
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
                    int FindNode=redBlackTree.getRandNode(redBlackTree.getRoot()).getInf();
                    startTime = System.nanoTime();
                    redBlackTree.find(redBlackTree.getRoot(),FindNode );
                    endTime = System.nanoTime();
                    System.out.print(endTime - startTime + ",");
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
                if (i == 9999) {
                    startTime = System.nanoTime();
                    redBlackTree.add(i + j);
                    endTime = System.nanoTime();
                    System.out.print(endTime - startTime + ",");
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
                    NodeRedBlackTree FindNode=redBlackTree.getMax(redBlackTree.getRoot());
                    startTime = System.nanoTime();
                    redBlackTree.delete(FindNode );
                    endTime = System.nanoTime();
                    System.out.print(endTime - startTime + ",");
                } else {
                    redBlackTree.add(i + j);
                }

            }
        }
        System.out.println("];");
        System.out.print("RedBlackTreeMaxDeepSortMass=[");
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
        System.out.print("RedBlackTreeMinDeepSortMass=[");
        redBlackTree = new RedBlackTree();
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    System.out.print(redBlackTree.getMinDeep(redBlackTree.getRoot()) + ",");
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

                    float sum=0;
                    for (int k = 0; k <1000 ; k++) {
                        int FindNode=redBlackTree.getMax(redBlackTree.getRoot()).getInf();

                        startTime = System.nanoTime();
                        redBlackTree.find(redBlackTree.getRoot(),FindNode );
                        endTime = System.nanoTime();
                        sum+=(endTime-startTime)/1000.0;
                    }

                    System.out.print((int)sum+ ",");
                    redBlackTree.add(i + j);
                } else {
                    redBlackTree.add(i + j);
                }

            }
        }
















/*
        System.out.println("];");
        System.out.println("binare add=[");
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    startTime = System.nanoTime();
                    binareTree.add( numbers[i + j]);
                    endTime = System.nanoTime();
                    System.out.print(endTime - startTime + ",");
                } else {
                    binareTree.add( numbers[i + j]);
                }

            }
        }
        System.out.println("];");
        System.out.println("RedBlack add=[");
        for (int j = 0; j < max - 1; j += step) {
            for (int i = 0; i < step; i++) {
                if (i == 9999) {
                    startTime = System.nanoTime();
                    redBlackTree.add( numbers[i + j]);
                    endTime = System.nanoTime();
                    System.out.print(endTime - startTime + ",");
                } else {
                    redBlackTree.add( numbers[i + j]);
                }

            }
        }
*/



    }
}

