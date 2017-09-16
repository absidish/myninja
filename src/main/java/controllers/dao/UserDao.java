package controllers.dao;


import javax.persistence.TypedQuery;

import controllers.model.User;


public class UserDao extends AbstractDao<User>
{
    public UserDao()
    {
        super( User.class );
    }


    public User getByEmail( String email )
    {
        TypedQuery<User> q =
                getEntityManager().get().createQuery( "select u from User u where u.email = :email", User.class );

        q.setParameter( "email", email );

        q.setHint( "org.hibernate.cacheable", true );

        return getSingleResult( q );
    }
}
