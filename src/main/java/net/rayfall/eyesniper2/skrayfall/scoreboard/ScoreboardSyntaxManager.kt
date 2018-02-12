package net.rayfall.eyesniper2.skrayfall.scoreboard

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import net.rayfall.eyesniper2.skrayfall.SyntaxManagerInterface
import org.bukkit.plugin.Plugin

class ScoreboardSyntaxManager(val plugin: Plugin) : SyntaxManagerInterface {

    val idScoreBoardManager: IdScoreBoardManager = IdScoreBoardManager(plugin)


    override fun registerSyntax() {
        Skript.registerEffect(EffNameOfScore::class.java,
                "set name of sidebar (of|for) %players% to %string%")
        Skript.registerEffect(EffSetScore::class.java,
                "set score %string% in sidebar of %player% to %number%")
        Skript.registerEffect(EffDeleteScore::class.java, "delete score %string% in sidebar of %player%")
        Skript.registerEffect(EffRemoveScoreboard::class.java, "(wipe|erase|delete) %player%['s] sidebar")
        Skript.registerEffect(EffSetScoreBelowName::class.java,
                "set score %string% below %player% to %number% for %player%")
        Skript.registerEffect(EffRemoveScoreBelowName::class.java,
                "(wipe|erase) below score[s] for %player%")
        Skript.registerEffect(EffSetScoreTab::class.java,
                "set tab[list] score of %player% to %number% for %player%")
        Skript.registerEffect(EffRemoveScoreTab::class.java, "(wipe|erase|delete) %player%['s] tab[list]")
        Skript.registerEffect(EffSetIdBasedScore::class.java,
                "(set|create) id [based] score %string% in sidebar of %player% to " + "%number% with id %string%")
        Skript.registerEffect(EffEditIdScore::class.java,
                "(edit|update) score [with] id %string% to %string% and %number%")
        Skript.registerEffect(EffDeleteIdScore::class.java, "(delete|remove) score [with] id %string%")
        // Group ID Based scores
        Skript.registerEffect(EffCreateGroupIdScore::class.java,
                "(set|create) group id [based] score %string% in sidebar for %players% to " + "%number% with id %string%")
        Skript.registerEffect(EffAddPlayerToGroupId::class.java,
                "add %player% to group score [with id] %string%")
        Skript.registerEffect(EffEditGroupIdScore::class.java,
                "(edit|update) score [with][in] group [id] %string% to %string% and %number%")
        Skript.registerEffect(EffRemovePlayerFromIdScore::class.java,
                "(delete|remove) %player% from group [id based] score %string%")
        Skript.registerEffect(RemoveGroupIdScore::class.java,
                "(delete|remove) score[s] [with] group id %string%")
        Skript.registerExpression(ExprScoreNameFromId::class.java, String::class.java, ExpressionType.SIMPLE,
                "score (name|title) (of|from) id %string%")
        Skript.registerExpression(ExprScoreNameFromGroupId::class.java, String::class.java, ExpressionType.SIMPLE,
                "group score (name|title) (of|from) id %string%")
        Skript.registerExpression(ExprScoreValueFromId::class.java, Number::class.java, ExpressionType.SIMPLE,
                "score (value|number) (of|from) id %string%")
        Skript.registerExpression(ExprScoreValueFromGroupId::class.java, Number::class.java, ExpressionType.SIMPLE,
                "group score (value|number) (of|from) id %string%")
        Skript.registerExpression(ExprScoreBoardTitle::class.java, String::class.java, ExpressionType.SIMPLE,
                "sidebar (title|name) for %player%")
        Skript.registerCondition(CondisScoreboardSet::class.java, "side bar is set for %player%")
        // Team Support
        //teamManager = new TeamManager();
        //Skript.registerEffect(EffCreateTeam.class,
        //    "(create|make) [a] team named %string% [with %-players%]");
        //Skript.registerEffect(EffDeleteTeam.class, "(delete|remove) [a] team named %string%");
        //Skript.registerEffect(EffSetTeamPrefix.class, "set prefix of team %string% to %string%");
        //Skript.registerEffect(EffSetTeamSuffix.class, "set suffix of team %string% to %string%");
        //Skript.registerEffect(EffSetTeamDisplayName.class,
        //    "set team display names for %string% to %string%");
    }

}