package com.jalili.lsqtakehome.entities.internal;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

// looking back this was a waste of time, should have left the field as a string an moved on since
// uuids are inputs
public class UuidGenerator implements IdentifierGenerator {
 
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException
    {
        return UUID.randomUUID();
    }
}