//pointblank:gunmetal

ServerEvents.recipes(event => {
    event.remove({ output: 'pointblank:gunmetal_ingot' })
    event.remove({ output: 'pointblank:gunmetal_mesh' })
    // Usuwa crafting supply cart
    event.remove({ output: /astikorcartsredux:.*_supply_cart/ })
})
