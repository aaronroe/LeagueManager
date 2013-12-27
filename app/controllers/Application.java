package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import akka.io.TcpPipelineHandler;
import models.Team;
import models.User;
import models.forms.InitialTeam;
import play.Play;
import play.Routes;
import play.data.Form;
import play.mvc.*;
import play.mvc.Http.Response;
import play.mvc.Http.Session;
import play.mvc.Result;
import providers.MyUsernamePasswordAuthProvider;
import providers.MyUsernamePasswordAuthProvider.MyLogin;
import providers.MyUsernamePasswordAuthProvider.MySignup;

import views.html.*;
import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;

import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.providers.password.UsernamePasswordAuthProvider;
import com.feth.play.module.pa.user.AuthUser;

import static play.data.Form.form;

public class Application extends Controller {

	public static final String FLASH_MESSAGE_KEY = "message";
	public static final String FLASH_ERROR_KEY = "error";
	public static final String USER_ROLE = "user";
	
	public static Result index() {
        final User localUser = getLocalUser(session());
        if (localUser == null) {
            return ok(login.render(MyUsernamePasswordAuthProvider.LOGIN_FORM));
//            return ok(mainlogin.render(MyUsernamePasswordAuthProvider.LOGIN_FORM));
        }
        else {
            Team teamOfUsers = Team.findTeamOf(localUser);
            if (teamOfUsers == null) {
                return ok(teaminit.render(form(InitialTeam.class)));
            }
            else {
                return redirect(routes.Application.overview());
            }
        }
	}

	public static User getLocalUser(final Session session) {
		final AuthUser currentAuthUser = PlayAuthenticate.getUser(session);
		final User localUser = User.findByAuthUserIdentity(currentAuthUser);
		return localUser;
	}

	@Restrict(@Group(Application.USER_ROLE))
	public static Result overview() {
		final User localUser = getLocalUser(session());
        Team team = Team.findTeamOf(localUser);
        if (team == null) {
            return redirect(routes.Application.index());
        }
        else {
            return ok(overview.render(team.name, team.logoName));
        }
//		return ok(restricted.render(localUser));
	}

	@Restrict(@Group(Application.USER_ROLE))
	public static Result profile() {
		final User localUser = getLocalUser(session());
		return ok(profile.render(localUser));
	}

	public static Result login() {
		return ok(login.render(MyUsernamePasswordAuthProvider.LOGIN_FORM));
	}

	public static Result doLogin() {
		com.feth.play.module.pa.controllers.Authenticate.noCache(response());
		final Form<MyLogin> filledForm = MyUsernamePasswordAuthProvider.LOGIN_FORM
				.bindFromRequest();
		if (filledForm.hasErrors()) {
			// User did not fill everything properly
			return badRequest(login.render(filledForm));
		} else {
			// Everything was filled
			return UsernamePasswordAuthProvider.handleLogin(ctx());
		}
	}

    /**
     * Performs the initialization of a team with a name and a logo.
     * @return The result.
     */
    public static Result doTeamInit() {
        final Form<InitialTeam> filledForm = form(InitialTeam.class).bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(teaminit.render(filledForm));
        } else {
            final User localUser = getLocalUser(session());

            // create the new team.
            InitialTeam formData = filledForm.get();
            Team teamFromForm = Team.create(localUser.id, formData.name, formData.logo);

            return redirect(routes.Application.overview());
        }
    }

	public static Result signup() {
		return ok(signup.render(MyUsernamePasswordAuthProvider.SIGNUP_FORM));
	}

	public static Result jsRoutes() {
		return ok(
				Routes.javascriptRouter("jsRoutes",
						controllers.routes.javascript.Signup.forgotPassword()))
				.as("text/javascript");
	}

	public static Result doSignup() {
		com.feth.play.module.pa.controllers.Authenticate.noCache(response());
		final Form<MySignup> filledForm = MyUsernamePasswordAuthProvider.SIGNUP_FORM
				.bindFromRequest();
		if (filledForm.hasErrors()) {
			// User did not fill everything properly
			return badRequest(signup.render(filledForm));
		} else {
			// Everything was filled
			// do something with your part of the form before handling the user
			// signup
			return UsernamePasswordAuthProvider.handleSignup(ctx());
		}
	}

	public static String formatTimestamp(final long t) {
		return new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(new Date(t));
	}

}