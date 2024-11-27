package com.company;
import java.util.*;
import java.math.*;


public class Main {

    public static void main(String args[]) throws CloneNotSupportedException {
        Tree t2 = new Tree();
        Tree S = new Tree();
        Tree S1 = new Tree();
        Tree S2 = new Tree();
        Tree S3 = new Tree();

        int n = 20;
        Integer arr[] = new Integer[n];

        for(int i=1;i<=n;i++){
            arr[i-1] = i;
        }

        int mid = arr[arr.length/2];
        t2.root = new Node(mid);
        t2.root = t2.insertLevelOrder(arr, t2.root, 0);
        t2.inOrder(t2.root);


        S.root = new Node(mid);
        S1.root = new Node(mid);
        S2.root = new Node(mid);
        S3.root = new Node(mid);



        S.root = S.insertLevelOrder(arr, S.root, 0);
        S1.root = S.insertLevelOrder(arr, S1.root, 0);
        S2.root = S.insertLevelOrder(arr, S2.root, 0);
        S3.root = S.insertLevelOrder(arr, S3.root, 0);


        S.inOrder(S.root);
        S1.inOrder(S1.root);
        S2.inOrder(S2.root);
        S3.inOrder(S3.root);

        List<Integer> list = new ArrayList<Integer>();
        List<Integer> s_list = new ArrayList<Integer>();
        List<Integer> s_list1 = new ArrayList<Integer>();
        List<Integer> s_list2 = new ArrayList<Integer>();
        List<Integer> s_list3 = new ArrayList<Integer>();

        List<Integer> rot_list = new ArrayList<Integer>();

        for(int i=0;i<n;i++){
            list.add(arr[i]);
            s_list.add(arr[i]);
            s_list1.add(arr[i]);
            s_list2.add(arr[i]);
            s_list3.add(arr[i]);
            rot_list.add(arr[i]);

        }

        t2.inOrderInsert(t2.root, list);

        t2.assignHeight(t2.root);


        System.out.println("Binary Representation of the Roots of T :");

        HashMap<Integer, String> map = t2.assignBinary(t2.root, n, t2.root.height);
        System.out.println("Map : "+ map);
        t2.disclose_binary(map, t2.root);
        t2.printBinary(t2.root);


        //===== Creation of S  ==================>
        S.inOrderInsert(S.root, s_list);
        t2.assignHeight(S.root);


        S1.inOrderInsert(S1.root, s_list1);
        t2.assignHeight(S1.root);

        S2.inOrderInsert(S2.root, s_list2);
        t2.assignHeight(S2.root);

        S3.inOrderInsert(S3.root, s_list3);
        t2.assignHeight(S3.root);

        t2.setIntervals(t2.root);


        HashMap<String, String> dummy_map1 = new HashMap<String, String>();

        HashMap<String, String> TMap = S.getMap(t2.root, dummy_map1);

        HashMap<Integer, Node> TObjectMap = new HashMap<Integer, Node>();
        HashMap<Integer, Node> TObjectMapper = t2.assignObject(TObjectMap, t2.root);

        System.out.println("After Assigning the Objects for T to Map : ");
        System.out.println("TObjectMapper : "+ TObjectMapper);




        System.out.println("T Tree ;");
        t2.print2D(t2.root);
        //t2.printPaths(t2.root);

        List<Integer> leftT = t2.getLeftForeArm(t2.root.left);
        List<Integer> rightT = t2.getRightForeArm(t2.root.right);

        System.out.println("Left Forearm for T "+leftT);
        System.out.println("Right Forearm for T "+rightT);

      ArrayList<Integer> leaf_list = new ArrayList<Integer>();
        ArrayList<Integer> TLeaves = t2.getLeaves(t2.root, leaf_list);
        System.out.println("T Leaves "+ TLeaves);


        //===== Adding rotations to S=========>
        List<Integer> dummy_list = new ArrayList<Integer>();
        Random random = new Random();

        while(dummy_list.size() < 1){
            int rand = rot_list.get(random.nextInt(n));

            if(!dummy_list.contains(rand)){
                System.out.println("Rand : "+ rand);
                S.preOrder(S.root, rand, false);
                S1.preOrder(S1.root, rand, false);
                S2.preOrder(S2.root, rand, false);
                S3.preOrder(S3.root, rand, false);
            }
            dummy_list.add(rand);
        }

        int rem = rot_list.size()/ 2;

        List<Integer> dummy_list1 = new ArrayList<Integer>();

        while(dummy_list1.size() < rem){
            int rand = rot_list.get(random.nextInt(n));

            if(!dummy_list.contains(rand) && !dummy_list1.contains(rand)){
                S.preOrder(S.root, rand, false);
                S1.preOrder(S1.root, rand, false);
                S2.preOrder(S2.root, rand, false);
                S3.preOrder(S3.root, rand, false);

            }
            dummy_list1.add(rand);
        }

        System.out.println("S Before Random Rotations : ");
        S.print2D(S.root);
        //================Applying Rotations =========>


        System.out.println("Binary Representation of the Roots of S :");

        HashMap<Integer, String> map1 = S.assignBinary(S.root, n, S.root.height);
        System.out.println("Map : "+ map);
        S.disclose_binary(map, S.root);
        S.printBinary(S.root);



        S.root = S.rotateFunction(S.root);
//
//
//        S1.root = S1.rotateFunction(S1.root);
        S2.root = S2.rotateFunction(S2.root);
//        S3.root = S3.rotateFunction(S3.root);

        S.setIntervals(S.root);

        HashMap<String, String> dummy_map = new HashMap<String, String>();

        HashMap<String, String> TMap1 = S.getMap(t2.root, dummy_map);


        HashMap<String, Integer> Int_Map = new HashMap<String, Integer>();
        HashMap<String, Integer> root_mapperz = S.root_interval_mapping(t2.root, Int_Map);



        //==================================================A1==========================================================>
        System.out.println("===========================A1=========================================>");
        System.out.println("===========================A1=========================================>");


        System.out.println("A1 : ");

        S2.A1(t2, S2);


        //================================================A2==========================================================>

        System.out.println("===========================A2=========================================>");
        System.out.println("===========================A2=========================================>");


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


        System.out.println("For 20 :"+ S.calc1(20, 5));



        //================================================A3==========================================================>

        System.out.println("===========================A3=========================================>");
        System.out.println("===========================A3=========================================>");


        System.out.println("A3 : ");

                System.out.println("Before S3 : ");
        S3.print2D(S3.root);

        Tree V = t2.black_list_a3(S3.root,TObjectMapper ,root_mapperz, S3);

        System.out.println("After A3, balances trees :=");

         V.print2D(V.root);

      V.A2(t2, S3, n, leftT, rightT,TMap1);

    }
}