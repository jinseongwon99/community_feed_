package com.jinsungwon99.common.domain;

public class PositiveIntegerCounter {
    
        private int count;

        public PositiveIntegerCounter() {

            this.count = 0;
        }

        public  void increase(){

            this.count++;
        }

        public  void decrease(){
            if(count <= 0){
                return;
            }
            this.count--;
        }
    }



