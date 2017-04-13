import java.util.*;
import java.math.BigInteger;
import java.lang.Math;

public class KCalculation {
	public int n, q;
	public BigInteger x, y, a, k;

	public BigInteger call() {
		// return K
		q = getRandom();
		n = getRandom();
		BigInteger q1 = BigInteger.valueOf(q);
		BigInteger n1 = BigInteger.valueOf(n);

		x = getBigNumber(20);
		y = getBigNumber(20);
		a = q1.modPow(x, n1);
		k = q1.modPow(y, n1);


		System.out.println("q1: = " + q1);
		System.out.println("n1: = " + n1);
		System.out.println("x: = " + x);
		System.out.println("y: = " + y);
		System.out.println("a: = " + a);
		System.out.println("k: = " + k);

		// k = BigInteger.pow(a, y) % n;
		return k;
	}

	private int getRandom() {
		int v;
		Random rand = new Random();
		v = rand.nextInt(100) + 1;  
		return v;
	}

	private BigInteger getBigNumber(int len) {
		Random rnd = new Random();
	    StringBuilder sb = new StringBuilder(len);
	    for(int i=0; i < len; i++){
	        sb.append((char)('0' + rnd.nextInt(10)));
	    }
	    String str = sb.toString();
		return new BigInteger(str);
	}
}