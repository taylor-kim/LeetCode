class MyCircularDeque {
    BiDirList dummyHead;
    BiDirList dummyTail;
    int size;
    int k;

    public MyCircularDeque(int k) {
        this.k = k;
        dummyHead = new BiDirList(0);
        dummyTail = new BiDirList(0);

        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }
    
    public boolean insertFront(int value) {
        if (isFull()) return false;

        dummyHead.setNext(new BiDirList(value));
        size++;

        return true;
    }
    
    public boolean insertLast(int value) {
        if (isFull()) return false;

        dummyTail.setPrev(new BiDirList(value));
        size++;

        return true;
    }
    
    public boolean deleteFront() {
        if (isEmpty()) return false;

        BiDirList target = dummyHead.next;

        dummyHead.next = target.next;
        target.next.prev = dummyHead;

        size--;

        return true;
    }
    
    public boolean deleteLast() {
        if (isEmpty()) return false;

        BiDirList target = dummyTail.prev;

        dummyTail.prev = target.prev;
        target.prev.next = dummyTail;

        size--;

        return true;
    }
    
    public int getFront() {
        if (isEmpty()) return -1;

        return dummyHead.next.value;
    }
    
    public int getRear() {
        if (isEmpty()) return -1;

        return dummyTail.prev.value;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == k;
    }

    class BiDirList {
        private BiDirList prev;
        private BiDirList next;
        private int value;

        public BiDirList(int value) {
            this.value = value;
        }

        public void setNext(BiDirList node) {
            node.next = this.next;
            node.prev = this; 
            this.next = node;
        }

        public void setPrev(BiDirList node) {
            node.prev = this.prev;
            node.next = this;
            this.prev = node;
        }
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