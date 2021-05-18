import java.util.EmptyStackException;

public class MyStack<Object>{
    private Node head;
    private Node tail;
    private class Node{
        Object data;
        Node next;
        Node(Object d){ 
            data = d;
            next = null;
        }
    }
    public void push( Object data){
        Node new_el = new Node(data);
        new_el.next = null; 
        if(head== null) {    // In an empty stack both head and tail are initialized to same element.
            head= new_el;    
            tail= new_el;
        }
        else{               // Tail is updated and linked at each addition of element.
            tail.next = new_el;
            tail = new_el;
        }            
    }
    public void pop() throws EmptyStackException{
        if(head == null) throw new EmptyStackException();
        else{
            Node seclast = head;      
            if(seclast.next == null) head = null;    // If one element is present that is removed
            else { 
                while(seclast.next!= tail ){     // Otherwise we reach the second last element
                    seclast = seclast.next;
                }
                seclast.next = null;   // remove the last element
                tail = seclast;     // update the tail
            }
        }
    }
    public Object peek() throws EmptyStackException{
        if(head == null) throw new EmptyStackException();
        else return tail.data;
    }
    public boolean empty(){
        if(head== null) return true;
        else return false;
    }
    public static void main(String[] args){
        
    }
}