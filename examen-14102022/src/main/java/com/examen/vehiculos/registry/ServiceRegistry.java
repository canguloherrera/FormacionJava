package com.examen.vehiculos.registry;

public interface ServiceRegistry {
    public <T> AdapterService<T> getService(String serviceName);
}