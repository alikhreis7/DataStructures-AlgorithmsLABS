package lab5;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 * LinkedBinarySearchTree for tree traversal lab
 * @param <E>
 */
public class LinkedBinarySearchTree<E extends Comparable> implements Iterable<E>{
    
    private class Node<E>{
        E elem;
        Node<E> parent;
        Node<E> left;
        Node<E> right;
        public Node(E e,Node<E> p, Node<E> l, Node<E> r){
            elem = e;
            parent= p;
            left = l;
            right = r;
        }
    }
    
    private Node<E> root;
    private int size;
    
    /**
     *
     */
    public LinkedBinarySearchTree(){
        root = null;
        size = 0;
    }
    
    /**
     * Adds elem into BST
     * @param elem
     * @return
     */
    public boolean add(E elem){
        if(root == null){
            root = new Node<>(elem, null, null, null);
            size ++;
            return true;
        }
        else{
            root = insert(elem, root, null);
            return true;
        }
    }
    
    /**
     * Recursive BST insertion
     * @param elem
     * @param curr
     * @param from
     * @return
     */
    private Node<E> insert(E elem, Node<E> curr, Node<E> from){
        if(curr == null){
            curr = new Node<>(elem, from, null, null);
            size ++;
            return curr;
        }
        if( elem.compareTo(curr.elem)<0){
            curr.left = insert(elem, curr.left, curr);
        }
        if( elem.compareTo(curr.elem)>0){
            curr.right = insert(elem, curr.right, curr);
        }
        return curr;
    }
    

    
    /*****************************************************************
     *
     * Recursive Printing Functions
     *
     *
     *****************************************************************/
    
    /**
     * Caller method for preorder recursive printing
     */
    public void printPreorderRecursive(){
        System.out.print("Recursive Preorder Printing: ");
        preorderRecursive(root);
    }
    
    /**
     * preorder tree traversal, prints(curr.elem + ", ")
     * @param curr
     */
    private void preorderRecursive(Node<E> curr){
    	if (curr == null)
    		return;
    	System.out.println(curr.elem + ", ");
    	preorderRecursive(curr.left); 
        preorderRecursive(curr.right); 
    }
    
    /**
     * Caller method for inorder recursive printing
     */
    public void printInorderRecursive(){
        System.out.print("Recursive Inorder Printing: ");
        inorderRecursive(root);
    }
    
    /**
     * inorder tree traversal, prints(curr.elem + ", ")
     * @param curr
     */
    private void inorderRecursive(Node<E> curr){
    	if (curr == null)
    		return;
    	inorderRecursive(curr.left);
    	System.out.println(curr.elem + ", ");
        inorderRecursive(curr.right); 
    }
    
    
    /**
     * Caller method for postorder recursive printing
     */
    public void printPostorderRecursive(){
        System.out.print("Recursive Postorder Printing: ");
        postorderRecursive(root);
    }
    
    /**
     * postorder tree traversal, prints(curr.elem + ", ")
     * @param curr
     */
    private void postorderRecursive(Node<E> curr){
    	if (curr == null)
    		return;
    	postorderRecursive(curr.left);
        postorderRecursive(curr.right);
        System.out.println(curr.elem + ", ");
    }

    
    
     /*****************************************************************
     *
     * Iterator Functions
     *
     *
     *****************************************************************/
    
    
    public Iterator iterator(){
        return new InorderIterator();
    }
    
    public Iterator inorderIterator(){
        return new InorderIterator();
    }
    
    public Iterator preorderIterator(){
        return new PreorderIterator();
    }
    

    
     /*****************************************************************
     *
     * Iterators 
     *
     *
     *****************************************************************/ 
    
    
    
    
    /**
     * Tree Iterator using preorder traversal for ordering
     */
    private class PreorderIterator implements Iterator<E>{
        Node<E> next;
        
        public PreorderIterator(){
            next = root;
        }
        
        public boolean hasNext(){
            return next != null;
        }
        
        public E next(){
        	E finall = next.elem;
        	if(next.left != null) {
        		next = next.left;             // We check the left part of the tree 
        		return finall;
        	}
        	
        	else if(next.right != null) {
        		next = next.right;            // We check the right part of the tree
        		return finall;
        	}
        	
        	else {
        		
        		Node<E> parent = next.parent; // node that is parent in the tree
        		Node<E> child = next;         // node that is child in the tree 
        		while(parent != null && (parent.right == child || parent.right == null)) {
        			child = parent;
        			parent = parent.parent;
        		}
        		
        		if(parent == null) {
        			next = next.left;
        		}
        		
        		else {
        			next = parent.right;
        			return finall;
        		}
        		
        		return finall;
        	}	
        }
        
        public void remove(){
            // not implemented
        }
    }
    
    /**
     * Tree Iterator using inorder traversal for ordering
     */
    private class InorderIterator implements Iterator<E>{
        
        Node<E> next;
        
        public InorderIterator(){
        	next = root;
            while(next.left != null) {
            	next = next.left;
            }
        }
        
        public boolean hasNext(){
        	return next != null;
        }
        
        public E next(){
        	
        	E finall = next.elem;
        	if(next.right != null){         // We check the right part of the tree
        	  next = next.right;
        	  while (next.left != null)     // We check the left part of the tree
        	      next = next.left;
        	  return finall;                // return the final node
        	  
        	}
        	
        	else {
        		
        	  while(true){ 
        	     if(next.parent == null) {
        	    	 next = null;
        	    	 return finall;
        	     }
        	     if(next.parent.left == next) {
        	    	 next = next.parent;
        	    	 return finall;
        	     }
        	     next = next.parent;
        	  }
        	}  
        }
        
        public void remove(){
            // not implemented
        }
    }
}
