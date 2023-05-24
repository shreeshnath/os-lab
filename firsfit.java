import java.util.ArrayList;
import java.util.List;

class MemoryBlock {
    private int startAddress;
    private int endAddress;
    private boolean allocated;

    public MemoryBlock(int startAddress, int endAddress) {
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.allocated = false;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public int getEndAddress() {
        return endAddress;
    }

    public boolean isAllocated() {
        return allocated;
    }

    public void setAllocated(boolean allocated) {
        this.allocated = allocated;
    }
}

class Memory {
    private List<MemoryBlock> memoryBlocks;

    public Memory() {
        memoryBlocks = new ArrayList<>();
    }

    public void addMemoryBlock(int startAddress, int endAddress) {
        memoryBlocks.add(new MemoryBlock(startAddress, endAddress));
    }

    public int allocateMemory(int size) {
        for (MemoryBlock block : memoryBlocks) {
            if (!block.isAllocated() && block.getEndAddress() - block.getStartAddress() >= size) {
                block.setAllocated(true);
                return block.getStartAddress();
            }
        }
        return -1; // Memory allocation failed
    }

    public void deallocateMemory(int startAddress) {
        for (MemoryBlock block : memoryBlocks) {
            if (block.getStartAddress() == startAddress) {
                block.setAllocated(false);
                return;
            }
        }
    }
}

public class firsfit {
    public static void main(String[] args) {
        // Create a memory object
        Memory memory = new Memory();

        // Add memory blocks
        memory.addMemoryBlock(0, 99);
        memory.addMemoryBlock(100, 199);
        memory.addMemoryBlock(200, 299);

        // Allocate memory
        int allocatedAddress1 = memory.allocateMemory(50);
        int allocatedAddress2 = memory.allocateMemory(100);
        int allocatedAddress3 = memory.allocateMemory(75);

        // Deallocate memory
        memory.deallocateMemory(allocatedAddress2);

        // Allocate memory again
        int allocatedAddress4 = memory.allocateMemory(80);

        // Print the allocated addresses
        System.out.println("Allocated addresses:");
        System.out.println(allocatedAddress1);
        System.out.println(allocatedAddress3);
        System.out.println(allocatedAddress4);
    }

    @Override
    public String toString() {
        return "FirstFitAlgorithm []";
    }
}