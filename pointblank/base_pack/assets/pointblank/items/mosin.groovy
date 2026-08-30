import groovy.transform.Field
import mod.pbj.attachment.Attachments
import mod.pbj.item.FireMode
import mod.pbj.item.GunItem
import mod.pbj.registry.SoundRegistry
import mod.pbj.script.event.EventDef
import mod.pbj.script.event.ReloadEvent
import mod.pbj.util.TimeUnit

import static mod.pbj.registry.AmmoRegistry.AMMO762X51
import static mod.pbj.registry.AmmoRegistry.AMMOCREATIVE
import static mod.pbj.registry.FeatureTypeRegistry.AIMING
import static mod.pbj.registry.FeatureTypeRegistry.PARTVISIBILITY
import static mod.pbj.util.Conditions.*
/**
 * gun = GunItem.builder(this) { code inside here }
**/
gun = GunItem.builder(this) {
    // Parenthesis are often optional, but not always, so check for errors!
    withName "mosin"
    withMaxAmmoCapacity 5
    withCompatibleAmmo AMMOCREATIVE, AMMO762X51
    withDamage 10
    withRpm 48
    withAdsSpeed 550
    withFireModes FireMode.SINGLE
    withFireSound SoundRegistry.BALLISTA
    withAnimationType GunItem.AnimationType.RIFLE
    withGunRecoilInitialAmplitude 1.5
    withShakeRecoilAmplitude 0.35
    withShakeRecoilSpeed 2
    withViewRecoilAmplitude 3
    withCompatibleAttachment "pu_scope"
    withFireAnimation(
            "animation.model.fire",
            expr("ammoCount > 0") | hasAttachment("pu_scope"),
            1240,
            TimeUnit.MILLISECOND
    )
    withFireAnimation(
            "animation.model.fireempty",
            expr("ammoCount == 0") & doesNotHaveAttachment("pu_scope"),
            1240,
            TimeUnit.MILLISECOND
    )
    withPhasedReload(
            GunItem.ReloadPhase.RELOADING,
            //condition
            onEmptyReload() & doesNotHaveAttachment("pu_scope"),
            2000,
            "animation.model.reloadempty"
    )
    withPhasedReload(
            GunItem.ReloadPhase.PREPARING,
            expr("reloadIterationIndex >= 0") | (expr("reloadIterationIndex >= -1") & hasAttachment("pu_scope")),
            1000,
            "animation.model.reloadoneprepare"
    )
    withPhasedReload(
            GunItem.ReloadPhase.RELOADING,
            expr("reloadIterationIndex > 0"),
            780,
            "animation.model.reloadone"
    )
    withPhasedReload(
            GunItem.ReloadPhase.COMPLETETING,
            expr("reloadIterationIndex >= 0"),
            820,
            "animation.model.reloadonefinish"
    )
    withDrawAnimation(
            "animation.model.draw",
            // same as a json string expression, it's a condition
            expr("ammoCount > 0"),
            1150
    )
    withDrawAnimation(
            "animation.model.drawempty",
            expr("ammoCount == 0"),
            1150
    )
    withPutAwayAnimation(
            "animation.model.putaway",
            // same as a json string expression, it's a condition
            expr("ammoCount > 0"),
            650
    )
    withPutAwayAnimation(
            "animation.model.putawayempty",
            expr("ammoCount == 0"),
            650
    )
    withIdleAnimation(
            "animation.model.idle",
            expr("ammoCount > 0"),
            0
    )
    withIdleAnimation(
            "animation.model.idleempty",
            expr("ammoCount == 0") & doesNotHaveAttachment("pu_scope"),
            0
    )
    withInspectAnimation(
            "animation.model.inspect",
            expr("ammoCount > 0"),
            5500
    )
    withInspectAnimation(
            "animation.model.inspectempty",
            expr("ammoCount == 0"),
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
            .withZoom(0.4)
    )
    withFeature(
            PARTVISIBILITY.get()
            .withHiddenPart("infantry", hasAttachment("pu_scope"))
            .withShownPart("sniper", hasAttachment("pu_scope"))
    )
}

@Field
boolean fullReload = false

@EventDef
def reload(ReloadEvent.Reload event) {
    if(GunItem.getAmmo(event.itemStack, event.fireModeInstance) > 0 || Attachments.getAttachments(event.itemStack, false).get("//pu_scope") != null) {
        event.ammo = Math.min(event.ammo, 1)
        fullReload = false
    }
    else {
        fullReload = true
    }
}

@EventDef
def reload(ReloadEvent.Completing event) {
    if(fullReload) event.cancel()
}

// Neccessary IF the gun is not the only thing in the file
return gun