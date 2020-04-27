package mini_project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.* ;
import java.util.regex.Pattern;

class Innovation implements Serializable
{
	String eventname;
	String intro;
	String date;
	String time;
	String time2;
	int teamsize;
	double regfee;
	String contactname;
	String contactno;

	Innovation()
	{
		eventname = "";
		intro = "";
		date = "";
		time= "";
		time2 = "";
		teamsize = 0;
		regfee = 0.0;
		contactname= "";
		contactno= "";
	}
	String toStringg()
	{
		return "Innovation{"+"Event Name : " + eventname + " , \nIntroduction : " + intro + " , Date of event : " + date + " , Time of event_1: " + time + " , Time of event_2: " + time2 + " , Team Size : " + teamsize + " , Registration Fee : " + regfee+ " , Contact Name : " + contactname+" , Contact Number : " + contactno+ " }";
	}
}
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
		data = null ;//initialization
		left = null ;
		right = null ;
	}
}
class Event_tree//tree of events
{
	Node_e root_e ;//root node of tree
	Event_tree()//constructor
	{
		root_e = null ;
	}

	void create_e(int cnt, Participant_tree t[]) // function to create tree
	{
		t[cnt] = new Participant_tree() ;
		int flag = 0 ;
		int flag1 = 0;
		Node_e temp = new Node_e() ;//node of event
		Scanner sc = new Scanner(System.in) ;
		System.out.println() ;
		System.out.println() ;
		int y = 0 ;
		int y1 = 0;
		do
		{
			do
			{	
				flag1 = 0;
				System.out.print("\t\t\t\tEnter name of event : ") ;//accepting information of event
				temp.event_name = sc.nextLine() ;
				if(temp.event_name.matches("^[a-zA-Z]*$")==false)
				{
					flag1 = 1;
					System.out.println("\t\t\t\t*****Please enter a valid event name!*****");
				}
			}while(flag1==1);
			y = Search_e(temp.event_name,t,0) ;

			if(y != -1)
			{
				System.out.println() ;
				System.out.println("\t\t\t\tThis event is already in the event list ...") ;
				System.out.println() ;
				System.out.print("\t\t\t\tDo you want to add another event? (yes : 1 /no : 2) : ") ; //validation for event if event is already present in the list
				y1 = sc.nextInt() ;
				if(y1 == 2)
				{
					y = -1 ;
				}
				if(y1 < 1 || y1 > 2) 
				{
					System.out.println("\t\t\t\tInvalid input") ;
					y1 = 1 ;
				}
			}
		}while(y1 == 1 || y != -1) ;
		//System.out.println() ;
		if(y1 == 0 || y1 == 1)
		{
			System.out.print("\t\t\t\tEnter first time slot in the form (t1-t2) : ") ;
			temp.time_slot_1 = sc.nextLine() ;
			//System.out.println() ;
			System.out.print("\t\t\t\tEnter second time slot in the form (t1-t2) : ") ;
			temp.time_slot_2 = sc.nextLine() ;
			if(root_e == null)//if tree is empty
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

	}
	void inorder_e(Node_e ptr,int i,Participant_tree t[])// displaying the list
	{

		if(root_e == null)//if tree is empty
		{
			System.out.println("\t\t\t\tList of events is empty") ;
		}
		if(ptr != null)
		{

			inorder_e(ptr.left,i,t);
			System.out.println() ;
			System.out.println() ;
			System.out.println("\t\t\t\t\t\t\t**\tEvent name : "+ptr.event_name+"\t**");//displaying name of event
			System.out.println() ;
			if(ptr.data != null)
			{

				System.out.println("\t\t\t\t\t\t--\tList of participants\t--") ;//displaying according to time slots
				System.out.println() ;
				System.out.println("\t\t\t\t# Time slot 1 : ") ;
				//System.out.println() ;
				t[i].inorder(t[i].root_p[0], 0);
				//System.out.println() ;
				System.out.println("\t\t\t\t# Time slot 2 : ") ;
				//System.out.println() ;
				t[i].inorder(t[i].root_p[1], 1);
			}
			else
			{
				System.out.println() ;
				System.out.println("\t\t\t\t!!\tList of participants is empty\t!!") ; //if list is empty
				System.out.println() ;
			}
			i++ ;
			inorder_e(ptr.right,i,t);
		}
	}
	void display_eventlist(Node_e ptr)//displaying all the events
	{
		if(root_e == null)
		{
			System.out.println("\t\t\t\t!!\tList of events is empty\t!!") ;
		}
		if(ptr != null)
		{

			display_eventlist(ptr.left);
			System.out.println("\t\t\t\tEvent name : "+ptr.event_name+"\t");//displaying all the event name without list of participants
			System.out.println() ;
			display_eventlist(ptr.right);
		}		
	}
	int Search_e(String s,Participant_tree t[],int ch)//searching an event in list
	{
		int j = 0 ;
		Node_e ptr = root_e ;
		int flag = 0 ;
		if(ptr == null && ch == 1)//if tree is empty
		{
			System.out.println("\t\t\t\t---  List of events is empty   ----") ;
		}
		while(ptr != null)
		{
			if(s.equalsIgnoreCase(ptr.event_name))//if string matches with event name
			{
				flag = 1 ;
				break ;
			}
			if(s.compareTo(ptr.event_name) >  0)//if string is alphabetically greater than current node's event name
			{
				ptr = ptr.right ;
				j++ ;
			}
			else//if string is alphabetically smaller than current node's event name
			{
				ptr = ptr.left ;
				j++ ;
			}
		}
		if(flag == 1)//if event is found in the list
		{
			System.out.println() ;
			System.out.println() ;
			System.out.println("\t\t\t\t\t\t\t**\tEvent name : "+ptr.event_name+"\t**");//displaying name of event
			System.out.println() ;
			if(ptr.data != null)
			{

				System.out.println("\t\t\t\t\t\t--\tList of participants\t--") ;//displaying according to time slots
				System.out.println() ;
				System.out.println("\t\t\t\t# Time slot 1 : ") ;
				//System.out.println() ;
				t[j].inorder(t[j].root_p[0], 0);
				//System.out.println() ;
				System.out.println("\t\t\t\t# Time slot 2 : ") ;
				//System.out.println() ;
				t[j].inorder(t[j].root_p[1], 1);
			}
			else
			{
				System.out.println() ;
				System.out.println("\t\t\t\t!!\tList of participants is empty\t!!") ;//if the list is empty
				System.out.println() ;
			}
			return j ;

		}
		else
		{
			System.out.println() ;
			if(ch == 1)
			{
				System.out.println("\t\t\t\t -----   This Event is not found in the list   -----" ) ;// if the event is not found
			}
			System.out.println() ;
			return -1 ;
		}
	}
	void insert_e(String s,int cnt,int i,Participant_tree t[], tree tt)//inserting a new participant record in the list
	{
		Node_e ptr = root_e ;//first searching event in the event tree 
		int flag = 0 ;

		if(ptr == null)
		{
			System.out.println() ;
			System.out.println("\t\t\t\t----   List of events is empty   ----") ;
			System.out.println() ;
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
			ptr.data = t[i].create(ptr, tt) ; //Inserting Participant record
			System.out.println() ;
			System.out.println("\t\t\t\t<<<<\tCongratulations!! Your Registration is confirmed\t>>>>") ;
		}
		else
		{
			System.out.println() ;
			System.out.println("\t\t\t\t -----   This Event is not found in the list   -----" ) ;// if the event is not found
			System.out.println() ;
		}
	}
	void delete_e(String s,int j,int m,Participant_tree t[])//function to cancel the participant's registration
	{
		int flag = 0 ;
		Node_p ptr = t[j].root_p[m] ;

		Node_p prev = null ;

		if(ptr == null)
		{
			System.out.println() ;
			System.out.println("\t\t\t\t----   List of Participants is empty   ----") ;
			System.out.println() ;
		}
		while(ptr != null)//searching position of the participant in list
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
				if(ptr == t[j].root_p[m])// if the node is root
				{
					t[j].root_p[m] = null ;
					System.out.println() ;
					System.out.println("\t\t\t\t----   Registration has been Cancelled    ----") ;
				}
				else if(s.compareTo(prev.email_id) > 0)
				{

					prev.right = null ;
					System.out.println() ;
					System.out.println("\t\t\t\t----   Registration has been Cancelled    ----") ;
				}
				else
				{

					prev.left = null ;
					System.out.println() ;
					System.out.println("\t\t\t\t----   Registration has been Cancelled    ----") ;
				}
			}
			if(ptr.left != null && ptr.right == null)// if the node has only left child
			{
				if(ptr == t[j].root_p[m])// if the node is root node
				{
					prev = ptr ;
					t[j].root_p[m] = ptr.left ;
					prev = null ;
					System.out.println("\t\t\t\t----   Registration has been Cancelled    ----") ;
				}
				else if(s.compareTo(prev.email_id) > 0)
				{
					prev.right = ptr.left ;
					ptr.left = null ;
					System.out.println() ;
					System.out.println("\t\t\t\t----   Registration has been Cancelled    ----") ;
				}
				else
				{
					prev.left = ptr.left ;
					ptr.left = null ;
					System.out.println() ;
					System.out.println("\t\t\t\t----   Registration has been Cancelled    ----") ;
				}
			}
			if(ptr.left == null && ptr.right != null)// if the node has only right child
			{
				if(ptr == t[j].root_p[m])//if the node is root node
				{
					prev = ptr ;
					t[j].root_p[m] = ptr.right ;
					prev = null ;
					System.out.println("\t\t\t\t----   Registration has been Cancelled    ----") ;
				}
				else if(s.compareTo(prev.email_id) > 0)
				{
					prev.right = ptr.right ;
					ptr.right = null ;
					System.out.println() ;
					System.out.println("\t\t\t\t----   Registration has been Cancelled    ----") ;
				}
				else
				{
					prev.left = ptr.right ;
					ptr.right = null ;
					System.out.println() ;
					System.out.println("\t\t\t\t----   Registration has been Cancelled    ----") ;
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
				System.out.println() ;
				System.out.println("\t\t\t\t----   Registration has been Cancelled    ----") ;

			}

		}
	}

}
class node //node for the bst that will be used for time slot detection and assistance 
{
	String bst_name; //student name
	String bst_email;//student email id : unique 
	String bst_college; //college name
	ArrayList <String> arr  = new ArrayList <String>();//to store the time slots of the events the student is attending
	node left;
	node right;
	String time;
	int flag;
	node()
	{
		bst_name = "";
		bst_email  = "";
		bst_college = "";
		left = null;
		right = null;
		flag = 0;
		time = "";

	}
}
class tree
{
	node bst_root;
	node search(String s, node bst_root2,int ch)//searching for the students details in the bst to analyse the previous time slots 
	{
		int flag = 0;
		node ptr = bst_root2;

		while(ptr != null)
		{
			if(s.equalsIgnoreCase(ptr.bst_email))
			{
				flag = 1 ;
				break ;
			}
			if(s.compareTo(ptr.bst_email) >  0)
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
			ptr.flag = 1;
			System.out.println("\t\t\t\tParticipant Found!");//if students email id exists in the bst 
			System.out.println("\t\t\t\tParticipant Name : "+ptr.bst_name) ;
			System.out.println("\t\t\t\tParticipant Email : "+ptr.bst_email) ;
			System.out.println("\t\t\t\tParticipant College : "+ptr.bst_college) ;
			if(ch == 0 )
			{
				System.out.println("\n\t\t\t\tYou have opted for "+ptr.time+". time slot. Kindly choose another timeslot for your event");
			}
			return ptr;
		}
		else
		{
			System.out.println() ;
			System.out.println("\t\t\t\t -----   This Participant's record does not exist   -----" ) ;// if the event is not found
			System.out.println() ;

		}
		return ptr;
	}
	void delete(String s, String time)//to delete the registration for a particular event. 
	{
		node parent = null;
		node ptr = null;
		node ptr2 = null;
		node temptempparent = bst_root;
		node tempparent = bst_root;
		ptr = bst_root;
		int flag = 0;
		do
		{


			if(ptr.bst_email.compareTo(s)<0)
			{
				parent = ptr;
				ptr = ptr.right;
			}
			else if(ptr.bst_email.compareTo(s)>0)
			{	
				parent = ptr;
				ptr = ptr.left;
			}
			if(ptr==null)
			{
				System.out.println() ;
				System.out.println("\t\t\t\tNode doesnt exist");
				System.out.println() ;
				flag = 1;
			}
			else if(s.compareTo(ptr.bst_email)==0)
			{

				//ptr2 = ptr;

				if(ptr.left==null && ptr.right==null) //leaf node
				{
					if(ptr==bst_root)
					{
						if(ptr.arr.size()==1)
						{
							bst_root = null;
							System.out.println() ;
							//System.out.println("\t\t\t\tRecord has been deleted!");
							System.out.println() ;
							flag = 1;
						}
						else
						{
							int i = 0;
							while(i!=ptr.arr.size())
							{
								if(ptr.arr.get(i)==time)
								{
									ptr.arr.remove(i);
								}

							}
							if(ptr.arr.size()==0)
							{
								bst_root = null;
								System.out.println() ;
								//System.out.println("\t\t\t\tRecord has been deleted");
								System.out.println() ;
								flag = 1;
							}
						}
					}
					else
					{
						if(parent.left==ptr)
						{
							int i = 0;
							while(i!=ptr.arr.size())
							{
								if(ptr.arr.get(i)==time)
								{
									ptr.arr.remove(i);
								}
							}
							if(ptr.arr.size()==0)
							{
								parent.left = null;
								flag = 1;
								ptr = null;

							}

						}
						else
						{
							int i = 0;
							while(i!=ptr.arr.size())
							{
								if(ptr.arr.get(i)==time)
								{
									ptr.arr.remove(i);
								}
							}
							if(ptr.arr.size()==0)
							{
								parent.right = null;
								flag = 1;
								ptr = null;
							}
						}
					}

				}
				else if(ptr.left!=null && ptr.right == null) //has only left child
				{
					if(ptr==bst_root)//if root is to be deleted 
					{
						//root = null;
						//flag = 1;
						int i = 0;
						while(i!=ptr.arr.size())
						{
							if(ptr.arr.get(i)==time)
							{
								ptr.arr.remove(i);
							}
						}
						if(ptr.arr.size()==0)
						{
							parent = ptr.left;
							ptr.left = null;
							bst_root = parent;
							ptr = null;
							flag = 1;
						}

					}
					else
					{
						if(parent.right==ptr)
						{
							int i = 0;
							while(i!=ptr.arr.size())
							{
								if(ptr.arr.get(i)==time)
								{
									ptr.arr.remove(i);
								}
							}
							if(ptr.arr.size()==0)
							{
								parent.right = ptr.left;
								ptr.left = null;
								ptr = null;
								flag = 1;
							}
						}
						else 
						{
							if(parent.left==ptr)
							{
								int i = 0;
								while(i!=ptr.arr.size())
								{
									if(ptr.arr.get(i)==time)
									{
										ptr.arr.remove(i);
									}
								}
								if(ptr.arr.size()==0)
								{
									parent.left = ptr.left;
									ptr.left = null;
									ptr = null;
									flag = 1;
								}
							}
						}
					}

				}
				else if(ptr.left==null && ptr.right!=null)//has only right child
				{
					if(ptr==bst_root)//if root is to be deleted 
					{
						//root = null;
						//flag = 1;
						int i = 0;
						while(i!=ptr.arr.size())
						{
							if(ptr.arr.get(i)==time)
							{
								ptr.arr.remove(i);
							}
						}
						if(ptr.arr.size()==0)
						{
							parent = ptr.right;
							ptr.right = null;
							bst_root = parent;
							ptr = null;
							flag = 1;
						}


					}
					else
					{
						if(parent.right==ptr)
						{
							int i = 0;
							while(i!=ptr.arr.size())
							{
								if(ptr.arr.get(i)==time)
								{
									ptr.arr.remove(i);
								}
							}
							if(ptr.arr.size()==0)
							{
								parent.right = ptr.right;
								ptr.right = null;
								ptr = null;
								flag = 1;
							}
						}
						else
						{
							if(parent.left==ptr)
							{
								int i = 0;
								while(i!=ptr.arr.size())
								{
									if(ptr.arr.get(i)==time)
									{
										ptr.arr.remove(i);
									}
								}
								if(ptr.arr.size()==0)
								{
									parent.left = ptr.right;
									ptr.right = null;
									ptr = null;
									flag = 1;
								}

							}
						}
					}

				}
				else if(ptr.left!=null && ptr.right!=null) 
				{
					if(parent==null)//if the node to be deleted is root
					{
						ptr = bst_root;
						ptr2 = bst_root;
						ptr = ptr.left;
						while(ptr!=null)
						{
							temptempparent = tempparent;
							tempparent = ptr;
							ptr = ptr.right;
						}
						int i = 0;
						while(i!=ptr.arr.size())
						{
							if(ptr.arr.get(i)==time)
							{
								ptr.arr.remove(i);
							}
						}
						if(ptr.arr.size()==0)
						{
							tempparent.left = ptr2.left;
							tempparent.right = ptr2.right;
							temptempparent.right = null;
							ptr2.left = null;
							ptr2.right = null;
							ptr2 = null;
							flag = 1;
						}


					}
					else
					{
						if(parent.left==ptr) //ptr ke right child ka left most should be attached 
						{                 //sucessor
							ptr2 = ptr;
							ptr = ptr.right;
							while(ptr!=null)
							{
								temptempparent = tempparent;
								tempparent = ptr;
								ptr = ptr.left;
							}
							int i = 0;
							while(i!=ptr.arr.size())
							{
								if(ptr.arr.get(i)==time)
								{
									ptr.arr.remove(i);
								}
							}
							if(ptr.arr.size()==0)
							{
								parent.left = tempparent;
								temptempparent.left = null;
								tempparent.left = ptr2.left;
								tempparent.right = ptr2.right;
								ptr2.left = null;
								ptr2.right = null;
								ptr2 = null;
								flag = 1;
							}


						}
						else if(parent.right==ptr)
						{                        //ptr ka lc ka right most should be atached
							//predecessor
							ptr2 = ptr;
							ptr = ptr.left;
							while(ptr!=null)
							{
								temptempparent = tempparent;
								tempparent = ptr;
								ptr = ptr.right;
							}
							int i = 0;
							while(i!=ptr.arr.size())
							{
								if(ptr.arr.get(i)==time)
								{
									ptr.arr.remove(i);
								}
							}
							if(ptr.arr.size()==0)
							{
								parent.right = tempparent;
								temptempparent.right = null;
								tempparent.right = ptr2.right;
								tempparent.left = ptr2.left;
								ptr2.left = null;
								ptr2.right = null;
								ptr2 = null;
								flag = 1;
							}

						}
					}

				}

			}
		}while(flag!=1);
		System.out.println() ;
		System.out.println("\t\t\t\tRecord has been deleted");

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

	static boolean isvalid(String email)
	{
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
				"[a-zA-Z0-9_+&*-]+)*@" + 
				"(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
				"A-Z]{2,7}$"; 
		Pattern pat = Pattern.compile(emailRegex); 
		if (email == null) 
			return false; 
		return pat.matcher(email).matches(); 
	}
	Node_p create(Node_e temp1, tree tt) // function to create tree of participants
	{
		int flag = 0 ;
		boolean flag11 = false; 
		String s;
		int t ; // time slot
		String time = "";
		Node_p temp = new Node_p() ;
		node bst_temp = new node();
		Scanner sc = new Scanner(System.in) ;
		System.out.println() ;
		do
		{
			System.out.print("\t\t\t\tEnter email_id of the Participant : ") ;
			temp.email_id = sc.next() ;
			Participant_tree.isvalid(temp.email_id);
			if(flag11==false)
				System.out.println("\t\t\t\t*****Please enter a valid email ID!*****");
		}while(flag11==false);
		s = temp.email_id;
		node a = tt.search(s, tt.bst_root,0);
		if(a!=null && a.flag==1)//if participant has participated in another event
		{
			System.out.println("\t\t\t\t1. First Time slot (t1-t2): "+temp1.time_slot_1) ;
			System.out.println("\t\t\t\t2. Second Time slot (t1-t2): "+temp1.time_slot_2) ;
			System.out.println() ;
			int flaag = 0 ;
			do
			{

				System.out.print("\t\t\t\tEnter time slot no (0/1) : ") ;//validation for time slot
				t = sc.nextInt() ;
				if(t == 0)
				{
					time = temp1.time_slot_1 ;
				}
				else if(t == 1)
				{
					time = temp1.time_slot_2 ;
				}

				else
				{
					flaag = 1 ;
					System.out.println("\t\t\t\t*****Invalid input, try again*****");
				}
			}while(flaag == 1);


			a.time = time;
			a.arr.add(time);
			temp.p_name = a.bst_name;
			temp.clg_name = a.bst_college;

		}
		else//if participant is a new participant
		{
			flag11 = false;
			do
			{
				flag11 = true;
				System.out.print("\t\t\t\tEnter Name of Participant : ") ;//accepting participant's information
				temp.p_name = sc.next() ;
				if(temp.p_name.matches("^[a-zA-Z]*$")==false)
				{
					flag11= false;
					System.out.println("\t\t\t\t*****Enter a valid name!*****");
				}
			}while(flag11==false);

			bst_temp.bst_name = temp.p_name;
			do
			{
				flag11 = true;
				System.out.print("\t\t\t\tEnter Name of College : ") ;
				temp.clg_name = sc.next() ;
				if(temp.clg_name.matches("^[a-zA-Z]*$")==false)
				{
					flag11= false;
					System.out.println("\t\t\t\t*****Enter a valid College Name!*****");
				}
			}while(flag11==false);

			bst_temp.bst_college = temp.clg_name;
			bst_temp.bst_email = s;
			System.out.println("\t\t\t\t1. First Time slot (t1-t2): "+temp1.time_slot_1) ;
			System.out.println("\t\t\t\t2. Second Time slot (t1-t2): "+temp1.time_slot_2) ;
			System.out.println() ;
			int flaag = 0 ;
			do
			{
				flaag = 0 ;
				System.out.print("\t\t\t\tEnter time slot no (0/1) : ") ;//validation for time slot
				t = sc.nextInt() ;
				if(t==0)
				{
					time = temp1.time_slot_1;
				}
				else if(t==1)
				{
					time = temp1.time_slot_2;
				}
				else
				{
					flaag = 1 ;
					System.out.println("\n\n\n\n*****Invalid input, try again*****");
				}
			}while(flaag == 1);
			bst_temp.time = time;
			bst_temp.arr.add(time);	
		}
		flag = 0;
		node ptr1 = tt.bst_root;
		if(tt.bst_root==null)
		{
			tt.bst_root = bst_temp;
			ptr1 = tt.bst_root;
		}
		else
		{

			if(a!=null && a.flag==1)
			{
				ptr1=tt.bst_root;

				while(ptr1!=null)
				{
					if(ptr1.bst_email.equalsIgnoreCase(a.bst_email))
					{
						flag = 1 ;
						ptr1.arr = a.arr;
						ptr1.time = a.time;
						break ;
					}
					if(a.bst_email.compareTo(ptr1.bst_email) >  0)
					{
						ptr1 = ptr1.right ;
					}
					else
					{
						ptr1 = ptr1.left ;
					}
				}
			}
			else
			{
				while(flag==0)
				{
					if(bst_temp.bst_email.compareTo(ptr1.bst_email)< 0 )// condition to attach node to left
					{
						if(ptr1.left != null)
						{
							ptr1 = ptr1.left ;
						}
						else
						{
							ptr1.left = bst_temp ;
							flag = 1 ;
						}
					}
					if(bst_temp.bst_email.compareTo(ptr1.bst_email) > 0 )// condition to attach node to right
					{
						if(ptr1.right != null)
						{
							ptr1 = ptr1.right ;
						}
						else
						{
							ptr1.right = bst_temp ;
							flag = 1 ;
						}

					}
				}
			}

		}
		int flag1 = 0 ;
		if(root_p[t] == null)//if root is null
		{
			root_p[t] = temp ;
		}
		else
		{
			Node_p ptr = root_p[t] ;

			while(flag1 == 0)
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
						flag1 = 1 ;
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
						flag1 = 1 ;
					}
				}
			}
		}
		return temp ;
	}



	void inorder(Node_p ptr,int t)// displaying the Participant list
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
	int Search(String s)//searching a participant in the list
	{
		int flag = 0 ;
		int i = 0 ;
		Node_p ptr = null ;
		for(i = 0 ; i < 2 ; i++)
		{
			ptr = root_p[i] ;
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
			if(flag == 1)
			{
				break ;
			}
		}

		if(flag == 1)//if participant is found
		{
			System.out.println() ;
			System.out.println("\t\t\t\t**\tDetails of the Participant matching with the entered email ID : ") ;
			System.out.println("") ;
			System.out.println("\t\t\t\tParticipant Name : "+ptr.p_name) ;
			System.out.println("\t\t\t\tEmail_id : "+ptr.email_id) ;
			System.out.println("\t\t\t\tName of College : "+ptr.clg_name) ;
			return i ;
		}
		else//if participant is not found
		{
			System.out.println("\n\t\t\t\t -----   The Participant has not registered for the event "+s+"   -----" ) ;
			return -1 ;
		}
	}


}
public class Mini_project {

	public static void readwrite(Scanner sc)//read data into vector and write into the file
	{
		Vector <Innovation> v = new Vector<>();
		int flag = 0;//reading data into the vector
		boolean flag11 = false;
		do
		{
			sc.nextLine();
			Innovation ii = new Innovation();
			do
			{
				flag11 = true;
				System.out.print("\t\t\t\tEnter Event name : ");
				ii.eventname = sc.nextLine();
				if(ii.eventname.matches("^[a-zA-Z]*$")==false)
				{
					flag11= false;
					System.out.println("\t\t\t\t*****Enter a valid Event Name!*****");
				}
			}while(flag11==false);
			System.out.print("\t\t\t\tEnter Event Introduction : ");
			ii.intro = sc.nextLine();

			System.out.print("\t\t\t\tEnter Date of the event : ");
			ii.date = sc.nextLine();
			System.out.print("\t\t\t\tEnter time of the event (Time slot 1 = (t1-t2)): ");
			ii.time = sc.next();
			System.out.print("\t\t\t\tEnter time of the event (Time slot 2 = (t1-t2)): ");
			ii.time2 = sc.next();
			do
			{
				System.out.print("\t\t\t\tEnter Team size in Integer : ");
				ii.teamsize = sc.nextInt();
				if(ii.teamsize<0)
					System.out.println("\t\t\t\t*****Team size cannot be less than 1!*****");
			}while(ii.teamsize<0);
			do
			{
				System.out.print("\t\t\t\tEnter Registration fee : ");
				ii.regfee = sc.nextDouble();
				if(ii.regfee<0.0)
					System.out.println("\t\t\t\t*****Registration fee cannot be negative!*****");
			}while(ii.regfee<0.0);
			do
			{
				flag11 = true;
				System.out.print("\t\t\t\tEnter event Contact head's name : ");
				ii.contactname = sc.next();
				if(ii.contactname.matches("^[a-zA-Z]*$")==false)
				{
					flag11= false;
					System.out.println("\t\t\t\t*****Enter a valid Name!*****");
				}
			}while(flag11==false);
			System.out.print("\t\t\t\tEnter Event contact head's number : ");
			ii.contactno = sc.next();
			v.addElement(ii);
			do
			{
				System.out.println() ;
				System.out.print("\t\t\t\tDo you want to add another event entry? 1-yes / 0-no : ");
				System.out.println() ;
				flag = sc.nextInt();	
				if(flag<0 && flag>1)
				{
					System.out.println() ;
					System.out.println("\t\t\t\t*****Invalid input!*****");
				}
			}while(flag<0 && flag>1);
		}while(flag!=0);
		//writing data into the file from the vector
		File f = new File("info.txt"); //to write records in the file
		try 
		{
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(v);
			fos.close();
			os.close();
			System.out.println("\t\t\t\t^^^^^Data Written Successfully!!^^^^^");
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	public static void readfiles()
	{
		try
		{
			FileInputStream fil = new FileInputStream("info.txt");
			ObjectInputStream os = new ObjectInputStream(fil);
			Vector<Innovation> deserializeInnovation = (Vector<Innovation>)os.readObject();
			Iterator<Innovation> iter = deserializeInnovation.iterator();
			while(iter.hasNext()==true)
			{
				Innovation s = iter.next();
				System.out.println("\t\t\t\tEvent Name : " + s.eventname);
				System.out.println("\t\t\t\tEvent Introduction : " + s.intro);
				System.out.println("\t\t\t\tDate of Event : " + s.date);
				System.out.println("\t\t\t\tTime of Event (Time slot 1): " + s.time);
				System.out.println("\t\t\t\tTime of Event (Time slot 2): " + s.time2);
				System.out.println("\t\t\t\tTeam Size : " + s.teamsize);
				System.out.println("\t\t\t\tRegistration fee : " + s.regfee);
				System.out.println("\t\t\t\tEvent Head's Name : " + s.contactname);
				System.out.println("\t\t\t\tEvent Head's Contact details : " + s.contactno);
				System.out.println("\t\t\t\t-------------------------------------");
			}
			fil.close();
			os.close();
			System.out.println("\t\t\t\t^^^^^Data read successfully!^^^^^");
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) 
		{	
			e.printStackTrace();
		}	
	}
	public static void editfiles(Scanner sc)
	{
		try
		{
			FileInputStream fil = new FileInputStream("info.txt");
			ObjectInputStream os = new ObjectInputStream(fil);
			Vector<Innovation> deserializeStudent = (Vector<Innovation>)os.readObject();
			Vector <Innovation> v = new Vector <>();
			Iterator<Innovation> iter = deserializeStudent.iterator();
			int flag = 0;
			while(iter.hasNext()==true)
			{
				Innovation s = iter.next();
				v.addElement(s);
				flag = 1;
			}
			flag = 0;

			boolean flag11 = false;
			do
			{
				sc.nextLine();
				Innovation ii = new Innovation();
				do
				{
					flag11 = true;
					System.out.print("\t\t\t\tEnter Event name : ");
					ii.eventname = sc.nextLine();
					if(ii.eventname.matches("^[a-zA-Z]*$")==false)
					{
						flag11= false;
						System.out.println("\t\t\t\t*****Enter a valid Event Name!*****");
					}
				}while(flag11==false);
				System.out.print("\t\t\t\tEnter Event Introduction : ");
				ii.intro = sc.nextLine();

				System.out.print("\t\t\t\tEnter Date of the event : ");
				ii.date = sc.nextLine();
				System.out.print("\t\t\t\tEnter time of the event (Time slot 1 = (t1-t2)): ");
				ii.time = sc.next();
				System.out.print("\t\t\t\tEnter time of the event (Time slot 2 = (t1-t2)): ");
				ii.time2 = sc.next();
				do
				{
					System.out.print("\t\t\t\tEnter Team size in Integer : ");
					ii.teamsize = sc.nextInt();
					if(ii.teamsize<0)
						System.out.println("\t\t\t\t*****Team size cannot be less than 1!*****");
				}while(ii.teamsize<0);
				do
				{
					System.out.print("\t\t\t\tEnter Registration fee : ");
					ii.regfee = sc.nextDouble();
					if(ii.regfee<0.0)
						System.out.println("\t\t\t\t*****Registration fee cannot be negative!*****");
				}while(ii.regfee<0.0);
				do
				{
					flag11 = true;
					System.out.print("\t\t\t\tEnter event Contact head's name : ");
					ii.contactname = sc.next();
					if(ii.contactname.matches("^[a-zA-Z]*$")==false)
					{
						flag11= false;
						System.out.println("\t\t\t\t*****Enter a valid Name!*****");
					}
				}while(flag11==false);
				System.out.print("\t\t\t\tEnter Event contact head's number : ");
				ii.contactno = sc.next();
				v.addElement(ii);
				do
				{
					System.out.println() ;
					System.out.print("\t\t\t\tDo you want to add another event entry? 1-yes / 0-no : ");
					flag = sc.nextInt();	
					System.out.println() ;
					if(flag<0 && flag>1)
					{
						System.out.println() ;
						System.out.println("\t\t\t\t^^^^^Invalid input!^^^^^");
					}
				}while(flag<0 && flag>1);
			}while(flag!=0);

			FileOutputStream fill = new FileOutputStream("info.txt");
			ObjectOutputStream oss = new ObjectOutputStream(fill);
			oss.writeObject(v);
			fil.close();
			os.close();
			if(flag==1)
			{
				System.out.println("\t\t\t\tData deleted succesfully!");
			}
			else
			{
				System.out.println("\t\t\t\tNothing to delete.");
			}
		}

		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) 
		{	
			e.printStackTrace();
		}	
	}
	public static boolean isvalid(String email)
	{
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
				"[a-zA-Z0-9_+&*-]+)*@" + 
				"(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
				"A-Z]{2,7}$"; 
		Pattern pat = Pattern.compile(emailRegex); 
		if (email == null) 
			return false; 
		return pat.matcher(email).matches(); 
	}

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in) ;
		int ch = 0 ;
		int no_e ; //no of events
		int cnt = 0 ;
		int q = 0;
		//File f = new File("test.txt");


		Event_tree a = new Event_tree() ;
		//Participant_tree p = new Participant_tree() ;
		Participant_tree t[] = new Participant_tree[10] ;
		tree tt = new tree();
		//int[] arr;
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
					System.out.println() ;//Student panel section
					System.out.println("\t\t\t\t\t\t\t\t\t\t -------------------------------") ;
					System.out.println("\t\t\t\t\t\t\t\t\t\t|                               |") ;
					System.out.println("\t\t\t\t\t\t\t\t\t\t|\t  STUDENT PANEL     	|") ;
					System.out.println("\t\t\t\t\t\t\t\t\t\t|                               |") ;
					System.out.println("\t\t\t\t\t\t\t\t\t\t -------------------------------") ;
					System.out.println() ;
					System.out.println() ;
					System.out.println("\t\t\t\t1. Accept Event Details in brief") ;
					System.out.println("\t\t\t\t2. Accept event details in detail (file)");
					System.out.println("\t\t\t\t3. Display Rocords of all Events") ;
					System.out.println("\t\t\t\t4. Search a particular event in the list") ;
					System.out.println("\t\t\t\t5. Search a particular Participant") ;
					System.out.println("\t\t\t\t0. Exit") ;
					System.out.println() ;
					System.out.print("\t\t\t\tSelect Your Choice : ") ;
					ch1 = sc.nextInt() ;
					System.out.println() ;
					switch(ch1)//calling functions according to choice
					{
					case 1 :
						do
						{
							System.out.print("\t\t\t\tEnter Total No of Events : ") ;//Accepting no of events
							no_e = sc.nextInt() ;
							if(no_e <0)
								System.out.println("\t\t\t\tNumber of events should be 1 or greater than 1");
						}while(no_e<0);



						//arr = new int[no_e];
						System.out.println() ;

						for(int i = 0 ; i < no_e ; i++)
						{
							a.create_e(cnt,t) ;
							cnt++ ;
						}
						break ;
					case 2 :
						if(q==0)
						{
							readwrite(sc);
							q++;
						}
						else
						{
							editfiles(sc);
							q++;
						}

						break ;
					case 3 :
						readfiles();
						System.out.println("\t\t\t\t------------------------------------------");
						a.inorder_e(a.root_e,0,t);
						break ;
					case 4 :
						boolean flag11 = false;
						String s;
						do
						{
							flag11=true;
							System.out.print("\t\t\t\tEnter the name of event you want to search : ") ;
							s = op.nextLine() ;
							if (s.matches("^[a-zA-Z]*$"))
								System.out.println("\t\t\t\t*****Please enter a valid event name*****");
						}while(flag11==false);

						System.out.println() ;
						a.Search_e(s,t,1) ;
						break ;
					case 5 :
						String ss;
						do {
							System.out.print("\t\t\t\tEnter email ID of the student to be searched : ") ;
							ss = op.nextLine();
							if(isvalid(ss)==false)
								System.out.println("\t\t\t\t*****Enter a valid email ID!*****");	
						}while(isvalid(ss)==false);

						tree test = new tree();
						test.search(ss, tt.bst_root,1) ;
						break ;
					case 0 :
						System.out.println() ;
						System.out.println("\t\t\t\t\tTHANK YOU !") ;
						break ;
					default :
						System.out.println() ;
						System.out.println("\t\t\t\t\tINVALID CHOICE...TRY AGAIN") ;//validation for choice
						break ;
					}
				}while(ch1 != 0) ;
				break ;
			case 2 :
				Scanner op1 = new Scanner(System.in) ;
				int ch2 = 0 ;
				do
				{
					System.out.println() ;//Participant section
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
					System.out.println("\t\t\t\t5. Unique Code for On-Site Registration") ;
					System.out.println("\t\t\t\t0. Exit") ;
					System.out.println() ;
					System.out.print("\t\t\t\tSelect Your Choice : ") ;
					ch2 = sc.nextInt() ;
					System.out.println() ;
					switch(ch2)//calling functions according to choice
					{
					case 1 :

						readfiles();



						System.out.println("\t\t\t\t------------------------------------------");
						//a.inorder_e(a.root_e,0,t);
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
						a.insert_e(s,cnt,0,t, tt) ;
						break ;
					case 3 :
						sc.nextLine();
						String ss;
						do
						{
							System.out.print("\t\t\t\tEnter email ID of the student to be searched : ");
							ss = sc.nextLine();	
							if(isvalid(ss)==false)
								System.out.println("\t\t\t\t*****Enter a valid email ID!*****");
						}while(isvalid(ss)==false);
						tree test = new tree();
						test.search(ss, tt.bst_root,1);

						break ;
					case 4 :

						int m = 0 ;
						System.out.println() ;
						String f;
						do
						{
							System.out.print("\t\t\t\tEnter email id of participant : ") ;
							f = op1.nextLine() ;
							if(isvalid(f)==false)
								System.out.println("\t\t\t\t*****Enter a valid email ID!*****");

						}while(isvalid(f)==false);

						System.out.println() ;
						boolean flag11=false;
						String k  ="";			
						{
							System.out.print("\t\t\t\tEnter event name : ") ;
							k = op1.nextLine() ;
							if(k.matches("^[a-zA-Z]*$")==false)
								System.out.println("\t\t\t\t*****Please enter a valid event name*****");
						}while(flag11==false);

						System.out.println() ;
						int j = a.Search_e(k,t,1) ;
						System.out.print("\n\t\t\t\tEnter time slot of the event for cancellation (the full string including special characters) : ");
						String time = op1.nextLine();
						tt.delete(f, time);
						if(j != -1)
						{
							m = t[j].Search(f) ;
							if(m != -1)
							{
								a.delete_e(f,j,m,t);
							}

						}
						else
						{
							System.out.println("\t\t\t\t -----   You have mentioned wrong event   -----" ) ;// if the event is not found
						}

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
				System.out.println("\t\t\t\t\tINVALID CHOICE...TRY AGAIN") ;			//validation for choice
				break ;
			}

		}while(ch != 0) ;
	}
}