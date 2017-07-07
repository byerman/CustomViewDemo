package com.huaweisoft.customviewdemo.factory;

import com.huaweisoft.customviewdemo.model.Leaf;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by baiaj on 2017/7/4.
 * 创建叶子的工厂类
 */

public class LeafFactory {

    // 叶子飘动一个周期所花的时间
    private static final long LEAF_FLOAT_TIME = 3000;
    // 叶子旋转一周需要的时间
    private static final long LEAF_ROTATE_TIME = 2000;
    private static final int MAX_LEAFS = 6;
    Random random = new Random();
    private int addTime;

    public Leaf generateLeaf() {
        Leaf leaf = new Leaf();
        int randomType = random.nextInt(3);
        // 随机类型,随机振幅
        Leaf.StartType type = Leaf.StartType.MIDDLE;
        switch (randomType) {
            case 0:
                break;
            case 1:
                type = Leaf.StartType.LITTLE;
                break;
            case 2:
                type = Leaf.StartType.BIG;
                break;
            default:
                break;
        }
        leaf.setType(type);
        // 随机起始的旋转角度
        leaf.setRotateAngle(random.nextInt(360));
        // 随机旋转方向(顺时针或者逆时针)
        leaf.setRotateDirection(random.nextInt(2));
        // 随机开始时间
        addTime = addTime + random.nextInt((int) (LEAF_FLOAT_TIME * 1.5));
        leaf.setStartTime(System.currentTimeMillis() + addTime);
        return leaf;
    }

    // 根据最大叶子数产生叶子信息
    public List<Leaf> generateLeafs() {
        return generateLeafs(MAX_LEAFS);
    }

    // 根据传入的叶子数量产生叶子信息
    public List<Leaf> generateLeafs(int leafSize) {
        List<Leaf> leafs = new LinkedList<Leaf>();
        for (int i = 0; i < leafSize; i++) {
            leafs.add(generateLeaf());
        }
        return leafs;
    }

}
