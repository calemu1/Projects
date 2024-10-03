class PQueueException extends Exception
{
    public PQueueException() { }

    public PQueueException(String msg)
    {
        super(msg);
    }
}
public interface PQueueAPI<E extends Comparable<E>>
{
    boolean isEmpty();
    void insert(E item);
    E remove() throws PQueueException;
    E peek() throws PQueueException;
    int size();
}
