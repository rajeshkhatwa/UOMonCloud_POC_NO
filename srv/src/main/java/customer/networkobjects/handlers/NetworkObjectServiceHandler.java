package customer.networkobjects.handlers;

import java.util.List;
import java.util.stream.Stream;

import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;

import org.springframework.stereotype.Component;

import cds.gen.networkobjectsservice.NetworkObjects;
import cds.gen.networkobjectsservice.NetworkObjectsService_;


@Component
@ServiceName(NetworkObjectsService_.CDS_NAME) /* check in Gen Folder  */
public class NetworkObjectServiceHandler implements EventHandler
{
 
    @After(event = CqnService.EVENT_READ)
    public void afterReadNetObj( Stream<NetworkObjects> NetObj )
    {
      System.out.println("Inside the after  Read Method" );
      NetObj.peek(b -> System.out.println("Original Value : "  +  b.getNetworkObjectType() )  )
      .filter(b -> b.getNetworkObjectType().equals("CLUSTER") )
      .peek(b -> System.out.println("Filtered Value before change description : "  +  b.getNetworkObjectDescription() )  )
      .forEach(b -> b.setNetworkObjectDescription("Cluster_Changed_Value"));
      
    
    }

    @Before( event = CqnService.EVENT_CREATE )
    public void beforeCreateNetObj(List <NetworkObjects> NetObj )
    {
     System.out.println("Inside the before create Method" );   
     for (NetworkObjects networkObject : NetObj )
     {
      System.out.println(networkObject.getNetworkObjectDescription());
      networkObject.setNetworkObjectDescription("Value changed in Custom handler");
          
      
     }
    }
}    
