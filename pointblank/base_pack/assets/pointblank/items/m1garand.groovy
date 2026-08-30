import mod.pbj.item.FireMode
import mod.pbj.item.GunItem
import mod.pbj.registry.SoundRegistry
import mod.pbj.util.TimeUnit

import static mod.pbj.registry.AmmoRegistry.AMMO762X51
import static mod.pbj.registry.AmmoRegistry.AMMOCREATIVE
import static mod.pbj.registry.FeatureTypeRegistry.AIMING
import static mod.pbj.registry.FeatureTypeRegistry.MUZZLEFLASH
import static mod.pbj.util.Conditions.*
/**
 * gun = GunItem.builder(this) { code inside here }
**/
gun = GunItem.builder(this) {
    // Parenthesis are often optional, but not always, so check for errors!
    withName "m1garand"
    withMaxAmmoCapacity 8
    withCompatibleAmmo AMMOCREATIVE, AMMO762X51 //Temp, add 30-06 ammo type
    withDamage 6.5
    withRpm 400
    withAdsSpeed 450
    withFireModes FireMode.SINGLE
    withFireSound SoundRegistry.MK14EBR
    withAnimationType GunItem.AnimationType.RIFLE
    withGunRecoilInitialAmplitude 1.2
    withShakeRecoilAmplitude 0.35
    withShakeRecoilSpeed 2
    withViewRecoilAmplitude 2.5
    withFireAnimation(
            "animation.model.fire",
            expr("ammoCount > 0"),
            640,
            TimeUnit.MILLISECOND
    )
    withFireAnimation(
            "animation.model.firelast",
            expr("ammoCount == 0"),
            640,
            TimeUnit.MILLISECOND
    )
    withPhasedReload(
            GunItem.ReloadPhase.RELOADING,
            //condition
            onNonEmptyReload(),
            1670,
            "animation.model.reload"
    )
    withPhasedReload(
            GunItem.ReloadPhase.RELOADING,
            onEmptyReload(),
            1330,
            "animation.model.reload2"
    )
    withDrawAnimation(
            "animation.model.draw",
            // same as a json string expression, it's a condition
            expr("ammoCount > 0"),
            750
    )
    withDrawAnimation(
            "animation.model.drawempty",
            expr("ammoCount == 0"),
            750
    )
    withPutAwayAnimation(
            "animation.model.putaway",
            // same as a json string expression, it's a condition
            expr("ammoCount > 0"),
            800
    )
    withPutAwayAnimation(
            "animation.model.putawayempty",
            expr("ammoCount == 0"),
            800
    )
    withIdleAnimation(
            "animation.model.idle",
            expr("ammoCount > 0"),
            0
    )
    withIdleAnimation(
            "animation.model.idleempty",
            expr("ammoCount == 0"),
            0
    )
    withInspectAnimation(
            "animation.model.inspect",
            expr("ammoCount > 0"),
            4400
    )
    withInspectAnimation(
            "animation.model.inspectempty",
            expr("ammoCount == 0"),
            3800
    )
    withEffect(
            GunItem.FirePhase.HIT_SCAN_ACQUIRED,
            "tracer"
    )
    withEffect(
            GunItem.FirePhase.FIRING,
            "muzzle_flash_small"
    )
    //Features
    withFeature(
            //The .get() IS necessary
            AIMING.get()
            .withZoom(0.4)
    )
    withFeature(
            MUZZLEFLASH.get()
            .withEffect(
                    GunItem.FirePhase.FIRING,
                    "muzzle_flash_small",
                    isUsingDefaultMuzzle()
            )
    )
}

// Neccessary IF the gun is not the only thing in the file
return gun