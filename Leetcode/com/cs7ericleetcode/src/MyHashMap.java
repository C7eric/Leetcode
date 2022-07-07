package Leetcode.Leetcode.com.cs7ericleetcode.src;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author C77eric
 * @version 1.0
 */
public class MyHashMap {

    public class Pair{

        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static final int BASE = 769;
    private LinkedList[] data;

    public MyHashMap(){

        data = new LinkedList[BASE];
        for(int i = 0;i < BASE;++i){
            data[i] = new LinkedList<Pair>();
        }
    }

    public void put(int key,int value){
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while(iterator.hasNext()){
            Pair pair = iterator.next();
            if(pair.getValue() == key){
                pair.setValue(key);
                return;
            }
            data[h].offerLast(new Pair(key, value));
        }
    }

    public int get(int key){
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while(iterator.hasNext()){
            Pair pair = iterator.next();
            if(pair.getKey() == key){
                return pair.value;
            }
        }
        return -1;
    }

    public void remove(int key){
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while(iterator.hasNext()){
            Pair pair = iterator.next();
            if(pair.getKey() == key){
                data[h].remove(pair);
                return;
            }

        }
    }

    public int hash(int key){
        return key % BASE;
    }
}
