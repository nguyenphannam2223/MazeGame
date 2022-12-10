package Project;

public class LinkedListStack<T> {
    // this class is used as a container of data
    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;

        }
    }

    private int size;
    private Node<T> head;

    public LinkedListStack() {
        size = 0;
        head = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean push(String item) {
        // add a new node at the beginning
        Node<T> node = new Node<T>((T) item);
        if (!isEmpty()) {
            node.next = head;
        }
        head = node;
        size++;
        return true;
    }

    public boolean pop() {
        // remove the first node
        // make sure the stack is not empty
        if (isEmpty()) {
            return false;
        }
        // advance head
        head = head.next;
        size--;
        return true;
    }

    public String peek() {
        // make sure the stack is not empty
        if (isEmpty()) {
            return null;
        }
        return (String) head.data;
    }

    public void display(String message){
        Node current = head;

        if(head == null) {    
            System.out.println("List is empty");    
            return;    
        }    
        System.out.println(message);    
        while(current != null) {    
            //Prints each node by incrementing pointer    
            System.out.print(current.data + " ");    
            current = current.next;    
        }    
        System.out.println("\n");
    }
}
