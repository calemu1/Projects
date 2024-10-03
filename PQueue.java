import java.util.Comparator;
import java.util.ArrayList;

public class PQueue<E extends Comparable<E>> implements PQueueAPI<E> {
    private ArrayList<E> tree;
    private Comparator<? super E> cmp;

    public PQueue() {
        tree = new ArrayList<>();
        cmp = Comparator.naturalOrder();
    }

    public PQueue(Comparator<? super E> fn) {
        tree = new ArrayList<>();
        cmp = fn;
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void insert(E obj) {
        tree.add(obj);
        int place = tree.size() - 1;
        int parent = (place - 1) / 2;

        // Bubble up to restore heap property
        while (place > 0 && cmp.compare(tree.get(place), tree.get(parent)) < 0) {
            swap(place, parent);
            place = parent;
            parent = (place - 1) / 2;
        }
    }

    @Override
    public E remove() throws PQueueException {
        if (isEmpty()) {
            throw new PQueueException("Priority queue is empty");
        }

        E root = tree.get(0);
        E last = tree.remove(tree.size() - 1);

        if (!isEmpty()) {
            tree.set(0, last);
            rebuild(0);
        }

        return root;
    }

    @Override
    public E peek() throws PQueueException {
        if (isEmpty()) {
            throw new PQueueException("Priority queue is empty");
        }
        return tree.get(0);
    }

    @Override
    public int size() {
        return tree.size();
    }

    private void swap(int place, int parent) {
        E temp = tree.get(place);
        tree.set(place, tree.get(parent));
        tree.set(parent, temp);
    }
    private void rebuild(int root) {
        int left = 2 * root + 1;
        int right = 2 * root + 2;
        int smallest = root;

        if (left < tree.size() && cmp.compare(tree.get(left), tree.get(smallest)) < 0) {
            smallest = left;
        }

        if (right < tree.size() && cmp.compare(tree.get(right), tree.get(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != root) {
            swap(root, smallest);
            rebuild(smallest);
        }
    }
}

