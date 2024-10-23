package pl.paulb.dndmanager.model;

public class Currency {
    private int copperCoins;
    private int silverCoins;
    private int electrumCoins;
    private int goldCoins;
    private int platinumCoins;

    public Currency() {
        this.copperCoins = 0;
        this.silverCoins = 0;
        this.electrumCoins = 0;
        this.goldCoins = 0;
        this.platinumCoins = 0;
    }

    public Currency(int copperCoins, int silverCoins, int electrumCoins, int goldCoins, int platinumCoins) {
        this.copperCoins = copperCoins;
        this.silverCoins = silverCoins;
        this.electrumCoins = electrumCoins;
        this.goldCoins = goldCoins;
        this.platinumCoins = platinumCoins;
    }

    public int getCopperCoins() {
        return copperCoins;
    }

    public void setCopperCoins(int copperCoins) {
        this.copperCoins = copperCoins;
    }

    public int getSilverCoins() {
        return silverCoins;
    }

    public void setSilverCoins(int silverCoins) {
        this.silverCoins = silverCoins;
    }

    public int getElectrumCoins() {
        return electrumCoins;
    }

    public void setElectrumCoins(int electrumCoins) {
        this.electrumCoins = electrumCoins;
    }

    public int getGoldCoins() {
        return goldCoins;
    }

    public void setGoldCoins(int goldCoins) {
        this.goldCoins = goldCoins;
    }

    public int getPlatinumCoins() {
        return platinumCoins;
    }

    public void setPlatinumCoins(int platinumCoins) {
        this.platinumCoins = platinumCoins;
    }

    public double getTotalInGold() {
        return copperCoins / 100.0 +
                silverCoins / 10.0 +
                electrumCoins / 2.0 +
                goldCoins +
                platinumCoins * 10;
    }

    public void addCurrency(Currency other) {
        this.copperCoins += other.getCopperCoins();
        this.silverCoins += other.getSilverCoins();
        this.electrumCoins += other.getElectrumCoins();
        this.goldCoins += other.getGoldCoins();
        this.platinumCoins += other.getPlatinumCoins();
    }

    public boolean deductCurrency(Currency other) {
        if (this.getTotalInGold() >= other.getTotalInGold()) {
            this.copperCoins -= other.copperCoins;
            this.silverCoins -= other.silverCoins;
            this.electrumCoins -= other.electrumCoins;
            this.goldCoins -= other.goldCoins;
            this.platinumCoins -= other.platinumCoins;
            return true;
        }
        return false;
    }
}
