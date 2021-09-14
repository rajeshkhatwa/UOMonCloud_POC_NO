using NetworkObjectsService from '../../srv/NetworkObjects-service';

annotate NetworkObjectsService.NetworkObjects with @(
UI : {
    HeaderInfo  : {
        $Type : 'UI.HeaderInfoType',
        TypeName : 'Network Object',
        TypeNamePlural : 'Network Objects',
          Description: { Value: network_object_description }
    },

SelectionFields  : [ ID, network_object_type, network_object_description ],
LineItem  : [

    {Value: ID, Label: 'Generated Id'},
    {Value: network_object_type, Label: 'Network Object Type'},
    {Value: network_object_description, Label: 'Network Object Description'},
]   

}

 );
