/**
 * CArtAgO - DEIS, University of Bologna
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package cartago.infrastructure.rmi;

import java.rmi.*;

import cartago.*;
import java.util.*;

/**
 * Remote interface for CArtAgO controllers.
 * 
 * @author aricci
 *
 */
public interface ICartagoControllerRemote  extends Remote {

    AgentId[] getCurrentAgents() throws CartagoException, RemoteException;

    ArtifactId[] getCurrentArtifacts() throws CartagoException, RemoteException;
    
    boolean removeArtifact(String id) throws CartagoException, RemoteException;

    boolean removeAgent(String userName) throws CartagoException, RemoteException;  
    
    ArtifactInfo getArtifactInfo(String name) throws CartagoException, RemoteException; 
}
