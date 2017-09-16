package controllers.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Cacheable
@Cache( usage = CacheConcurrencyStrategy.READ_WRITE )
@Table( name = "users" )
public class User implements Serializable
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;

    @Column( name = "name" )
    private String name;

    @Column( name = "surname" )
    private String surname;

    @Column( name = "email" )
    private String email;

    @Column( name = "create_date" )
    private Date createDate;

    @Column( name = "password" )
    private String password;

    @Column( name = "blocked" )
    private boolean blocked;



    public User()
    {
    }


    public User( final String name, final String surname, final String pwd, final String email )
    {
        this.name = name;
        this.surname = surname;
        this.password = pwd;
        this.email = email;
        this.createDate = new Date();
        this.blocked = false;
    }


    public long getId()
    {
        return id;
    }


    public void setId( final long id )
    {
        this.id = id;
    }


    public String getName()
    {
        return name;
    }


    public void setName( final String name )
    {
        this.name = name;
    }


    public String getSurname()
    {
        return surname;
    }


    public void setSurname( final String surname )
    {
        this.surname = surname;
    }


    public String getEmail()
    {
        return email;
    }


    public void setEmail( final String email )
    {
        this.email = email;
    }


    public Date getCreateDate()
    {
        return createDate;
    }


    public void setCreateDate( final Date createDate )
    {
        this.createDate = createDate;
    }


    public String getPassword()
    {
        return password;
    }


    public void setPassword( final String password )
    {
        this.password = password;
    }


    public boolean isBlocked()
    {
        return blocked;
    }


    public void setBlocked( final boolean blocked )
    {
        this.blocked = blocked;
    }

}
