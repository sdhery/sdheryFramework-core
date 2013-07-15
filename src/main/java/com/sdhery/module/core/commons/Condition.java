package com.sdhery.module.core.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-7-15
 * Time: 下午4:54
 * 用于sql动态参数查询处理
 */
public class Condition {
    /**
     * 排序条件
     */
    private String orderByClause;
    /**
     * 是否重复
     */
    private boolean distinct;
    /**
     * 条件集合
     */
    private List<ConditionItem> conditionItems;

    public Condition() {
        conditionItems = new ArrayList<ConditionItem>();
    }

    public static class ConditionItem {
        /**
         * 条件
         */
        private String condition;

        /**
         * 条件值
         */
        private Object value;

        /**
         * 第二个条件值，当条件是betweenValue时有用
         */
        private Object secondValue;

        /**
         * 是否没有条件值
         */
        private boolean noValue;

        /**
         * 是否普通查询条件值
         */
        private boolean singleValue;

        /**
         * 是否查询之间的条件值
         */
        private boolean betweenValue;

        /**
         * 是否多个条件值
         */
        private boolean listValue;

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public void setSecondValue(Object secondValue) {
            this.secondValue = secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public void setNoValue(boolean noValue) {
            this.noValue = noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public void setSingleValue(boolean singleValue) {
            this.singleValue = singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public void setBetweenValue(boolean betweenValue) {
            this.betweenValue = betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public void setListValue(boolean listValue) {
            this.listValue = listValue;
        }
    }

}
