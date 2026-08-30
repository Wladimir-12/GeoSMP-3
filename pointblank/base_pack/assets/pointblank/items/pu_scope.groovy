import mod.pbj.item.AttachmentItem
import mod.pbj.registry.FeatureTypeRegistry
import net.minecraft.network.chat.Component

AttachmentItem.builder(this) {
    withName "pu_scope"
    withDescription Component.translatable("description.pointblank.pu_scope")
    withCategory "scope"
    withFeature(
            FeatureTypeRegistry.AIMING.get()
            .withZoom(0.72)
    )
    withFeature(
            FeatureTypeRegistry.PIP.get()
            .withOverlayTexture("textures/gui/pu_scope_pip.png")
            .withZoom(0.8)
            .withParallaxEnabled(true)
    )
}
