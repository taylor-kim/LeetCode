class MyCircularDeque {
    List<Integer> list = new ArrayList();
    int k;

    public MyCircularDeque(int k) {
        this.k = k;
    }
    
    public boolean insertFront(int value) {
        if (isFull()) return false;

        list.add(0, value);

        return true;
    }
    
    public boolean insertLast(int value) {
        if (isFull()) return false;

        list.add(value);

        return true;
    }
    
    public boolean deleteFront() {
        if (isEmpty()) return false;

        list.remove(0);

        return true;
    }
    
    public boolean deleteLast() {
        if (isEmpty()) return false;

        list.remove(list.size() - 1);

        return true;
    }
    
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }

        return list.get(0);
    }
    
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }

        return list.get(list.size() - 1);
    }
    
    public boolean isEmpty() {
        return list.size() == 0;
    }
    
    public boolean isFull() {
        return list.size() == k;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */