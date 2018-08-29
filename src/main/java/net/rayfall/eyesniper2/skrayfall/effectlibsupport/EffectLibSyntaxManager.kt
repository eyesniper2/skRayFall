package net.rayfall.eyesniper2.skrayfall.effectlibsupport

import ch.njol.skript.Skript
import de.slikey.effectlib.EffectManager
import net.rayfall.eyesniper2.skrayfall.SyntaxManagerInterface
import net.rayfall.eyesniper2.skrayfall.particles.EffClientsideStaticParticles
import net.rayfall.eyesniper2.skrayfall.particles.EffStaticParticles
import org.bukkit.plugin.Plugin

class EffectLibSyntaxManager(val plugin: Plugin) : SyntaxManagerInterface {

    var effectManager: EffectManager? = null
    val rayfallEffectManager: RayFallEffectManager = RayFallEffectManager(plugin)

    override fun registerSyntax() {
        if (Skript.isRunningMinecraft(1, 13)) {
            plugin.logger.warning("EffectLib 6.0 for 1.13 is not supported as of right now since it has backwards " +
                    "breaking changes for users below 1.10")
            return
        }
        plugin.logger.info("Got bacon for the EffectLib particle ninjas!")
        effectManager = EffectManager(plugin)
        // more stuff and options to be added
        Skript.registerEffect(EffDeleteEffect::class.java, "(delete|stop) (effect|formation) %string%")
        Skript.registerEffect(EffEffectLibAtom::class.java,
                "(spawn|create|apply) (a|the|an) atom (effect|formation) (at|on|for|to) " + "%entity/location% with id %string%")
        Skript.registerEffect(EffEffectLibBleed::class.java,
                "(spawn|create|apply) (a|the|an) bleed (effect|formation) (at|on|for|to) " + "%entity/location% with id %string%")
        Skript.registerEffect(EffEffectLibText::class.java,
                "(spawn|create|apply) (a|the|an) text (effect|formation) with text %string% as "
                        + "%effectlibparticle% (at|on|for|to) %entities/location% with id %string%"
                        + "[(,| and) %number% large]")
        Skript.registerEffect(EffEffectLibAnimatedBallEffect::class.java,
                "(spawn|create|apply) (a|the|an) animated ball (effect|formation) (at|on|for|to) "
                        + "%entity/location% with id %string% [with particle %-effectlibparticle%] "
                        + "[offset by %number%, %number%(,| and) %number%]")
        Skript.registerEffect(EffEffectLibArc::class.java,
                "(spawn|create|apply) (a|the|an) arc (effect|formation) from %entity/location% "
                        + "to %entity/location% with id %string% [with particle[s] %-effectlibparticle%] "
                        + "[%number% high] [and %number% particles]")
        Skript.registerEffect(EffEffectLibWave::class.java,
                "(spawn|create|apply) (a|the|an) wave (effect|formation) at %entity/location% " + "with id %string% [with particle[s] %-effectlibparticle%]")
        Skript.registerEffect(EffEffectLibLine::class.java,
                "(spawn|create|apply) (a|the|an) line (effect|formation) from %entity/location% " + "to %entity/location% with id %string% [with particle[s] %-effectlibparticle%]")
        Skript.registerEffect(EffEffectLibCircle::class.java,
                "(spawn|create|apply) (a|the|an) circle (effect|formation) (at|on|for) "
                        + "%entity/location% with id %string% [with particle[s] %-effectlibparticle%] "
                        + "[(and|with) radius %-number%]")
        Skript.registerEffect(EffStaticParticles::class.java,
                "(create|display|show) %number% [of] %effectlibparticle% particle[s] at %location% "
                        + "[(with data of %-itemstack%|with color %number%, %number%(,| and) %number%)] "
                        + "[offset by %number%, %number%(,| and) %number%] [(with|at) speed %number%]")
        Skript.registerEffect(EffClientsideStaticParticles::class.java,
                "(create|display|show) %number% [of] %effectlibparticle% particle[s] at %location% "
                        + "for %player% [with data of %-itemstack%] [offset by %number%, %number%(,| and) "
                        + "%number%] [(with|at) speed %number%]")
        Skript.registerEffect(EffEffectTornado::class.java,
                "(spawn|create|apply) (a|the|an) tornado (effect|formation) at %entity/location% with "
                        + "id %string% [with tornado particle[s] %-effectlibparticle% and cloud particle[s] "
                        + "%-effectlibparticle%] [(set|and) radius %number%] [(set|and) max height %number%]")
        // Skript.registerEffect(EffEffectLibUpwardsSpiral.class,
        // "(spawn|create|apply) (a|the|an) upward[s] spiral (effect|formation)
        // (at|on|for|to) %entity/location% with id %string%");

    }
}