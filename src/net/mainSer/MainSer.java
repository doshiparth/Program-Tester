/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mainSer;

import net.mainSer.userStatus.IntUserStatus;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

/**
 *
 * @author Neel Patel
 */
public class MainSer extends UnicastRemoteObject implements IntMainSer{

     MainSer()throws RemoteException{}
     
     @Override
     public boolean aya() throws RemoteException {
          return true;
     }

     @Override
     public String getDataSer() throws RemoteException {
          return SerDetails.getDataSer();
     }

     @Override
     public String getLogSer() throws RemoteException {
          return SerDetails.getLogSer();
     }

     @Override
     public boolean registerDataSer(String url) throws RemoteException {
          return SerDetails.registerDataSer(url);
     }

     @Override
     public IntUserStatus getStatus(String user, String passwd) {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }
     
}