package controllers.dao;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import static java.lang.String.format;


public abstract class AbstractDao<T>
{

    @Inject
    private Provider<EntityManager> emp;

    private Class<T> entityClass;


    public AbstractDao( Class<T> clazz )
    {
        this.entityClass = clazz;
    }


    @Transactional
    public T save( T entity )
    {
        emp.get().persist( entity );
        return entity;
    }


    public T get( Serializable id )
    {
        if ( id == null )
        {
            throw new PersistenceException( "Id may not be null" );
        }
        return ( T ) emp.get().find( entityClass, id );
    }


    public List<T> getAll()
    {
        TypedQuery<T> query =
                emp.get().createQuery( format( "select a from %s a", entityClass.getSimpleName() ), entityClass );

        query.setHint( "org.hibernate.cacheable", true );

        return query.getResultList();
    }


    @Transactional
    public void update( T entity )
    {
        emp.get().merge( entity );
    }


    @Transactional
    public void delete( T entity )
    {
        emp.get().remove( entity );
    }


    protected static <T> T getSingleResult( TypedQuery<T> query )
    {
        query.setMaxResults( 1 );

        List<T> list = query.getResultList();

        if ( list == null || list.isEmpty() )
        {
            return null;
        }

        return list.get( 0 );
    }


    public Provider<EntityManager> getEntityManager()
    {
        return emp;
    }
}

