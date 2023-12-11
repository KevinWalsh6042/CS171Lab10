import java.util.Random;
import java.util.Scanner;

public class Lab10 {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Q1();
        Q2();
        Q3();
        Q4();
        scan.close();
    }

    public static void Q1() {
        while (true) {
            System.out.println("Pick a shape: square, rectangle, circle (or 'q' to quit)");

            String input = scan.nextLine();
            if (input.equals("q")) {
                return;
            }

            if (input.equals("square")) {
                double a = getSide('a');
                System.out.println("The circumference of the square is: " + a * 4);
                System.out.println("The area of the square is: " + a * a);
                return;
            }

            if (input.equals("rectangle")) {
                double a, b;
                a = getSide('a');
                b = getSide('b');
                System.out.println("The circumference of the rectangle is: " + (2 * a + 2 * b));
                System.out.println("The area of the rectangle is: " + (a * b));
                return;
            } 
            
            if (input.equals("circle")) {
                double r;
                System.out.println("Enter the radius: ");
                r = Double.parseDouble(scan.nextLine());
                System.out.println("The circumference of the circle is: " + (Math.PI * r * 2));
                System.out.println("The area of the circle is: " + (Math.PI * r * r));
                return;
            }
            
        }
    }

    public static void Q2() {
        System.out.println("Q2: Enter the current day (1-31): ");
        int num = Integer.parseInt(scan.nextLine());
        System.out.println("Enter the current month: (1-12)");
        int num2 = Integer.parseInt(scan.nextLine());
        
        String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December" };
    
        if (num % 10  == 1)
        {
            System.out.print("You selected "+num+"st of ");
        }    

        else if (num == 2 || num == 22)
        {
            System.out.print("You selected "+num+"nd of ");
        }
        else if (num == 3 || num == 23)
        {
            System.out.print("You selected "+num+"rd of ");
        }
        else if(num<32 && num > 0)
        {
            System.out.print("You selected "+num+"th of ");
        }
        else
        {
            System.out.print("invalid input ");
            return;
        }
        
        

        if(num2 > 0 && num2 < 13)
        {
            System.out.println(months[num2-1]);
            return;
        }
        System.out.println("Invalid month");
        

    }

public static void Q3() 
{
    System.out.println("Q3: Enter how many numbers you want to check for primality: ");
    int n = Integer.parseInt(scan.nextLine());
    int counter = 0;
    for (int i = 0; i < n; i++) 
    {
        if (i >= 2)
        {
            boolean check = true;

            for (int j = 2; j * j <= i; j++) 
            {
                if (i % j == 0) 
                {
                    check = false;
                    break;
                } 
            }
            if (check) 
            {
                counter++;
            }
        }
    }
    System.out.println("There are: " + counter + " primes between 0 and " + n);
}
    

    public static void Q4() {
        Random rng = new Random();

        String next;
        System.out.println("Q4: Let's play a game. Type \"A\" to attack, \"B\" to buff your next attack. Kill the enemy to win!");
        System.out.println("Q4: You must roll higher than the enemy armor class (12) to hit. Roll 20 for a critical hit!");
        System.out.println("Q4: Your damage is 2-16 (2d8)");

        int enemyHP = 100;
        int turns = 0;

        int maxdamage = 8;
        int enemyArmour = 12;
        int buffValue = 5;

        boolean buff = false;
        while (true) {

            boolean doAttack = false;
            boolean validAttack = false;
            while (!validAttack) {
                next = scan.nextLine();
                validAttack = true;
                switch (next) {
                    case "A", "a":
                        doAttack = true;
                        break;
                    case "B", "b":
                        buff = true;
                        System.out.println("Buffing! "+ buffValue +" to your next attack roll and damage");
                        break;
                    default:
                        System.out.println("Invalid input");
                        validAttack = false;
                }
            }

            if (doAttack) {
                turns++;
                int attackRoll = rng.nextInt(20) + 1;
                int damage = 0;
                System.out.print("You rolled: " + attackRoll);
                if(buff) {
                    attackRoll += 5;
                    System.out.print(" + "+buffValue+" (buff active)\n");
                } else {
                    System.out.println();
                }
                if (attackRoll >= enemyArmour) {
                    damage = rng.nextInt(maxdamage) + 1;
                    damage += rng.nextInt(maxdamage) + 1;
                    if(buff) {
                        damage += 5;
                    }
                    if (attackRoll == 20 || (buff && attackRoll == 20 + 5)) {
                        damage *= 2;
                        System.out.print("Critical hit! ");
                    }
                    System.out.print("You dealt " + damage + " damage");
                    if(buff) {
                        System.out.print(" (buffed attack)");
                    }

                    enemyHP -= damage;
                    System.out.println("\nEnemy HP: " + Math.max(0, enemyHP));

                } else {
                    System.out.println("Miss");
                }

                buff = false;
                if (enemyHP <= 0) {
                    System.out.println("Enemy died in " + turns + " turns");
                    scan.close();
                    return;
                }
            }

        }
    }

    public static double getSide(char letter)
    {
        System.out.println("Enter the length of side " + letter + ": ");
        return Double.parseDouble(scan.nextLine());
    }


}
