class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        return othersMap(nums1, nums2);
    }

    public int[] othersMap(int[] nums1, int[] nums2) {
        Map<Integer, Integer> freq = new HashMap();

        for (int num : nums1) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        List<Integer> list = new ArrayList();

        for (int num : nums2) {
            if (freq.getOrDefault(num, 0) > 0) {
                freq.put(num, freq.get(num) - 1);
                list.add(num);
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] mySol(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> list = new ArrayList();

        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}