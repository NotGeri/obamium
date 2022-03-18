package dev.geri.obamium.other;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.explosion.Explosion;

import java.util.Map;

public class Bamba extends Enchantment {

    public Bamba() {
        super(Rarity.VERY_RARE, EnchantmentTarget.VANISHABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public Map<EquipmentSlot, ItemStack> getEquipment(LivingEntity entity) {
        return super.getEquipment(entity);
    }

    @Override
    public Rarity getRarity() {
        return super.getRarity();
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 11;
    }

    @Override
    public int getMinPower(int level) {
        return super.getMinPower(level);
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMaxPower(level);
    }

    @Override
    public int getProtectionAmount(int level, DamageSource source) {
        return super.getProtectionAmount(level, source);
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        return super.getAttackDamage(level, group);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }

    @Override
    protected String getOrCreateTranslationKey() {
        return super.getOrCreateTranslationKey();
    }

    @Override
    public String getTranslationKey() {
        return super.getTranslationKey();
    }

    @Override
    public Text getName(int level) { // todo: custom name
        return super.getName(level);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return super.isAcceptableItem(stack);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity livingEntity) {
            livingEntity.world.createExplosion(livingEntity, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 10, false, Explosion.DestructionType.BREAK);
        }

        super.onTargetDamaged(user, target, level);
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        super.onUserDamaged(user, attacker, level);
    }

    @Override
    public boolean isTreasure() {
        return super.isTreasure();
    }

    @Override
    public boolean isCursed() {
        return super.isCursed();
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return super.isAvailableForEnchantedBookOffer();
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return super.isAvailableForRandomSelection();
    }
}
