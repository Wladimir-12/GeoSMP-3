LootJs.modifiers((event) => {
	event.addLootModifier(LootFilter.alwaysTrue())
	.removeItemByMod("artifacts");
});
