/**
 * Set represents all groups of odd natural numbers
 *
 * @author Yotam Combe
 * @version 6.6.2022
 */

public class Set {
    public static void main(String args[]) {
        IntNode c = new IntNode(1, null);
        IntNode b = new IntNode(3, c);
        IntNode a = new IntNode(5, b);
        Set s = new Set(a);
        System.out.println(s.toString());
    }
    
    private IntNode _head; //start of Set
    private int _length; //Set length
    
    /**
     * empty builder
     * the function is in O(1) time complexity 
     * the function is in O(1) space complexity 
     */
    public Set() {
        _head = null;
        _length = 0;
    }
    
    /**
     * builder
     * the function is in O(n) time complexity 
     * the function is in O(1) space complexity 
     * @param IntNode element to point on the list to push to the Set
     */
    public Set(IntNode element) {
        _head = element;
        while(_head != null && (_head.getValue() % 2 == 0 || _head.getValue() < 1)) {
            _head = _head.getNext(); //finding the first element with a valid value
        }
        if(_head == null) { //stopping condition
            _length = 0;
            return;
        }
        _length = 1;
        IntNode temp = _head;
        while(temp.getNext() != null) { //removes all unvalid elements from the Set
            while(temp.getNext() != null && (temp.getValue() % 2 == 0 || temp.getValue() < 1)) {
                temp.setNext(temp.getNext().getNext()); //if next element after temp is not valid, skip it
            }
            if(temp.getNext() != null)
                temp = temp.getNext(); //updates temp only if next is not null
            _length++;
        }
        mergeSort(); //sorts the Set
    }
    
    /**
     * checks if the Set is empty
     * the function is in O(1) time complexity
     * the function is in O(1) space complexity
     * @return true if the Set is empty, false if not
     */
    public boolean isEmpty() {
        if(_length == 0)
            return true;
        return false;
    }
    
    /**
     * checks if a certain number is a member in the Set
     * the function is in O(n) time complexity 
     * the function is in O(1) space complexity 
     * @param num to check if is inside the Set
     * @return true if the num is a member, false if not
     */
    public boolean isMember(int num) {
        if(_length == 0) //stopping condition
            return false;
            
        IntNode temp = _head;
        if(num < temp.getValue()) //stopping condition
            return false;
        while(temp != null && num >= temp.getValue()) {
            if(num == temp.getValue())
                return true;
            temp = temp.getNext();
        }
        return false;
    }

    /**
     * checks if two Sets are equal
     * the function is in O(n) time complexity 
     * the function is in O(1) space complexity 
     * @param Set other to compare with this Set
     * @return true if the two Sets are equal, false if not
     */
    public boolean equals(Set other) {
        if(_length != other._length) //stopping condition
            return false;
            
        IntNode temp1 = _head;
        IntNode temp2 = other._head;
        while(temp1 != null) {
            if(temp1.getValue() != temp2.getValue())
                return false;
            temp1 = temp1.getNext();
            temp2 = temp2.getNext();
        }
        return true;
    }
    
    /**
     * returns the number of elements in the Set
     * the function is in O(1) time complexity 
     * the function is in O(1) space complexity 
     * @return number of elements in the Set
     */
    public int numOfElements() {
        return _length;
    }
    
    /**
     * checks if other Set is a sub Set of this Set
     * the function is in O(n) time complexity 
     * the function is in O(1) space complexity 
     * @param Set other to check if its a sub Set
     * @return true if other is a sub Set of this Set, false if not
     */
    public boolean subSet(Set other) {
        if(other._length > _length) //stopping condition
            return false;
            
        IntNode temp1 = _head;
        IntNode temp2 = other._head;
        while(temp1 != null && temp2 != null) {
            if(temp1.getValue() == temp2.getValue())
                temp2 = temp2.getNext();
            temp1 = temp1.getNext();
        }
        if(temp2 == null)
            return true;
        return false;
    }
    
    /**
     * adds number to Set
     * the function is in O(n) time complexity 
     * the function is in O(1) space complexity 
     * @param int x to add to the Set
     */
    public void addToSet(int x) {
        if(x % 2 == 0 || x < 1 || this.isMember(x)) //stopping condition
            return;
            
        IntNode temp = _head;
        if(temp == null || temp.getValue() > x) //in case we need to push x as first element
            _head = new IntNode(x, temp);
        else {
            while(temp.getNext() != null && temp.getNext().getValue() < x) {
                temp = temp.getNext();
            }
            temp.setNext(new IntNode(x, temp.getNext()));
        }
        _length++;
    }
    
    /**
     * removes a certain number from the Set
     * the function is in O(n) time complexity 
     * the function is in O(1) space complexity 
     * @param int x to know what number should it remove from the Set
     */
    public void removeFromSet(int x) {
        if(x % 2 == 0 || x < 1 || !this.isMember(x)) //stopping condition
            return;
            
        if(_head.getValue() == x) { //in case of x in first element
            _head = _head.getNext();
            _length--;
        } else {
            IntNode temp = _head;
            while(temp.getNext() != null) {
                if(temp.getNext().getValue() == x) {
                    temp.setNext(temp.getNext().getNext());
                    _length--;
                    return;
                }
                temp = temp.getNext();
            }
        }
    }
    
    /**
     * returns a String which declairs the Set
     * the function is in O(n) time complexity 
     * the function is in O(1) space complexity 
     * @return a String which declairs the Set
     */
    public String toString() {
        if(_head == null) //stopping condition
            return "{}";
            
        String str = "{" + _head.getValue();
        IntNode temp = _head.getNext();
        while(temp != null) {
            str = str + "," + temp.getValue();
            temp = temp.getNext();
        }
        return str + "}";
    }
    
    /**
     * returns the intersection Set between two Sets
     * the function is in O(n) time complexity 
     * the function is in O(n) space complexity 
     * @param Set other to compare with this Set
     * @return the intersection Set of both Sets
     */
    public Set intersection(Set other) {
        if(_head == null || other._head == null) //stopping condition
            return null;
            
        IntNode temp1 = _head;
        IntNode temp2 = other._head;
        IntNode tempSet = new IntNode(1, null);
        Set s = new Set(tempSet);
        
        while(temp1 != null && temp2 != null) {
            if(temp1.getValue() < temp2.getValue()) //first option
                temp1 = temp1.getNext();
            else if(temp1.getValue() == temp2.getValue()) { //second option
                tempSet.setNext(new IntNode(temp1.getValue(), null));
                s._length++;
                tempSet = tempSet.getNext();
                temp1 = temp1.getNext();
                temp2 = temp2.getNext();
            } else //third option
                temp2 = temp2.getNext();
        }
        s._head = s._head.getNext(); //skips the 1 head we created before the while loop
        s._length--;
        return s;
    }
    
    /**
     * returns the union Set of two Sets
     * the function is in O(n) time complexity 
     * the function is in O(n) space complexity 
     * @param Set other to compate with this Set
     * @return the union Set of the two Sets
     */
    public Set union(Set other) {
        if(_head == null) //stopping condition
            return other;
        if(other == null) //stopping condition
            return this;
        
        IntNode temp1 = _head;
        IntNode temp2 = other._head;
        IntNode tempSet = new IntNode(1, null);
        Set s = new Set(tempSet);
        while(temp1 != null && temp2 != null) {
            if(temp1.getValue() < temp2.getValue()) { //first option
                tempSet.setNext(new IntNode(temp1.getValue(), null));
                temp1 = temp1.getNext();
            } else if(temp1.getValue() == temp2.getValue()) { //second option
                tempSet.setNext(new IntNode(temp1.getValue(), null));
                temp1 = temp1.getNext();
                temp2 = temp2.getNext();
            } else { //third option
                tempSet.setNext(new IntNode(temp2.getValue(), null));
                temp2 = temp2.getNext();
            }
            s._length++;
            tempSet = tempSet.getNext();
        }
        if(temp1 == null) //if temp2 is finished --> asign temp1
            while(temp2 != null) { //avoids aliasing
                tempSet.setNext(new IntNode(temp2.getValue(), null));
                s._length++;
                temp2 = temp2.getNext();
                tempSet = tempSet.getNext();
            }
        else //if temp1 is finished --> asign temp2
            while(temp1 != null) { //avoids aliasing
                tempSet.setNext(new IntNode(temp1.getValue(), null));
                s._length++;
                temp1 = temp1.getNext();
                tempSet = tempSet.getNext();
            }
        s._head = s._head.getNext(); //skips the 0 head we created before the while loop
        s._length--;
        return s;
    }
    
    /**
     * returns the difference numbers Set between two Sets
     * the function is in O(n) time complexity 
     * the function is in O(n) space complexity 
     * @param Set other to compare with this Set
     * @return a new Set which includes all numbers from this Set that doesn't show in Set other
     */
    public Set difference(Set other) {
        if(_head == null) //stopping condition
            return null;
        
        IntNode temp1 = _head;
        IntNode temp2 = other._head;
        IntNode tempSet = new IntNode(1, null);
        Set s = new Set(tempSet);
        while(temp1 != null && temp2 != null) {
            if(temp1.getValue() < temp2.getValue()) { //first option
                tempSet.setNext(new IntNode(temp1.getValue(), null));
                s._length++;
                tempSet = tempSet.getNext();
                temp1 = temp1.getNext();
            } else if(temp1.getValue() == temp2.getValue()) { //second option
                temp1 = temp1.getNext();
                temp2 = temp2.getNext();
            } else { //third option
                temp2 = temp2.getNext();
            }
        }
        if(temp2 == null) //if temp2 is finished --> asign temp1
            while(temp1 != null) { //avoids aliasing
                tempSet.setNext(new IntNode(temp1.getValue(), null));
                s._length++;
                tempSet = tempSet.getNext();
                temp1 = temp1.getNext();
            }
        s._head = s._head.getNext(); //skips the 0 head we created before the while loop
        s._length--;
        return s;
    }
    
    //////////////////////////////////////////////////////////////////////////HELPER_SECTION/////////////////////////////////////////////////////////////////////
    
    /**
     * recursion which splits a IntNode list into two
     * the function is in O(n) time complexity 
     * the function is in O(1) space complexity (without refering to the recursion)
     * @param node to work on
     * @return new IntNode with half of original node (node changes in the background)
     */
    private IntNode split(IntNode node) {
        if(node == null || node.getNext() == null)
            return null;
        IntNode list2 = node.getNext();
        node.setNext(list2.getNext());
        list2.setNext(split(list2.getNext()));
        return list2;
    }
    
    /**
     * recursion which merges two IntNode in order
     * the function is in O(n) time complexity 
     * the function is in O(1) space complexity (without refering to the recursion)
     * @param two nodeLists to merge
     * @return a merged IntNode of both lists
     */
    private IntNode merge(IntNode list1, IntNode list2) {
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;
            
        if(list1.getValue() < list2.getValue()) {
            list1.setNext(merge(list1.getNext(), list2));
            return list1;
        } else {
            list2.setNext(merge(list1, list2.getNext()));
            return list2;
        }
    }
    
    /**
     * recursion which uses split() and merge() to sort an IntNode list
     * the function is in O(n*log n) time complexity 
     * the function is in O(1) space complexity (without refering to the recursion)
     * @param node IntNode to sort
     * @return new IntNode with same elements of node, only sorted
     */
    private IntNode mergeSort(IntNode node) {
        if(node == null || node.getNext() == null)
            return node;
        
        IntNode list2 = split(node);
        node = mergeSort(node);
        list2 = mergeSort(list2);
        return merge(node, list2);
    }
    
    /**
     * overloading function which calls other mergeSort()
     * the function is in O(n*log n) time complexity 
     * the function is in O(1) space complexity (without refering to the recursion)
     */
    private void mergeSort() {
        _head = mergeSort(_head);
    }
}