Algorithm: Greedy Task Scheduling

Input:
    Tasks T
    Slots S
    Conflict Graph G
    Resource capacities for each slot

Output:
    Assignment of tasks to slots OR infeasible

Step 1: Sort tasks in decreasing order of:
        - weight (priority)
        - number of conflicts

Step 2: For each task t in sorted list:
    
    bestSlot = -1
    minPenalty = infinity

    For each slot s in [t.l, t.u]:   // SLA window

        If (F1: no conflict with already assigned tasks in slot s)
           AND (F2: resources of t fit in slot s):

            Compute penalty = weight(t) × s

            If penalty < minPenalty:
                minPenalty = penalty
                bestSlot = s

    If bestSlot == -1:
        return INFEASIBLE

    Assign task t → bestSlot
    Update slot resource usage

Step 3: Return assignment

Time Complexity:
    Sorting: O(n log n)
    Assignment: O(n × K × d)

    where:
    n = number of tasks
    K = number of slots
    d = average conflicts per task

Overall: O(n log n + n × K × d)
