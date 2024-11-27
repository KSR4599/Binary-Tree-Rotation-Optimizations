package com.company;

import javax.lang.model.element.NestingKind;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tree implements Cloneable {
    Node root;
    int rotations = 0;


    // Function to insert nodes in level order
    public Node insertLevelOrder(Integer[] arr, Node root,
                                 int i) {
        if (i < arr.length) {
            Node temp = new Node(arr[i]);
            root = temp;

            root.left = insertLevelOrder(arr, root.left,
                    2 * i + 1);

            root.right = insertLevelOrder(arr, root.right,
                    2 * i + 2);
        }
        return root;
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Function to print tree nodes in InOrder fashion
    public void assignHeight(Node root) {
        if (root != null) {
            assignHeight(root.left);
            assignHeight(root.right);

            if (root.left != null && root.right != null) {
                root.height = 1 + Math.max(height(root.left), height(root.right));
            }

        }
    }

    // Function to print tree nodes in InOrder fashion
    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    // Function to print tree nodes in InOrder fashion
    public void AdjustHeight(Node root) {
        if (root != null) {
            AdjustHeight(root.left);
            AdjustHeight(root.right);
            root.rotate = true;
            root.height = 1 + Math.max(height(root.left), height(root.right));

        }
    }

    public void inOrderInsert(Node root, List<Integer> list) {
        if (root != null && list.size() >= 0) {
            inOrderInsert(root.left, list);

            root.data = list.get(0);
            list.remove(0);
            inOrderInsert(root.right, list);
        }
    }

    void preOrder(Node node, int key, boolean rot) {

        if (node == null) {
            return;
        }

        if (node.data == key) {
            node.rotate = rot;
        }
        preOrder(node.left, key, rot);
        preOrder(node.right, key, rot);

    }



    static int COUNT = 20;

    static void print2DUtil(Node root, int space) {

        if (root == null)
            return;

        space += COUNT;


        print2DUtil(root.right, space);

        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");

        System.out.print(root.data + " rotate:" + root.rotate + "height:" + root.height + "ignore:"+ root.ignore + "\n");

        print2DUtil(root.left, space);
    }


    static void print2D(Node root) {
        print2DUtil(root, 0);
    }



    /*Given a binary tree, print out all of its root-to-leaf
    paths, one per line. Uses a recursive helper to do
    the work.*/
    void printPaths(Node node)
    {
        int path[] = new int[20];
        printPathsRecur(node, path, 0);
    }

    /* Recursive helper function -- given a node, and an array
       containing the path from the root node up to but not
       including this node, print out all the root-leaf paths.*/
    void printPathsRecur(Node node, int path[], int pathLen)
    {
        if (node == null)
            return;

        /* append this node to the path array */
        path[pathLen] = node.data;
        pathLen++;

        /* it's a leaf, so print the path that led to here  */
        if (node.left == null && node.right == null)
            printArray(path, pathLen);
        else
        {
            /* otherwise try both subtrees */
            printPathsRecur(node.left, path, pathLen);
            printPathsRecur(node.right, path, pathLen);
        }
    }

    /* Utility function that prints out an array on a line. */
    void printArray(int ints[], int len)
    {
        int i;

        for (i = 0; i < len; i++)
        {
            if(i<len-1){
                System.out.print(ints[i] + " -> ");
            }else{
                System.out.print(ints[i] + ";");
            }
        }
        System.out.println("");
    }


    int height(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

    Node rightRotate(Node y) {

        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    Node rightRotate1(Node y, Node Parent) {

        if ((y.right == null && y.left == null) || (y.left != null && y.right == null) || (y.right != null && y.left == null)) {
            Node T2 = y.right;
            y.right = Parent;
            Parent.left = T2;
            return y;

        } else {
            Node T2 = y.right;
            y.right = Parent;
            Parent.left = T2;

            return y;
        }


    }


    Node leftRotate1(Node y, Node Parent) {
        if ((y.right == null && y.left == null) || (y.left != null && y.right == null) || (y.right != null && y.left == null)) {
            Node T2 = y.left;
            y.left = Parent;
            Parent.right = T2;
            return y;

        } else {
            Node T2 = y.left;
            y.left = Parent;
            Parent.right = T2;

            return y;
        }
    }


    // Get Balance factor of node N
    int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    Node rotateFunction(Node node) {

        if (node == null)
            return node;

             node.left = rotateFunction(node.left);


        node.height = 1 + Math.max(height(node.left),
                height(node.right));


        if (node.rotate && node.left != null && node.height > 1) {
            return rightRotate(node);
        }

        if (node.rotate && node.right != null && node.height > 1) {
            return leftRotate(node);
        }


           node.right =  rotateFunction(node.right);


        return node;
    }


    int calc1(int n , int c){
        int result = (int)(Math.log(n) / Math.log(2));
        int ret = (2*n) - (2*result) - (c) -  +1;
        return ret;
    }

    void listRotFalse(Node root, List<Integer> list_a, List<Integer> list_b) {
        if (root != null) {

            listRotFalse(root.left, list_a, list_b);
            listRotFalse(root.right, list_a, list_b);

            if (list_a.contains(root.data) || list_b.contains(root.data)) {
                root.rotate = false;

            }
            root.height = 1 + Math.max(height(root.left), height(root.right));
        }
    }




    double computeRoot(int n, int h) {

        if ((Math.pow(2, h - 1)) <= n && n <= ((Math.pow(2, h - 1)) + (Math.pow(2, h - 2) - 2))) {
            return (n - Math.pow(2, h - 2) + 1);
        }

        if ((Math.pow(2, h - 1) + (Math.pow(2, h - 2) - 1)) <= n && n <= (Math.pow(2, h) - 1)) {
            return Math.pow(2, h - 1);
        }

        return h;
    }

    Node rootRotate(Node node, boolean left) {
        if (node == null)
            return node;

        node.height = 1 + Math.max(height(node.left),
                height(node.right));

        if (left) {
            return leftRotate(node);
        } else {
            return rightRotate(node);
        }

    }

    Node rootRotate1(Node node, boolean left, Node Parent) {
        if (node == null)
            return node;

        node.height = 1 + Math.max(height(node.left),
                height(node.right));

        if (left) {
            return leftRotate1(node, Parent);
        } else {
            return rightRotate1(node, Parent);
        }

    }


    Node rootTop(Node root, int key, int rootHeight, Tree S) {

        if (root != null) {
            rootTop(root.left, key, rootHeight, S);
            if (root.data == key) {
                int rot = rootHeight - root.height;
                int j = 0;
                while (j < rot) {
                    S.root = S.rootRotate(S.root, true);
                    S.rotations = S.rotations + 1;
                    System.out.println("Update rot to " + S.rotations);
                    j++;
                }
            }
            rootTop(root.right, key, rootHeight, S);
        }

        return S.root;
    }

    List<Integer> getLeftForeArm(Node root) {
        List<Integer> list = new ArrayList<Integer>();
        while (root != null) {
            list.add(root.data);
            root = root.right;
        }
        return list;
    }

    List<Integer> getRightForeArm(Node root) {
        List<Integer> list = new ArrayList<Integer>();
        while (root != null) {
            list.add(root.data);
            root = root.right;
        }
        return list;
    }


    ArrayList<Node> intoLeftForearms(Tree S, Node root) {
        ArrayList<Node> nodez = new ArrayList<Node>();
        S.root.left = root;
        Tree ex = new Tree();
        ex.root = new Node(0);

        while (root != null) {
            while (root.left != null && root.ignore == false && root.left.ignore == false) {
                root = rootRotate1(root.left, false, root);
                S.rotations = S.rotations + 1;
            }
            nodez.add(root);
            root = root.right;
        }
        return nodez;
    }

    ArrayList<Node> intoRightForearms(Tree S, Node root) {
        ArrayList<Node> nodez = new ArrayList<Node>();
        S.root.right = root;
        Tree ex = new Tree();
        ex.root = new Node(0);

        while (root != null) {
            while (root.left != null && root.ignore == false) {
                root = rootRotate1(root.left, false, root);
                S.rotations = S.rotations + 1;

            }
            nodez.add(root);
            System.out.println("Root : " + root.data);
            root = root.right;
        }
        return nodez;
    }




    void inorderAssignLeft(Node root_s, ArrayList<Node> noder) {

        if (noder.size() == 0) {
            return;
        } else {
            root_s.right = new Node(0);
            root_s.right = noder.get(0);
            noder.remove(0);
            inorderAssignLeft(root_s.right, noder);
        }
    }


    void inorderAssignRight(Node root_s, ArrayList<Node> noder1) {
        if (noder1.size() == 0) {
            return;
        } else {
            root_s.right = new Node(0);
            root_s.right = noder1.get(0);
            noder1.remove(0);
            inorderAssignRight(root_s.right, noder1);
        }

    }


    Node rotatorLeft(Node y, Node parent, Tree g) {
        if (y.parent.parent == null) { // g.root?
            if (y.right.left == null) {
                y.right.left = y;

                y = y.right;
                y.left.right = null;
                y.left.parent = y;
                y.parent = parent;
                g.root = y;
                g.rotations = g.rotations + 1;
                return g.root;
            } else {
                Node tmp = y.right.left;
                y.right.left = y;
                y = y.right;
                y.left.right = tmp;
                y.left.parent = y;
                y.parent = parent;
                y.left.right.parent = y.left;
                g.root = y;
                g.rotations = g.rotations + 1;
                return g.root;

            }


        } else if (y.right == null) {
//TO:DO
        } else {
            if (y.right.left == null) {

                Node tmp = y;
                y = y.right;
                parent.right = y;
                y.left = tmp;
                y.left.right = null;
                y.left.parent = y;
                y.parent = parent;
                g.rotations = g.rotations + 1;
                return g.root;

            } else {


                Node tmp = y.right.left;
                parent.right = y.right;
                y.right.left = y;
                y = y.right;
                y.left.right = tmp;
                y.left.parent = y;
                y.parent = parent;
                y.left.right.parent = y.left;
                g.rotations = g.rotations + 1;
                return g.root;
            }
        }
        return g.root;
    }



    Node rotatorRight(Node y, Node parent, Tree g) {
        if (y.parent.parent == null) { // g.root?
            if (y.left.right == null) {
                y.left.right = y;

                y = y.left;
                y.right.left = null;
                y.right.parent = y;
                y.parent = parent;
                g.root = y;
                g.rotations = g.rotations + 1;

                return g.root;
            } else {
                Node tmp = y.left.right;
                y.left.right = y;
                y = y.left;
                y.right.left = tmp;
                y.right.parent = y;
                y.parent = parent;
                y.right.left.parent = y.right;
                g.root = y;
                g.rotations = g.rotations + 1;
                return g.root;

            }


        } else if (y.left == null) {
//TO:DO
        } else {
            if (y.left.right == null) {

                Node tmp = y;
                y = y.left;
                parent.left = y;
                y.right = tmp;
                y.right.left = null;
                y.right.parent = y;
                y.parent = parent;
                g.rotations = g.rotations + 1;
                return g.root;

            } else {


                Node tmp = y.left.right;
                parent.left = y.left;
                y.left.right = y;
                y = y.left;
                y.right.left = tmp;
                y.right.parent = y;
                y.parent = parent;
                y.right.left.parent = y.right;
                g.rotations = g.rotations + 1;
                return g.root;
            }
        }
        return g.root;
    }

    Node dupe = null;

    void AssignParentLeft(Node root, Node head) {
        root.parent = head;
        head = root;
        if (root.right != null) {
            AssignParentLeft(root.right, head);
        } else {
            return;
        }
    }

    void AssignParentRight(Node root, Node head) {
        root.parent = head;
        head = root;
        if (root.left != null) {
            AssignParentRight(root.left, head);
        } else {
            return;
        }
    }


    void AlgoAL(Node root, Tree g, int h, int n) throws CloneNotSupportedException {
        boolean flag = false;
        dupe = (Node) root.clone();
        int s = h;
        if (dupe != null) {
            //int match = Integer.parseInt(Integer.toBinaryString(root.data));

            if ((Math.pow(2, h - 1) <= n) && (n <= Math.pow(2, h - 1) + Math.pow(2, h - 2) - 2)) {
                flag = true;
                int k = n - (int) Math.pow(2, h - 1) + 1;

                for (int i = 1; i <= k; i++) {

                    if (dupe.right != null) {

                        if (dupe.right.rotate == true) {
                            dupe.rotate = false;
                            g.root = rotatorLeft(dupe, dupe.parent, g);
                        }
                    } else {
                        dupe.rotate = false;
                        g.root = rotatorLeft(dupe, dupe.parent, g);
                    }
                    dupe = dupe.parent.right;
                }
                s = h - 1;
            }

            //Node duper = (Node) g.root.clone();


            for (int j = s - 1; j >= 1; j--) {
                int k = (int) Math.pow(2, j) - 1;
                if (flag) {
                    dupe = (Node) g.root.clone();
                } else {
                    dupe = (Node) root.clone();
                    flag = true;
                }
                for (int i = 1; i < k; i++) {
                    if (dupe.right != null) {

                        if (dupe.right.rotate == true) {
                            dupe.rotate = false;
                            g.root = rotatorLeft(dupe, dupe.parent, g);
                        }else {
                            dupe.rotate = false;
                        }
                    } else {
                        dupe.rotate = false;
                        g.root = rotatorLeft(dupe, dupe.parent, g);
                    }
                    dupe = dupe.parent.right;
                }

                System.out.println("Inorder in ALgoL after j = " + j);
                print2D(g.root);
            }

        }

    }


    void AlgoAL2(Node root, Tree g, int h, int n, List<Integer> leftT, Tree t) throws CloneNotSupportedException {
        boolean flag = false;
        dupe = (Node) root.clone();
        int s = h;
        boolean rotate = false;
        boolean step2 = false;
        if (dupe != null) {
            //int match = Integer.parseInt(Integer.toBinaryString(root.data));

            if ((Math.pow(2, h - 1) <= n) && (n <= Math.pow(2, h - 1) + Math.pow(2, h - 2) - 2)) {
                flag = true;
                step2 = true;
                int k = n - (int) Math.pow(2, h - 1) + 1;

                for (int i = 1; i <= k; i++) {
                    rotate = false;

                    if (dupe.right != null) {

                        if (dupe.rotate == true) {

                            if (dupe.binary == 1 && !leftT.contains(dupe.data)) {
                                dupe.rotate = false;
                                g.root = rotatorLeft(dupe, dupe.parent, g);
                                rotate = true;
                            }
                        } else {

                            if (dupe.binary == 1 && !leftT.contains(dupe.data)) {
                                dupe.rotate = false;
                                g.root = rotatorLeft(dupe, dupe.parent, g);
                                rotate = true;
                            }
                        }
                        if (rotate) {
                            dupe = dupe.parent.right;
                        }else {
                            dupe = dupe.right;
                        }
                    }
                }
                s = h - 1;
            }

            //Node duper = (Node) g.root.clone();
            int x;
            if (step2){
                 x =10;
            }else{
                 x = 1;
            }

            for (int j = s - 1; j >= 1; j--){
                int k = (int) Math.pow(2, j) - 1;
                if (flag) {
                    dupe = (Node) g.root.clone();
                } else {
                    dupe = (Node) root.clone();
                    flag = true;
                }


                for (int i = 1; i < k; i++) {

                    rotate = false;
                    if (dupe != null) {
                        if (dupe.right != null) {


                            if (dupe.rotate == true) {
                                if (dupe.binary == x && !leftT.contains(dupe.data)) {
                                    dupe.rotate = false;
                                    g.root = rotatorLeft(dupe, dupe.parent, g);
                                    rotate = true;
                                } else {
                                    dupe.rotate = false;
                                }
                            } else {
                            if (dupe.binary == x && !leftT.contains(dupe.data)) {
                                dupe.rotate = false;
                                g.root = rotatorLeft(dupe, dupe.parent, g);
                                rotate = true;
                            }
                        }
                        if (rotate) {
                            dupe = dupe.parent.right;
                        } else {
                            dupe = dupe.right;
                        }
                    }
                    }
                }
                x = x*10;

                System.out.println("Inorder in ALgoAL2 after j = " + j);
                print2D(g.root);
                if(identical(g.root.left,t.root.left)){
                    return;
                }
            }

        }

    }

    void AlgoAR2(Node root, Tree g, int h, int n, int r, List<Integer> rightT, Tree t) throws CloneNotSupportedException {
        boolean flag = false;
        dupe = (Node) root.clone();
        int s = h;
        boolean rotate = false;
        if (dupe != null) {
            //int match = Integer.parseInt(Integer.toBinaryString(root.data));

            if ((Math.pow(2, h - 1) + Math.pow(2, h - 2) <= n) && (n <= Math.pow(2, h) - 2)) {
                flag = true;
                int k = n - (int) Math.pow(2, h - 1) + 1;

                for (int i = 1; i <= (((r + 1) / 2) - 1); i++) {
                    if (dupe != null) {
                        if (dupe.right != null) {

                            if (dupe.rotate == true) {

                                if (dupe.binary == 1 && !rightT.contains(dupe.data)) {
                                    dupe.rotate = false;
                                    g.root = rotatorLeft(dupe, dupe.parent, g);
                                }
                            } else {
                                if (dupe.binary == 1 && !rightT.contains(dupe.data)) {
                                    dupe.rotate = false;
                                    g.root = rotatorLeft(dupe, dupe.parent, g);
                                }
                            }
                            if (rotate) {
                                dupe = dupe.parent.right;
                            } else {
                                dupe = dupe.right;
                            }
                        }
                    }
                    s = h - 1;
                }

                //Node duper = (Node) g.root.clone();

                int x = 10;
                for (int j = s - 1; j >= 1; j--) {

                    if (flag) {
                        dupe = (Node) root.clone();
                    } else {
                        dupe = (Node) root.clone();
                        flag = true;
                    }
                    for (int i = 1; i < k - 1; i++) {
                        if (dupe != null) {

                            if (dupe.right != null) {

                                if (dupe.rotate == true) {
                                    if (dupe.binary == x && !rightT.contains(dupe.data)) {
                                        dupe.rotate = false;
                                        g.root = rotatorLeft(dupe, dupe.parent, g);
                                    }
                                } else {
                                    dupe.rotate = false;
                                }
                            }else {
                                if (dupe.binary == x && !rightT.contains(dupe.data)) {
                                    dupe.rotate = false;
                                    g.root = rotatorLeft(dupe, dupe.parent, g);
                                }
                            }
                            if (rotate) {
                                dupe = dupe.parent.right;
                            } else {
                                dupe = dupe.right;
                            }
                        }
                    }
                    x = x * 10;
                    System.out.println("Inorder in ALgoR2 after j = " + j);
                    print2D(g.root);
                    if (identical(g.root.right, t.root.right)) {
                        return;
                    }
                }

            }

        }
    }




    ArrayList<Integer> getLeaves(Node root, ArrayList<Integer> list){

        if (root != null) {
           if(root.left ==null && root.right==null){
               list.add(root.data);
           } else {
               if(root.left == null && root.right !=null){
                   getLeaves(root.right, list);
               } else if(root.left != null && root.right ==null){
                   getLeaves(root.left, list);
               } else {
                   getLeaves(root.left, list);
                   getLeaves(root.right, list);
               }
           }
        }
        return list;
    }

    void AlgoAR(Node root, Tree g, int h, int n, int r) throws CloneNotSupportedException {
    boolean flag = false;
    dupe = (Node) root.clone();
    int s = h;
        if (dupe != null) {
        //int match = Integer.parseInt(Integer.toBinaryString(root.data));

        if ((Math.pow(2, h - 1) + Math.pow(2, h - 2) <= n) && (n <= Math.pow(2, h) - 2)) {
            flag = true;
            int k = n - (int) Math.pow(2, h - 1) + 1;

            for (int i = 1; i <= (((r+1)/2)-1); i++) {

                if (dupe.right != null) {

                    if (dupe.right.rotate == true) {
                        dupe.rotate = false;
                        g.root = rotatorLeft(dupe, dupe.parent, g);
                    }
                } else {
                    dupe.rotate = false;
                    g.root = rotatorLeft(dupe, dupe.parent, g);
                }
                dupe = dupe.parent.right;
            }
            s = h - 1;
        }

        //Node duper = (Node) g.root.clone();


        for (int j = s - 1; j >= 1; j--) {
            int k = (int) Math.pow(2, j) - 1;
            if (flag) {
                dupe = (Node) g.root.clone();
            } else {
                dupe = (Node) root.clone();
                flag = true;
            }
            for (int i = 1; i < k-1; i++) {
                if(dupe != null) {

                    if (dupe.right != null) {

                        if (dupe.right.rotate == true) {
                            dupe.rotate = false;
                            g.root = rotatorLeft(dupe, dupe.parent, g);
                        } else {
                            dupe.rotate = false;
                        }
                    } else {
                        dupe.rotate = false;
                        g.root = rotatorLeft(dupe, dupe.parent, g);
                    }
                    dupe = dupe.parent.right;
                }
            }

            System.out.println("Inorder in ALgoR after j = " + j);
            print2D(g.root);
        }

    }

}



    int leftMost(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    int rightMost(Node root) {
        while (root.right != null) {
            root = root.right;
        }
        return root.data;
    }


    void setIntervals(Node root) {
        if (root != null) {

            root.leftInt = leftMost(root);
            root.rightInt = rightMost(root);
            setIntervals(root.left);
            setIntervals(root.right);
        }
    }


    String getPostOrderString(Node node) {
        String returnString = "";
        if (node != null) {
            returnString += getPostOrderString(node.left);
            returnString += getPostOrderString(node.right);
            returnString += String.valueOf(node.data);
        }
        return returnString;

    }

    HashMap<String, String> getMap(Node root, HashMap<String, String> map) {
        if (root != null) {
            getMap(root.left, map);

            String h = "";
            String x = getPostOrderString(root);
            String key = String.valueOf(root.leftInt) +"-" + String.valueOf(root.rightInt);
            map.put(key,x);

            getMap(root.right, map);
        }
        return map;
    }


    void black_list(Node node, HashMap<String, String> map) {

        if (node != null) {
            black_list(node.left, map);

            String c = String.valueOf(node.leftInt) + "-"+String.valueOf(node.rightInt);
            if (map.containsKey(c)) {
                String current_post = "";
                current_post = getPostOrderString(node);
                if (map.get(c).equals(current_post) && node.left != null && node.right != null) {
                    node.ignore = true;
                    if(node.parent != null) {
                        node.parent.ignore = true;
                    }
                }
            }

            black_list(node.right, map);

        }

    }

    int getLevelUtil(Node node, int data, int level)
    {
        if (node == null)
            return 0;

        if (node.data == data)
            return level;

        int downlevel = getLevelUtil(node.left, data, level + 1);
        if (downlevel != 0)
            return downlevel;

        downlevel = getLevelUtil(node.right, data, level + 1);
        return downlevel;
    }


    int getLevel(Node node, int data)
    {
        return getLevelUtil(node, data, 1);
    }



    HashMap<Integer, String> assignBinary(Node root, int n, int h){
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        for (int x = 1; x <= n; x++)
        {
            int level = Math.abs(getLevel(root, x));
            if (level != 0){

                map.put(x, String.valueOf((int)Math.pow(2,Math.abs(getLevel(root,x)-1-h))));
            }
        }

        return map;
    }

    void printBinary(Node root) {
        if (root != null) {
            printBinary(root.left);
            printBinary(root.right);
            System.out.println("Data : "+ root.data + " Binary : "+ root.binary);

        }
    }

    void disclose_binary(HashMap<Integer,String> map, Node root){
        if (root != null) {
            disclose_binary(map, root.left);
            disclose_binary(map, root.right);
            int g = Integer.parseInt(map.get(root.data));
            root.binary = Integer.parseInt(Integer.toBinaryString(g));

        }
    }

boolean identical (Node sRoot, Node tRoot) {

    String S = getPostOrderString(sRoot);
    String t = getPostOrderString(tRoot);

    return S.equals(t);
}






    static void A1(Tree T, Tree S) throws CloneNotSupportedException {

        int n = 20;
        double x = T.computeRoot(n, T.root.height+1);

        System.out.println("TRoot = "+ x);

        S.root  = S.rootTop(S.root, (int) x, S.root.height, S);


        System.out.println("After root top number of rotations : "+ S.rotations);

        //Step 5

        List<Integer> leftList = S.getLeftForeArm(S.root.left);
        List<Integer> rightList = S.getRightForeArm(S.root.right);


        System.out.println("Left Forearm : "+ leftList);
        System.out.println("Right Forearm : "+ rightList);


        ArrayList<Node> noder = S.intoLeftForearms(S, S.root.left);

        System.out.println("Total Rotations after intoLeftFormarm : "+ S.rotations);


        S.root.left = null;
        S.root.left = noder.get(0);
        noder.remove(0);

        S.inorderAssignLeft(S.root.left,noder);

        //========> Right Forearm Generation :

        ArrayList<Node> noder1 = S.intoRightForearms(S, S.root.right);

        System.out.println("Total Rotations after intoRightFormarm : "+ S.rotations);

        int r = noder1.size();
        S.root.right = null;
        S.root.right = noder1.get(0);
        noder1.remove(0);

        S.inorderAssignRight(S.root.right,noder1);

        S.AdjustHeight(S.root);

        S.AssignParentLeft(S.root.left , S.root);

        //Duplicate Tree
        Tree SS_Right = new Tree();
        Node S_Right = (Node) S.root.clone();
        SS_Right.root = S_Right;

        SS_Right.AdjustHeight(SS_Right.root);

        SS_Right.AssignParentLeft(SS_Right.root.right, SS_Right.root);

        System.out.println("SSRight before algoR :");
        SS_Right.print2D(SS_Right.root);

        int original_node = SS_Right.root.data;

        System.out.println("2d of S before algoAL : ");
        S.print2D(S.root);
        //S.printPaths(S.root);

        S.AlgoAL(S.root.left,  S, T.root.height+1, n);
        System.out.println("Inorder of S Left after AlgoL : "+ S);
        S.inOrder(S.root);
        S.print2D(S.root);
        // }

        System.out.println("Total Rotations after performLeftRot : "+ S.rotations);

        //Right
        System.out.println("2d of S before algoAR : ");
        SS_Right.print2D(SS_Right.root.right);

        SS_Right.AlgoAR(SS_Right.root.right,  SS_Right, T.root.height+1, n, r);
        System.out.println("Inorder of S Right after algoR : "+ S);
        SS_Right.inOrder(SS_Right.root);
        SS_Right.print2D(SS_Right.root);


        System.out.println("Total Rotations after performRightRot : "+ SS_Right.rotations);
        System.out.println("Total Rotations after performRightRot for S: "+ S.rotations);

        Tree fin = new Tree();
        fin.root = new Node(original_node);
        fin.root.left = S.root;
        fin.root.right = SS_Right.root;

        System.out.println("Final Tree : ");
        SS_Right.inOrder(fin.root);
        SS_Right.print2D(fin.root);


        System.out.println("Total Rotations : "+ (S.rotations + SS_Right.rotations));


    }


    HashMap<Integer, Node> assignObject(HashMap<Integer, Node> map, Node root){

        if (root != null) {
            assignObject(map, root.left);

            map.put(root.data, root);

            assignObject(map, root.right);
        }

        return map;

    }

    HashMap<String, Integer> root_interval_mapping(Node root, HashMap<String, Integer> map) {
        if (root != null) {
            root_interval_mapping(root.left, map);

            String key = String.valueOf(root.leftInt) +"-" + String.valueOf(root.rightInt);
            map.put(key,root.data);

            root_interval_mapping(root.right, map);
        }
        return map;
    }




    Tree black_list_a3(Node node, HashMap<Integer, Node> object_mapper, HashMap<String, Integer> interval_mapper, Tree S) throws CloneNotSupportedException {

        if (node != null) {
            black_list_a3(node.left, object_mapper, interval_mapper, S);

            String c = String.valueOf(node.leftInt) + "-"+String.valueOf(node.rightInt);

            String[] arr = c.split("-");
            if(!arr[0].equals(arr[1])) {
                if (interval_mapper.containsKey(c)) {

                    int t_val = interval_mapper.get(c);
                    Node t_node = object_mapper.get(t_val);

                    System.out.println("Rotating for Root Interval " + arr[0] + " and "+ arr[1]);

//                    Node s_root = A1(t_node, node);
//                    System.out.println("Balanced S Root : ");
//                    print2D(s_root);

                    int a = Integer.parseInt(arr[0]);
                    int b = Integer.parseInt(arr[1]);
                    int n_len = b - a +1;

                    Integer[] arr1 = new Integer[n_len];
                    int y = 0;
                    for(int i= a;i<=b;i++){
                        arr1[y] = i;
                        y++;
                    }



                    Tree dump = new Tree();
                    int mid = Integer.parseInt(arr[0]) + Integer.parseInt(arr[1])/2;
                    dump.root = new Node(mid);
                    dump.root = dump.insertLevelOrder(arr1, dump.root, 0);
                    dump.inOrder(dump.root);

                    List<Integer> deep_list = new ArrayList<Integer>();

                    for(int i=0;i<n_len;i++){
                        deep_list.add(arr1[i]);
                    }

                    dump.inOrderInsert(dump.root, deep_list);
                    System.out.println("Now the balanced sub-tree : ");
                    dump.print2D(dump.root);

                    if(dump.root.data > node.parent.data){
                        node.parent.right =dump.root;
                    } else {
                        node.parent.left = dump.root;
                    }

                }
            } else {
                System.out.println("Matched for "+ arr[0]+ " and "+ arr[1]+ " So, not rotating");
            }

            black_list_a3(node.right, object_mapper, interval_mapper, S);

        }
        return S;
    }


    void A2(Tree t2, Tree S, int n,List<Integer> leftT , List<Integer> rightT , HashMap<String, String> TMap1 ) throws CloneNotSupportedException {

        System.out.println("T Inorder : ");
        t2.inOrder(t2.root);
        System.out.println("TMap :"+ TMap1);

        System.out.println("S before Blacklisting : ");
        S.print2D(S.root);

        S.black_list(S.root, TMap1);

        System.out.println("S after Blacklisting : ");
        S.print2D(S.root);
////


//        //===========================================>
//
//
        double x = t2.computeRoot(n, t2.root.height+1);

        System.out.println("TRoot = "+ x);

        S.root  = S.rootTop(S.root, (int) x, S.root.height, S);


        int rootTop = S.rotations;
        System.out.println("After root top number of rotations : "+ rootTop);

        //Step 5

        List<Integer> leftList = S.getLeftForeArm(S.root.left);
        List<Integer> rightList = S.getRightForeArm(S.root.right);


        System.out.println("Left Forearm : "+ leftList +" and size "+ leftList.size());
        System.out.println("Right Forearm : "+ rightList + " and size "+ rightList.size());


        ArrayList<Node> noder = S.intoLeftForearms(S, S.root.left);

        int intoLeftForearm = S.rotations - rootTop;
        System.out.println("Total Rotations after intoLeftFormarm : "+ intoLeftForearm);


        S.root.left = null;
        S.root.left = noder.get(0);
        noder.remove(0);

        S.inorderAssignLeft(S.root.left,noder);

        //========> Right Forearm Generation :

        ArrayList<Node> noder1 = S.intoRightForearms(S, S.root.right);

        int intoRightForearm = S.rotations;
        System.out.println("Total Rotations after intoRightFormarm : "+ intoRightForearm);




        int r = noder1.size();
        S.root.right = null;
        S.root.right = noder1.get(0);
        noder1.remove(0);

        S.inorderAssignRight(S.root.right,noder1);

        S.AdjustHeight(S.root);

        S.AssignParentLeft(S.root.left , S.root);



        S.listRotFalse(S.root, leftT, rightT);
        System.out.println("S after generating left and right forearms :");
        S.print2D(S.root);






//        //Duplicate Tree
        Tree SS_Right = new Tree();
        Node S_Right = (Node) S.root.clone();
        SS_Right.root = S_Right;

        SS_Right.AdjustHeight(SS_Right.root);

        SS_Right.AssignParentLeft(SS_Right.root.right, SS_Right.root);

        System.out.println("SSRight before algoR :");
        SS_Right.print2D(SS_Right.root);

        int original_node = SS_Right.root.data;

        System.out.println("2d of S before rotations : ");
        S.print2D(S.root);
        //S.printPaths(S.root);

        S.AlgoAL2(S.root.left,  S, t2.root.height+1, n, leftT, t2);
        System.out.println("Inorder of S Left after AlgoL : "+ S);
        S.inOrder(S.root);
        S.print2D(S.root);

        S.AssignParentLeft(S.root.left , S.root);
        S.inOrder(S.root);
        S.print2D(S.root);
        // }
//
        SS_Right.root.left = S.root;
        int performLeftRot  = S.rotations - intoLeftForearm;
        System.out.println("Total Rotations after performLeftRot : "+ performLeftRot);

        //Right
        System.out.println("2d of S before algoAR : ");
        SS_Right.print2D(SS_Right.root.right);

        SS_Right.AlgoAR2(SS_Right.root.right,  SS_Right, t2.root.height+1, n, r, rightT, t2);
        System.out.println("Inorder of S Right after algoR : "+ S);
        SS_Right.inOrder(SS_Right.root);
        SS_Right.print2D(SS_Right.root);
//
//
        System.out.println("Total Rotations after performRightRot : "+ SS_Right.rotations);
        System.out.println("Total Rotations after performRightRot for S: "+ S.rotations);

        Tree fin = new Tree();
        fin.root = new Node(original_node);
        fin.root.left = S.root;
        fin.root.right = SS_Right.root.right;

        System.out.println("Final Tree : ");
        SS_Right.inOrder(fin.root);
        SS_Right.print2D(fin.root);


        System.out.println("Total Rotations : "+ (S.rotations + SS_Right.rotations));
        System.out.println("Total Rotations X: "+ (rootTop + 2*(performLeftRot+ intoLeftForearm)));

    }





}



