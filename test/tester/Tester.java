/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import java.io.IOException;
import topicselector.ReadWriter;

/**
 *
 * @author Rustem
 */
public class Tester {
    public static void main(String[] args) throws IOException {
        ReadWriter rW = new ReadWriter();
        print(rW.readMatrixFromFile("C:\\Users\\Rustem\\Desktop\\development\\tests\\password.xlsx"));
    }
    private static void print(String[][] data){
        for (String[] row : data) {
            for (String item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
            
        }
    }
}
