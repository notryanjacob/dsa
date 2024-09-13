class Process:
    def __init__(self, pid, arrival_time, burst_time):
        self.pid = pid
        self.arrival_time = arrival_time
        self.burst_time = burst_time
        self.remaining_time = burst_time
        self.waiting_time = 0
        self.turnaround_time = 0
        self.start_time = 0
        self.end_time = 0

def fcfs_scheduling(processes):
    processes.sort(key=lambda x: x.arrival_time)
    current_time = 0
    schedule = []
    
    for process in processes:
        if current_time < process.arrival_time:
            current_time = process.arrival_time
        process.start_time = current_time
        current_time += process.burst_time
        process.end_time = current_time
        process.turnaround_time = process.end_time - process.arrival_time
        process.waiting_time = process.turnaround_time - process.burst_time
        schedule.append((process.start_time, process.pid))
    
    return schedule

def sjf_non_preemptive_scheduling(processes):
    processes.sort(key=lambda x: (x.arrival_time, x.burst_time))
    current_time = 0
    schedule = []
    ready_queue = []

    while processes or ready_queue:
        while processes and processes[0].arrival_time <= current_time:
            ready_queue.append(processes.pop(0))
        if ready_queue:
            ready_queue.sort(key=lambda x: x.burst_time)
            process = ready_queue.pop(0)
            if current_time < process.arrival_time:
                current_time = process.arrival_time
            process.start_time = current_time
            current_time += process.burst_time
            process.end_time = current_time
            process.turnaround_time = process.end_time - process.arrival_time
            process.waiting_time = process.turnaround_time - process.burst_time
            schedule.append((process.start_time, process.pid))
        else:
            current_time = processes[0].arrival_time

    return schedule

def sjf_preemptive_scheduling(processes):
    processes.sort(key=lambda x: x.arrival_time)
    current_time = 0
    schedule = []
    ready_queue = []
    running_process = None

    while processes or ready_queue or running_process:
        while processes and processes[0].arrival_time <= current_time:
            ready_queue.append(processes.pop(0))
        if running_process:
            ready_queue.append(running_process)
            ready_queue.sort(key=lambda x: x.remaining_time)
            running_process = ready_queue.pop(0)
        else:
            if ready_queue:
                ready_queue.sort(key=lambda x: x.remaining_time)
                running_process = ready_queue.pop(0)
            else:
                current_time = processes[0].arrival_time
                continue

        if not schedule or schedule[-1][1] != running_process.pid:
            schedule.append((current_time, running_process.pid))

        if processes and processes[0].arrival_time < current_time + running_process.remaining_time:
            execution_time = processes[0].arrival_time - current_time
            running_process.remaining_time -= execution_time
            current_time = processes[0].arrival_time
        else:
            execution_time = running_process.remaining_time
            running_process.remaining_time = 0
            current_time += execution_time

        if running_process.remaining_time == 0:
            running_process.end_time = current_time
            running_process.turnaround_time = running_process.end_time - running_process.arrival_time
            running_process.waiting_time = running_process.turnaround_time - running_process.burst_time
            running_process = None

    return schedule

def calculate_metrics(processes):
    total_waiting_time = sum(p.waiting_time for p in processes)
    total_turnaround_time = sum(p.turnaround_time for p in processes)
    avg_waiting_time = total_waiting_time / len(processes)
    avg_turnaround_time = total_turnaround_time / len(processes)
    return avg_waiting_time, avg_turnaround_time

def print_schedule(schedule):
    print("Schedule:")
    for start_time, pid in schedule:
        print(f"At time {start_time}: Process {pid} starts")

def print_metrics(processes):
    print("Metrics:")
    for process in processes:
        print(f"Process {process.pid}: Waiting Time = {process.waiting_time}, Turnaround Time = {process.turnaround_time}")
    avg_waiting_time, avg_turnaround_time = calculate_metrics(processes)
    print(f"Average Waiting Time: {avg_waiting_time}")
    print(f"Average Turnaround Time: {avg_turnaround_time}")

def main():
    num_processes = int(input("Enter the number of processes: "))
    processes = []
    for i in range(num_processes):
        pid = input(f"Enter Process ID for process {i+1}: ")
        arrival_time = int(input(f"Enter Arrival Time for process {pid}: "))
        burst_time = int(input(f"Enter Burst Time for process {pid}: "))
        processes.append(Process(pid, arrival_time, burst_time))

    print("\n1. FCFS Scheduling")
    print("2. SJF Non-Preemptive Scheduling")
    print("3. SJF Preemptive Scheduling")
    choice = int(input("Enter your choice: "))

    if choice == 1:
        schedule = fcfs_scheduling(processes)
    elif choice == 2:
        schedule = sjf_non_preemptive_scheduling(processes)
    elif choice == 3:
        schedule = sjf_preemptive_scheduling(processes)
    else:
        print("Invalid choice")
        return

    print_schedule(schedule)
    print_metrics(processes)

if __name__ == "__main__":
    main()
