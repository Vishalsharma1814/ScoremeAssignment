**Title: Resource-Constrained Task Scheduling in Compute Clusters**

**Problem Overview**
We are given a set of tasks that must be scheduled into discrete time slots. Each task has resource requirements, conflicts with other tasks, and a valid time window (SLA). The goal is to assign each task to a slot such that conflicts are avoided, resource capacities are not exceeded, SLA windows are respected, and total penalty is minimized.

---

**NP-Hardness Justification**
This problem is NP-hard because it generalizes the graph coloring problem. If we ignore resource and SLA constraints, the problem reduces to assigning tasks (nodes) to slots (colors) such that no conflicting tasks share a slot. Since graph coloring is NP-hard, this problem is also NP-hard.

---

**Penalty Function**
The base penalty is defined as:

Penalty = Σ (weight × slot)

Additionally, a load imbalance penalty is included:

Penalty += Σ (slot utilization²)

This ensures that tasks are scheduled earlier and resources are evenly distributed across slots.

---

**Algorithm Design**
A greedy heuristic is used:

1. Tasks are sorted by priority (weight) and number of conflicts
2. For each task:

   * All valid slots within its SLA window are checked
   * Conflict and resource constraints are verified
   * The slot with minimum penalty is selected

If no valid slot is found, the instance is marked infeasible.

---

**Correctness**
The algorithm guarantees:

* No conflicting tasks are placed in the same slot
* Resource constraints are always respected
* SLA windows are not violated

Thus, all feasibility conditions are satisfied.

---

**Limitations**
Since the algorithm is greedy, it does not guarantee an optimal solution. It may fail in cases with dense conflicts or very limited slots.

---

**Conclusion**
This approach provides a practical and efficient method for scheduling tasks in real-world systems such as compute clusters, where exact optimization is computationally expensive.
