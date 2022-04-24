import java.lang.Math.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;


public class RSA {
    public static void main(String args[]) {

        int p=randomPrimeInt(), q=randomPrimeInt();
        //System.out.println(p+" "+q);
        int n=p*q, fin=(p-1)*(q-1);
        int e=getepszilon(fin);
        int d=getD(e, fin);
        int[] publicKey={e,n};
        int[] privateKey={d,n};
        System.out.println("public key: ("+publicKey[0]+", "+publicKey[1]+")");
        System.out.println("private key: ("+privateKey[0]+", "+privateKey[1]+")");
        int msg=getMessage();
        BigInteger enc=encryption(msg,publicKey);
        BigInteger dec=decryption(enc, privateKey);
        System.out.println("Message: "+msg+", encrypted message: "+enc+", decrypted message: "+dec);


    }
    public static int randomPrimeInt(){
        Random rand=new Random();
        int upperbound=800;
        int intRandom = rand.nextInt(upperbound);
        int intRandomPrime;
        if (isPrime(intRandom)){
            intRandomPrime=intRandom;
        }
        else{
            intRandomPrime=randomPrimeInt();
        }
        return intRandomPrime;
    }
    public static boolean isPrime(int n){
        if (n < 2)
            return false;

        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }
    public static int euripidesAlgorithm(int p, int q){
        int  rk0=p, rk1=q, rk2;
        while (rk1!=0){
            rk2=(rk0 %rk1);
            rk0=rk1;
            rk1=rk2;
        }
        return rk0;
    }
    public static int getepszilon(int fin) {
        int e;
        for (e = 2; e < fin; e++) {

            if (euripidesAlgorithm(e,fin) ==1){
                break;
            }
        }
        return e;
    }
    public static int getD(int e, int fin){
        int d;
        for (d=2;d<fin; d++){
            if ((d*e % fin)==1){
                break;
            }
        }
        return d;
    }
    public static int getMessage(){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter an integer to encrypt: ");
        int a = in.nextInt();
        return a;
    }
    public static BigInteger encryption(int msg, int[] publicKey){
        double encryptedDouble=(Math.pow(msg,publicKey[0]) % publicKey[1]);
        BigInteger encrypted= BigDecimal.valueOf(encryptedDouble).toBigInteger();
        return encrypted;
    }
    public static BigInteger decryption(BigInteger encrypted, int[] privateKey){
        BigInteger n = BigInteger.valueOf(privateKey[1]);
        BigInteger decrypted =(encrypted.pow(privateKey[0])).mod(n);
        return decrypted;
    }

}
