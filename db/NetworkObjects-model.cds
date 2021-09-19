namespace NetworkObjects.db;
using { cuid, managed } from '@sap/cds/common';

entity NetworkObjects : cuid, managed
{
 
network_object_type : String(10) @title : 'Network Object Type'   @mandatory;
network_object_description : String(60) @title : 'Network Object Description' @mandatory;

}
entity NetworkObjectTypes
{
  key network_object_type : String(10) @title : 'Network Object Type';
   network_object_type_description : String(60) @title : 'Network Object Type Description'
}

