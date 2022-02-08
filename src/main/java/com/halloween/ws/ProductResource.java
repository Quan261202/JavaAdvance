package com.halloween.ws;


import com.halloween.model.Products;
import com.halloween.service.ICategoryService;
import com.halloween.service.IProductService;
import com.halloween.service.impl.CategoryService;
import com.halloween.service.impl.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/products")
public class ProductResource {

    private static final IProductService dao = new ProductService();
    private static final ICategoryService categoryDao = new CategoryService();

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

    @GET
    @Path("category/{categoryID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getByCategory(@PathParam("categoryID") Integer categoryID) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", categoryDao.getCategoryName(categoryID));
        map.put("products", dao.getAllByCategory(categoryID));
        return map;
    }

    @GET
    @Path("category/{categoryID}/{offset}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Products> getThreeItemByCategory(@PathParam("categoryID") Integer categoryID, @PathParam("offset") Integer offset) {
        return dao.getThreeItem(categoryID, 3, offset);
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
