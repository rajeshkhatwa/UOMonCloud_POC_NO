package customer.networkobjects.handlers;

import java.util.List;
import java.util.stream.Stream;

import com.sap.cds.Result;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.ql.cqn.CqnSelectListItem;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cds.gen.networkobjects.db.NetworkObjectTypes;
import cds.gen.networkobjects.db.NetworkObjectTypes_;
import cds.gen.networkobjectsservice.NetworkObjects;
import cds.gen.networkobjectsservice.NetworkObjectsService_;




@Component
@ServiceName(NetworkObjectsService_.CDS_NAME) /* check in Gen Folder  */
public class NetworkObjectServiceHandler implements EventHandler
{
 
    @Autowired
    PersistenceService db;

    @After(event = CqnService.EVENT_READ , entity = "NetworkObjectsService.NetworkObjects")
    public void afterReadNetObj( Stream<NetworkObjects> NetObj )
    {
      String Description;
      System.out.println("Inside the after  Read Method" );
      NetObj.peek(b -> System.out.println("Original Value : "  +  b.getNetworkObjectType() )  )
      .filter(b -> b.getNetworkObjectType().equals("CLUSTER") )
      .peek(b -> System.out.println("Filtered Value before change description : "  +  b.getNetworkObjectDescription() )  )
      .forEach(b -> b.setNetworkObjectDescription("Cluster_Changed_Value"));
      
     CqnSelect select = Select.from(NetworkObjectTypes_.class).columns(b -> b.network_object_type( ),
                                                                       b -> b.network_object_type_description( ) );

     Result NObjTypes = db.run(select);
     //instead of List of ..you can use Stream of also.
     Description = NObjTypes.listOf(NetworkObjectTypes.class).get(0).getNetworkObjectTypeDescription();
     System.out.println(Description);


     List<NetworkObjectTypes> NOList = NObjTypes.listOf(NetworkObjectTypes.class);

    // Update the Network Object Type Description
    NObjTypes.listOf(NetworkObjectTypes.class).get(0).setNetworkObjectTypeDescription("Changed using Code : CQN Update");
    CqnUpdate update = Update.entity(NetworkObjectTypes_.class).data("network_object_type_description","Changed using Code : CQN Update" )
                       .where(b -> b.network_object_type().eq("CLUSTER") );

          db.run(update);          
    // 

    }

    @Before(event = CqnService.EVENT_READ , entity = "NetworkObjectsService.NetworkObjects")
    public void beforeReadNetObj( CdsReadEventContext ctx )
    {
        // Get List of Columns
        Select <?> select = Select.copy(ctx.getCqn());
        //this is deperecated , should use items method now.

        // Can use setCqn to modify the comuns to be selected
        List<CqnSelectListItem> columns = select.columns();
        List<CqnSelectListItem> columns2 = select.items();
      
    
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
