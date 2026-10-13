class Solution {
    public int leastInterval(char[] tasks, int n) {
        final Map<Character, Task> taskByChar = new HashMap<>();
        for(char c : tasks) {
            Task newTask = taskByChar.getOrDefault(c, new Task(c));
            newTask.remaining++;
            taskByChar.put(c, newTask);
        }

        // processing queue, prioritizing chars with highest frequency remaining
        Queue<Task> toProcess = new PriorityQueue<>((a, b) -> {
            return b.remaining - a.remaining;
        });
        for(Task task : taskByChar.values()) {
            toProcess.offer(task);
        }

        Set<Task> onCooldown = new HashSet<>();
        Set<Task> cooled = new HashSet<>();
        int cycles = 0;
        while(!toProcess.isEmpty() || !onCooldown.isEmpty()) {
            cycles++;
            // process if possible
            Task processed = null;
            if(!toProcess.isEmpty()) {
                processed = toProcess.poll();
                if (--processed.remaining > 0) {
                    // if this char hasn't been fully processed, send to cooldown
                    processed.cooldown = n+1;
                    onCooldown.add(processed);
                }
            }

            // decrement cooldowns
            for(Task t : onCooldown) {
                if(--t.cooldown == 0) {
                    // enqueue for processing when ready
                    toProcess.offer(t);
                    cooled.add(t);
                }
            }
            onCooldown.removeAll(cooled);
            cooled.clear();
        }

        return cycles;
    }

    private class Task {
        char c;
        int cooldown;
        int remaining;

        public Task(char c) {
            this.c = c;
            cooldown = 0;
            remaining = 0;
        }
    }
}
