/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pojo.zip;

import org.bson.Document;

/**
 *
 * @author aquil
 */
public class Zips extends Document
{
    private String pop;

    private String zip;

    private Loc loc;

    private String city;

    private String state;

    public String getPop ()
    {
        return pop;
    }

    public void setPop (String pop)
    {
        this.pop = pop;
    }

    public String getZip ()
    {
        return zip;
    }

    public void setZip (String zip)
    {
        this.zip = zip;
    }

    public Loc getLoc ()
    {
        return loc;
    }

    public void setLoc (Loc loc)
    {
        this.loc = loc;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pop = "+pop+", zip = "+zip+", loc = "+loc+", city = "+city+", state = "+state+"]";
    }
}
