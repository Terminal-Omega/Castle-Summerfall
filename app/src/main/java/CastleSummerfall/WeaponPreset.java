package CastleSummerfall;

import java.util.List;

/**
 * @author @Corbanator A special InteractablePreset for weapons.
 */
public class WeaponPreset extends InteractablePreset {

    // all the various attributes of a weapon.
    public int attackSpeed;
    public int attackSpeedRange;
    public String type;
    public int damage;
    public int damageRange;
    public int range;
    public int rangeRange;
    public String name;
    public boolean sharp;
    public int pierce;
    public int pierceRange;

    public WeaponPreset(String name, String[] descriptions, List<AbilityOption> abilityOptions, int size, int weight,
            boolean canBePickedUp, int rarity) {
        super(name, descriptions, abilityOptions, size, weight, canBePickedUp, rarity);
    }

    public WeaponPreset(InteractablePreset preset) {
        this(preset.name, preset.descriptions, preset.abilityOptions, preset.size, preset.weight, preset.canBePickedUp,
                preset.rarity);
    }

    /**
     * this is placed seperately from the constructor for ease of creating
     * WeaponPresets.
     *
     * @param attackSpeed
     * @param attackSpeedRange
     * @param type
     * @param damage
     * @param damageRange
     * @param range
     * @param rangeRange
     * @param name
     * @param sharp
     * @param pierce
     * @param pierceRange
     */
    public void setValues(int attackSpeed, int attackSpeedRange, String type, int damage, int damageRange, int range,
            int rangeRange, String name, boolean sharp, int pierce, int pierceRange) {
        this.attackSpeed = attackSpeed;
        this.attackSpeedRange = attackSpeedRange;
        this.type = type;
        this.damage = damage;
        this.damageRange = damageRange;
        this.range = range;
        this.rangeRange = rangeRange;
        this.name = name;
        this.sharp = sharp;
        this.pierce = pierce;
        this.pierceRange = pierceRange;
    }
}
