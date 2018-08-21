
import java.math.BigDecimal;
import java.math.BigInteger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ranjith-suranga
 */
public class TestClass {
    
    public static void main(String[] args) {
        
        BigDecimal b1 = new BigDecimal("0.3");
        BigDecimal b2 = new BigDecimal("0.2");
        
        BigDecimal b3 = b1.subtract(b2);
        
        System.out.println(b3);
        
        
    }
    
}
