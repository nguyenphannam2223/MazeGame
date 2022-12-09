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

    public boolean push(Coordinates item) {
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

    public Coordinates peek() {
        // make sure the stack is not empty
        if (isEmpty()) {
            return null;
        }
        return (Coordinates) head.data;
    }

    String toString() {
        String result = "";
        Node<T> current = head;
        while(current.getNext() != null){
            result += current.getData();
            if(current.getNext() != null){
                result += ", ";
            }
            current = current.getNext();
        }
        return "List: " + result;
    }
}