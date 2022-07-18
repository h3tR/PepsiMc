package ml.jozefpeeterslaan72wuustwezel.pepsimc.mixin;


import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
@Mixin(targets = "net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement$Placer")
public class JigsawPlacementMixin {

    @Final
    @Shadow
    private List<? super PoolElementStructurePiece> pieces;

    @Redirect(method = "tryPlacingChildren(Lnet/minecraft/world/level/levelgen/structure/PoolElementStructurePiece;Lorg/apache/commons/lang3/mutable/MutableObject;IZLnet/minecraft/world/level/LevelHeightAccessor;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/pools/StructureTemplatePool;getShuffledTemplates(Ljava/util/Random;)Ljava/util/List;"))
    private List<StructurePoolElement> getShuffledTemplates(StructureTemplatePool pool, Random rand) {
        boolean hasStore = pieces.stream()
                .map(piece -> ((PoolElementStructurePiece) piece).getElement().toString())
                .anyMatch(pieceName -> pieceName.contains("pepsimc:") && pieceName.contains("_pepsi_store_1"));
        if (hasStore) {
            return pool.getShuffledTemplates(rand).stream().filter(piece -> {
                String pieceName = piece.toString();
                return !pieceName.contains("pepsimc:") || !pieceName.contains("_pepsi_store_1");
            }).collect(Collectors.toList());
        } else if (Math.random() <= pieces.size() / 200f) {
            StructurePoolElement StorePiece = null;
            List<StructurePoolElement> original = pool.getShuffledTemplates(rand);
            List<StructurePoolElement> result = new ArrayList<>();
            for (StructurePoolElement piece : original) {
                String pieceName = piece.toString();
                if (pieceName.contains("pepsimc:") && pieceName.contains("_pepsi_store_1")) {
                    StorePiece = piece;
                } else {
                    result.add(piece);
                }
            }
            if (StorePiece != null) {
                result.add(0, StorePiece);
            }
            return result;
        }

        return pool.getShuffledTemplates(rand);
    }

}
