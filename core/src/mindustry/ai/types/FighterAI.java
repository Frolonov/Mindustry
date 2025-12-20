package mindustry.ai.types;

import arc.math.*;
import mindustry.entities.units.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;
import static mindustry.world.meta.BlockFlag.*;

public class FighterAI extends AIController{
    final static Rand rand = new Rand();
    final static BlockFlag[] randomTargets = {core, turret, repair, generator, factory, reactor};

    @Override
    public void updateMovement(){

        if(target != null && unit.hasWeapons()){
            if(unit.type.circleTarget){
                circleAttack(160f);
            }else{
                moveTo(target, unit.type.range * 0.8f);
                unit.lookAt(target);
            }
        }
    }

    @Override
    public Teamc targetFlag(float x, float y, BlockFlag flag, boolean enemy){
            return super.targetFlag(x, y, flag, enemy);
    }

    @Override
    public Teamc findTarget(float x, float y, float range, boolean air, boolean ground){
        var result = findMainTarget(x, y, range, air, ground);

        //if the main target is in range, use it, otherwise target whatever is closest
        return checkTarget(result, x, y, range) ? target(x, y, range, air, ground) : result;
    }

    @Override
    public Teamc findMainTarget(float x, float y, float range, boolean air, boolean ground){
        var core = targetFlag(x, y, BlockFlag.core, true);

            for(var flag : unit.type.targetFlags){
                if(flag == null){
                    Teamc result =  target(x, y, range, air, ground);
                    if(result != null) return result;
                }else{
                    Teamc result = targetFlag(x, y, flag, true);
                    if(result != null) return result;
                }
            }

        return core;
    }
}
