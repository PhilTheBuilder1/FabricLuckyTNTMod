package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;

public class TetrahedronTNTEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity ent) {
        BlockPos pos = new BlockPos(ent.pos());

        double heigth = (Math.sqrt(3D) / 2D) * 60D;
        double sideHeigth = Math.sqrt(60D * 60D - 30D * 30D);

        BlockPos A = pos.add(-30, -30, -Math.round((1D / 3D) * sideHeigth));
        BlockPos B = pos.add(30, -30, -Math.round((1D / 3D) * sideHeigth));
        BlockPos C = pos.add(0, -30, Math.round((2D / 3D) * sideHeigth));
        BlockPos D = pos.add(0, Math.round(heigth - 30D), 0);

        Vec3d DA = new Vec3d(A.getX() - D.getX(), A.getY() - D.getY(), A.getZ() - D.getZ());
        Vec3d DB = new Vec3d(B.getX() - D.getX(), B.getY() - D.getY(), B.getZ() - D.getZ());
        Vec3d DC = new Vec3d(C.getX() - D.getX(), C.getY() - D.getY(), C.getZ() - D.getZ());
        Vec3d AB = new Vec3d(B.getX() - A.getX(), B.getY() - A.getY(), B.getZ() - A.getZ());
        Vec3d AC = new Vec3d(C.getX() - A.getX(), C.getY() - A.getY(), C.getZ() - A.getZ());

        Vec3d NDAB = DB.crossProduct(DA);
        Vec3d NDAC = DA.crossProduct(DC);
        Vec3d NDCB = DC.crossProduct(DB);
        Vec3d NABC = AB.crossProduct(AC);

        for (int offX = -40; offX <= 40; offX++) {
            for (int offY = -40; offY <= 40; offY++) {
                for (int offZ = -40; offZ <= 40; offZ++) {
                    Vec3d vec = new Vec3d(Math.round(ent.x() + offX), Math.round(ent.y() + offY), Math.round(ent.z() + offZ));

                    if (distance(vec, NDAB, D) <= 0 && distance(vec, NDAC, D) <= 0 && distance(vec, NDCB, D) <= 0 && distance(vec, NABC, A) <= 0) {
                        BlockPos pos5 = new BlockPos(ent.pos()).add(offX, offY, offZ);

                        if (ent.level().getBlockState(pos5).getBlock().getBlastResistance() <= 200) {
                            ent.level().getBlockState(pos5).getBlock().onDestroyedByExplosion(ent.level(), pos5, ImprovedExplosion.dummyExplosion(ent.level()));
                            ent.level().removeBlock(pos5, false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity ent) {
        for(double i = 0D; i <= 1D; i += 0.05D) {
            ent.level().addParticle(new DustParticleEffect(new Vector3f(0f, 0f, 0f), 0.5f), ent.x() - 0.5D + i, ent.y() + 1D, ent.z() - 0.5D, 0, 0, 0);
        }

        Vec3d vec1 = new Vec3d(0.5D, 0D, 1D);
        Vec3d vec2 = new Vec3d(-0.5D, 0D, 1D);

        for(double i = 0; i <= vec1.length(); i += vec1.length() / 20D) {
            ent.level().addParticle(new DustParticleEffect(new Vector3f(0f, 0f, 0f), 0.5f), ent.x() - 0.5D + vec1.x * i, ent.y() + 1D, ent.z() - 0.5D + vec1.z * i, 0, 0, 0);
            ent.level().addParticle(new DustParticleEffect(new Vector3f(0f, 0f, 0f), 0.5f), ent.x() + 0.5D + vec2.x * i, ent.y() + 1D, ent.z() - 0.5D + vec2.z * i, 0, 0, 0);
        }

        Vec3d Vec3d = new Vec3d(0.5D, 0.75D, 0.5D);
        Vec3d vec4 = new Vec3d(-0.5D, 0.75D, 0.5D);
        Vec3d vec5 = new Vec3d(0D, 0.75D, -0.5D);

        for(double i = 0; i <= Vec3d.length(); i += Vec3d.length() / 20D) {
            ent.level().addParticle(new DustParticleEffect(new Vector3f(0f, 0f, 0f), 0.5f), ent.x() - 0.5D + Vec3d.x * i, ent.y() + 1D + Vec3d.y * i, ent.z() - 0.5D + Vec3d.z * i, 0, 0, 0);
            ent.level().addParticle(new DustParticleEffect(new Vector3f(0f, 0f, 0f), 0.5f), ent.x() + 0.5D + vec4.x * i, ent.y() + 1D + Vec3d.y * i, ent.z() - 0.5D + vec4.z * i, 0, 0, 0);
        }

        for(double i = 0; i <= vec5.length(); i += vec5.length() / 20D) {
            ent.level().addParticle(new DustParticleEffect(new Vector3f(0f, 0f, 0f), 0.5f), ent.x(), ent.y() + 1D + vec5.y * i, ent.z() + 0.5D + vec5.z * i, 0, 0, 0);
        }
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity ent) {
        return 100;
    }

    public static double distance(Vec3d point, Vec3d normal, BlockPos pointOnSide) {
        double n0 = -(normal.x * pointOnSide.getX() + normal.y * pointOnSide.getY() + normal.z * pointOnSide.getZ());
        double divisor = normal.x * point.x + normal.y * point.y + normal.z * point.z;
        return (divisor + n0) / normal.length();
    }
}
