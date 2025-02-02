class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        return official_sort_and_bidir_mapping(nums, limit);
    }

    public int[] official_sort_and_bidir_mapping(int[] nums, int limit) {
        int n = nums.length;
        Integer[][] arr = new Integer[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });

        Map<Integer, LinkedList<Integer[]>> groups = new HashMap();
        Map<Integer, Integer> numToGroup = new HashMap();

        int groupId = 0;

        groups.computeIfAbsent(groupId, k -> new LinkedList()).add(arr[0]);
        numToGroup.put(arr[0][0], groupId);

        for (int i = 1; i < n; i++) {
            if (Math.abs(arr[i - 1][0] - arr[i][0]) > limit) {
                groupId++;
            }
            groups.computeIfAbsent(groupId, k -> new LinkedList()).add(arr[i]);
            numToGroup.put(arr[i][0], groupId);
        }

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            groupId = numToGroup.get(num);
            nums[i] = groups.get(groupId).pop()[0];
        }

        return nums;
    }

    public int[] tryAgain_20250203(int[] nums, int limit) {
        int n = nums.length;
        Integer[][] arr = new Integer[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });

        Map<Integer, List<Integer[]>> groups = new HashMap();

        int groupId = 0;

        groups.computeIfAbsent(groupId, k -> new ArrayList()).add(arr[0]);

        for (int i = 1; i < n; i++) {
            if (Math.abs(arr[i - 1][0] - arr[i][0]) > limit) {
                groupId++;
            }
            groups.computeIfAbsent(groupId, k -> new ArrayList()).add(arr[i]);
        }

        for (List<Integer[]> group : groups.values()) {
            List<Integer> indices = new ArrayList();

            for (Integer[] item : group) {
                indices.add(item[1]);
            }

            Collections.sort(indices);

            for (int i = 0; i < group.size(); i++) {
                nums[indices.get(i)] = group.get(i)[0];
            }
        }

        return nums;
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

        Set<Integer> groups = new HashSet();

        for (int i = 0; i < n; i++) {
            uf.addItem(i, arr[i]);
            groups.add(uf.find(i));
        }

        for (int group : groups) {
            List<Integer[]> groupItems = uf.items[group];

            List<Integer> indices = new ArrayList();

            for (Integer[] item : groupItems) {
                indices.add(item[1]);
            }

            Collections.sort(indices);

            for (int i = 0; i < indices.size(); i++) {
                nums[indices.get(i)] = groupItems.get(i)[0];
            }
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
        }

        public void addItem(int a, Integer[] item) {
            items[find(a)].add(item);
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