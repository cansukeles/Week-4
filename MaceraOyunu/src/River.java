public class River extends BattleLoc{
    River(Player player) {
        super(player,"River", new Bear(), "water", 3);}

        @Override
        public void earnAward() {
            this.getPlayer().getInventory().setHasWater(true);
    }
}
