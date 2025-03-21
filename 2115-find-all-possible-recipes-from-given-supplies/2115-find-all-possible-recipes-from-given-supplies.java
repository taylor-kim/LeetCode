class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        return mySol(recipes, ingredients, supplies);
    }

    public List<String> mySol(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, Integer> indegree = new HashMap();
        Queue<String> queue = new LinkedList();
        Map<String, List<String>> graph = new HashMap();
        Set<String> recipeSet = new HashSet();

        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];

            for (String ingredient : ingredients.get(i)) {
                graph.computeIfAbsent(ingredient, k -> new ArrayList()).add(recipe);
                indegree.put(recipe, indegree.getOrDefault(recipe, 0) + 1);
            }

            recipeSet.add(recipe);
        }

        for (String supply : supplies) {
            queue.add(supply);
        }

        List<String> ans = new ArrayList();

        while (!queue.isEmpty()) {
            String node = queue.poll();

            if (recipeSet.contains(node)) {
                ans.add(node);
            }

            for (String next : graph.getOrDefault(node, new ArrayList<>())) {
                indegree.put(next, indegree.getOrDefault(next, 0) - 1);

                if (indegree.get(next) == 0) {
                    queue.add(next);
                }
            }
        }

        return ans;
    }
}