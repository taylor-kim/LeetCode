class FoodRatings {

    private Map<String, TreeSet<Food>> foodSet;
    private Map<String, Integer> foodRate;
    private Map<String, String> foodCuisine;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodSet = new HashMap();
        foodRate = new HashMap();
        foodCuisine = new HashMap();

        for (int i = 0; i < foods.length; i++) {
            Food food = new Food(foods[i], ratings[i]);
            foodSet.computeIfAbsent(cuisines[i], k -> new TreeSet()).add(food);
            foodRate.put(food.name, food.rate);
            foodCuisine.put(food.name, cuisines[i]);
        }
    }
    
    public void changeRating(String food, int newRating) {
        TreeSet<Food> treeSet = foodSet.get(foodCuisine.get(food));

        Food old = new Food(food, foodRate.get(food));
        treeSet.remove(old);

        foodRate.put(food, newRating);
        Food updated = new Food(food, newRating);

        treeSet.add(updated);
    }
    
    public String highestRated(String cuisine) {
        return foodSet.get(cuisine).first().name;
    }

    class Food implements Comparable<Food> {
        private int rate;
        private String name;

        public Food(String name, int rate) {
            this.name = name;
            this.rate = rate;
        }

        public int compareTo(Food food) {
            if (this.rate != food.rate) {
                return food.rate - this.rate;
            } else {
                return this.name.compareTo(food.name);
            }
        }
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */