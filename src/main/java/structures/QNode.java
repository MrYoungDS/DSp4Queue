package structures;

/**
 * A {@link QNode} is an element of a queue that is implemented
 * using a Linked List structure to allow for unbounded size.
 */
class QNode<T>
{
    public T element;
    public QNode<T> next;
    public QNode<T> previous;

    public QNode(T elem) {
        element = elem;
        next = null;
        previous = null;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T elem) {
        element = elem;
    }

    public QNode<T> getNext() {
        return next;
    }

    public QNode<T> getPrevious() {
        return previous;
    }

    public void setNext(QNode<T> nextNode) {
        next = nextNode;
    }

    public void setPrevious(QNode<T> prevNode) {
        previous = prevNode;
    }
}
