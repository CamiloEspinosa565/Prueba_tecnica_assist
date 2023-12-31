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
@DynamoDBTable(tableName = "CuentaBancaria")
public class CuentaBancaria {
    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String idCuenta;
    @DynamoDBAttribute
    private String saldo;
    @DynamoDBAttribute
    private String IdUsuario;
    @DynamoDBAttribute
    private String estado;
}
