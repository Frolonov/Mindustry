package mindustry.ai.types;

import arc.math.*;
import mindustry.entities.units.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;
import static mindustry.world.meta.BlockFlag.*;

public class FighterAI extends AIController{

    @Override
    public void updateMovement(){

        if(target != null && unit.hasWeapons()){
            if(unit.type.circleTarget){
                circleAttack(88f);
            }else{
                moveTo(target, unit.type.range * 0.8f);
                unit.lookAt(target);
            }
        }
    }

    @Override
    public Teamc findTarget(float x, float y, float range, boolean air, boolean ground){
        var result = target(x, y, range, air, ground);

        //if the main target is in range, use it, otherwise target whatever is closest
        return checkTarget(result, x, y, range) ? target(x, y, range, air, ground) : result;
    }
}
