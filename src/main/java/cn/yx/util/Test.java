package cn.yx.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JiaoYuxuan on 2018/6/19.
 */
public class Test {

    /**
     * 计算出某赔率下, 获得100需要投入的金额
     * @param odds 赔率
     * @return
     */
    public static BigDecimal getInvestTo100(Object odds) {
        return NumberUtil.getDivision(100, odds);
    }

    /**
     * 根据输入的赔率 计算出返还率
     * @param odds
     * @return
     */
    public static BigDecimal getReturnRate(Object...odds) {
        BigDecimal rBd = BigDecimal.valueOf(0);

        for(Object obj : odds) {
            rBd = NumberUtil.add(rBd, getInvestTo100(obj));
        }

        return NumberUtil.getDivision(100, rBd);
    }

    /**
     * 根据输入的赔率 和资金配比 算出返还率
     * @param rate
     * @param money
     * @return
     */
    public static BigDecimal getReturnRateFrom100(Odds rate, Odds money) {
        BigDecimal amount = BigDecimal.valueOf(0);
        BigDecimal reward;

        for(Object obj : money.getRates()) {
            amount = NumberUtil.add(amount, obj);
        }

        reward = NumberUtil.add(NumberUtil.multiply(rate.getSheng(), money.getSheng()), NumberUtil.multiply(rate.getPing(), money.getPing()), NumberUtil.multiply(rate.getFu(), money.getFu()));

        return NumberUtil.getDivision(reward, NumberUtil.multiply(amount, 3));
    }

    /**
     * 根据输入的赔率列表 计算出最佳返还率组合
     * @param odds
     * @return
     */
    public static Odds getMaxReturnRate(Odds...odds) {
        Odds rOdds = new Odds();
        List shengs = Arrays.stream(odds).map(Odds::getSheng).collect(Collectors.toList());
        List pings = Arrays.stream(odds).map(Odds::getPing).collect(Collectors.toList());
        List fus = Arrays.stream(odds).map(Odds::getFu).collect(Collectors.toList());

        rOdds.setSheng(NumberUtil.max(shengs.toArray()));
        rOdds.setPing(NumberUtil.max(pings.toArray()));
        rOdds.setFu(NumberUtil.max(fus.toArray()));

        rOdds.setReturnRate(getReturnRate(rOdds.getRates()));
        return rOdds;
    }

    /**
     * 根据输入赔率计算出100元如何分配得到最佳返还率
     * @param odds 赔率
     * @return
     */
    public static Odds getAllocationFrom100(Odds odds) {
        Odds rOdds = new Odds();
        BigDecimal rateAmount = NumberUtil.add(NumberUtil.multiply(odds.getSheng(), odds.getPing()), NumberUtil.multiply(odds.getPing(), odds.getFu()), NumberUtil.multiply(odds.getFu(), odds.getSheng()));

        rOdds.setSheng(NumberUtil.getDivision(NumberUtil.multiply(100, odds.getFu(), odds.getPing()), rateAmount));
        rOdds.setPing(NumberUtil.getDivision(NumberUtil.multiply(100, odds.getSheng(), odds.getFu()), rateAmount));
        rOdds.setFu(NumberUtil.getDivision(NumberUtil.multiply(100, odds.getSheng(), odds.getPing()), rateAmount));

        rOdds.setReturnRate(getReturnRateFrom100(odds, rOdds));
        return rOdds;
    }
    public static Odds getRoundAllocation(Odds rate, Odds money) {
        Odds rOdds = new Odds();

        rOdds.setSheng(NumberUtil.getScale2(money.getSheng()));
        rOdds.setPing(NumberUtil.getScale2(money.getPing()));
        rOdds.setFu(NumberUtil.getScale2(money.getFu()));

        rOdds.setReturnRate(getReturnRateFrom100(rate, rOdds));

        return rOdds;
    }

    public static void main(String[] args) {
//        Odds odds1 = new Odds(1.24, 4.8, 8.7);
//        Odds odds2 = new Odds(1.5, 4.2, 7.5);
        Odds odds1 = new Odds(2.01, 3.95, 2.45);
        Odds odds2 = new Odds(2.19, 3.9, 2.47);
        System.out.println("输入：");
        System.out.println(" 赔率1:" + odds1);
        System.out.println(" 赔率2:" + odds2);

        System.out.println("输出：");
        Odds maxRate = getMaxReturnRate(odds1, odds2);
        System.out.println(" 最佳赔率[" + maxRate + "]");

        Odds allocation = getAllocationFrom100(maxRate);
        System.out.println(" 100元分配[" + allocation + "]");

        Odds roundAllocation =getRoundAllocation(maxRate, allocation);
        System.out.println(" 100元分配(精确到分)[" + roundAllocation + "]");

        System.out.println(" 胜平负回报计算 ==> " + roundAllocation.getReturnMode(maxRate));
    }
}
class Odds {
    private Object sheng = 0;
    private Object ping = 0;
    private Object fu = 0;
    private Object returnRate = null;

    public Odds(Object sheng, Object ping, Object fu) {
        this.sheng = sheng;
        this.ping = ping;
        this.fu = fu;
    }

    public Odds (){}

    public Object getSheng() {
        return sheng;
    }

    public void setSheng(Object sheng) {
        this.sheng = sheng;
    }

    public Object getPing() {
        return ping;
    }

    public void setPing(Object ping) {
        this.ping = ping;
    }

    public Object getFu() {
        return fu;
    }

    public void setFu(Object fu) {
        this.fu = fu;
    }

    public Object getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(Object returnRate) {
        this.returnRate = returnRate;
    }

    public Object[] getRates() {
        return new Object[]{sheng, ping, fu};
    }

    public String toString() {
        if(returnRate != null) {
            return "胜:" + sheng + ",平:" + ping + ",负:" + fu + ",返还率:" + returnRate;
        }
        return "胜:" + sheng + ",平:" + ping + ",负:" + fu;
    }

    public String getReturnMode(Odds rate) {
        StringBuilder sb = new StringBuilder();
        sb.append("胜[投入:" + this.sheng + "赔率:" + rate.getSheng() + "回报:" + NumberUtil.multiply(this.sheng, rate.getSheng()) + "]");
        sb.append("平[投入:" + this.ping + "赔率:" + rate.getPing() + "回报:" + NumberUtil.multiply(this.ping, rate.getPing()) + "]");
        sb.append("负[投入:" + this.fu + "赔率:" + rate.getFu() + "回报:" + NumberUtil.multiply(this.fu, rate.getFu()) + "]");

        return sb.toString();
    }
}

