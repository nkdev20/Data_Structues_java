package GenericTrees;

import java.util.*;

public class GenericTree {

    public static class Node{
        int data;
        ArrayList<Node> children;


        public Node(){
            this.data = 0;
            this.children = new ArrayList<>();
        }


        public Node(int data){
            this.data = data;
            this.children = new ArrayList<>();
        }
    }




    public static Node construct(int arr[]){
        Stack<Node> st = new Stack<>();
        Node root = new Node();

        for(int i = 0; i< arr.length; i++){
            Node nn = new Node(arr[i]);
            if(st.size() == 0){
                st.push(nn);
                root = nn;
            } else {
                if(arr[i] != -1){
                    st.peek().children.add(nn);
                    st.push(nn);
                } else {
                    st.pop();
                }
            }
        }

        return  root;
    }


    public static void display(Node node){
        String str = node.data+" -> ";
        for(Node child: node.children){
            str+=child.data+" ";
        }
        System.out.println(str);

        for(Node child: node.children){
            display(child);
        }

    }

    public static int size(Node node){
        int size = 0;
        for(Node child: node.children){
            size += size(child);
        }

        return size+1;
    }


    public static int max(Node node){
        int max = Integer.MIN_VALUE;

        for(Node child: node.children){
            int nn = max(child);
            max = Math.max(max, nn);
        }

        return Math.max(max, node.data);
    }

    public static int height(Node node){
        int height = -1;

        for(Node child: node.children){
            int nn = height(child);
            height = Math.max(height, nn);
        }

        return height+1;
    }


    public static void levelOrder(Node node)
    {
        Queue<Node> qu = new ArrayDeque<>();
        qu.add(node);
        while(qu.size() > 0){
            Node nn = qu.remove();
            System.out.print(nn.data+" ");
            for(Node child: nn.children){
                qu.add(child);
            }

        }
    }


    public static void levelOrderLinewise(Node node)
    {
        Queue<Node> q1 = new ArrayDeque<>();
        Queue<Node> q2 = new ArrayDeque<>();
        q1.add(node);
        while(q1.size() != 0){
            Node nn = q1.remove();
            System.out.print(nn.data+" ");
            for(Node child: nn.children){
                q2.add(child);
            }
            if(q1.size() == 0){
                System.out.println("");
                Queue<Node> temp = q1;
                q1 = q2;
                q2 = temp;
            }
        }
    }


    public static void levelOrderLinewise2(Node node)
    {
        Queue<Node> q1 = new LinkedList<>();
        q1.add(node);
        q1.add(null);
        while(q1.size() != 0){
            Node nn = q1.remove();
            if(nn == null && q1.size() != 0){
                System.out.println("");
                q1.add(null);
            } else {
                System.out.print(nn.data+" ");
                for(Node child: nn.children){
                    q1.add(child);
                }
            }

        }
    }


    public static void levelOrderLinewise3(Node node)
    {
        Queue<Node> q1 = new LinkedList<>();
        q1.add(node);
        int size = 1;
        while(size > 0){
            Node nn = q1.remove();
            size--;
            System.out.print(nn.data+" ");

            for(Node child: nn.children) {

                q1.add(child);
            }
            if(size == 0){
                System.out.println("");
                size = q1.size();
            }

        }
    }

    public static void mirror(Node node)
    {
        for(Node child: node.children){
            mirror(child);
        }

        int i = 0;
        int j = node.children.size()-1;

        while(i < j){
            Node temp = node.children.get(j);
            node.children.set(j, node.children.get(i));
            node.children.set(i, temp);
            i++;
            j--;
        }

    }


    public static void removeNode(Node node)
    {
        ArrayList<Node> newChildren = new ArrayList<>();

        for(Node child: node.children)
        {
            if(child.children.size()!= 0){
                newChildren.add(child);
            }
        }
        node.children = newChildren;

        for(Node child: node.children){
            removeNode(child);
        }

    }


    public static Node linearize2(Node node)
    {
        if(node.children.size() == 0){
            return node;
        }


        Node tail = linearize2(node.children.get(node.children.size()-1));


        while(node.children.size()>1){
            Node last = node.children.remove(node.children.size()-1);
            Node slast = linearize2(node.children.get(node.children.size()-1));
            slast.children.add(last);
        }

        return tail;
    }


    public static boolean find(Node node, int data)
    {
        if(node.data == data) return true;

        boolean res = false;
        for(Node child: node.children){
            res = find(child, data);
            if(res == true) return true;
        }

        return res;
    }



    public static void main(String[] args) {
        int arr[] = {10, 20,   50, -1, 60, -1, -1, 30, 70, -1,80, 110,-1, 120, -1, -1, 90, -1,-1,
                40, 100, -1, -1};

        Node root = construct(arr);

        display(root);

        System.out.println(size(root));

        System.out.println(max(root));

        System.out.println(height(root));

//        levelOrder(root);
//        levelOrderLinewise(root);

//        levelOrderLinewise2(root);



//        mirror(root);
//        removeNode(root);
//        linearize2(root);

//        levelOrderLinewise3(root);
        System.out.println(find(root, 101));
    }
}
