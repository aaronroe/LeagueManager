package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import models.Athlete;
import models.Game;
import models.Team;
import models.User;
import models.athlete.SoloQueueRating;
import models.forms.InitialRoster;
import models.forms.InitialTeam;
import play.Routes;
import play.data.Form;
import play.mvc.*;
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
        }
        else {
            Game localGame = Game.findGameOf(localUser);
            if (!localGame.isTeamInit) {
                return redirect(routes.Application.teamInit());
            }
            else if (!localGame.isRosterInit) {
                return redirect(routes.Application.rosterInit());
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
        Game localGame = Game.findGameOf(localUser);
        if (!localGame.isTeamInit || !localGame.isRosterInit) {
            return redirect(routes.Application.index());
        }

        Team team = Team.findTeamOf(localUser);

        return ok(overview.render(team));
	}

    /**
     * Gets the game management view.
     * @return The game management view. Redirect if there is something uninitialized.
     */
    @Restrict(@Group(Application.USER_ROLE))
    public static Result management() {
        final User localUser = getLocalUser(session());
        Game localGame = Game.findGameOf(localUser);
        if (!localGame.isTeamInit || !localGame.isRosterInit) {
            return redirect(routes.Application.index());
        }

        Team team = Team.findTeamOf(localUser);

        return ok(management.render(team));
    }

    /**
     * Gets the game schedule view.
     * @return The game schedule view. Redirect if there is something uninitialized.
     */
    @Restrict(@Group(Application.USER_ROLE))
    public static Result schedule() {
        final User localUser = getLocalUser(session());
        Game localGame = Game.findGameOf(localUser);
        if (!localGame.isTeamInit || !localGame.isRosterInit) {
            return redirect(routes.Application.index());
        }

        Team team = Team.findTeamOf(localUser);

        return ok(schedule.render(team));
    }

    /**
     * Gets the game roster view.
     * @return The game roster view. Redirect if there is something uninitialized.
     */
    @Restrict(@Group(Application.USER_ROLE))
    public static Result roster() {
        final User localUser = getLocalUser(session());
        Game localGame = Game.findGameOf(localUser);
        if (!localGame.isTeamInit || !localGame.isRosterInit) {
            return redirect(routes.Application.index());
        }

        Team team = Team.findTeamOf(localUser);

        return ok(roster.render(team));
    }

    /**
     * Gets the game roster recruit view.
     * @return The game roster recruit view. Redirect if there is something uninitialized.
     */
    @Restrict(@Group(Application.USER_ROLE))
    public static Result rosterRecruit() {
        final User localUser = getLocalUser(session());
        Game localGame = Game.findGameOf(localUser);
        if (!localGame.isTeamInit || !localGame.isRosterInit) {
            return redirect(routes.Application.index());
        }

        Team team = Team.findTeamOf(localUser);

        return ok(roster.render(team));
    }

    /**
     * Gets the game finances view.
     * @return The game finances view. Redirect if there is something uninitialized.
     */
    @Restrict(@Group(Application.USER_ROLE))
    public static Result finances() {
        final User localUser = getLocalUser(session());
        Game localGame = Game.findGameOf(localUser);
        if (!localGame.isTeamInit || !localGame.isRosterInit) {
            return redirect(routes.Application.index());
        }

        Team team = Team.findTeamOf(localUser);

        return ok(finances.render(team));
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
        Game localGame = Game.findGameOf(localUser);
        if (!localGame.isTeamInit) {
            return ok(teaminit.render(form(InitialTeam.class)));
        }

        Team team = Team.findTeamOf(localUser);

        return redirect(routes.Application.index());
    }

    /**
     * Performs the initialization of a team with a name and a logo.
     * @return The result of the team init form submission.
     */
    @Restrict(@Group(Application.USER_ROLE))
    public static Result doTeamInit() {
        final User localUser = getLocalUser(session());
        Game localGame = Game.findGameOf(localUser);
        if (localGame.isTeamInit) {
            return redirect(routes.Application.index());
        }

        final Form<InitialTeam> filledForm = form(InitialTeam.class).bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(teaminit.render(filledForm));
        } else {
            // create the new team.
            InitialTeam formData = filledForm.get();
            Team teamFromForm = localGame.createUserTeam(formData.name, formData.logo);

            // create an example team.
            Team skTelecomT1K = Team.create(localGame, "SK Telecom T1 K", "sktk.png");

            // initialize a pool of athletes now!
            Athlete.create("HotShotGG", localGame, SoloQueueRating.DiamondI);
            Athlete.create("NyJacky", localGame, SoloQueueRating.DiamondI);
            Athlete.create("bigfatjiji", localGame, SoloQueueRating.DiamondI);
            Athlete.create("Chaox", localGame, SoloQueueRating.Challenger);
            Athlete.create("PhantomL0rd", localGame, SoloQueueRating.DiamondI);
            Athlete.create("Faker", skTelecomT1K.id, localGame, SoloQueueRating.Challenger);

            localGame.setTeamInit(true);

            return redirect(routes.Application.rosterInit());
        }
    }

    /**
     * Gets the roster intialization view, where the user chooses the initial players.
     * @return The roster initialization view. Redirect if the team does not exist or if the roster already exists.
     */
    @Restrict(@Group(Application.USER_ROLE))
    public static Result rosterInit() {
        final User localUser = getLocalUser(session());
        Game localGame = Game.findGameOf(localUser);
        if (!localGame.isTeamInit || localGame.isRosterInit) {
            return redirect(routes.Application.index());
        }

        Team team = Team.findTeamOf(localUser);

        return ok(rosterinit.render(form(InitialRoster.class), Athlete.findRecruitableAthletes(localGame), team));
    }

    /**
     * Performs the initialization of a roster.
     * @return The result of the roster init form submission..
     */
    @Restrict(@Group(Application.USER_ROLE))
    public static Result doRosterInit() {
        final User localUser = getLocalUser(session());
        Game localGame = Game.findGameOf(localUser);
        if (!localGame.isTeamInit || localGame.isRosterInit) {
            return redirect(routes.Application.index());
        }

        Team localTeam = Team.findTeamOf(localUser);

        final Form<InitialRoster> filledForm = form(InitialRoster.class).bindFromRequest();

        if (filledForm.hasErrors()) {
            return badRequest(rosterinit.render(filledForm, Athlete.findRecruitableAthletes(localGame), localTeam));
        } else {
            InitialRoster initialRoster = filledForm.get();
            for (Long athleteId : initialRoster.athletes) {
                Athlete.find.ref(athleteId).setTeam(localTeam);
            }

            localGame.setRosterInit(true);

            return redirect(routes.Application.overview());
        }
    }


    /**
     * Gets the game signup view.
     * @return The game signup view. Redirects if the user is already signed in.
     */
	public static Result signup() {
        final User localUser = getLocalUser(session());
        Game localGame = Game.findGameOf(localUser);
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
            // let play-authenticate handle signup.
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