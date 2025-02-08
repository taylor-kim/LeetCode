class NumberContainers {
    private Map<Integer, Integer> indexToNumber;
    private Map<Integer, TreeSet<Integer>> numberToIndices;

    public NumberContainers() {
        indexToNumber = new HashMap();
        numberToIndices = new HashMap();
    }
    
    public void change(int index, int number) {
        if (indexToNumber.containsKey(index)) {
            int prevNumber = indexToNumber.get(index);
            numberToIndices.get(prevNumber).remove(index);

            if (numberToIndices.get(prevNumber).size() == 0) {
                numberToIndices.remove(prevNumber);
            }
        }

        indexToNumber.put(index, number);
        numberToIndices.computeIfAbsent(number, k -> new TreeSet()).add(index);
    }
    
    public int find(int number) {
        if (numberToIndices.containsKey(number)) {
            return numberToIndices.get(number).first();
        }

        return -1;
    }
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers obj = new NumberContainers();
 * obj.change(index,number);
 * int param_2 = obj.find(number);
 */