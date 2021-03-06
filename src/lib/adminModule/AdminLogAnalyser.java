/*
* Copyright 2017 Program Tester Team
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at

*     http://www.apache.org/licenses/LICENSE-2.0

* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
* package lib.adminModule;
*/

package lib.adminModule;

import lib.logAna.LogAnalizer;
import static programtester.config.Configuration.getDefaultLogDir;

import java.io.IOException;
import java.util.Scanner;
import java.util.Map;

/**
 * Created by Sony on 19-03-2017.
 */

public class AdminLogAnalyser {

    private static Scanner s1 = new Scanner(System.in);
   private static LogAnalizer la = new LogAnalizer(getDefaultLogDir());

    public static synchronized void start() {


        while (true) {

            System.out.println("\nMenu > LogAnalyser Menu..\n"
                    + "1.get user status\n"
                    + "2.refresh data\n"
                    + "3.get all user with credit\n"
                    + "0.exit from Exit from Main Server\n");
            switch (s1.nextInt()) {
                case 1:
                    String s = "";
                    for(s=s1.nextLine();s.trim().isEmpty();s=s1.nextLine());
                    Map<Long, Integer> mp = la.getUserStatus(s);
                    for (Long l1 : mp.keySet())
                        System.out.println("Program-id:  " + l1 + "       status:   " + mp.get(l1));
                    break;
                case 2:
                    la.refresh();
                    break;
                case 3:
                    try {
                        Map<String, Integer> mp1 = la.getAllUserStatus();
                        for (String s2 : mp1.keySet()) {
                            System.out.println("User-Name:   " + s2 + "     " + mp1.get(s2));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}

