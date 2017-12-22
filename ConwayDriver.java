import java.util.*;
public class ConwayDriver {
  private static Random r;
  private static ConwayGrid conway;
  private static Scanner keyboard;
  public static void main(String[] args) {
     conway = new ConwayGrid(10);
     r = new Random();
     keyboard = new Scanner(System.in);

     String s = "";
     String prompt =  "next - next generation\n" +
                      "random - random addition of x\n" +
                      "alive - add an alive block\n" +
                      "dead - add a dead block\n" +
                      "quit - leave\n";
     while(!s.toLowerCase().equals("quit")) {
       if(s.toLowerCase().equals("random")) {
         System.out.println("How many random: ");
         randomAdditions(keyboard.nextInt());
         keyboard.nextLine();
       } else if(s.toLowerCase().equals("alive") || s.toLowerCase().equals("dead")) {
         System.out.print("Enter an x {space} y");
         conway.modify(keyboard.nextInt(), keyboard.nextInt(), s.toLowerCase().equals("alive"));
         keyboard.nextLine();
       } else {
         conway.nextGen();
       }
       System.out.print(prompt);
       s = keyboard.nextLine();
       System.out.println(conway);
     }
  }

  public static void randomAdditions(int spots) {//Not guaranteed to put #spots into grid, just tries
    Integer[] a = conway.getDimensions();
    int xmax = a[0];
    int ymax = a[1];
    while(spots > 0) {
      conway.modify(r.nextInt(xmax),r.nextInt(ymax), true);
      spots--;
    }
  }
}
