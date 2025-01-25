class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        return official_sort_and_grouping(nums, limit);
    }

    public int[] official_sort_and_grouping(int[] nums, int limit) {
        int[] numsSorted = new int[nums.length];
        for (int i = 0; i < nums.length; i++) numsSorted[i] = nums[i];
        Arrays.sort(numsSorted);

        int currGroup = 0;
        HashMap<Integer, Integer> numToGroup = new HashMap<>();
        numToGroup.put(numsSorted[0], currGroup);

        HashMap<Integer, LinkedList<Integer>> groupToList = new HashMap<>();
        groupToList.put(
            currGroup,
            new LinkedList<Integer>(Arrays.asList(numsSorted[0]))
        );

        for (int i = 1; i < nums.length; i++) {
            if (Math.abs(numsSorted[i] - numsSorted[i - 1]) > limit) {
                // new group
                currGroup++;
            }

            // assign current element to group
            numToGroup.put(numsSorted[i], currGroup);

            // add element to sorted group list
            if (!groupToList.containsKey(currGroup)) {
                groupToList.put(currGroup, new LinkedList<Integer>());
            }
            groupToList.get(currGroup).add(numsSorted[i]);
        }

        // iterate through input and overwrite each element with the next element in its corresponding group
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int group = numToGroup.get(num);
            nums[i] = groupToList.get(group).pop();
        }

        return nums;
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