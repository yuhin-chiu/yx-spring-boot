package cn.yx.util;

import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by 焦宇轩 on 2018/2/9.
 */
public class NumberUtil {

    public static final BigDecimal ZERO_2 = BigDecimal.valueOf(0, 2);

    /**
     * 获取一个未知类型的2位精度BigDecimal
     * @param obj
     * @return
     */
    public static BigDecimal getScale2(Object obj) {
        if(obj == null) {
            return ZERO_2;
        }
        BigDecimal decimal;

        try {
            decimal = getDecimal(obj);
        } catch (Exception e) {
            return ZERO_2;
        }

        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 将一个未知类型数字转化为百分比格式，保留小数点后2位，无法做到时返回“-”
     */
    public static String getPercent(Object object) {
        return getPercent(object, false);
    }
    /**
     *
     *
     */
    /**
     * 将一个未知类型数字转化为百分比格式，保留小数点后2位，无法做到时返回“-”
     * flag = true 时，如果object = Integer.MaxValue 也返回 “-”
     *
     * @param object
     * @param flag
     * @return
     */
    public static String getPercent(Object object, boolean flag) {
        if (object == null) {
            return "-";
        }

        BigDecimal decimal;
        try {
            decimal = getDecimal(object);
        } catch (Exception e) {
            return "-";
        }

        if (flag && decimal.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE)) == 0) {
            return "-";
        }

        DecimalFormat df = new DecimalFormat("0.00%");
        // 修复例如0.00345转化后为0.34%的问题
        return df.format(decimal.setScale(4, BigDecimal.ROUND_HALF_UP));
    }


    /**
     * 获取两个数相除后的百分比，保留小数点后2位
     *
     * @param object1 除数
     * @param object2 被除数
     * @return
     */
    public static String getPercentBetween(Object object1, Object object2) {
        if (object1 == null || object2 == null) {
            return "-";
        }

        BigDecimal denominator, numerator;
        try {
            numerator = getDecimal(object1);
            denominator = getDecimal(object2);
        } catch (Exception e) {
            return "-";
        }

        if (denominator == null || denominator.compareTo(BigDecimal.ZERO) == 0) {
            return "-";
        }

        return getPercent(numerator.divide(denominator, 4, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 将一个未知数字类型转化为String,保留小数点后scale位,无法做到时返回“-”
     * flag = true 时，如果object = Integer.MaxValue 也返回 “-”
     *
     * @param obj   输入对象
     * @param scale 精度
     * @param flag
     * @return
     */
    public static String getFraction(Object obj, int scale, boolean flag) {
        if (obj == null) {
            return "-";
        }

        BigDecimal decimal;
        try {
            decimal = getDecimal(obj);
        } catch (Exception e) {
            return "-";
        }

        if (flag && decimal.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE)) == 0) {
            return "-";
        }

        return decimal.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 保留一个浮点数小数点后2位
     */
    public static String getFraction(Object obj) {
        return getFraction(obj, 2, false);
    }

    /**
     * 保留一个浮点数小数点后2位
     * flag = true 时，如果object = Integer.MaxValue 也返回 “-”
     */
    public static String getFraction(Object obj, boolean flag) {
        return getFraction(obj, 2, flag);
    }

    /**
     * 将一个未知数字类型转化为String,保留小数点后scale位,无法做到时返回“-”
     *
     * @param obj   输入对象
     * @param scale 精度
     * @return
     */
    public static String getFraction(Object obj, int scale) {
        return getFraction(obj, scale, false);
    }

    /**
     * 获取两个数字相除后的小数，保留小数点后scale位
     *
     * @param object1
     * @param object2
     * @param scale
     * @return
     */
    public static String getDivisionBetween(Object object1, Object object2, int scale) {
        if (object1 == null || object2 == null) {
            return "-";
        }

        BigDecimal denominator, numerator;
        try {
            numerator = getDecimal(object1);
            denominator = getDecimal(object2);
        } catch (Exception e) {
            return "-";
        }

        if (denominator == null || denominator.compareTo(BigDecimal.ZERO) == 0) {
            return "-";
        }

        return numerator.divide(denominator, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 获取两个数相除后的小数，保留小数点后2位
     */
    public static String getDivisionBetween(Object object1, Object object2) {
        return getDivisionBetween(object1, object2, 2);
    }

    /**
     * 获取两个浮点数相除，保留8有效数字用于计算，防止除0
     *
     * @param object1
     * @param object2
     * @return BigDecimal
     */
    public static BigDecimal getDivision(Object object1, Object object2) {
        if (object1 == null || object2 == null) {
            return BigDecimal.valueOf(0);
        }

        BigDecimal denominator, numerator;
        try {
            numerator = getDecimal(object1);
            denominator = getDecimal(object2);
        } catch (Exception e) {
            return BigDecimal.valueOf(0);
        }

        if (denominator == null || denominator.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.valueOf(0);
        }

        return numerator.divide(denominator, 8, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 求和，null值会被替换为0，结果为两位精度
     * @param objs
     * @return
     */
    public static BigDecimal add2(Object...objs) {
        return add(objs).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 求和，null值会被替换为0
     * @param objs
     * @return
     */
    public static BigDecimal add(Object...objs) {
        if(objs == null || objs.length == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal[] decimals;

        try {
            decimals = getDecimals(objs);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }

        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal decimal : decimals) {
            result = result.add(decimal);
        }
        return result;
    }
    public static BigDecimal max(Object...objs) {
        if(objs == null || objs.length == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal[] decimals;

        try {
            decimals = getDecimals(objs);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = BigDecimal.ZERO;
        for(BigDecimal decimal: decimals) {
            if(decimal.compareTo(result) > 0) {
                result = decimal;
            }
        }
        return result;
    }
    /**
     * 第一个数 减去后面的，null值会被替换为0, 结果永远两位精度
     * @param objs
     * @return
     */
    public static BigDecimal subtract2(Object...objs) {
        return subtract(objs).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 第一个数 减去后面的，null值会被替换为0
     * @param objs
     * @return
     */
    public static BigDecimal subtract(Object...objs) {
        if(objs == null || objs.length == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal[] decimals;

        try {
            decimals = getDecimals(objs);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }

        BigDecimal result = decimals[0];
        for (int i = 1; i < decimals.length; i++) {
            result = result.subtract(decimals[i]);
        }
        return result;
    }

    /**
     * 连乘 null值会被替换为0，结果为两位精度
     * @param objs
     * @return
     */
    public static BigDecimal multiply2(Object...objs) {
        return multiply(objs).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 连乘 null值会被替换为0
     * @param objs
     * @return
     */
    public static BigDecimal multiply(Object...objs) {
        if(objs == null || objs.length == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal[] decimals;

        try {
            decimals = getDecimals(objs);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }

        BigDecimal result = BigDecimal.ONE;
        for (BigDecimal decimal : decimals) {
            result = result.multiply(decimal);
        }
        return result;
    }

    /**
     * 判断一个未知类型是否可转为数字0, 为检测浮点数，精确度设为4，空字符串会返回true null会返回false
     * @param object
     * @return
     */
    public static boolean isZero(Object object) {
        if (object == null) {
            return false;
        }

        BigDecimal decimal;

        try {
            decimal = getDecimal(object).setScale(4, BigDecimal.ROUND_HALF_UP);
            if (BigDecimal.ZERO.compareTo(decimal) == 0) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * null返回true 空字符串 或者 数字 0 返回 true
     * @param object
     * @return
     */
    public static boolean isNullOrZero(Object object) {
        if (object == null) {
            return true;
        }
        return isZero(object);
    }

    /**
     * 将一个未知类型数字转化为BigDecimal, 当object为空字符串时，也返回0
     *
     * @param object
     * @return
     * @throws Exception
     */
    public static BigDecimal getDecimal(Object object) throws Exception {
        try {
            if (object != null) {
                if (object instanceof Integer) {
                    return BigDecimal.valueOf((Integer) object);
                } else if (object instanceof Double) {
                    return BigDecimal.valueOf((Double) object);
                } else if (object instanceof Float) {
                    return BigDecimal.valueOf((Float) object);
                } else if (object instanceof Long) {
                    return BigDecimal.valueOf((Long) object);
                } else if (object instanceof BigDecimal) {
                    return (BigDecimal) object;
                } else if (object instanceof String) {
                    if (StringUtils.isBlank((CharSequence) object)) {
                        return BigDecimal.ZERO;
                    }
                    return new BigDecimal((String) object);
                }
            } else {
                return BigDecimal.ZERO;
            }
        } catch (Exception e) {
            throw new Exception("NumberUtil转换数字为BigDecimal时异常");
        }
        throw new IllegalArgumentException("Unsupported number type: " + object.getClass());
    }

    /**
     * 将一堆未知类型数字转化为BigDecimal
     * @param objs
     * @return
     * @throws Exception
     */
    public static BigDecimal[] getDecimals(Object...objs) throws Exception {
        if(objs == null || objs.length == 0) {
            return new BigDecimal[]{BigDecimal.ZERO};
        }

        BigDecimal[] decimals = new BigDecimal[objs.length];

        for (int i = 0; i < objs.length; i++) {
            decimals[i] = getDecimal(objs[i]);
        }
        return decimals;
    }
    /**
     * 用于Collectors的累加，传入一个mapper函数，该函数返回值Object应为Integer,Double,Long,BigDecimal中的一个，否则返回0
     * 累加结果为BigDecimal类型
     *
     * @param mapper
     * @param <T>
     * @return
     */
    public static <T> Collector<T, ?, BigDecimal>
    summingBigDecimal(Function<T, Object> mapper) {
        return new Collector<T, List<T>, BigDecimal>() {
            @Override
            public Supplier<List<T>> supplier() {
                return ArrayList::new;
            }

            @Override
            public BiConsumer<List<T>, T> accumulator() {
                return List::add;
            }

            @Override
            public BinaryOperator<List<T>> combiner() {
                return (left, right) -> {
                    left.addAll(right);
                    return left;
                };
            }

            @Override
            public Function<List<T>, BigDecimal> finisher() {
                return list -> {
                    BigDecimal sum = new BigDecimal(0);
                    for (T obj : list) {
                        try {
                            sum = sum.add(getDecimal(mapper.apply(obj)));
                        } catch (Exception e) {
                            continue;
                        }
                    }
                    return sum;
                };
            }

            @Override
            public Set<Characteristics> characteristics() {
                Set<Characteristics> CH_NOID = Collections.emptySet();
                return CH_NOID;
            }
        };
    }

    /**
     * 根据参数func对应方法，对列表中所有对象的指定Double字段求和
     *
     * @param list
     * @param func
     * @return
     */
    public static <T> Double summingDouble(List<T> list, Function<T, BigDecimal> func) {
        return list.stream().collect(Collectors.summingDouble(item -> func.apply(item).doubleValue()));
    }

    /**
     * 根据参数func对应方法，对列表中所有对象的指定Integer字段求和
     *
     * @param list
     * @param func
     * @return
     */
    public static <T> Integer summingInt(List<T> list, ToIntFunction<T> func) {
        return list.stream().collect(Collectors.summingInt(item -> func.applyAsInt(item)));
    }

    /**
     * 根据参数func和参数toIntFunc 对列表中的对象一个Double字段一个Integer字段相乘求和
     *
     * @param list
     * @param func
     * @param toIntFunc
     * @return
     */
    public static <T> Double summingMulti(List<T> list, Function<T, BigDecimal> func, ToIntFunction<T> toIntFunc) {
        return list.stream().collect(Collectors.summingDouble(e -> func.apply(e).doubleValue() * toIntFunc.applyAsInt(e)));
    }
    public static <T> Double summingMulti(List<T> list, Function<T, BigDecimal> func, Function<T, BigDecimal> toBigDecimalFunc) {
        return list.stream().collect(Collectors.summingDouble(e -> func.apply(e).doubleValue() * toBigDecimalFunc.apply(e).doubleValue()));
    }

    public static Object mergeObject(Object k1, final Object k2) throws IntrospectionException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class type = k1.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        Object k3 = type.newInstance();

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object value1 = readMethod.invoke(k1, new Object[0]);
                Object value2 = readMethod.invoke(k2, new Object[0]);
                Object value3;
                if (value1 == null) {
                    value3 = value2;
                } else if (value2 == null) {
                    value3 = value1;
                } else {
                    if (value1 instanceof BigDecimal) {
                        value3 = ((BigDecimal) value1).add((BigDecimal) value2);
                    } else if (value1 instanceof Integer) {
                        value3 = (Integer) value1 + (Integer) value2;
                    } else if (value1 instanceof Double) {
                        value3 = (Double) value1 + (Double) value2;
                    } else {
                        value3 = value1;
                    }
                }
                Method writeMethod = descriptor.getWriteMethod();
                writeMethod.invoke(k3, value3);
            }
        }
        return k3;
    }
}
