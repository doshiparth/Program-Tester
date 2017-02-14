/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testRunDetails;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;
import lib.runDetails.*;
import programtester.config.Configurator;

/**
 *
 * @author Rushabh Modi
 */
public class IOManagerTest2 {
    static Scanner sc=new Scanner(System.in);
    public static void main(String... args) throws IOException{
          Configurator.init();
          System.out.println("enter file name:-");
          String s;
          for(s=sc.nextLine();s=="";s=sc.nextLine());
          System.out.println("encript ? :-");
          boolean b=sc.nextBoolean();
        IntIODetail io;
        Path pi;
        pi=Paths.get(s).toAbsolutePath();
        io=IOManager.getIODetail(pi,b);
        //io=IOManager.getIODetail(pi);
        pi=Paths.get("input2.txt");
        Files.write(pi,io.getAllInput(),StandardOpenOption.CREATE_NEW);
        pi=Paths.get("output2.txt");
        Files.write(pi,io.getAllOutput(),StandardOpenOption.CREATE_NEW);
    }
}
