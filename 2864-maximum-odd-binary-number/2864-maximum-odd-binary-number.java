class Solution {
    public String maximumOddBinaryNumber(String s) {
        return official_onepass(s);
    }
    
    public String official_onepass(String s) {
        char[] arr = s.toCharArray();
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            if (arr[left] == '1') {
                left++;
                continue;
            }
            
            if (arr[right] == '0') {
                right--;
                continue;
            }
            
            arr[left++] = '1';
            arr[right--] = '0';
        }
        
        arr[left - 1] = '0';
        arr[arr.length - 1] = '1';
        
        return String.valueOf(arr);
    }
    
    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public String mySol(String s) {
        int n = s.length();
        int numberOfOne = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '1') {
                numberOfOne++;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("1".repeat(numberOfOne - 1));
        sb.append("0".repeat(n - numberOfOne));
        sb.append("1");
        
        return sb.toString();
    }
}