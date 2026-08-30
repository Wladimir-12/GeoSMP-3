import mod.pbj.feature.FireModeFeature
import mod.pbj.item.AttachmentItem
import mod.pbj.item.FireMode
import mod.pbj.item.FireModeInstance
import mod.pbj.registry.SoundRegistry
import net.minecraft.network.chat.Component

import static mod.pbj.registry.AmmoRegistry.AMMO556
import static mod.pbj.registry.AmmoRegistry.AMMO556
import static mod.pbj.registry.AmmoRegistry.AMMO762X51
import static mod.pbj.registry.FeatureTypeRegistry.*
import static mod.pbj.util.Conditions.doesNotHaveAttachment
import static mod.pbj.util.Conditions.doesNotHaveAttachmentGroup
/**
 * gun = GunItem.builder(this) { code inside here }
 **/
attachment = AttachmentItem.builder (this) {
    withName "wlvrn_308"
    withCategory "conversion"
    withDescription "description.pointblank.wlvrn_308"
    withFeature(
            FIREMODE.get()
                    .withFireMode(new FireModeFeature.FireModeDescriptor.Builder()
                            .withName ("single_308")
                            .withType (FireMode.SINGLE)
                            .withDisplayName (Component.translatable("label.pointblank.fireMode.single"))
                            .withAmmoSupplier (AMMO762X51)
                            .withMaxAmmoCapacity (20)
                            .withRpm (475)
                            .withEnableFireModeAnimation("animation.model.switch", 600)
                            .withDamage(8.0)
                            .build()
                    )
                    .withFireMode(new FireModeFeature.FireModeDescriptor.Builder()
                            .withName ("automatic_308")
                            .withType (FireMode.AUTOMATIC)
                            .withDisplayName (Component.translatable("label.pointblank.fireMode.automatic"))
                            .withAmmoSupplier (AMMO762X51)
                            .withMaxAmmoCapacity (20)
                            .withRpm (475)
                            .withDamage(8.0)
                            .withEnableFireModeAnimation("animation.model.switch", 600)
                            .build()
                    )
    )
    withFeature(
            SOUND.get()
            .withFireSound(SoundRegistry.MK14EBR, 1.0, doesNotHaveAttachmentGroup("ar_suppressors"))
            .withFireSound(SoundRegistry.MK14EBR_SILENCED, 1.0)
    )
    withFeature(
            RECOIL.get().withRecoilModifier(1.8)
    )
    withFeature(
            ADSSPEED.get().withAdsSpeedMultiplier(1.25)
    )
}

return attachment
