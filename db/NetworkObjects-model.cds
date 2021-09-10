namespace NetworkObjects.db;
using { cuid, managed } from '@sap/cds/common';

entity NetworkObjects : cuid, managed
{
 
network_object_type : String;
network_object_description : String;

}
