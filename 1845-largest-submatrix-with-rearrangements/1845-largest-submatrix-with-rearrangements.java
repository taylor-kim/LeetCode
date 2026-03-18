class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;
        
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            Arrays.sort(matrix[i]);
            reverse(matrix[i]);
            for (int j = 0; j < n; j++) {
                maxArea = Math.max(maxArea, (j + 1) * matrix[i][j]);
            }
        }
        
        return maxArea;
    }
    
    private void reverse(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
