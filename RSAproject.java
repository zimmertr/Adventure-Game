import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.BitSet;
import java.util.Scanner;
import java.lang.Integer;
import java.math.BigInteger;


/**
 * Created with IntelliJ IDEA.
 * User: Pauls
 * Date: 4/12/13
 * Time: 10:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSAproject {

    public static void main (String[] args) {
        String bb, nn, mm;
        BigInteger x = new BigInteger("1");
        String binary;

        Scanner scan = new Scanner(System.in);

        System.out.println("Entered Values");
        System.out.println("--------------------");
        System.out.print("Enter b: ");
        bb = scan.next();
        System.out.print("Enter n: ");
        nn = scan.next();
        System.out.print("Enter m: ");
        mm = scan.next();
        System.out.println("--------------------");

        BigInteger b = new BigInteger(bb);
        BigInteger n = new BigInteger(nn);
        BigInteger m = new BigInteger(mm);


        binary = n.toString(2);
        System.out.println("\n" + "Binary: " + binary + "\n");
        System.out.print("Loop Check: ");

        BigInteger powerN = new BigInteger("1");
        powerN = powerN.multiply(b.mod(m));

        for(int i = binary.length()-1 ; i >= 0; i--){
            System.out.print(binary.charAt(i) + ",");
            if(binary.charAt(i) == '1'){
                x = (x.multiply(powerN)).mod(m);
            }
            powerN = (powerN.multiply(powerN)).mod(m);
        }

        System.out.println("\n");
        System.out.println("Final Answer: " + x.toString());

        //System.out.println((b.pow(Integer.parseInt(nn))).mod(new BigInteger(mm)));
    }

}
