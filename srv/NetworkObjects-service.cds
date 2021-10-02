using NetworkObjects.db as NetObj from '../db/NetworkObjects-model';

service NetworkObjectsService   {
entity NetworkObjects as projection on NetObj.NetworkObjects;


entity NetworkObjectTypes as projection on NetObj.NetworkObjectTypes;

}