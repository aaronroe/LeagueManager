package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import akka.io.TcpPipelineHandler;
import models.Athlete;
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

    /**
     * The default route. Handles a lot of the game progression at the moment.
     * @return The current "default" view.
     */
	public static Result index() {
        final User localUser = getLocalUser(session());
        if (localUser == null) {
            return redirect(routes.Application.login());
//            return ok(mainlogin.render(MyUsernamePasswordAuthProvider.LOGIN_FORM));
        }
        else {
            Team teamOfUsers = Team.findTeamOf(localUser);
            if (teamOfUsers == null) {
                return redirect(routes.Application.teamInit());
            }
            else {
                return redirect(routes.Application.overview());
            }
        }
	}

    /**
     * Helper method for this controller to use to get the current logged in user.
     * @param session The session to check. To check if for the immediate user, it is just session().
     * @return The user that is logged in for this session.
     */
	public static User getLocalUser(final Session session) {
		final AuthUser currentAuthUser = PlayAuthenticate.getUser(session);
		final User localUser = User.findByAuthUserIdentity(currentAuthUser);
		return localUser;
	}

    /**
     * Gets the game overview view.
     * @return The game overview view. Redirect if there is something uninitialized.
     */
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

    /**
     * Gets the game login view.
     * @return The login view. Redirect if the user is alreay logged in.
     */
	public static Result login() {
        final User localUser = getLocalUser(session());
        if (localUser == null) {
            return ok(login.render(MyUsernamePasswordAuthProvider.LOGIN_FORM));
        }
        else {
            return redirect(routes.Application.index());
        }
	}

    /**
     * Handles a POST for a login form.
     * @return The result of the submission of a login form.
     */
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
     * Gets the team initialization view, where the user types in a team name and chooses a logo.
     * @return The team intialization view. Redirect if the team has already been initialized.
     */
    @Restrict(@Group(Application.USER_ROLE))
    public static Result teamInit() {
        final User localUser = getLocalUser(session());
        Team team = Team.findTeamOf(localUser);
        if (team == null) {
            return ok(teaminit.render(form(InitialTeam.class)));
        }
        else {
            return redirect(routes.Application.index());
        }
    }

    /**
     * Performs the initialization of a team with a name and a logo.
     * @return The result of the team init form submission.
     */
    @Restrict(@Group(Application.USER_ROLE))
    public static Result doTeamInit() {
        final Form<InitialTeam> filledForm = form(InitialTeam.class).bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(teaminit.render(filledForm));
        } else {
            final User localUser = getLocalUser(session());

            // create the new team.
            InitialTeam formData = filledForm.get();
            Team teamFromForm = Team.create(localUser.id, formData.name, formData.logo);

            // initialize a pool of athletes now!
            Athlete.create("HotShotGG", null);
            Athlete.create("NyJacky", null);
            Athlete.create("bigfatjiji", null);
            Athlete.create("Faker-Senpai", null);

            return redirect(routes.Application.overview());
        }
    }

    /**
     * Gets the roster intialization view, where the user chooses the initial players.
     * @return The roster initialization view. Redirect if the team does not exist or if the roster already exists.
     */
    @Restrict(@Group(Application.USER_ROLE))
    public static Result rosterInit() {
        final User localUser = getLocalUser(session());
        Team team = Team.findTeamOf(localUser);
        if (team == null) {
            return redirect(routes.Application.index());
        }
        else {
            return ok();
        }
    }

    /**
     * Performs the initialization of a roster.
     * @return The result of the roster init form submission..
     */
    @Restrict(@Group(Application.USER_ROLE))
    public static Result doRosterInit() {
        return ok();
    }

    /**
     * Gets the game signup view.
     * @return The game signup view. Redirects if the user is already signed in.
     */
	public static Result signup() {
        final User localUser = getLocalUser(session());
        if (localUser == null) {
            return ok(signup.render(MyUsernamePasswordAuthProvider.SIGNUP_FORM));
        }
        else {
            return redirect(routes.Application.index());
        }
	}

    /**
     * Handles a POST for a signup form.
     * @return The result of the submission of a signup form.
     */
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

	public static Result jsRoutes() {
		return ok(
				Routes.javascriptRouter("jsRoutes",
						controllers.routes.javascript.Signup.forgotPassword()))
				.as("text/javascript");
	}

	public static String formatTimestamp(final long t) {
		return new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(new Date(t));
	}

}