def best_fit(block_sizes, process_sizes):
    # Create a list to store the allocation status of each block
    allocation = [-1] * len(process_sizes)

    # Iterate over each process
    for i in range(len(process_sizes)):
        # Find the best-fit block for the current process
        best_fit_index = -1
        for j in range(len(block_sizes)):
            if block_sizes[j] >= process_sizes[i]:
                if best_fit_index == -1 or block_sizes[j] < block_sizes[best_fit_index]:
                    best_fit_index = j

        # If a block is found, allocate the process to it
        if best_fit_index != -1:
            allocation[i] = best_fit_index

            # Reduce the available block size after allocation
            block_sizes[best_fit_index] -= process_sizes[i]

    # Print the allocation details
    print("Process No.\tProcess Size\tBlock No.")
    for i in range(len(process_sizes)):
        print(i + 1, "\t\t", process_sizes[i], end="\t\t")
        if allocation[i] != -1:
            print(allocation[i] + 1)
        else:
            print("Not Allocated")


# Example usage
block_sizes = [100, 500, 200, 300, 600]
process_sizes = [212, 417, 112, 426]
best_fit(block_sizes, process_sizes)