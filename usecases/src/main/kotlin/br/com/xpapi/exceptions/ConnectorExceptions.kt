package br.com.xpapi.exceptions

class EmptyErrorBodyException: Exception()

class ConnectorException(msg: String): Exception(msg)