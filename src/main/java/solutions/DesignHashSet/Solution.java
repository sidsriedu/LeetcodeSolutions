package solutions.DesignHashSet;

import java.util.LinkedList;
import java.util.List;

class Solution {

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);      // set = [1]
        myHashSet.add(2);      // set = [1, 2]
        System.out.println(myHashSet.contains(1)); // return True
        System.out.println(myHashSet.contains(3)); // return False, (not found)
        myHashSet.add(2);      // set = [1, 2]
        System.out.println(myHashSet.contains(2)); // return True
        myHashSet.remove(2);   // set = [1]
        System.out.println(myHashSet.contains(2)); // return False, (already removed)
    }


}

class MyHashSet {

    int size;
    List<Integer>[] array;

    public MyHashSet() {
        size = 10000;
        array = new LinkedList[size];
        for(int index=0; index<size; index++)
            array[index] = new LinkedList<Integer>();
    }

    public void add(int key) {
        int index = hash(key);
        List<Integer> bucket = array[index];
        if(!bucket.contains(key))
            bucket.add(key);
    }

    public void remove(int key) {
        int index = hash(key);
        List<Integer> bucket = array[index];
        bucket.remove(Integer.valueOf(key));
    }

    public boolean contains(int key) {
        int index = hash(key);
        List<Integer> bucket = array[index];
        return bucket.contains(key);
    }

    public int hash(int key){
        return key%size;
    }

}
