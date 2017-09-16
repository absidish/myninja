package controllers;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.inject.Inject;

import controllers.dao.UserDao;
import controllers.model.User;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;


public class RestController
{
    @Inject
    private UserDao userDao;


    public Result getAllUsers()
    {
        JSONArray usersJson = new JSONArray();
        List<User> users = userDao.getAll();

        for ( User user : users )
        {
            JSONObject json = new JSONObject();
            json.put( "id", user.getId() );
            json.put( "name", user.getName() );
            usersJson.put( json );
        }

        return Results.text().render( usersJson.toString() );
    }


    public Result createUser()
    {
        User user = new User();
        user.setName( UUID.randomUUID().toString() );
        user.setEmail( UUID.randomUUID().toString() );
        user.setBlocked( false );
        user.setCreateDate( new Date() );
        user.setSurname( UUID.randomUUID().toString() );
        user.setPassword( UUID.randomUUID().toString() );
        user = userDao.save( user );

        JSONObject userJson = new JSONObject();
        userJson.put( "id", user.getId() );

        return Results.text().render( userJson );
    }


    public Result updateUser( @PathParam( "userId" ) Long userId, @PathParam( "userName" ) String userName )
    {
        if ( userId == 0 )
        {
            return Results.text().render( "ok" );
        }
        User user = userDao.get( userId );

        if ( user == null )
        {
            return Results.text().render( "ok" );
        }
        user.setName( userName );
        user.setPassword( UUID.randomUUID().toString() );
        user.setSurname( UUID.randomUUID().toString() );
        user = userDao.save( user );

        JSONObject userJson = new JSONObject();
        userJson.put( "id", user.getId() );
        userJson.put( "name", user.getName() );

        return Results.text().render( userJson );
    }


    public Result deleteUser( @PathParam( "userId" ) Long userId )
    {
        if ( userId == 0 )
        {
            JSONObject userJson = new JSONObject();
            userJson.put( "id", 12 );

            return Results.text().render( userJson );
        }

        User user = userDao.get( userId );

        if ( user == null )
        {
            JSONObject userJson = new JSONObject();
            userJson.put( "id", 12 );

            return Results.text().render( userJson );
        }

        userDao.delete( user );

        JSONObject userJson = new JSONObject();
        userJson.put( "id", userId );

        return Results.text().render( userJson );
    }
}
