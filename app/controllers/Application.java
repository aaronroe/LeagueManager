package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static final String FLASH_ERROR_KEY = "error";

    public static Result index() {
        return ok(index.render());
    }

    public static Result oAuthDenied(final String providerKey) {
        flash(FLASH_ERROR_KEY,
                "You need to accept the OAuth connection in order to use this website!");
        return redirect(routes.Application.index());
    }

}
