// Java program for the above approach
import java.io.*;
import java.util.*;

// A Linked List Node
class ListNode {
	int val;
	ListNode next;

	// Constructor
	ListNode(int val)
	{
		this.val = val;
		this.next = null;
	}
}

public class Question8 {

	// Function to create Node
	static ListNode getNode(int data)
	{
		ListNode temp = new ListNode(data);
		return temp;
	}

	// Function to print the Linked List
	static void printList(ListNode head)
	{
		while (head.next != null) {
			System.out.print(head.val + " -> ");
			head = head.next;
		}
		System.out.print(head.val);
	}

	// Function that removes continuous nodes
	// whose sum is K
	static ListNode removeZeroSum(ListNode head, int K)
	{
		// Root node initialise to 0
		ListNode root = new ListNode(0);

		// Append at the front of the given
		// Linked List
		root.next = head;

		// Map to store the sum and reference
		// of the Node
		Map<Integer, ListNode> umap
			= new HashMap<Integer, ListNode>();

		umap.put(0, root);

		// To store the sum while traversing
		int sum = 0;

		// Traversing the Linked List
		while (head != null) {

			// Find sum
			sum += head.val;

			// If found value with (sum - K)
			if (umap.containsKey(sum - K)) {

				ListNode prev = umap.get(sum - K);
				ListNode start = prev;

				// Delete all the node
				// traverse till current node
				int aux = sum;

				// Update sum
				sum = sum - K;

				// Traverse till current head
				while (prev != head) {
					prev = prev.next;
					aux += prev.val;
					if (prev != head) {
						umap.remove(aux);
					}
				}

				// Update the start value to
				// the next value of current head
				start.next = head.next;
			}

			// If (sum - K) value not found
			else if (!umap.containsKey(sum)) {
				umap.put(sum, head);
			}

			head = head.next;
		}

		// Return the value of updated
		// head node
		return root.next;
	}

	// Driver code
	public static void main(String[] args)
	{
		// head Node
		ListNode head;

		// Create Linked List
		head = getNode(1);
		head.next = getNode(2);
		head.next.next = getNode(-3);
		head.next.next.next = getNode(3);
		head.next.next.next.next = getNode(1);

		// Given sum K
		int K = 5;

		// Function call to get head node
		// of the updated Linked List
		head = removeZeroSum(head, K);

		// Print the updated Linked List
		if (head != null)
			printList(head);
	}
}

