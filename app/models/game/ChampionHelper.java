package models.game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that helps with constants for the champions.
 */
public class ChampionHelper {

    /**
     * Singleton instance to use for champion helper.
     */
    public static ChampionHelper singleton = new ChampionHelper();

    /**
     * Array of the champions.
     */
    private String[] champions;

    /**
     * The number of champions in the game.
     */
    private final int NUM_CHAMPIONS = 117;

    /**
     * private constructor, as this class should be used with the singleton instance.
     */
    private ChampionHelper() {
        champions = new String[this.NUM_CHAMPIONS];
        champions[0] = Aatrox;
        champions[1] = Ahri;
        champions[2] = Akali;
        champions[3] = Alistar;
        champions[4] = Amumu;
        champions[5] = Anivia;
        champions[6] = Annie;
        champions[7] = Ashe;
        champions[8] = Blitzcrank;
        champions[9] = Brand;
        champions[10] = Caitlyn;
        champions[11] = Cassiopeia;
        champions[12] = ChoGath;
        champions[13] = Corki;
        champions[14] = Darius;
        champions[15] = Diana;
        champions[16] = DrMundo;
        champions[17] = Draven;
        champions[18] = Elise;
        champions[19] = Evelynn;
        champions[20] = Ezreal;
        champions[21] = Fiddlesticks;
        champions[22] = Fiora;
        champions[23] = Fizz;
        champions[24] = Galio;
        champions[25] = Gangplank;
        champions[26] = Garen;
        champions[27] = Gragas;
        champions[28] = Graves;
        champions[29] = Hecarim;
        champions[30] = Heimerdinger;
        champions[31] = Irelia;
        champions[32] = Janna;
        champions[33] = JarvanIV;
        champions[34] = Jax;
        champions[35] = Jayce;
        champions[36] = Jinx;
        champions[37] = Karma;
        champions[38] = Karthus;
        champions[39] = Kassadin;
        champions[40] = Katarina;
        champions[41] = Kayle;
        champions[42] = Kennen;
        champions[43] = KhaZix;
        champions[44] = KogMaw;
        champions[45] = LeBlanc;
        champions[46] = LeeSin;
        champions[47] = Leona;
        champions[48] = Lissandra;
        champions[49] = Lucian;
        champions[50] = Lulu;
        champions[51] = Lux;
        champions[52] = Malphite;
        champions[53] = Malzahar;
        champions[54] = Maokai;
        champions[55] = MasterYi;
        champions[56] = MissFortune;
        champions[57] = Mordekaiser;
        champions[58] = Morgana;
        champions[59] = Nami;
        champions[60] = Nasus;
        champions[61] = Nautilus;
        champions[62] = Nidalee;
        champions[63] = Nocturne;
        champions[64] = Nunu;
        champions[65] = Olaf;
        champions[66] = Orianna;
        champions[67] = Pantheon;
        champions[68] = Poppy;
        champions[69] = Quinn;
        champions[70] = Rammus;
        champions[71] = Renekton;
        champions[72] = Rengar;
        champions[73] = Riven;
        champions[74] = Rumble;
        champions[75] = Ryze;
        champions[76] = Sejuani;
        champions[77] = Shaco;
        champions[78] = Shen;
        champions[79] = Shyvana;
        champions[80] = Singed;
        champions[81] = Sion;
        champions[82] = Sivir;
        champions[83] = Skarner;
        champions[84] = Sona;
        champions[85] = Soraka;
        champions[86] = Swain;
        champions[87] = Syndra;
        champions[88] = Talon;
        champions[89] = Taric;
        champions[90] = Teemo;
        champions[91] = Thresh;
        champions[92] = Tristana;
        champions[93] = Trundle;
        champions[94] = Tryndamere;
        champions[95] = TwistedFate;
        champions[96] = Twitch;
        champions[97] = Udyr;
        champions[98] = Urgot;
        champions[99] = Varus;
        champions[100] = Vayne;
        champions[101] = Veigar;
        champions[102] = Vi;
        champions[103] = Viktor;
        champions[104] = Vladimir;
        champions[105] = Volibear;
        champions[106] = Warwick;
        champions[107] = Wukong;
        champions[108] = Xerath;
        champions[109] = XinZhao;
        champions[110] = Yasuo;
        champions[111] = Yorick;
        champions[112] = Zac;
        champions[113] = Zed;
        champions[114] = Ziggs;
        champions[115] = Zilean;
        champions[116] = Zyra;
    }

    // champion name constants.
    public static final String Aatrox = "Aatrox";
    public static final String Ahri = "Ahri";
    public static final String Akali = "Akali";
    public static final String Alistar = "Alistar";
    public static final String Amumu = "Amumu";
    public static final String Anivia = "Anivia";
    public static final String Annie = "Annie";
    public static final String Ashe = "Ashe";
    public static final String Blitzcrank = "Blitzcrank";
    public static final String Brand = "Brand";
    public static final String Caitlyn = "Caitlyn";
    public static final String Cassiopeia = "Cassiopeia";
    public static final String ChoGath = "Cho'Gath";
    public static final String Corki = "Corki";
    public static final String Darius = "Darius";
    public static final String Diana = "Diana";
    public static final String DrMundo = "Dr. Mundo";
    public static final String Draven = "Draven";
    public static final String Elise = "Elise";
    public static final String Evelynn = "Evelynn";
    public static final String Ezreal = "Ezreal";
    public static final String Fiddlesticks = "Fiddlesticks";
    public static final String Fiora = "Fiora";
    public static final String Fizz = "Fizz";
    public static final String Galio = "Galio";
    public static final String Gangplank = "Gangplank";
    public static final String Garen = "Garen";
    public static final String Gragas = "Gragas";
    public static final String Graves = "Graves";
    public static final String Hecarim = "Hecarim";
    public static final String Heimerdinger = "Heimerdinger";
    public static final String Irelia = "Irelia";
    public static final String Janna = "Janna";
    public static final String JarvanIV = "Jarvan IV";
    public static final String Jax = "Jax";
    public static final String Jayce = "Jayce";
    public static final String Jinx = "Jinx";
    public static final String Karma = "Karma";
    public static final String Karthus = "Karthus";
    public static final String Kassadin = "Kassadin";
    public static final String Katarina = "Katarina";
    public static final String Kayle = "Kayle";
    public static final String Kennen = "Kennen";
    public static final String KhaZix = "Kha'Zix";
    public static final String KogMaw = "Kog'Maw";
    public static final String LeBlanc = "LeBlanc";
    public static final String LeeSin = "Lee Sin";
    public static final String Leona = "Leona";
    public static final String Lissandra = "Lissandra";
    public static final String Lucian = "Lucian";
    public static final String Lulu = "Lulu";
    public static final String Lux = "Lux";
    public static final String Malphite = "Malphite";
    public static final String Malzahar = "Malzahar";
    public static final String Maokai = "Maokai";
    public static final String MasterYi = "Master Yi";
    public static final String MissFortune = "Miss Fortune";
    public static final String Mordekaiser = "Mordekaiser";
    public static final String Morgana = "Morgana";
    public static final String Nami = "Nami";
    public static final String Nasus = "Nasus";
    public static final String Nautilus = "Nautilus";
    public static final String Nidalee = "Nidalee";
    public static final String Nocturne = "Nocturne";
    public static final String Nunu = "Nunu";
    public static final String Olaf = "Olaf";
    public static final String Orianna = "Orianna";
    public static final String Pantheon = "Pantheon";
    public static final String Poppy = "Poppy";
    public static final String Quinn = "Quinn";
    public static final String Rammus = "Rammus";
    public static final String Renekton = "Renekton";
    public static final String Rengar = "Rengar";
    public static final String Riven = "Riven";
    public static final String Rumble = "Rumble";
    public static final String Ryze = "Ryze";
    public static final String Sejuani = "Sejuani";
    public static final String Shaco = "Shaco";
    public static final String Shen = "Shen";
    public static final String Shyvana = "Shyvana";
    public static final String Singed = "Singed";
    public static final String Sion = "Sion";
    public static final String Sivir = "Sivir";
    public static final String Skarner = "Skarner";
    public static final String Sona = "Sona";
    public static final String Soraka = "Soraka";
    public static final String Swain = "Swain";
    public static final String Syndra = "Syndra";
    public static final String Talon = "Talon";
    public static final String Taric = "Taric";
    public static final String Teemo = "Teemo";
    public static final String Thresh = "Thresh";
    public static final String Tristana = "Tristana";
    public static final String Trundle = "Trundle";
    public static final String Tryndamere = "Tryndamere";
    public static final String TwistedFate = "Twisted Fate";
    public static final String Twitch = "Twitch";
    public static final String Udyr = "Udyr";
    public static final String Urgot = "Urgot";
    public static final String Varus = "Varus";
    public static final String Vayne = "Vayne";
    public static final String Veigar = "Veigar";
    public static final String Vi = "Vi";
    public static final String Viktor = "Viktor";
    public static final String Vladimir = "Vladimir";
    public static final String Volibear = "Volibear";
    public static final String Warwick = "Warwick";
    public static final String Wukong = "Wukong";
    public static final String Xerath = "Xerath";
    public static final String XinZhao = "Xin Zhao";
    public static final String Yasuo = "Yasuo";
    public static final String Yorick = "Yorick";
    public static final String Zac = "Zac";
    public static final String Zed = "Zed";
    public static final String Ziggs = "Ziggs";
    public static final String Zilean = "Zilean";
    public static final String Zyra = "Zyra";

    /**
     * Returns an iterable of Strings, which represent champions.
     * @return An iterable of champions, in String format.
     */
    public final Iterable<String> getAllChampions() {
        List<String> championsList = new LinkedList<String>(Arrays.asList(this.champions));

        return championsList;
    }
}
