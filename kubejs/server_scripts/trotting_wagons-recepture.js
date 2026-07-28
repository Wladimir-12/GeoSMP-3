ServerEvents.recipes(event => {
    event.replaceInput(
        { mod: 'trotting_wagons' },
        'minecraft:spruce_planks',
        '#minecraft:planks'
    )
    event.replaceInput(
	{ mod: 'trotting_wagons' },
	'minecraft:stripped_spruce_log' ,
	'#c:stripped_logs'
    )

})

