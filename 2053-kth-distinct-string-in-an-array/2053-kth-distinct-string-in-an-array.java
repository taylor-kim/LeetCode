class Solution {
    public String kthDistinct(String[] arr, int k) {
        return mySol(arr, k);
    }

    public String mySol(String[] arr, int k) {
        Map<String, Integer> map = new LinkedHashMap();

        for (String s : arr) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        for (String key : map.keySet()) {
            if (map.get(key) == 1) {
                if (--k == 0) {
                    return key;
                }
            }
        }

        return "";
    }
}