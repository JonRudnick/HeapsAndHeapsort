
/**
 * HeapAndHeapsort
 * 
 * Author: Jonathan Rudnick
 * Date: March 1, 2016
 * Assignment: HW #5 Part 3
 * 
 */

// Code to demonstrate heaps and Heapsort

@SuppressWarnings({"unchecked"})

// An object from this class is a heap containing generic
// data, along with a priority number for each item. Once
// a heap has been built, it can also be used to Heapsort.
public class HeapsAndHeapsort<Data> {

   // EXECUTION BEGINS HERE 
   
   public static void main(String[] args) {
       testHeapAndHeapsort();
   }

   private static final int MAX_SIZE = 100; 
   // An array of objects to maintain in heap
   private Data data[];
   // An array of associated priorities
   private int priorities[];
   // Size of heap (# nodes in it) 
   private int heapsize;

   private void swapInHeap(int i, int j)
   {
     Data temp = data[i];
     int priority = priorities[i]; 
     data[i] = data[j];
     priorities[i] = priorities[j]; 
     data[j] = temp; 
     priorities[j] = priority;
   }   
   private int parent(int n) { return n/2; }
   private int leftChild(int n) { return 2*n; }
   private int rightChild(int n) { return 2*n+1; }   

   public HeapAndHeapsort() {
      data = (Data[]) new Object[MAX_SIZE];
      priorities = new int[MAX_SIZE];
      heapsize = 0; 
   }
   // Return number of nodes in the heap
   public int getSize() { return heapsize; } 
   // Add a thing with priority to heap, and heapify up 
   public void insertNode(Data d, int priority) {
      int index = ++heapsize;
      data[index] = d;
      priorities[index] = priority;
      while (index > 1 && priorities[index] > priorities[parent(index)]) 
      {
         swapInHeap(index, parent(index)); 
         index = parent(index);
      }
   }   
   public void lazyInsertNode(Data d, int priority) {
      int index = ++heapsize;
      data[index] = d;
      priorities[index] = priority;
   }
   // Remove and return thing on top of heap, and heapify down 
   public Data removeTopOfHeap() {
      int index = 1, indexL, indexR, indexM; 
      if (heapsize == 0) return null;
      // Swap first and last
      swapInHeap(1, heapsize); 
      heapsize--;
      // Heapify down starting at top
      while(index < heapsize) {
         // do child nodes have higher priority? 
         indexM = indexL = indexR = index; 
         if (leftChild(index) <= heapsize) { 
            indexL = leftChild(index);
            if (priorities[indexM] < priorities[indexL]) 
               indexM = indexL; 
         }
         if (rightChild(index) <= heapsize) { 
            indexR = rightChild(index); 
            if (priorities[indexM] < priorities[indexR]) 
               indexM = indexR; 
         }  
         // indexM is now the index of the one with highest priority
         // Swap with a child or quit heapifying?
         if (indexM != index) { 
            swapInHeap(index, indexM); 
            index = indexM;
         } else {
            break;
         }
      }
      return data[heapsize+1];         
   }   
   // Display heap 
   public void display() {
      for (int i=1; i <= heapsize; i++)
         System.out.print("(" + data[i]
           + ":" + priorities[i] + ") ");
      System.out.println();
      System.out.println();
    }   
   // Display each level of heap on separate line 
   public void displayLevels() {
      int count = 0, mark = 1; 
      System.out.println("Levels of the heap:");
      for (int i=1; i <= heapsize; i++) {
         System.out.print("(" + data[i]
           + ":" + priorities[i] + ") "); 
         if (++count == mark) {
             System.out.println();
             count = 0; 
             mark *= 2;
         }
      }
      System.out.println();
      System.out.println();
    }   
    // Heapsort amounts to repeatedly removing fro  m the top of the heap 
    // and putting this in the array right after the active portion 
    public void heapsort() { 
       int holdsize = heapsize;
       while (heapsize > 0) removeTopOfHeap(); 
       heapsize = holdsize;
    }

    // Test code for heap and Heapsort
    public static void testHeapAndHeapsort() 
    {
        HeapAndHeapsort<Vehicle> heap = new HeapAndHeapsort<Vehicle>();
        heap.lazyInsertNode(new Vehicle("Car", 1724.4), 31);
        heap.displayLevels();
        heap.lazyInsertNode(new Vehicle("Boat", 2523.6), 15);
        heap.displayLevels();
        heap.lazyInsertNode(new Vehicle("Plane", 7482.9), 47);
        heap.displayLevels();
        heap.lazyInsertNode(new Vehicle("Rocket", 1527274.5), 55);
        heap.displayLevels();
        heap.lazyInsertNode(new Vehicle("Bicycle", 19.3), 23);
        heap.displayLevels();
        heap.lazyInsertNode(new Vehicle("Motorcycle", 441.9), 42);
        heap.displayLevels();
        heap.lazyInsertNode(new Vehicle("Starship", 1735399121.8), 44);
        heap.displayLevels();
        heap.lazyInsertNode(new Vehicle("Bathysphere", 2121.8), 51);
        heap.displayLevels();
        System.out.print("The heap as a list: ");
        heap.display();
        heap.fixHeap();
        heap.display();
        //heap.heapsort();
        //System.out.print("After Heapsorting: ");
        //heap.display();
    }
    public void fixHeapAtNode(int index)
    {
        int indexL, indexR, indexM;
    	if(leftChild(index) <= heapsize)
    		fixHeapAtNode(leftChild(index));
    	else if(rightChild(index) <= heapsize)
    		fixHeapAtNode(rightChild(index));
    	while(index < heapsize) {
         // do child nodes have higher priority? 
         indexM = indexL = indexR = index; 
         if (leftChild(index) <= heapsize) { 
            indexL = leftChild(index);
            if (priorities[indexM] < priorities[indexL]) 
               indexM = indexL; 
         }
         if (rightChild(index) <= heapsize) { 
            indexR = rightChild(index); 
            if (priorities[indexM] < priorities[indexR]) 
               indexM = indexR; 
         }  
         // indexM is now the index of the one with highest priority
         // Swap with a child or quit heapifying?
         if (indexM != index) { 
            swapInHeap(index, indexM); 
            index = indexM;
         } else {
            break;
         }
      }
    }
    public void fixHeap()
    {
    	if(heapsize > 1)
    		fixHeapAtNode(1);
    }
}

// A class of objects to put into the heap    
class Vehicle 
{ 
    private String name;
    private double weight;     // not used in this example
    public Vehicle(String n, double w) {
        name = n;
        weight = w;
    }
    String getName() { return name; } 
    double getWeight() { return weight; }
    public String toString() { return name; }
}
