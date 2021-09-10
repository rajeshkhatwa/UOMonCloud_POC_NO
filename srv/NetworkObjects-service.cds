using NetworkObjects.db as NetObj from '../db/NetworkObjects-model';

service NetworkObjectsService   {
entity NetworkObjects as projection on NetObj.NetworkObjects;
 

}