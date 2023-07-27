package pgdp.tree;

import java.util.*;

public class Node<T>{

    private List<Node<T>> children;
    private Node<T> parent;
    private T data;
    public Node(T data) {
        this.data = data;
        children = new ArrayList<>();
    }
    public void insert(Node<T> value) {
     if(value != null){
         children.add(value);
         value.parent = this;
     }
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public int size() {
        int size = 1;
        if(children.isEmpty())
            return 1;
        else{
           for(Node<T> element : children){
             size += element.size();
           }
        }
        return size;

    }

    public void remove() {
        if(this.parent == null)
            return;
        parent.children.remove(this);
        if(!this.isLeaf()){
            for (Node<T> child : children) {
                child.parent = this.parent;
            }
        }
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public Node<T> getParent() {
        return parent;
    }

    public T getData() {
        return data;
    }

    public void traversal() {
        System.out.println(data);
        if(this.children.size() == 0)
            return;
        for (Node<T> child : children)
            child.traversal();

    }

        public static void main(String[] args) {

            Node<Integer> node = new Node<>(0);
            Node<Integer> one = new Node<>(1);
            Node<Integer> two = new Node<>(2);
            Node<Integer> three = new Node<>(3);
            Node<Integer> four = new Node<>(4);
            Node<Integer> five = new Node<>(5);
            Node<Integer> six = new Node<>(6);
            Node<Integer> seven = new Node<>(7);
            Node<Integer> eight = new Node<>(8);
            Node<Integer> nine = new Node<>(9);


            node.insert(one);
            node.insert(two);
            one.insert(three);
            three.insert(four);
            three.insert(five);
            three.insert(six);
            six.insert(seven);
            seven.insert(eight);
            seven.insert(nine);

            System.out.println(three.size());
       //     six.remove();
         //  three.traversal();

        }
    }


