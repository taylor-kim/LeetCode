class FoodRatings {
    private Map<String, Data> dataMap = new HashMap();
    private Map<String, RatingManager> ratingManangerMap = new HashMap();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for (int i = 0; i < foods.length; i++) {
            Data d = new Data();
            d.food = foods[i];
            d.cuisine = cuisines[i];
            d.rating = ratings[i];

            dataMap.put(d.food, d);

            ratingManangerMap.computeIfAbsent(d.cuisine, k -> new RatingManager()).add(d);
        }
    }
    
    public void changeRating(String food, int newRating) {
        ratingManangerMap.get(dataMap.get(food).cuisine).changeRating(food, newRating);
    }
    
    public String highestRated(String cuisine) {
        return ratingManangerMap.get(cuisine).getHighest();
    }

    class Data {
        String food;
        String cuisine;
        int rating;
    }

    class RatingManager {
        TreeSet<Data> set = new TreeSet<>((a, b) -> {
            return a.rating != b.rating ? b.rating - a.rating : a.food.compareTo(b.food);
        });

        void add(Data d) {
            set.add(d);
        }

        String getHighest() {
            return set.first().food;
        }

        void changeRating(String food, int rating) {
            Data d = dataMap.get(food);
            set.remove(d);

            d.rating = rating;
            
            add(d);
        }
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */