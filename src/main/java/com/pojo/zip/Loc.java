/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pojo.zip;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 *
 * @author aquil
 */
@BsonDiscriminator
public class Loc
{
    public Loc(){}
    private String x;

    private String y;

    public String getX ()
    {
        return x;
    }

    public void setX (String x)
    {
        this.x = x;
    }

    public String getY ()
    {
        return y;
    }

    public void setY (String y)
    {
        this.y = y;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [x = "+x+", y = "+y+"]";
    }
}
		