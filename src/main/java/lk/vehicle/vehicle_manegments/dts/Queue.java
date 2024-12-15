package lk.vehicle.vehicle_manegments.dts;

public class Queue {
    Node top;

    public void push(int data){
        Node node = new Node(data);

        if(top == null){
            top = node;
        }else {
            Node temp = top;

            while(temp.next != null){
                temp = temp.next;
            }

            temp.next = node;

        }

        System.out.println (data);

        System.out.println (data);

    }}
