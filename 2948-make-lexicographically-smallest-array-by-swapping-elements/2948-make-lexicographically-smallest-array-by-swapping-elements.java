class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        return mySol2_20260829(nums, limit);
    }

    public int[] mySol2_20260829(int[] nums, int limit) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        
        List<List<Integer>> groups = new ArrayList();

        int prev = sorted[0];
        List<Integer> group = new LinkedList();
        group.add(prev);
        
        for (int i = 1; i < n; i++) {
            int num = sorted[i];

            if (prev + limit >= num) {
                group.add(num);
            } else {
                groups.add(group);
                group = new ArrayList();
                group.add(num);
            }

            prev = num;
        }

        groups.add(group);

        int[] ans = new int[n];
        int index = 0;

        for (int num : nums) {
            int lo = 0;
            int hi = groups.size();
            
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                group = groups.get(mid);

                int min = group.get(0);
                int max = group.get(group.size() - 1);

                if (max < num) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            // System.out.println("groups:%s, lo:%d, num:%d".formatted(groups, lo, num));

            group = groups.get(lo);

            int smaller = group.remove(0);

            ans[index++] = smaller;

            if (group.size() == 0) {
                groups.remove(lo);
            }
        }

        return ans;
    }

    public int[] mySol_20260829_fail(int[] nums, int limit) {
        int n = nums.length;

        TreeMap<Integer, Integer> map = new TreeMap();

        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int[] ans = new int[n];
        int index = 0;

        for (int i = 0; i < n; i++) {
            int num = map.containsKey(nums[i]) ? nums[i] : map.higherKey(nums[i]);

            int smallest = num - limit;

            // Integer target = map.ceilingKey(smallest);
            Integer target = findSmallest(map, num, limit);

            int result = target != null ? target : num;

            ans[index++] = result;

            // System.out.println(result + ", " + map);

            map.put(result, map.get(result) - 1);

            if (map.get(result) == 0) {
                map.remove(result);
            }
        }

        return ans;
    }

    private Integer findSmallest(TreeMap<Integer, Integer> map, int num, int limit) {
        Integer found = null;
        Integer ans = num;

        while (true) {
            found = map.ceilingKey(ans - limit);

            if (found != null && found < ans) {
                ans = found;
            } else {
                break;
            }
        }

        return ans == num ? null : ans;
    }











    public int[] official_sort_and_bidir_mapping(int[] nums, int limit) {
        int n = nums.length;
        int[] arr = nums.clone();

        Arrays.sort(arr);

        Map<Integer, LinkedList<Integer>> groups = new HashMap();
        Map<Integer, Integer> numToGroup = new HashMap();

        int groupId = 0;

        groups.computeIfAbsent(groupId, k -> new LinkedList()).add(arr[0]);
        numToGroup.put(arr[0], groupId);

        for (int i = 1; i < n; i++) {
            if (Math.abs(arr[i - 1] - arr[i]) > limit) {
                groupId++;
            }
            groups.computeIfAbsent(groupId, k -> new LinkedList()).add(arr[i]);
            numToGroup.put(arr[i], groupId);
        }

        for (List<Integer> group : groups.values()) {
            System.out.println(group);
        }

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            groupId = numToGroup.get(num);
            nums[i] = groups.get(groupId).pop();
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