namespace NetworkObjects.db;
using { cuid, managed } from '@sap/cds/common';

entity NetworkObjects : cuid, managed
{
 
network_object_type : String(10);
network_object_description : String(60);

}


