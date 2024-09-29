class AllOne {
    MyList head;
    MyList tail;
    Map<String, MyList> map;

    public AllOne() {
        head = new MyList(0);
        tail = new MyList(0);

        head.next = tail;
        tail.prev = head;

        map = new HashMap();
    }
    
    public void inc(String key) {
        if (map.containsKey(key)) {
            MyList node = map.get(key);
            int freq = node.freq;
            node.keys.remove(key);

            MyList nextNode = node.next;

            if (nextNode == tail || nextNode.freq != freq + 1) {
                MyList newNode = new MyList(freq + 1);

                newNode.prev = node;
                node.next = newNode;
                newNode.next = nextNode;
                nextNode.prev = newNode;

                newNode.keys.add(key);
                map.put(key, newNode);
            } else {
                nextNode.keys.add(key);
                map.put(key, nextNode);
            }

            removeNodeIfNeed(node);
        } else {
            MyList node = head.next;

            if (node == tail || node.freq != 1) {
                MyList newNode = new MyList(1);
                newNode.keys.add(key);

                newNode.prev = node.prev;
                node.prev = newNode;

                newNode.prev.next = newNode;
                newNode.next = node;

                map.put(key, newNode);
            } else {
                node.keys.add(key);
                map.put(key, node);
            }
        }
    }
    
    public void dec(String key) {
        if (!map.containsKey(key)) return;

        MyList node = map.get(key);
        node.keys.remove(key);
        int freq = node.freq;

        if (freq == 1) {
            map.remove(key);
        } else {
            MyList prevNode = node.prev;

            if (prevNode == head || prevNode.freq != freq - 1) {
                MyList newNode = new MyList(freq - 1);

                newNode.prev = prevNode;
                prevNode.next = newNode;
                newNode.next = node;
                node.prev = newNode;

                newNode.keys.add(key);
                map.put(key, newNode);
            } else {
                prevNode.keys.add(key);
                map.put(key, prevNode);
            }
        }

        removeNodeIfNeed(node);
    }
    
    public String getMaxKey() {
        if (tail.prev == head) return "";

        return tail.prev.keys.iterator().next();
    }
    
    public String getMinKey() {
        if (head.next == tail) return "";

        return head.next.keys.iterator().next();
    }

    private void removeNodeIfNeed(MyList node) {
        if (node.keys.size() == 0) {
            MyList prev = node.prev;
            MyList next = node.next;

            prev.next = next;
            next.prev = prev;
        }
    }

    class MyList {
        int freq = 0;
        MyList prev;
        MyList next;
        Set<String> keys = new HashSet();

        public MyList(int freq) {
            this.freq = freq;
        }
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */