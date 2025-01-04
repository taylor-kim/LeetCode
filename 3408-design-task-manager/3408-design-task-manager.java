class TaskManager {
    private TreeMap<List<Integer>, List<Integer>> map = new TreeMap<>((a, b) -> {
        return !b.get(2).equals(a.get(2)) ? b.get(2) - a.get(2) : b.get(1) - a.get(1);
    });

    private Map<Integer, List<Integer>> taskMap = new HashMap();

    public TaskManager(List<List<Integer>> tasks) {
        for (List<Integer> task : tasks) {
            this.add(task);
        }
    }
    
    public void add(int userId, int taskId, int priority) {
        List<Integer> task = new ArrayList();
        task.add(userId);
        task.add(taskId);
        task.add(priority);
        
        this.add(task);
    }

    private void add(List<Integer> task) {
        map.put(task, task);

        taskMap.put(task.get(1), task);
    }
    
    public void edit(int taskId, int newPriority) {
        List<Integer> removed = taskMap.remove(taskId);
        map.remove(removed);

        removed.set(2, newPriority);

        taskMap.put(taskId, removed);
        map.put(removed, removed);
    }
    
    public void rmv(int taskId) {
        List<Integer> removed = taskMap.remove(taskId);
        map.remove(removed);
    }
    
    public int execTop() {
        if (map.size() == 0) return -1;

        List<Integer> pop = map.firstKey();

        map.remove(pop);
        taskMap.remove(pop.get(1));

        return pop.get(0);
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */