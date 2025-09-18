class TaskManager {
    Map<Integer, Data> taskData = new HashMap();

    TreeSet<Data> set = new TreeSet<>((a, b) -> {
        return a.priority != b.priority ? b.priority - a.priority : b.taskId - a.taskId;
    });

    public TaskManager(List<List<Integer>> tasks) {
        for (List<Integer> each : tasks) {
            Data d = new Data(each);

            add(d);
        }
    }
    
    public void add(int userId, int taskId, int priority) {
        Data d = new Data(userId, taskId, priority);

        add(d);
    }

    private void add(Data d) {
        taskData.put(d.taskId, d);
        set.add(d);
    }
    
    public void edit(int taskId, int newPriority) {
        Data d = taskData.get(taskId);
        set.remove(d);
        d.priority = newPriority;
        set.add(d);
    }
    
    public void rmv(int taskId) {
        Data d = taskData.remove(taskId);
        set.remove(d);
    }
    
    public int execTop() {
        if (set.isEmpty()) return -1;

        Data d = set.first();

        set.remove(d);
        taskData.remove(d.taskId);

        return d.user;
    }

    class Data {
        int user;
        int taskId;
        int priority;

        public Data(List<Integer> list) {
            this(list.get(0), list.get(1), list.get(2));
        }

        public Data(int u, int t, int p) {
            this.user = u;
            this.taskId = t;
            this.priority = p;
        }
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