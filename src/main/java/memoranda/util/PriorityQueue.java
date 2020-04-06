package main.java.memoranda.util;

import nu.xom.Element;

public class PriorityQueue {
        
        /* Priority queue implemented with Heap Data Structure,
         * sorts annotations by priority
         */
        
        private Pair[] a;
        private int n;

        /**
         * Constructor to build a PriorityQueue based on size passed in
         * @param size
         */
        public PriorityQueue(int size){
                a = new Pair[size+2];
                n = 0;
        }

        /**
         * Method to insert Pair passed in to the set the priority int
         * @param x
         */
        public void insert(Pair x){
                ++n;
                a[n]=x;
                for(int j=n; j>1 && a[j].getPriority() < a[j/2].getPriority(); j/=2)
                {
                        Pair t = a[j];
                        a[j] = a[j/2];
                        a[j/2] = t;
                }
        }

        /**
         * Method to remove Element from the PriorityQueue and return the Element
         * @return Element
         */
        public Element remove(){
                if(!this.Empty()){
                        Element m = a[1].getElement();  
                        a[1] = a[n];
                        --n;
                        int j = 1;
                        while(2*j<=n)
                        {
                                int k=2*j;
                                if(k+1<=n && a[k+1].getPriority() < a[k].getPriority())
                                        k=k+1;  
                                if(a[j].getPriority() < a[k].getPriority())
                                        break;
                                Pair t = a[j]; 
                                a[j] = a[k];
                                a[k] = t;  
                                j = k;
                        }
                        return m;
                }
                else 
                        return null;
        }

        /**
         * Method to check if the PriorityQueue is empty or not.
         * @return boolean
         */
        public boolean Empty(){
                return n==0;
        }

}