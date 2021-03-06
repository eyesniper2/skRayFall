package net.rayfall.eyesniper2.skrayfall.coreprotect;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.DocumentationId;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import java.util.List;

@Name("Is Natural/Unnatural")
@Description("Using Core Protect, check to see if a block is natural or if it has been modified.")
@RequiredPlugins("CoreProtect")
@DocumentationId("CondCoreProtectIsNaturalUnnatural")
public class CondIsNotNaturalCoreProtect extends Condition {

    private Expression<Block> block;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        block = (Expression<Block>) exp[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    public boolean check(Event evt) {
        Block testBlock = block.getSingle(evt);
        List<String[]> result = Core.skCoreProtect.blockLookup(testBlock, Integer.MAX_VALUE);
        return !result.isEmpty();
    }

}
