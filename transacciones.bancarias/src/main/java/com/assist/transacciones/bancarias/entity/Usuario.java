package com.assist.transacciones.bancarias.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "User")
public class Usuario {
    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String idUsuario;
    @DynamoDBAttribute
    private String cedula;
    @DynamoDBAttribute
    @DynamoDBAutoGeneratedKey
    private String nombre;
    @DynamoDBAttribute
    private String apellido;
    @DynamoDBAttribute
    private String direccion;
}
