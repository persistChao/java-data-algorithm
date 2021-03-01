package com.answer.data.array;

/**
 *
 * 汉诺塔问题
 *   三个盘子 三个柱子 移动盘子 将 三个盘子（由大到小排放，从一根柱子移动到另一根柱子 顺序不变）
 *   先将 a->C ， a->b ，c->b  ,a->c , b->a ,b->c ,a->c
 *   h(x) = 2h(x-1)+1
 *   先把 n-1 个从A经过c移动到b
 *   再把1个从A移动到c
 *   再把n-1个盘子从B经过A移动到C
 *
 * @author answer
 * @version 1.0
 * @date 2021/2/18 8:12 下午
 */
public class Hannuo {

    public static void trans(int n , String a , String b ,String c){
        if (n>0) {
            trans(n-1,a,c,b);
            System.out.printf("moving from %s to %s%n",a,c);
            trans(n-1,b,a,c);
        }
    }

    public static void main(String[] args) {
        trans(3, "A", "B", "C");
    }
}
