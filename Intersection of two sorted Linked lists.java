import java.io.*;
import java.util.*;
class Node 
{
    int data;
    Node next;
    
    Node(int a)
        {
            data = a;
            next = null;
        }
}
class GfG
{
    static Scanner sc = new Scanner(System.in);
    public static Node inputList(int size)
    {
        Node head, tail;
        int val;
        
        val = sc.nextInt();
        
        head = tail = new Node(val);
        
        size--;
        
        while(size-->0)
        {
            val = sc.nextInt();
            tail.next = new Node(val);
            tail = tail.next;
        }
        
        return head;
    }
    
    public static void printList(Node n)
    {
        while(n!=null)
        {
            System.out.print(n.data + " ");
            n = n.next;
        }
    }
    
    public static void main(String args[])
        {
            
            int t = sc.nextInt();
            while(t-->0)
                {
                    int n , m;
                    
                    n = sc.nextInt();
                    m = sc.nextInt();
                    
                    Node head1 = inputList(n);
                    Node head2 = inputList(m);
                    
                    Sol obj = new Sol();
                    
                    Node result = obj.findIntersection(head1, head2);
	    
	                printList(result);
	                System.out.println();
                }
        }
}// } Driver Code Ends


/* Node of a linked list
 class Node {
   int data;
    Node next;
    Node(int d)  { data = d;  next = null; }
}
*/

class Sol
{

    public static Node findIntersection(Node head1, Node head2)
    {
        Node p1=head1, p2=head2;
        Node head=null, tail=null;
        
        while(p1!=null && p2!=null)
            if(p1.data > p2.data)
                // nodes dont match
                // moving to next node in list with smaller node
                p2 = p2.next;
            
            else if(p2.data > p1.data)
                // nodes dont match
                // moving to next node in list with smaller node
                p1 = p1.next;
            
            else
            {
                // nodes match
                
                if(head==null)
                    head = tail = new Node(p1.data);
                    // creating new head for intersection list
                else
                {
                    // appending new node at the end
                    tail.next = new Node(p1.data);
                    tail = tail.next;
                }
                p1 = p1.next;
                p2 = p2.next;
                // moving to next nodes in both lists
            }
        
        return head;
    }

}
