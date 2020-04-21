

package mini_project;
import java.util.* ;

class Node_e//node of events
{
	String event_name ; //name of event
	String time_slot_1 ; //First time slot of event ;
	String time_slot_2 ; //Second time slot of event ;
	Node_p data ;//array of tree is data for tree of events
	Node_e left ;//left ptr
	Node_e right ;//right ptr
	Node_e()//constructor
	{
		data = null ;
		left = null ;
		right = null ;
	}
}
class Event_tree//tree of events
{
	Node_e root_e ;
	Event_tree()//constructor
	{
		root_e = null ;
	}
	Participant_tree t[] = new Participant_tree[10] ;
	void create_e(int cnt) // function to create tree
	{
		t[cnt] = new Participant_tree() ;
		int flag = 0 ;
		Node_e temp = new Node_e() ;//node of event
		Scanner sc = new Scanner(System.in) ;
		System.out.println() ;
		System.out.println() ;
		System.out.print("\t\t\t\tEnter name of event : ") ;
		temp.event_name = sc.nextLine() ;
		//System.out.println() ;
		System.out.print("\t\t\t\tEnter first time slot in the form (t1 to t2) : ") ;//this needs validation..
													//I think we should keep it like each event has one time slot on each day
		temp.time_slot_1 = sc.nextLine() ;			//Innovation is 2 day event..so two slots for each event
		//System.out.println() ;
		System.out.print("\t\t\t\tEnter second time slot in the form (t1 to t2) : ") ;
		temp.time_slot_2 = sc.nextLine() ;
		if(root_e == null)
		{
			root_e = temp ;
		}
		else
		{
			Node_e ptr = root_e ;
			while(flag == 0)
			{
				if(temp.event_name.compareTo(ptr.event_name)< 0 )// condition to attach node to left
				{
					if(ptr.left != null)
					{
						ptr = ptr.left ;
					}
					else
					{
						ptr.left = temp ;
						flag = 1 ;
					}
				}
				if(temp.event_name.compareTo(ptr.event_name) > 0 )// condition to attach node to right
				{
					if(ptr.right != null)
					{
						ptr = ptr.right ;
					}
					else
					{
						ptr.right = temp ;
						flag = 1 ;
					}

				}
			}
		}
		
	}
	void inorder_e(Node_e ptr,int i)// displaying the list
	{
		
		if(root_e == null)
		{
			System.out.println("\t\t\t\tList of events is empty") ;
		}
		if(ptr != null)
		{
			
			inorder_e(ptr.left,i);
			System.out.println() ;
			System.out.println() ;
			System.out.println("\t\t\t\t\t\t\t**\tEvent name : "+ptr.event_name+"\t**");
			System.out.println() ;
			if(ptr.data != null)
			{
				
				System.out.println("\t\t\t\t\t\t--\tList of participants\t--") ;//displaying according to time slots
				System.out.println() ;
				System.out.println("\t\t\t\tTime slot 1 : ") ;
				//System.out.println() ;
				t[i].inorder(t[i].root_p[0], 0);
				//System.out.println() ;
				System.out.println("\t\t\t\tTime slot 2 : ") ;
				//System.out.println() ;
				t[i].inorder(t[i].root_p[1], 1);
			}
			else
			{
				System.out.println("\t\t\t\t!!\tList of participants is empty\t!!") ;
				System.out.println() ;
			}
			i++ ;
			inorder_e(ptr.right,i);
		}
	}
	void display_eventlist(Node_e ptr)
	{
		if(root_e == null)
		{
			System.out.println("\t\t\t\t!!\tList of events is empty\t!!") ;
		}
		if(ptr != null)
		{
			
			display_eventlist(ptr.left);
			System.out.println("\t\t\t\tEvent name : "+ptr.event_name+"\t");
			System.out.println() ;
			display_eventlist(ptr.right);
		}		
	}
	void Search_e(String s)//searching an event in list
	{
		Node_e ptr = root_e ;
		int flag = 0 ;
		if(ptr == null)
		{
			System.out.println("\t\t\t\tList of events is empty") ;
		}
		while(ptr != null)
		{
			if(s.equalsIgnoreCase(ptr.event_name))
			{
				flag = 1 ;
				break ;
			}
			if(s.compareTo(ptr.event_name) >  0)
			{
				ptr = ptr.right ;
			}
			else
			{
				ptr = ptr.left ;
			}
		}
		if(flag == 1)
		{
			System.out.println("\t\t\t\tEvent name : "+ptr.event_name) ;
		}
		else
		{
			System.out.println("\t\t\t\t -----   This Event has not found in list   -----" ) ;// if the event is not found
		}
	}
	void insert_e(String s,int cnt,int i)
	{
		Node_e ptr = root_e ;//first searching event in the event tree 
		int flag = 0 ;
		
		if(ptr == null)
		{
			System.out.println("\t\t\t\tList of events is empty") ;
		}
		while(ptr != null)
		{
			
			if(s.equalsIgnoreCase(ptr.event_name))
			{
				flag = 1 ;
				break ;
			}
			if(s.compareTo(ptr.event_name) >  0)
			{
				ptr = ptr.right ;
				i++ ;
			}
			else
			{
				ptr = ptr.left ;
				i++ ;
			}
		}
		if(flag == 1)//if event is present in the tree then creating tree of participants
		{
			
			System.out.println("\t\t\t\tEvent name : "+ptr.event_name) ;
			ptr.data = t[i].create(ptr) ; 
			System.out.println() ;
			System.out.println("\t\t\t\t<<<<\tCongratulations!! Your Registration is confirmed\t>>>>") ;
		}
		else
		{
			System.out.println("\t\t\t\t -----   This Event has not found in list   -----" ) ;// if the event is not found
		}
	}
	void delete_e(String s)//function to delete an event
	{
		Node_e ptr = root_e ;
		Node_e prev = null ;
		int flag = 0 ;
		if(ptr == null)
		{
			System.out.println("\t\t\t\tList of event is empty") ;
		}
		while(ptr != null)//searching position of the event in list
		{
			if(s.equalsIgnoreCase(ptr.event_name))
			{
				flag=1;
				break;
			}
			if(s.compareTo(ptr.event_name)>0)
			{
				prev = ptr;
				ptr=ptr.right;
			}
			else
			{
				prev = ptr;
				ptr=ptr.left;
			}
		}
		if(flag == 1)
		{
			if(ptr.left == null && ptr.right == null)// if the node is leaf node
			{
				if(ptr == root_e)// if the node is root
				{
					root_e = null ;
					System.out.println("\t\t\t\tEvent List is empty") ;
				}
				else if(s.compareTo(prev.event_name) > 0)
				{

					prev.right = null ;


					System.out.println("\t\t\t\tEvent is deleted from the List") ;
				}
				else
				{

					prev.left = null ;

					System.out.println("\t\t\t\tEvent is deleted from the List") ;
				}
			}
			if(ptr.left != null && ptr.right == null)// if the node has only left child
			{
				if(ptr == root_e)// if the node is root node
				{
					prev = ptr ;
					root_e = ptr.left ;
					prev = null ;
				}
				else if(s.compareTo(prev.event_name) > 0)
				{
					prev.right = ptr.left ;
					ptr.left = null ;
					System.out.println("\t\t\t\tEvent is deleted from the List") ;
				}
				else
				{
					prev.left = ptr.left ;
					ptr.left = null ;
					System.out.println("\t\t\t\tEvent is deleted from the List") ;
				}
			}
			if(ptr.left == null && ptr.right != null)// if the node has only right child
			{
				if(ptr == root_e)//if the node is root node
				{
					prev = ptr ;
					root_e = ptr.right ;
					prev = null ;
				}
				else if(s.compareTo(prev.event_name) > 0)
				{
					prev.right = ptr.right ;
					ptr.right = null ;
					System.out.println("\t\t\t\tEvent is deleted from the List") ;
				}
				else
				{
					prev.left = ptr.right ;
					ptr.right = null ;
					System.out.println("\t\t\t\tEvent is deleted from the List") ;
				}
			}
			if(ptr.left != null && ptr.right != null)// if the word has both right and left child
			{
				Node_e temp = ptr.left;
				Node_e temp_parent = ptr;


				while(temp.right != null)
				{
					temp_parent = temp ;
					temp = temp.right ;
				}
				if(temp_parent.left == temp)
				{
					temp_parent.left = temp.left ;
					ptr.event_name = temp.event_name ;
					//ptr.meaning = temp.meaning ;

				}
				else
				{
					temp_parent.right = temp.left ;
					ptr.event_name = temp.event_name ;
					//ptr.meaning = temp.meaning ;
				}
				temp = null ;
				System.out.println("\t\t\t\tEvent is deleted from the List") ;

			}

		}
		else
		{
			System.out.println("\t\t\t\t -----   This Event has not found in the list   -----" ) ;//if the node is not present in the dictionary
		}
	}
}
class Node_p//node of participants
{
	String p_name ;//name of participant
	String email_id ;// email id of participant
	String clg_name ; // name of college
	Node_p left ;//left ptr
	Node_p right ;//right ptr
	Node_p()//constructor
	{
		p_name = "" ;
		email_id = "" ;
		clg_name = "" ;
		left = null ;
		right = null ;
	}
}
class Participant_tree//tree of participants
{
	Node_p root_p[] = new Node_p[2] ;//array of tree
	Participant_tree()//constructor
	{
		for(int i = 0 ; i < 2 ; i++)
		{
			root_p[i] = null ;
		}
	}
	Node_p create(Node_e temp1) // function to create tree of participants
	{
		int flag = 0 ;
		int t ; // time slot
		Node_p temp = new Node_p() ;
		Scanner sc = new Scanner(System.in) ;
		System.out.println() ;
		System.out.print("\t\t\t\tEnter Name of Participant : ") ;
		temp.p_name = sc.next() ;
		System.out.print("\t\t\t\tEnter email_id of the Participant : ") ;
		temp.email_id = sc.next() ;
		System.out.print("\t\t\t\tEnter Name of College : ") ;
		temp.clg_name = sc.next() ;
		System.out.println("\t\t\t\t0. First Time slot : "+temp1.time_slot_1) ;
		System.out.println("\t\t\t\t1. Second Time slot : "+temp1.time_slot_1) ;
		System.out.println() ;
		System.out.print("\t\t\t\tEnter time slot no. (0/1) : ") ;
		t = sc.nextInt() ;
		if(root_p[t] == null)
		{
			root_p[t] = temp ;
		}
		else
		{
			Node_p ptr = root_p[t] ;
			while(flag == 0)
			{
				if(temp.email_id.compareTo(ptr.email_id)< 0 )// condition to attach node to left
				{
					if(ptr.left != null)
					{
						ptr = ptr.left ;
					}
					else
					{
						ptr.left = temp ;
						flag = 1 ;
					}
				}
				if(temp.email_id.compareTo(ptr.email_id) > 0 )// condition to attach node to right
				{
					if(ptr.right != null)
					{
						ptr = ptr.right ;
					}
					else
					{
						ptr.right = temp ;
						flag = 1 ;
					}

				}
			}

		}
		return temp ;

	}
	void inorder(Node_p ptr,int t)// displaying the dictionary
	{
		
		if(root_p[t] == null)
		{
			System.out.println("\t\t\t\tList of Participants is empty") ;
		}
		if(ptr != null)
		{
			inorder(ptr.left,t);
			System.out.println() ;
			System.out.println("\t\t\t\tParticipants Name : "+ptr.p_name+"");
			System.out.println("\t\t\t\tEmail id : "+ptr.email_id+"");
			System.out.println("\t\t\t\tCollege Name : "+ptr.clg_name) ;
			System.out.println() ;
			inorder(ptr.right,t);
		}
	}
	void Search(String s, int time[])//time[] = array of time slot to print the time slot
	{
		int flag = 0 ;
		Node_p ptr = null ;
		int i = 0 ;
		for(i = 0 ; i < 2 ; i++)
		{
			ptr = root_p[i] ;
			
			if(ptr == null)
			{
				System.out.println("\t\t\t\tList of Participants is empty") ;
			}
			while(ptr != null)
			{
				if(s.equalsIgnoreCase(ptr.email_id))
				{
					flag = 1 ;
					break ;
				}
				if(s.compareTo(ptr.email_id) >  0)
				{
					ptr = ptr.right ;
				}
				else
				{
					ptr = ptr.left ;
				}
			}
		}
		
		if(flag == 1)
		{
			System.out.println("\t\t\t\tAlloted Time Slot : "+time[i]) ;
			System.out.println("\t\t\t\tParticipant Name : "+ptr.p_name) ;
			System.out.println("\t\t\t\tEmail_id : "+ptr.email_id) ;
			System.out.println("\t\t\t\tName of College : "+ptr.clg_name) ;
		}
		else
		{
			System.out.println("\t\t\t\t -----   This Participant has not registered for the event   -----" ) ;
		}
	}
	void delete(String s)//function to delete a word
	{
		int i = 0 ;
		int flag = 0 ;
		for(i = 0 ; i < 2 ; i++)
		{
			Node_p ptr = root_p[i] ;
			Node_p prev = null ;
			
			if(ptr == null)
			{
				System.out.println("\t\t\t\tList of Participants is empty") ;
			}
			while(ptr != null)//searching position of the word in dictionary
			{
				if(s.equalsIgnoreCase(ptr.email_id))
				{
					flag=1;
					break;
				}
				if(s.compareTo(ptr.email_id) > 0)
				{
					prev = ptr;
					ptr=ptr.right;
				}
				else
				{
					prev = ptr;
					ptr=ptr.left;
				}
			}
			if(flag == 1)
			{
				if(ptr.left == null && ptr.right == null)// if the node is leaf node
				{
					if(ptr == root_p[i])// if the node is root
					{
						root_p[i] = null ;
						System.out.println("\t\t\t\tList of Participants is Empty") ;
					}
					else if(s.compareTo(prev.email_id) > 0)
					{

						prev.right = null ;


						System.out.println("\t\t\t\tRegistration is Cancelled") ;
					}
					else
					{

						prev.left = null ;

						System.out.println("\t\t\t\tRegistration is Cancelled") ;
					}
				}
				if(ptr.left != null && ptr.right == null)// if the node has only left child
				{
					if(ptr == root_p[i])// if the node is root node
					{
						prev = ptr ;
						root_p[i] = ptr.left ;
						prev = null ;
					}
					else if(s.compareTo(prev.email_id) > 0)
					{
						prev.right = ptr.left ;
						ptr.left = null ;
						System.out.println("\t\t\t\tRegistration is Cancelled") ;
					}
					else
					{
						prev.left = ptr.left ;
						ptr.left = null ;
						System.out.println("\t\t\t\tRegistration is Cancelled") ;
					}
				}
				if(ptr.left == null && ptr.right != null)// if the node has only right child
				{
					if(ptr == root_p[i])//if the node is root node
					{
						prev = ptr ;
						root_p[i] = ptr.right ;
						prev = null ;
					}
					else if(s.compareTo(prev.email_id) > 0)
					{
						prev.right = ptr.right ;
						ptr.right = null ;
						System.out.println("\t\t\t\tRegistration is Cancelled") ;
					}
					else
					{
						prev.left = ptr.right ;
						ptr.right = null ;
						System.out.println("\t\t\t\tRegistration is Cancelled") ;
					}
				}
				if(ptr.left != null && ptr.right != null)// if the node has both right and left child
				{
					Node_p temp = ptr.left;
					Node_p temp_parent = ptr;


					while(temp.right != null)
					{
						temp_parent = temp ;
						temp = temp.right ;
					}
					
					if(temp_parent.left == temp)
					{
						temp_parent.left = temp.left ;
						ptr.email_id = temp.email_id ;
						ptr.p_name = temp.p_name ;
						ptr.clg_name = temp.clg_name ;

					}
					else
					{
						temp_parent.right = temp.left ;
						ptr.email_id = temp.email_id ;
						ptr.p_name = temp.p_name ;
						ptr.clg_name = temp.clg_name ;
					}
					temp = null ;
					System.out.println("\t\t\t\tRegistration is Cancelled") ;

				}

			}
		}
		
	}

}
public class Mini_project {

	public static void main(String args[])
	{
		int ch = 0 ;
		int no_e ; //no of events
		int cnt = 0 ;
		
		Scanner sc = new Scanner(System.in) ;
		Event_tree a = new Event_tree() ;
		Participant_tree p = new Participant_tree() ;
		
		System.out.println() ;
		System.out.println("\t\t\t\t\t\t ------------------------------------------------------------------------------------") ;
		System.out.println("\t\t\t\t\t\t|                                                                                    |") ;
		System.out.println("\t\t\t\t\t\t|								  		     |") ;
		System.out.println("\t\t\t\t\t\t|\t\t\tAUTOMATING INNOVATION TICKETING SYSTEM                       |") ;
		System.out.println("\t\t\t\t\t\t|                                                                                    |") ;
		System.out.println("\t\t\t\t\t\t|                                                                                    |") ;
		System.out.println("\t\t\t\t\t\t ------------------------------------------------------------------------------------") ;
		System.out.println() ;
		System.out.println() ;
		System.out.println() ;
		System.out.println() ;
		do
		{
			System.out.println() ;
			System.out.println() ;
			System.out.println() ;
			System.out.println("\t\t\t\t1. Menu for STUDENT PANEL") ;
			System.out.println("\t\t\t\t2. Menu for PARTICIPANTS") ;
			System.out.println("\t\t\t\t0. Exit") ;
			System.out.println() ;
			System.out.print("\t\t\t\tSelect Your Choice : ") ;
			ch = sc.nextInt() ;
			System.out.println() ;
			switch(ch)
			{
			case 1 :
				int ch1 = 0 ;
				Scanner op = new Scanner(System.in) ;
				do
				{
					System.out.println() ;
					System.out.println("\t\t\t\t\t\t\t\t\t\t -------------------------------") ;
					System.out.println("\t\t\t\t\t\t\t\t\t\t|                               |") ;
					System.out.println("\t\t\t\t\t\t\t\t\t\t|\t  STUDENT PANEL     	|") ;
					System.out.println("\t\t\t\t\t\t\t\t\t\t|                               |") ;
					System.out.println("\t\t\t\t\t\t\t\t\t\t -------------------------------") ;
					System.out.println() ;
					System.out.println() ;
					System.out.println("\t\t\t\t1. Accept Event Details") ;
					System.out.println("\t\t\t\t2. Insert Activity Records") ;
					System.out.println("\t\t\t\t3. Display Rocords of all Events") ;
					System.out.println("\t\t\t\t4. Search a particular event in the list") ;
					System.out.println("\t\t\t\t5. Search a particular Participant") ;//Think about this later
					System.out.println("\t\t\t\t6. Remove an event from the list") ;
					System.out.println("\t\t\t\t0. Exit") ;//not taking award winner as our project is just ticketing system
					System.out.println() ;
					System.out.print("\t\t\t\tSelect Your Choice : ") ;
					ch1 = sc.nextInt() ;
					System.out.println() ;
					switch(ch1)
					{
					case 1 :
						System.out.print("\t\t\t\tEnter Total No of Events : ") ;//Accepting no of events
						no_e = sc.nextInt() ;
						System.out.println() ;
						
						for(int i = 0 ; i < no_e ; i++)
						{
							a.create_e(cnt) ;
							cnt++ ;
						}
						break ;
					case 2 :
						//Why do we need this?
						//Insertion will happen in participant section
						break ;
					case 3 :
						a.inorder_e(a.root_e,0);
						break ;
					case 4 :
						System.out.print("\t\t\t\tEnter the name of event you want to search : ") ;
						String s = op.nextLine() ;
						a.Search_e(s) ;
						break ;
					case 5 :
						break ;
					case 6 :
						System.out.print("\t\t\t\tEnter the name of event you want to Remove : ") ;
						String k  = op.nextLine() ;
						a.delete_e(k);
						break ;
					case 0 :
						System.out.println() ;
						System.out.println("\t\t\t\t\tTHANK YOU !") ;
						break ;
					default :
						System.out.println() ;
						System.out.println("\t\t\t\t\tINVALID CHOICE...TRY AGAIN") ;
						break ;
					}
				}while(ch1 != 0) ;
				break ;
			case 2 :
				Scanner op1 = new Scanner(System.in) ;
				int ch2 = 0 ;
				do
				{
					System.out.println() ;
					System.out.println("\t\t\t\t\t\t\t\t\t\t -------------------------------") ;
					System.out.println("\t\t\t\t\t\t\t\t\t\t|                               |") ;
					System.out.println("\t\t\t\t\t\t\t\t\t\t|\t  PARTICIPANTS     	|") ;
					System.out.println("\t\t\t\t\t\t\t\t\t\t|                               |") ;
					System.out.println("\t\t\t\t\t\t\t\t\t\t -------------------------------") ;
					System.out.println() ;
					System.out.println() ;
					System.out.println("\t\t\t\t1. Display Event Details") ;
					System.out.println("\t\t\t\t2. Student Registration") ;
					System.out.println("\t\t\t\t3. Search Student Details") ;
					System.out.println("\t\t\t\t4. Cancel Registration") ;
					System.out.println("\t\t\t\t5. Unique Code for On-Site Registration") ;//Think about this later
					System.out.println("\t\t\t\t0. Exit") ;//not taking nearby events cause even if we add that feature it should be displayed automatically.. no need of menu
					System.out.println() ;
					System.out.print("\t\t\t\tSelect Your Choice : ") ;
					ch2 = sc.nextInt() ;
					System.out.println() ;
					switch(ch2)
					{
					case 1 :
						//Radhika can add file code or function here
						a.inorder_e(a.root_e,0);
						break ;
					case 2 :
						String s ;
						System.out.println("\t\t\t\t\t\t^^^^^\tNAME OF THE EVENTS IN INNOVATION\t^^^^^") ;
						System.out.println() ;
						a.display_eventlist(a.root_e);
						System.out.println() ;
						System.out.print("\t\t\t\tWhich Event Would You Like to Register for ? : ") ;
						s = op1.nextLine() ;
						System.out.println() ;
						a.insert_e(s,cnt,0) ;
						break ;
					case 3 :
						
						break ;
					case 4 :
						System.out.println() ;
						System.out.println("\t\t\t\tEnter email id of participant : ") ;
						String f = op1.nextLine() ;
						p.delete(f) ;
						break ;
					case 0 :
						System.out.println() ;
						System.out.println("\t\t\t\t\tTHANK YOU !") ;
						break ;
					default :
						System.out.println() ;
						System.out.println("\t\t\t\t\tINVALID CHOICE...TRY AGAIN") ;
						break ;
					}
				}while(ch2 != 0) ;


				break ;
			case 0 :
				System.out.println() ;
				System.out.println("\t\t\t\t\tTHANK YOU !") ;
				break ;
			default :
				System.out.println() ;
				System.out.println("\t\t\t\t\tINVALID CHOICE...TRY AGAIN") ;
				break ;
			}

		}while(ch != 0) ;
	}
}
