using NetworkObjects.db.NetworkObjects as NetObj from '../../db/NetworkObjects-model';
annotate NetObj.NetworkObjects with 
{
    network_object_type @title: 'Network Object Type'
    @description: 'Network Object Type';
    
    network_object_description @title: 'Network Object Description';
} ;
