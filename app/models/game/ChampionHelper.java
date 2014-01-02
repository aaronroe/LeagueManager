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
     * List of the champions.
     */
    private String[] championList;

    /**
     * The number of champions in the game.
     */
    private final int NUM_CHAMPIONS = 117;

    /**
     * private constructor, as this class should be used with the singleton instance.
     */
    private ChampionHelper() {
        championList = new String[this.NUM_CHAMPIONS];
        championList[0] = Aatrox;
        championList[1] = Ahri;
        championList[2] = Akali;
        championList[3] = Alistar;
        championList[4] = Amumu;
        championList[5] = Anivia;
        championList[6] = Annie;
        championList[7] = Ashe;
        championList[8] = Blitzcrank;
        championList[9] = Brand;
        championList[10] = Caitlyn;
        championList[11] = Cassiopeia;
        championList[12] = ChoGath;
        championList[13] = Corki;
        championList[14] = Darius;
        championList[15] = Diana;
        championList[16] = DrMundo;
        championList[17] = Draven;
        championList[18] = Elise;
        championList[19] = Evelynn;
        championList[20] = Ezreal;
        championList[21] = Fiddlesticks;
        championList[22] = Fiora;
        championList[23] = Fizz;
        championList[24] = Galio;
        championList[25] = Gangplank;
        championList[26] = Garen;
        championList[27] = Gragas;
        championList[28] = Graves;
        championList[29] = Hecarim;
        championList[30] = Heimerdinger;
        championList[31] = Irelia;
        championList[32] = Janna;
        championList[33] = JarvanIV;
        championList[34] = Jax;
        championList[35] = Jayce;
        championList[36] = Jinx;
        championList[37] = Karma;
        championList[38] = Karthus;
        championList[39] = Kassadin;
        championList[40] = Katarina;
        championList[41] = Kayle;
        championList[42] = Kennen;
        championList[43] = KhaZix;
        championList[44] = KogMaw;
        championList[45] = LeBlanc;
        championList[46] = LeeSin;
        championList[47] = Leona;
        championList[48] = Lissandra;
        championList[49] = Lucian;
        championList[50] = Lulu;
        championList[51] = Lux;
        championList[52] = Malphite;
        championList[53] = Malzahar;
        championList[54] = Maokai;
        championList[55] = MasterYi;
        championList[56] = MissFortune;
        championList[57] = Mordekaiser;
        championList[58] = Morgana;
        championList[59] = Nami;
        championList[60] = Nasus;
        championList[61] = Nautilus;
        championList[62] = Nidalee;
        championList[63] = Nocturne;
        championList[64] = Nunu;
        championList[65] = Olaf;
        championList[66] = Orianna;
        championList[67] = Pantheon;
        championList[68] = Poppy;
        championList[69] = Quinn;
        championList[70] = Rammus;
        championList[71] = Renekton;
        championList[72] = Rengar;
        championList[73] = Riven;
        championList[74] = Rumble;
        championList[75] = Ryze;
        championList[76] = Sejuani;
        championList[77] = Shaco;
        championList[78] = Shen;
        championList[79] = Shyvana;
        championList[80] = Singed;
        championList[81] = Sion;
        championList[82] = Sivir;
        championList[83] = Skarner;
        championList[84] = Sona;
        championList[85] = Soraka;
        championList[86] = Swain;
        championList[87] = Syndra;
        championList[88] = Talon;
        championList[89] = Taric;
        championList[90] = Teemo;
        championList[91] = Thresh;
        championList[92] = Tristana;
        championList[93] = Trundle;
        championList[94] = Tryndamere;
        championList[95] = TwistedFate;
        championList[96] = Twitch;
        championList[97] = Udyr;
        championList[98] = Urgot;
        championList[99] = Varus;
        championList[100] = Vayne;
        championList[101] = Veigar;
        championList[102] = Vi;
        championList[103] = Viktor;
        championList[104] = Vladimir;
        championList[105] = Volibear;
        championList[106] = Warwick;
        championList[107] = Wukong;
        championList[108] = Xerath;
        championList[109] = XinZhao;
        championList[110] = Yasuo;
        championList[111] = Yorick;
        championList[112] = Zac;
        championList[113] = Zed;
        championList[114] = Ziggs;
        championList[115] = Zilean;
        championList[116] = Zyra;
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
        List<String> championsList = new LinkedList<String>(Arrays.asList(this.championList));

        return championsList;
    }
}
