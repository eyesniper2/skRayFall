package net.rayfall.eyesniper2.skrayfall.effectlibsupport

import org.bukkit.Particle
import org.skriptlang.skript.bukkit.particles.particleeffects.ParticleEffect

object EffectLibUtils {

    @JvmStatic
    fun getParticleFromVisualEffect(visualEffect: ParticleEffect?) : Particle? {
        return visualEffect?.particle()
    }
}