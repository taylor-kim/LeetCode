class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        return tryAgain_20250202(nums, limit);
    }

    public int[] tryAgain_20250202(int[] nums, int limit) {
        int n = nums.length;
        Integer[][] arr = new Integer[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });

        UnionFind uf = new UnionFind(n, arr);

        for (int i = 1; i < n; i++) {
            if (Math.abs(arr[i - 1][0] - arr[i][0]) <= limit) {
                uf.merge(i - 1, i);
            }
        }

        System.out.println(Arrays.toString(uf.parents));

        Set<Integer> groups = new HashSet();
        
        for (int i = 0; i < n; i++) {
            groups.add(uf.find(i));
        }

        for (int group : groups) {
            // StringBuilder sb = new StringBuilder();

            List<Integer> indices = new ArrayList();

            for (Integer[] item : uf.items[group]) {
                // sb.append(Arrays.toString(item)).append(", ");
                indices.add(item[1]);
            }

            Collections.sort(indices);

            int itemIndex = 0;

            for (int index : indices) {
                nums[index] = uf.items[group].get(itemIndex++)[0];
            }

            // System.out.println(sb.toString());
        }

        return nums;
    }

    class UnionFind {
        int[] parents;
        List<Integer[]>[] items;

        public UnionFind(int n, Integer[][] arr) {
            parents = new int[n];
            items = new List[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
                items[i] = new ArrayList();
                items[i].add(arr[i]);
            }
        }

        public int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }

            return parents[a];
        }

        public void merge(int a, int b) {
            a = find(a);
            b = find(b);

            if (a == b) return;

            if (a > b) {
                a += b;
                b = a - b;
                a = a - b;
            }

            parents[b] = a;
            items[a].addAll(items[b]);
            items[b] = null;
        }
    }

    public int[] mySol_fail(int[] nums, int limit) {
        int[] result = nums;

        do {
            nums = result;
            result = sort(nums, limit);
        } while (!Arrays.equals(result, nums));

        return result;
    }

    public int[] sort(int[] nums, int limit) {
        int n = nums.length;
        TreeMap<Integer, Queue<Integer>> treeMap = new TreeMap();
        nums = nums.clone();

        for (int i = 0; i < n; i++) {
            treeMap.computeIfAbsent(nums[i], k -> new PriorityQueue()).add(i);
        }

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            treeMap.get(num).poll();

            if (treeMap.get(num).size() == 0) {
                treeMap.remove(num);
            }

            int row = Math.max(num - limit, 1);
            Integer rowestValue = treeMap.higherKey(row - 1);

            // System.out.println(String.format("nums[%d]:%d, row:%d, rowestValue:%d", i, nums[i], row, rowestValue));

            if (rowestValue == null || rowestValue >= num) continue;

            Queue<Integer> indices = treeMap.get(rowestValue);

            // System.out.println(String.format("nums[%d]:%d, row:%d, rowestValue:%d, indices:%s", i, nums[i], row, rowestValue, indices));

            // System.out.println(treeMap);

            while (indices.size() > 0) {
                int candidate = indices.poll();

                if (i < candidate) {
                    int temp = nums[i];
                    nums[i] = nums[candidate];
                    nums[candidate] = temp;

                    treeMap.computeIfAbsent(nums[candidate], k -> new PriorityQueue()).add(candidate);
                    break;
                }
            }

            if (indices.size() == 0) {
                treeMap.remove(rowestValue);
            }
        }

        return nums;
    }
}