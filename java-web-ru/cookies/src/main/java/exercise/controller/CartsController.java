package exercise.controller;

import java.util.Collections;
import java.util.Map;
import exercise.dto.CartPage;
import exercise.util.NamedRoutes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import exercise.model.CartItem;
import org.eclipse.jetty.util.UrlEncoded;

import io.javalin.http.Context;


public class CartsController {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void index(Context ctx) throws Exception {
        var cookie = ctx.cookie("cart") == null ? "{}" : ctx.cookie("cart");

        Map<String, CartItem> cart = mapper.readValue(
            UrlEncoded.decodeString(cookie),
                new TypeReference<>() {
                }
        );

        var page = new CartPage(cart);
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }

    // BEGIN
    public static void clean(Context ctx) throws Exception {
        var cookie = "{}";
        var encodedCokie = UrlEncoded.encodeString(cookie);

        Map<String, CartItem> cart = mapper.readValue(
                UrlEncoded.decodeString(encodedCokie),
                new TypeReference<>() {
                }
        );

        var page = new CartPage(cart);
        ctx.cookie("cart", encodedCokie);
        ctx.render("index.jte", Collections.singletonMap("page", page));
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void add(Context ctx) throws Exception {
        var cookie = ctx.cookie("cart") == null ? "{}" : ctx.cookie("cart");
        var id = ctx.formParam("id");
        var name = ctx.formParam("name");

        Map<String, CartItem> cart = mapper.readValue(
                UrlEncoded.decodeString(cookie),
                new TypeReference<>() {
                }
        );

        if (cart.get(id) == null) {
            cart.put(id, new CartItem(name, 1));
        } else {
            cart.get(id).increaseCount();
        }

        var page = new CartPage(cart);
        ctx.cookie("cart", UrlEncoded.encodeString(mapper.writeValueAsString(cart)));
        ctx.render("index.jte", Collections.singletonMap("page", page));
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
