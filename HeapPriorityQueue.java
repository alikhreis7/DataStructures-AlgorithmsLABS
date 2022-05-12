/**
 * Array Heap implementation of a priority queue
 */
public class HeapPriorityQueue<K extends Comparable,V> implements PriorityQueue<K ,V> {
    
    private Entry[] storage; //The Heap itself in array form
    private int tail;        //Index of last element in the heap
    
    /**
    * Default constructor
    */
    public HeapPriorityQueue(){
		this(10);
    }
    
    /**
    * HeapPriorityQueue constructor with max storage of size elements
    */
    public HeapPriorityQueue(int size){
		this.tail = 0;  
		this.storage = new Entry[size];
    }
    
    /****************************************************
     * 
     *             Priority Queue Methods
     * 
     ****************************************************/
    
    /**
    * Returns the number of items in the priority queue.
    * O(1)
    * @return number of items
    */
    public int size(){
        return tail;
    }

    /**
    * Tests whether the priority queue is empty.
    * O(1)
    * @return true if the priority queue is empty, false otherwise
    */
    public boolean isEmpty(){
        if (tail==0){
			return true;
		}
		else {
			return false;
		}
    }
    
    /**
    * Inserts a key-value pair and returns the entry created.
    * O(log(n))
    * @param key     the key of the new entry
    * @param value   the associated value of the new entry
    * @return the entry storing the new key-value pair
    * @throws IllegalArgumentException if the heap is full
    */
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
		
		if (tail!= storage.length){
		Entry entry = new Entry(key,value);
		storage[tail] = entry;
		upHeap(tail);
		tail=tail+1;
		return entry;
		}
	 else {
		throw new IllegalArgumentException();
		}
    }
    
    /**
    * Returns (but does not remove) an entry with minimal key.
    * O(1)
    * @return entry having a minimal key (or null if empty)
    */
    public Entry<K,V> min(){
      if(isEmpty()) {
    		return null;
    	}
	  else {
        return tail;
		}
    } 
    
    /**
    * Removes and returns an entry with minimal key.
    * O(log(n))
    * @return the removed entry (or null if empty)
    */ 
    public Entry<K,V> removeMin(){
        
		if(isEmpty()) {
    		return null;
    	}
        Entry temp = storage[0];
    	if(tail == 0) {
    		tail = -1;
    		storage[0] = null;
    		return temp;
    	}
        storage[0] = storage[tail];
        storage[tail] = null;
        tail=tail-1;
        downHeap(0);
        return temp;
    }  
    
    
    /****************************************************
     * 
     *           Methods for Heap Operations
     * 
     ****************************************************/
    
    /**
    * Algorithm to place element after insertion at the tail.
    * O(log(n))
    */
    private void upHeap(int location){
         int after = location;
		while(storage[after].getKey().compareTo(storage[parent(after)].getKey()) < 0) {
			swap(after, parent(after));
			after = parent(after);
		}       
    }
    
    /**
    * Algorithm to place element after removal of root and tail element placed at root.
    * O(log(n))
    */
    private void downHeap(int location){
         int moveright = 2 * location + 2; 
         int moveleft = 2 * location + 1;  

         if(moveleft > tail) return;

         if(moveleft == tail) { 
             if(storage[location].key.compareTo(storage[moveleft].key) > 0){
                 swap(location,moveleft);
             }
             return;
         }
         if(storage[moveleft].getKey().compareTo(storage[moveright].getKey()) < 0) {
             if(storage[location].getKey().compareTo(storage[moveleft].getKey()) > 0) {
                 swap(location, moveleft);
                 downHeap(moveleft);
                }
        }
        else {
            if(storage[location].getKey().compareTo(storage[moveright].getKey()) > 0) {
                swap(location, moveright);
                downHeap(moveright);
                }
         }
         }            
    }
    
    /**
    * Find parent of a given location,
    * Parent of the root is the root
    * O(1)
    */
    private int parent(int location){
		int x;
		x=Math.floor((location - 1) / 2)
        return x;
    }
    
   
    /**
    * Inplace swap of 2 elements, assumes locations are in array
    * O(1)
    */
    private void swap(int location1, int location2){
        Entry temp = storage[location1];
		storage[location1] = storage[location2];
		storage[location2] = temp;
    }
    
}
