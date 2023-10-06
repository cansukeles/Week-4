public class Cave extends BattleLoc{
    Cave(Player player) {
        super(player, "Cave", new Zombie(), "food", 3);
    }

    @Override
    public void earnAward() {
        this.getPlayer().getInventory().setHasFood(true);
    }
}
