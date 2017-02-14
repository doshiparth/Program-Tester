package testProgramAdder;

import java.io.IOException;
import java.nio.file.Paths;
import lib.dT.problemManipulate.IntProgramDetail;
import lib.dT.problemManipulate.ProgramDetails;
import programtester.config.Configurator;

/**
 *
 * @author Rushabh
 */
public class testProgramDetails {
     public static void main(String args[]) throws IOException{
          Configurator.init();
          IntProgramDetail x=ProgramDetails.parseProgramDetail(Paths.get("temp","Sorting.txt").toAbsolutePath());
          System.out.println("Title:"+x.getTitle());
          System.out.println("Description:"+x.getDescription());
          System.out.println("Input:"+x.getInput());
          System.out.println("Output:"+x.getOutput());
          System.out.println("Sample Input:"+x.getSampleInput());
          System.out.println("Sample Output:"+x.getSampleOutput());
     }
}