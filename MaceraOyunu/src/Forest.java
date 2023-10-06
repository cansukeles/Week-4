public class Forest extends BattleLoc {
    Forest(Player player) {
        super(player, "Forest", new Vampire(), "firewood", 3);
    }

    @Override
    public void earnAward() {
        this.getPlayer().getInventory().setHasFireWood(true);
    }
}
