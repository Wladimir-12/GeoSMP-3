import mod.pbj.item.GunItem

import static mod.pbj.item.GunItem.builder

builder(this) {
    fromJson(file("aa12.disabled"))

    //Overrides values from JSON
    withRpm 1400
    //Advanced predicate
    withPhasedReload(
            GunItem.ReloadPhase.RELOADING,
            3520,
            "reloadempty"
    )
}
