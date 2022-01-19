package com.halloween.ws;


import com.halloween.model.Products;
import com.halloween.service.IProductService;
import com.halloween.service.impl.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/products")
public class ProductResource {

    private static final IProductService dao = new ProductService();

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Products> getAll() {
        return dao.getAllItems();
    }

    @GET
    @Path("{productID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Products getByID(@PathParam("productID") Integer productID) {
        return dao.findOne(productID);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(Products product) throws URISyntaxException {
        Integer productID = dao.save(product);
        URI url = new URI(uriInfo.getBaseUri() + "products/" + productID);
        if(productID != null) return Response.created(url).build();
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOne(Products product){
        Boolean isSuccess = dao.delete(product.getProductID());
        if(isSuccess) return Response.status(Response.Status.OK).build();
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Products product){
        Boolean isSuccess = dao.update(product, product.getProductID());
        if(isSuccess) return Response.status(Response.Status.OK).build();
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
}
