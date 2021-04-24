package com.company;

public class StringLinkedList {
    class Node{
        public Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }
        String value;
        Node next;

    }

    private Node head;
    private Node tail;

    public StringLinkedList() {
        this.head = null;
        this.tail = null;
    }

    void prepend(String value){
        Node node=new Node(value,head);
        if(head==null)
            tail=node;
        head=node;
    }

    String first(){
        if(head==null)
            throw new IndexOutOfBoundsException();
        return head.value;
    }

    void removeFirst(){
        if(head==null)
            throw new IndexOutOfBoundsException();
        if(head==tail)
            tail=null;
        head=head.next;

    }


    //Zadanie 4
    void append(String value){
        if(head==null)
            prepend(value);
        else {
            Node node = new Node(value, null);
            tail.next = node;
            tail = tail.next;
        }
    }

    String last(){
        if(head==null)
            throw new IndexOutOfBoundsException();
        return tail.value;
    }

    void removeLast(){
        if(head==tail)
            removeFirst();
        else {
            Node tmp = head;
            while (tmp.next != tail)
                tmp = tmp.next;
            tmp.next = null;
            tail = tmp;
        }
    }

    //Zadanie 5
    String get(int index){
        Node tmp=head;
        int count=0;
        while(tmp!=null){
            if(count==index)
                return tmp .value;
            count++;
            tmp=tmp.next;
        }
        throw new IndexOutOfBoundsException();
    }

    void insert(String value, int index){

        if(index==0) {
            prepend(value);
        } else {
            int i;
            Node tmp=head;
            for(i=0;tmp!=null && i<index-1;i++){
                tmp=tmp.next;
            }
            if(i==index || head==null)
                throw new IndexOutOfBoundsException();
            Node newNode=new Node(value, tmp.next);
            tmp.next=newNode;


        }

    }

    void remove(int index){
        boolean indexOK=false;
        if(index==0){
            removeFirst();
            indexOK = true;
        }else {
            Node tmp = head;
            for(int i=0;tmp!=null && i<index-1;i++){
                tmp=tmp.next;
            }
            if(tmp!=null && tmp.next!=null){
                Node next=tmp.next.next;
                tmp.next=next;
                indexOK=true;
            }

            if(!indexOK)
                throw new IndexOutOfBoundsException();
        }
    }

}
