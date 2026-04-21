import java.util.*;

class Task {
    String id;
    double[] res;
    int l, u;
    double weight;

    Task(String id, double[] res, int l, int u, double weight) {
        this.id = id;
        this.res = res;
        this.l = l;
        this.u = u;
        this.weight = weight;
    }
}

class Slot {
    double[] cap;
    double[] used;

    Slot(double[] cap) {
        this.cap = cap.clone();
        this.used = new double[cap.length];
    }

    boolean canFit(Task t) {
        for (int i = 0; i < cap.length; i++) {
            if (used[i] + t.res[i] > cap[i]) return false;
        }
        return true;
    }

    void add(Task t) {
        for (int i = 0; i < cap.length; i++) {
            used[i] += t.res[i];
        }
    }
}

public class Scheduler {

    static boolean hasConflict(String t, int slot,
                               Map<String, Integer> assignment,
                               Map<String, Set<String>> graph) {

        for (String other : assignment.keySet()) {
            if (assignment.get(other) == slot &&
                graph.getOrDefault(t, new HashSet<>()).contains(other)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("T1", new double[]{2,2,1,1}, 0, 2, 5));
        tasks.add(new Task("T2", new double[]{2,2,0,1}, 0, 2, 4));

        int K = 3;

        List<Slot> slots = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            slots.add(new Slot(new double[]{10,10,5,5}));
        }

        Map<String, Set<String>> graph = new HashMap<>();
        graph.put("T1", new HashSet<>(Arrays.asList("T2")));

        Map<String, Integer> assignment = new HashMap<>();

        // Sort by weight (priority)
        tasks.sort((a, b) -> Double.compare(b.weight, a.weight));

        for (Task t : tasks) {

            int bestSlot = -1;
            double bestPenalty = Double.MAX_VALUE;

            for (int s = t.l; s <= t.u; s++) {

                if (hasConflict(t.id, s, assignment, graph)) continue;
                if (!slots.get(s).canFit(t)) continue;

                double penalty = t.weight * s;

                if (penalty < bestPenalty) {
                    bestPenalty = penalty;
                    bestSlot = s;
                }
            }

            if (bestSlot == -1) {
                System.out.println("INFEASIBLE");
                return;
            }

            assignment.put(t.id, bestSlot);
            slots.get(bestSlot).add(t);
        }

        System.out.println("Final Assignment: " + assignment);
    }
}
