package mbg.model;

import java.util.ArrayList;
import java.util.List;

public class BittlePictureExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table Bittle_Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table Bittle_Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table Bittle_Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bittle_Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public BittlePictureExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bittle_Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bittle_Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bittle_Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bittle_Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bittle_Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bittle_Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bittle_Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bittle_Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bittle_Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bittle_Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table Bittle_Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andBittleIdIsNull() {
            addCriterion("Bittle_id is null");
            return (Criteria) this;
        }

        public Criteria andBittleIdIsNotNull() {
            addCriterion("Bittle_id is not null");
            return (Criteria) this;
        }

        public Criteria andBittleIdEqualTo(Long value) {
            addCriterion("Bittle_id =", value, "bittleId");
            return (Criteria) this;
        }

        public Criteria andBittleIdNotEqualTo(Long value) {
            addCriterion("Bittle_id <>", value, "bittleId");
            return (Criteria) this;
        }

        public Criteria andBittleIdGreaterThan(Long value) {
            addCriterion("Bittle_id >", value, "bittleId");
            return (Criteria) this;
        }

        public Criteria andBittleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("Bittle_id >=", value, "bittleId");
            return (Criteria) this;
        }

        public Criteria andBittleIdLessThan(Long value) {
            addCriterion("Bittle_id <", value, "bittleId");
            return (Criteria) this;
        }

        public Criteria andBittleIdLessThanOrEqualTo(Long value) {
            addCriterion("Bittle_id <=", value, "bittleId");
            return (Criteria) this;
        }

        public Criteria andBittleIdIn(List<Long> values) {
            addCriterion("Bittle_id in", values, "bittleId");
            return (Criteria) this;
        }

        public Criteria andBittleIdNotIn(List<Long> values) {
            addCriterion("Bittle_id not in", values, "bittleId");
            return (Criteria) this;
        }

        public Criteria andBittleIdBetween(Long value1, Long value2) {
            addCriterion("Bittle_id between", value1, value2, "bittleId");
            return (Criteria) this;
        }

        public Criteria andBittleIdNotBetween(Long value1, Long value2) {
            addCriterion("Bittle_id not between", value1, value2, "bittleId");
            return (Criteria) this;
        }

        public Criteria andPicturesIdIsNull() {
            addCriterion("pictures_id is null");
            return (Criteria) this;
        }

        public Criteria andPicturesIdIsNotNull() {
            addCriterion("pictures_id is not null");
            return (Criteria) this;
        }

        public Criteria andPicturesIdEqualTo(Long value) {
            addCriterion("pictures_id =", value, "picturesId");
            return (Criteria) this;
        }

        public Criteria andPicturesIdNotEqualTo(Long value) {
            addCriterion("pictures_id <>", value, "picturesId");
            return (Criteria) this;
        }

        public Criteria andPicturesIdGreaterThan(Long value) {
            addCriterion("pictures_id >", value, "picturesId");
            return (Criteria) this;
        }

        public Criteria andPicturesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("pictures_id >=", value, "picturesId");
            return (Criteria) this;
        }

        public Criteria andPicturesIdLessThan(Long value) {
            addCriterion("pictures_id <", value, "picturesId");
            return (Criteria) this;
        }

        public Criteria andPicturesIdLessThanOrEqualTo(Long value) {
            addCriterion("pictures_id <=", value, "picturesId");
            return (Criteria) this;
        }

        public Criteria andPicturesIdIn(List<Long> values) {
            addCriterion("pictures_id in", values, "picturesId");
            return (Criteria) this;
        }

        public Criteria andPicturesIdNotIn(List<Long> values) {
            addCriterion("pictures_id not in", values, "picturesId");
            return (Criteria) this;
        }

        public Criteria andPicturesIdBetween(Long value1, Long value2) {
            addCriterion("pictures_id between", value1, value2, "picturesId");
            return (Criteria) this;
        }

        public Criteria andPicturesIdNotBetween(Long value1, Long value2) {
            addCriterion("pictures_id not between", value1, value2, "picturesId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table Bittle_Picture
     *
     * @mbggenerated do_not_delete_during_merge Sat Aug 03 23:23:36 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table Bittle_Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}