package exercise.controllers;

import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;

import exercise.domain.User;
import exercise.domain.query.QUser;

import io.javalin.validation.BodyValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();

        String json = DB.json().toJson(users);
        ctx.attribute("users", users);
        ctx.json(json);
        // END
    }

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
                .id.equalTo(Long.parseLong(id))
                .findOne();

        String json = DB.json().toJson(user);
        ctx.attribute("users", user);
        ctx.json(json);
        // END
    }

    public void create(Context ctx) {

        // BEGIN
        String body = ctx.body();
        User userNew = DB.json().toBean(User.class, body);

        BodyValidator<User> userBodyValidator =  ctx.bodyValidator(User.class);

        userBodyValidator
                .check(it -> !it.getFirstName().isEmpty(), "First name can't be empty")
                .check(it -> !it.getLastName().isEmpty(), "Last name can't be empty")
                .check(it -> EmailValidator.getInstance().isValid(it.getEmail()), "Should be valid email")
                .check(it -> StringUtils.isNumeric(it.getPassword()), "Password must contains only numbers")
                .check(it -> it.getPassword().length() >= 4, "Password must contain at least 4 characters")
                .get();

        userNew.save();
        // END
    }

    public void update(Context ctx, String id) {
        // BEGIN
        String body = ctx.body();
        User userUpdate = DB.json().toBean(User.class, body);

        userUpdate.setId(id);
        userUpdate.update();
        // END
    }

    public void delete(Context ctx, String id) {
        // BEGIN
        User userDelete = new QUser()
                .id.equalTo(Long.parseLong(id))
                .findOne();

        userDelete.delete();
        // END
    }
}
