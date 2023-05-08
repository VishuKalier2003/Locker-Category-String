/* You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', 
'6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to 
be '9'. Each move consists of turning one wheel one slot. The lock initially starts at '0000', a string 
representing the state of the 4 wheels. You are given a list of deadends dead ends, meaning if the lock displays 
any of these codes, the wheels of the lock will stop turning and you will be unable to open it. Given a target 
representing the value of the wheels that will unlock the lock, return the minimum total number of turns required 
to open the lock, or -1 if it is impossible.
* Eg 1 : deadends = ["0201","0101","0102","1212","2002"]              target = "0202"     Output = 4 + 2 = 6
* Eg 2 : deadends = ["8888"]                                          target = "0009"     Output = 1
* Eg 3 : deadends = ["8887","8889","8878","8898","8788",              target = "8888"     Output = -1
*                    "8988","7888","9888"]                            
*/
import java.util.*;
public class Locker
{
      public int MovesToUnlock(String deadend[], String target)
      {
            int upper = 0, lower = 0;   // Variables to check Upper and Lower deadends... //* Variable => O(1)
            for(int i = 0; i < deadend.length; i++)   //! Comparison => O(4 x N)
            {     // Checking every deadend...
                  for(int j = 0; j < target.length(); j++)
                  {      // Checking every index in the String sequence...
                        int val1 = Character.getNumericValue(deadend[i].charAt(j));   // Deadend character...
                        int val2 = Character.getNumericValue(target.charAt(j));       // Target character... 
                        if(val1 - val2 == 1)     // Upper bound...
                              upper++;
                        if(val2 - val1 == 1)     // Lower bound...
                              lower++;
                  }
            }
            int rotation = 0;     // Variable to check rotations...   //*  Variable => O(1)
            if((lower == 4) && (upper == 4))
                  return -1;    // Not possible to reach the target sequence...
            else if((lower > 0) || (upper > 0))
            {     // If possible to reach the target sequence...
                  for(int i = 0; i < target.length(); i++)    //! Evaluation => O(N)
                  {
                        int value = Character.getNumericValue(target.charAt(i));  // target character...
                        if(value < 5)     // If closer to zero...
                              rotation = rotation + value;
                        else              // If closer to Nine...
                              rotation = rotation + (9 - value);
                  }
                  return rotation + 2;    // We add +2 so that the deadends are not hit...
            }
            return rotation;     // In case of no possibility of deadends...
      }
      public static void main(String args[])
      {
            Scanner sc = new Scanner(System.in);
            int x;
            System.out.print("Enter the number of Deadends : ");
            x = sc.nextInt();
            String ends[] = new String[x];
            for(int i = 0; i < ends.length; i++)
            {
                  System.out.print("Enter Deadend Sequence : ");
                  ends[i] = sc.next();
            }
            System.out.print("Enter the Target Sequence : ");
            String sequence = sc.next();
            Locker locker = new Locker();    // Object creation...
            System.out.println("The Minimum rotations : "+locker.MovesToUnlock(ends, sequence));
            sc.close();
      }
}



//! Time Complexity => O(4 x N)
//* Space Complexity => O(1)
/** //? DEDUCTIONS :-
 * ? If all adjacent eight codes of the target code are deadends, then it is impossible to reach the targer...
 * ? If we have one possible deadend, we simply reach the target in +2 steps...
 */