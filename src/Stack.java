/**
 * Nick Mancuso
 * CIS211
 * 10.2.18
 * <p>
 * This data structure represents a stack
 *
 * @param <T>
 */

public class Stack<T> implements StackInterface {

    private Node<T> head;

    public Stack(Node<T> head) {
        super();
        this.head = head;
    }

    public Stack() {
        super();
        this.head = null;
    }

    /**
     * Adds a new entry to the top of this stack.
     *
     * @param newEntry An object to be added to the stack.
     */
    @Override
    public void push(Object newEntry) {

        Node newNode = new Node (newEntry);
        newNode.setNext(head);
        head = newNode;

    }

    /**
     * Removes and returns this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty before the operation.
     */
    @Override
    public Object pop() {

        Node nodeToPop = head;

        if (head != null) {
            head = head.getNext();
            nodeToPop.setNext(null);
            return nodeToPop.getData();
        }

        return null;
    }

    /**
     * Retrieves this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    @Override
    public Object peek() {

        if (head != null) {
            return head.getData();
        }
        return null;
    }

    /**
     * Detects whether this stack is empty.
     *
     * @return True if the stack is empty.
     */
    @Override
    public boolean isEmpty() {

        return head == null;

    }

    /**
     * Removes all entries from this stack.
     */
    @Override
    public void clear() {

        while (!this.isEmpty()) {
            this.pop();
        }

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        Node current = head;

        while (current != null){
            sb.append(head + ", ");
            current = current.getNext();
        }

        return sb.toString();
    }
}
