package mini_project;
import java.util.* ;

class node_e//node of events
{
	String event_name ; //name of event
	participant_tree data ;//array of tree is data for tree of events
	node_e left ;//left ptr
	node_e right ;//right ptr
	node_e()//constructor
	{
		data = null ;
		left = null ;
		right = null ;
	}
}
class event_tree//tree of events
{
	node_e root_e ;
	event_tree()//constructor
	{
		root_e = null ;
	}
}
class node_p//node of participants
{
	String p_name ;//name of participant
	String email_id ;// email id of participant
	String clg_name ; // name of college
	node_p left ;//left ptr
	node_p right ;//right ptr
	node_p()//constructor
	{
		left = null ;
		right = null ;
	}
}
class participant_tree//tree of participants
{
	node_p root_p[] = new node_p[100] ;//array of tree
	participant_tree()//constructor
	{
		for(int i = 0 ; i < 100 ; i++)
		{
			root_p[i] = null ;
		}
	}
}
public class Mini_project {

	public static void main(String args[])
	{
		int ch = 0 ;
		int no_e ; //no of events
		Scanner sc = new Scanner(System.in) ;
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
					System.out.println("\t\t\t\t4. Search a particular Participant") ;//Think about this later
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
							//accept function
						}
						break ;
					case 2 :
						break ;
					case 3 :
						break ;
					case 4 :
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
					System.out.println("\t\t\t\t4. Unique Code for On-Site Registration") ;//Think about this later
					System.out.println("\t\t\t\t0. Exit") ;//not taking nearby events cause even if we add that feature it should be displayed automatically.. no need of menu
					System.out.println() ;
					System.out.print("\t\t\t\tSelect Your Choice : ") ;
					ch1 = sc.nextInt() ;
					System.out.println() ;
					switch(ch1)
					{
					case 1 :
						break ;
					case 2 :
						break ;
					case 3 :
						break ;
					case 4 :
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
