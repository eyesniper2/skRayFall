package net.rayfall.eyesniper2.skrayfall.effectlibsupport

import ch.njol.skript.Skript
import ch.njol.skript.util.VisualEffect
import org.bukkit.Particle

object EffectLibUtils {

    @JvmStatic
    fun getParticleFromVisualEffect(visualEffect: VisualEffect?) : Particle? {

        if (visualEffect == null){
            return null
        }

        return if (visualEffect.isParticle) {
            visualEffect.effect as Particle
        }  else {
            Skript.warning("Unable to use $visualEffect as it is not a particle.")
            null
        }
    }
}