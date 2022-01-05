package com.halloween.api;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;

@Path("/api/products")
public class ProductResources implements Serializable {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAll(){
        return "Hello World";
    }
}
