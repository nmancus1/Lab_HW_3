public class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
    public T getData() {
        return this.data;
    }
    public void setData(T data) {
        if(data instanceof String) {
            if(((String) this.data).length() >= 3) {
                this.data = data;
            }
        }
        else {
            this.data = data;
        }
    }
    public Node<T> getNext() {
        return next;
    }
    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        @SuppressWarnings("unchecked")
        Node<T> other = (Node<T>) obj;
        return this.data.equals(other.getData());

    }
    @Override
    public String toString() {
        return "Node [data=" + data + ", next=" + getNext() + "]";
    }

}
