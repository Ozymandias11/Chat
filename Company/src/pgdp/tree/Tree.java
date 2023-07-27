package pgdp.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree<T> {

    private Node<T> root;

    public Tree (T data) {
        root = new Node(data);
    }


    private Node<T> dfs(Node<T> root, T value){
        if(root.getData().equals(value))
            return root;
        int size  = root.getChildren().size();
        for (int i = 0; i < size; i++) {
            if(dfs(root.getChildren().get(i), value) != null)
                return dfs(root.getChildren().get(i), value);
        }
        return null;
    }


    public void insert(T value, T parent) {
        if(dfs(this.root, value) == null){
            Node<T> nodeToAdd = dfs(this.root, parent);
            if(nodeToAdd != null){
                nodeToAdd.insert(new Node<>(value));
            }

        }
    }


    public void remove(T value) {
        if(value == this.root.getData())
            return;
        Node<T> node = dfs(this.root, value);
       if(node != null)
           node.remove();

    }
    private Node<T> getLCA(T a, T b){
        Node<T> node =  dfs(this.root, a);
        Node<T> node1 =  dfs(this.root, b);


        if(node != null  && node1 != null){

            int distanceA = findDistance(this.root, node); distance = 0; // If I won't make it 0 B's distance will be distance of A + dinatance of B (Incorrect)
            int distanceB = findDistance(this.root, node1); distance = 0;

            if(distanceA > distanceB){
                for (int i = 0; i < distanceA - distanceB; i++) {
                    node = node.getParent();

                }
            }
            else if(distanceB > distanceA){
                for (int i = 0; i < distanceB - distanceA; i++) {
                    node1 = node1.getParent();
                }
            }
             while (!node.getData().equals(node1.getData())){
                node = node.getParent();
                node1 = node1.getParent();
            }
        }
        return node;
    }





    public T LCA(T a, T b) {
      if(root.getData() == a)
          return a;
      if(root.getData() == b)
          return b;

      return getLCA(a, b).getData();



    }
    private static int distance = 0;

    public int findDistance(Node<T> lca, Node<T> data){
        if(data.getData().equals(lca.getData()) || data.getParent() == null){
            return distance;
        }
        else{
            distance++;
            return findDistance(lca, data.getParent());
        }
    }
    public int distanceFinder(Node<T> lca, T data){
        Node<T> datatToPass = dfs(this.root, data);
        assert datatToPass != null;
        return findDistance(lca, datatToPass);
    }

    public int distanceBetweenNodes(T a, T b) {
        Node<T> node = dfs(this.root, a);
        Node<T> node1 = dfs(this.root, b);
        T lca = LCA(a, b);
        Node<T> lcaTemp = dfs(this.root, lca);
        if(node != null &&  node1 != null){
            int x = distanceFinder(lcaTemp, a);
            distance = 0;
            int y = distanceFinder(lcaTemp, b);
            distance = 0;
            return x + y;
        }

        return 0;
    }

    public Node<T> getRoot() {
        return this.root;
    }

    public boolean containsKey(T key) {
      return dfs(this.root, key) != null;
    }



    public void traversal() {
        root.traversal();

    }





}

