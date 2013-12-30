package models.game;

/**
 * Enum for champion names.
 */
public enum ChampionName {
    Aatrox("Aatrox"), Ahri("Ahri"), Akali("Akali"), Alistar("Alistar"), Amumu("Amumu"), Anivia("Anivia"), Annie("Annie"),
    Ashe("Ashe"), Blitzcrank("Blitzcrank"), Brand("Brand"), Caitlyn("Caitlyn"), Cassiopeia("Cassiopeia"),
    Cho_Gath("Cho'Gath"), Corki("Corki"), Darius("Darius"), Diana("Diana"), Dr_Mundo("Dr. Mundo"), Draven("Draven"),
    Elise("Elise"), Evelynn("Evelynn"), Ezreal("Ezreal"), Fiddlesticks("Fiddlesticks"), Fiora("Fiora"), Fizz("Fizz"),
    Galio("Galio"), Gangplank("Gangplank"), Garen("Garen"), Gragas("Gragas"), Graves("Graves"), Hecarim("Hecarim"),
    Heimerdinger("Heimerdinger"), Irelia("Irelia"), Janna("Janna"), Jarvan_IV("Jarvan IV"), Jax("Jax"), Jayce("Jayce"),
    Jinx("Jinx"), Karma("Karma"), Karthus("Karthus"), Kassadin("Kassadin"), Katarina("Katarina"), Kayle("Kayle"),
    Kennen("Kennen"), Kha_Zix("Kha'Zix"), Kog_Maw("Kog'Maw"), LeBlanc("LeBlanc"), Lee_Sin("Lee Sin"), Leona("Leona"),
    Lissandra("Lissandra"), Lucian("Lucian"), Lulu("Lulu"), Lux("Lux"), Malphite("Malphite"), Malzahar("Malzahar"),
    Maokai("Maokai"), Master_Yi("Master Yi"), Miss_Fortune("Miss Fortune"), Mordekaiser("Mordekaiser"),
    Morgana("Morgana"), Nami("Nami"), Nasus("Nasus"), Nautilus("Nautilus"), Nidalee("Nidalee"), Nocturne("Nocturne"),
    Nunu("Nunu"), Olaf("Olaf"), Orianna("Orianna"), Pantheon("Pantheon"), Poppy("Poppy"), Quinn("Quinn"),
    Rammus("Rammus"), Renekton("Renekton"), Rengar("Rengar"), Riven("Riven"), Rumble("Rumble"), Ryze("Ryze"),
    Sejuani("Sejuani"), Shaco("Shaco"), Shen("Shen"), Shyvana("Shyvana"), Singed("Singed"), Sion("Sion"),
    Sivir("Sivir"), Skarner("Skarner"), Sona("Sona"), Soraka("Soraka"), Swain("Swain"), Syndra("Syndra"),
    Talon("Talon"), Taric("Taric"), Teemo("Teemo"), Thresh("Thresh"), Tristana("Tristana"), Trundle("Trundle"),
    Tryndamere("Tryndamere"), Twisted_Fate("Twisted Fate"), Twitch("Twitch"), Udyr("Udyr"), Urgot("Urgot"),
    Varus("Varus"), Vayne("Vayne"), Veigar("Veigar"), Vi("Vi"), Viktor("Viktor"), Vladimir("Vladimir"),
    Volibear("Volibear"), Warwick("Warwick"), Wukong("Wukong"), Xerath("Xerath"), Xin_Zhao("Xin Zhao"), Yasuo("Yasuo"),
    Yorick("Yorick"), Zac("Zac"), Zed("Zed"), Ziggs("Ziggs"), Zilean("Zilean"), Zyra("Zyra");

    /**
     * Text representation for the champion name.
     */
    private final String textRepresentation;

    /**
     * Constructor for champion name enum.
     * @param textRepresentation The text representation of the champion name.
     */
    private ChampionName(String textRepresentation) {
        this.textRepresentation = textRepresentation;
    }

    /**
     * Gets the String representation of the Champion Name.
     * @return The String representation of the Champion Name.
     */
    public String toString() {
        return this.textRepresentation;
    }
}