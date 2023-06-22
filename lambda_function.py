import boto3
import json
import os
import random
dynamodb = boto3.resource('dynamodb')
cuenta = dynamodb.Table('CuentaBancaria')
transaccion = dynamodb.Table('Transacciones')
usuario = dynamodb.Table('User')
def lambda_handler(event, context):
    print(event)
    valor=event.get('valor')
    cuentaOrigen=event.get('origen')
    tipoT=event.get('tipo')
    cuentaDestino=event.get('cuenta')
    usuarioT=event.get('usuario')
    cuentaOrigenBD=cuenta.get_item(Key={'idCuenta': cuentaOrigen}).get('Item')
   
    if(cuenta.get_item(Key={'idCuenta': cuentaDestino}).get('Item')==None):
        return "No exite la cuenta de destino"
    cuentaDestinoBD=cuenta.get_item(Key={'idCuenta': cuentaDestino}).get('Item')
    response=usuario.scan(FilterExpression='#nombre = :value',
    ExpressionAttributeNames={
        '#nombre': 'cedula'
    },ExpressionAttributeValues={
        ':value': usuarioT.get('cedula')  
    })
    if(len(response.get('Items'))==0):
        return "El usuario no existe"
    usuarioBD=response.get('Items')[0]
    if(int(cuentaOrigenBD.get("saldo"))-int(valor)<0):
        return "No cuenta con fondos suficientes"
    if(cuentaOrigenBD.get("idUsuario")!=usuarioBD.get("idUsuario")):
        return "el usuario no es dueño de la cuenta"
    saldoOrigen=str(int(cuentaOrigenBD.get("saldo"))-int(valor))
    print(saldoOrigen)
    saldoDestino=str(int(cuentaDestinoBD.get("saldo"))+int(valor))
    print(saldoDestino)
    cuenta.update_item(
                Key={'idCuenta': cuentaOrigen},
                UpdateExpression="set saldo =  :val",
                ExpressionAttributeValues={':val': saldoOrigen},
                ReturnValues="UPDATED_NEW")
    cuenta.update_item(
                Key={'idCuenta': cuentaDestino},
                UpdateExpression="set saldo =   :val",
                ExpressionAttributeValues={':val': saldoDestino},
                ReturnValues="UPDATED_NEW")
    free=1
    while free>0:
        idTransaccion=str(random.randint(0,999999))
        if(transaccion.get_item(Key={'idTransaccion': idTransaccion}).get('Item')==None):
            free=0
    reponseTransaccion=transaccion.put_item(
        Item={
            "idTransaccion":idTransaccion,
            "idCuenta":cuentaDestino,
            "origen":cuentaOrigen,
            "tipo":tipoT,
            "valor":valor,
            "usuario":str(usuarioT),
        })
    return transaccion.get_item(Key={'idTransaccion': idTransaccion}).get('Item')