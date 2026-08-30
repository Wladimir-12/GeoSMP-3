import mod.pbj.feature.FireModeFeature
import mod.pbj.item.AnimationProvider
import mod.pbj.item.FireMode
import mod.pbj.item.FireModeInstance
import mod.pbj.item.GunItem
import mod.pbj.registry.AmmoRegistry
import mod.pbj.registry.ItemRegistry
import mod.pbj.registry.SoundRegistry
import mod.pbj.util.TimeUnit
import net.minecraft.network.chat.Component

import static mod.pbj.registry.AmmoRegistry.AMMO556
import static mod.pbj.registry.AmmoRegistry.AMMOCREATIVE
import static mod.pbj.registry.FeatureTypeRegistry.AIMING
import static mod.pbj.registry.FeatureTypeRegistry.FIREMODE
import static mod.pbj.registry.FeatureTypeRegistry.MUZZLEFLASH
import static mod.pbj.registry.FeatureTypeRegistry.PARTVISIBILITY
import static mod.pbj.registry.FeatureTypeRegistry.SOUND
import static mod.pbj.util.Conditions.*
/**
 * gun = GunItem.builder(this) { code inside here }
**/
gun = GunItem.builder (this) {
    // Parenthesis are often optional, but not always, so check for errors!
    withName "wlvrn"
    withMaxAmmoCapacity 30
    withCompatibleAmmo AMMOCREATIVE, AMMO556
    withDamage 5
    withRpm 675
    withAdsSpeed 400
    withAnimationType GunItem.AnimationType.RIFLE
    withGunRecoilInitialAmplitude 0.65
    withShakeRecoilAmplitude 0.75
    withShakeRecoilSpeed 1.2
    withViewRecoilAmplitude 1.6
    withCompatibleAttachment "cantedrail", "wlvrn_308"
    withCompatibleAttachmentGroup "ar_sightsandscopes",
            "ar_muzzle",
            "underbarrel",
            "wlvrn_skins"
    withFireAnimation(
            "animation.model.fire",
            doesNotHaveAttachment("wlvrn_308"),
            80,
            TimeUnit.MILLISECOND
    )
    withPhasedReload(
            GunItem.ReloadPhase.RELOADING,
            //condition
            onNonEmptyReload(),
            2500,
            "animation.model.reload"
    )
    withPhasedReload(
            GunItem.ReloadPhase.RELOADING,
            onEmptyReload(),
            3350,
            "animation.model.reloadempty"
    )
    withDrawAnimation(
            "animation.model.draw",
            750
    )

    withPutAwayAnimation(
            "animation.model.putaway",
            600
    )
    withInspectAnimation(
            "animation.model.inspect",
            5500
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
            .withCondition(doesNotHaveAttachmentGroup ("ar_sightsandscopes"))
    )
    withFeature(
            FIREMODE.get()
            .withFireMode(new FireModeFeature.FireModeDescriptor.Builder()
                .withName ("single")
                .withType (FireMode.SINGLE)
                .withDisplayName (Component.translatable("label.pointblank.fireMode.single"))
                .withAmmoSupplier (AMMO556)
                .withMaxAmmoCapacity (30)
                .withRpm (675)
                .withEnableFireModeAnimation("animation.model.switch", 600)
                .withDamage(5.0)
                .build()
            )
            .withFireMode(new FireModeFeature.FireModeDescriptor.Builder()
                .withName ("automatic")
                .withType (FireMode.AUTOMATIC)
                .withDisplayName (Component.translatable("label.pointblank.fireMode.automatic"))
                .withAmmoSupplier (AMMO556)
                .withMaxAmmoCapacity (30)
                .withRpm (675)
                .withDamage(5.0)
                .withEnableFireModeAnimation("animation.model.switch", 600)
                .build()
            )
            .withCondition (doesNotHaveAttachment("wlvrn_308"))
    )
    withFeature(
            SOUND.get()
                    .withFireSound(SoundRegistry.M4A1, 1, doesNotHaveAttachmentGroup("ar_suppressors") & doesNotHaveAttachment("wlvrn_308"))
                    .withFireSound(SoundRegistry.M4A1_SILENCED, 1, hasAttachmentGroup ("ar_suppressors") & doesNotHaveAttachment("wlvrn_308"))
    )
    withFeature(
            MUZZLEFLASH.get()
            .withEffect(
                    GunItem.FirePhase.FIRING,
                    "muzzle_flash_small",
                    isUsingDefaultMuzzle() & doesNotHaveAttachmentGroup("ar_suppressors")
            )
    )
    withFeature(
            PARTVISIBILITY.get()
                    .withHiddenPart("sights", hasAttachmentGroup ("ar_sightsandscopes"))
                    .withHiddenPart("sightsflipped", doesNotHaveAttachmentGroup ("ar_sightsandscopes"))
                    .withHiddenPart("magazine", hasAttachment ("wlvrn_308"))
                    .withHiddenPart("magazine2", doesNotHaveAttachment ("wlvrn_308"))
    )
}

// Neccessary IF the gun is not the only thing in the file
return gun