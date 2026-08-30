import mod.pbj.feature.ConditionContext
import mod.pbj.item.FireMode
import mod.pbj.item.GunItem
import mod.pbj.registry.SoundRegistry

import static mod.pbj.registry.AmmoRegistry.*
import static mod.pbj.registry.FeatureTypeRegistry.*
import static mod.pbj.util.Conditions.*
/**
 * gun = GunItem.builder(this) { code inside here }
**/
gun = GunItem.builder(this) {
    // Parenthesis are often optional, but not always, so check for errors!
    withName "m1911a1"
    withMaxAmmoCapacity 7
    withCompatibleAmmo AMMOCREATIVE, AMMO45ACP
    withDamage 5
    withRpm 800
    withAdsSpeed 350
    withFireModes FireMode.SINGLE
    withFireSound SoundRegistry.M1911A1
    withAnimationType GunItem.AnimationType.PISTOL
    withCompatibleAttachment "moa_hg"
    withCompatibleAttachmentGroup "smg_muzzle", "hg_sights", "m1911a1_skins"
    withGunRecoilInitialAmplitude 0.8
    withShakeRecoilAmplitude 0.35
    withShakeRecoilSpeed 3
    withViewRecoilAmplitude 3
    withPhasedReload(
            GunItem.ReloadPhase.RELOADING,
            //condition
            onEmptyReload(),
            1850,
            "animation.model.reloadempty"
    )
    withPhasedReload(
            GunItem.ReloadPhase.RELOADING,
            onNonEmptyReload(),
            1480,
            "animation.model.reload"
    )
    withDrawAnimation(
            "animation.model.draw",
            // same as a json string expression, it's a condition
            expr("ammoCount > 0"),
            550
    )
    withDrawAnimation(
            "animation.model.drawempty",
            expr("ammoCount == 0"),
            550
    )
    withPutAwayAnimation(
            "animation.model.putaway",
            // same as a json string expression, it's a condition
            expr("ammoCount > 0"),
            550
    )
    withPutAwayAnimation(
            "animation.model.putawayempty",
            expr("ammoCount == 0"),
            550
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
            3800
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
            .withZoom(0.25)
            .withCondition(doesNotHaveAttachmentGroup ("hg_sights"))
    )
    withFeature(
            SOUND.get()
            .withFireSound(SoundRegistry.M1911_SILENCED, 1)
            // Parenthesis for only 1 argument are optional
            .withCondition(hasAttachmentGroup "smg_suppressors")
    )
    withFeature(
            PARTVISIBILITY.get()
            .withHiddenPart(
                    "sightmount",
                    // Parenthesis for only 1 argument are optional
                    doesNotHaveAttachmentGroup("hg_sights")
            )
    )
    withFeature(
            MUZZLEFLASH.get()
            .withEffect(
                    GunItem.FirePhase.FIRING,
                    "muzzle_flash_small",
                    // Conditions from Conditions AND a custom condition ------------------------------- here, { ConditionContext ctx -> } is the base, after the arrow use ctx to get information and return true or false.
                    isUsingDefaultMuzzle() & doesNotHaveAttachmentGroup("smg_muzzle") & { ConditionContext ctx -> ctx.player() != null && ctx.player().currentlyGlowing }
            )
    )
}

// Neccessary IF the gun is not the only thing in the file
return gun