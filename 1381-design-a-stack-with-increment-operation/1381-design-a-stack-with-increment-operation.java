class CustomStack {
    int[] arr;
    int[] incArr;
    int index = 0;

    public CustomStack(int maxSize) {
        arr = new int[maxSize];
        incArr = new int[maxSize];
    }
    
    public void push(int x) {
        if (index < arr.length) {
            arr[index++] = x;
        }
    }
    
    public int pop() {
        if (index == 0) return -1;

        int ret = arr[--index] + incArr[index];

        if (index - 1 >= 0) {
            incArr[index - 1] += incArr[index];
        }

        incArr[index] = 0;

        return ret;
    }
    
    public void increment(int k, int val) {
        int i = Math.min(k, index) - 1;
        incArr[i] += val;
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */