public class Inventory {
    private Gun gun;
    private Armor armor;
    private boolean hasWater;
    private boolean hasFireWood;
    private boolean hasFood;


    public Inventory(boolean hasWater, boolean hasFireWood, boolean hasFood) {
        this.gun = new Gun("Punch", -1, 0, 0);
        this.armor = new Armor("Rag", -1, 0, 0);
        this.hasWater = hasWater;
        this.hasFireWood = hasFireWood;
        this.hasFood = hasFood;
    }

    public Gun getGun() {
        return gun;
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public boolean getHasWater() {
        return hasWater;
    }

    public void setHasWater(boolean hasWater) {
        this.hasWater = hasWater;
    }

    public boolean getHasFireWood() {
        return hasFireWood;
    }
    public void setHasFireWood(boolean hasFireWood) {
        this.hasFireWood = hasFireWood;
    }
    public boolean getHasFood(){
        return hasFood;
    }
    public void setHasFood(boolean hasFood){
        this.hasFood = hasFood;
    }


}

