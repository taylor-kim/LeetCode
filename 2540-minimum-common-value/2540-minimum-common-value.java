class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        return official_binarySearch(nums1, nums2);
    }
    
    public int official_binarySearch(int[] nums1, int[] nums2) {
        for (int num : nums1) {
            if (search(nums2, num)) {
                return num;
            }
        }
        
        return -1;
    }
    
    private boolean search(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;
        
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        
        return false;
    }
    
    public int mySol(int[] nums1, int[] nums2) {
        int i1 = 0;
        int i2 = 0;
        
        while (i1 < nums1.length && i2 < nums2.length) {
            if (nums1[i1] == nums2[i2]) {
                return nums1[i1];
            } else if (nums1[i1] < nums2[i2]) {
                i1++;
            } else {
                i2++;
            }
        }
        
        return -1;
    }
}