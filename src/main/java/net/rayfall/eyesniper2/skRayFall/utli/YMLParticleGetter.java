package net.rayfall.eyesniper2.skRayFall.utli;

import ch.njol.skript.Skript;
import de.slikey.effectlib.util.ParticleEffect;

public class YMLParticleGetter {

	public static ParticleEffect particleTypeGetter(String p) {
		switch (p.replace(" ", "").toLowerCase()) {
		case "hugeexplosion":
			return ParticleEffect.HUGE_EXPLOSION;
		case "largeexplode":
			return ParticleEffect.LARGE_EXPLODE;
		case "fireworksspark":
			return ParticleEffect.FIREWORKS_SPARK;
		case "suspend":
			return ParticleEffect.SUSPEND;
		case "depthsuspend":
			return ParticleEffect.DEPTH_SUSPEND;
		case "townaura":
			return ParticleEffect.TOWN_AURA;
		case "crit":
			return ParticleEffect.CRIT;
		case "magiccrit":
			return ParticleEffect.MAGIC_CRIT;
		case "smoke":
			return ParticleEffect.SMOKE;
		case "mobspell":
			return ParticleEffect.MOB_SPELL;
		case "mobspellambient":
			return ParticleEffect.MOB_SPELL_AMBIENT;
		case "spell":
			return ParticleEffect.SPELL;
		case "instantspell":
			return ParticleEffect.INSTANT_SPELL;
		case "witchmagic":
			return ParticleEffect.WITCH_MAGIC;
		case "portal":
			return ParticleEffect.PORTAL;
		case "enchantmenttable":
			return ParticleEffect.ENCHANTMENT_TABLE;
		case "explode":
			return ParticleEffect.EXPLODE;
		case "flame":
			return ParticleEffect.FLAME;
		case "lava":
			return ParticleEffect.LAVA;
		case "footstep":
			return ParticleEffect.FOOTSTEP;
		case "splash":
			return ParticleEffect.SPLASH;
		case "wake":
			return ParticleEffect.WAKE;
		case "largesmoke":
			return ParticleEffect.LARGE_SMOKE;
		case "cloud":
			return ParticleEffect.CLOUD;
		case "reddust":
			return ParticleEffect.RED_DUST;
		case "snowballpoof":
			return ParticleEffect.SNOWBALL_POOF;
		case "dripwater":
			return ParticleEffect.DRIP_WATER;
		case "driplava":
			return ParticleEffect.DRIP_LAVA;
		case "snowshovel":
			return ParticleEffect.SNOW_SHOVEL;
		case "slime":
			return ParticleEffect.SLIME;
		case "heart":
			return ParticleEffect.HEART;
		case "angryvillager":
			return ParticleEffect.ANGRY_VILLAGER;
		case "happyvillager":
			return ParticleEffect.HAPPY_VILLAGER;
		case "barrier":
			return ParticleEffect.BARRIER;
		case "droplet":
			return ParticleEffect.DROPLET;
		case "take":
			return ParticleEffect.TAKE;
		case "mobappearance":
			return ParticleEffect.MOB_APPEARANCE;
		default:
			Skript.error("No particle type selected! Using a Crit instead.");
			return ParticleEffect.CRIT;
		}
	}
}

// ICON_CRACK =("iconcrack_{subtype}")

// BLOCK_CRACK =("blockcrack_{subtype}")

// TILE_CRACK =("tilecrack_{subtype}")
