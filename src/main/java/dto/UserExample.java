package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.time.LocalDateTime;

public class UserExample {
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table public.user
     * @mbg.generated
     */
    protected String orderByClause;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table public.user
     * @mbg.generated
     */
    protected boolean distinct;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table public.user
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated
     */
    public UserExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table public.user
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        public Criteria andEmailaddressIsNull() {
            addCriterion("emailaddress is null");
            return (Criteria) this;
        }

        public Criteria andEmailaddressIsNotNull() {
            addCriterion("emailaddress is not null");
            return (Criteria) this;
        }

        public Criteria andEmailaddressEqualTo(String value) {
            addCriterion("emailaddress =", value, "emailaddress");
            return (Criteria) this;
        }

        public Criteria andEmailaddressNotEqualTo(String value) {
            addCriterion("emailaddress <>", value, "emailaddress");
            return (Criteria) this;
        }

        public Criteria andEmailaddressGreaterThan(String value) {
            addCriterion("emailaddress >", value, "emailaddress");
            return (Criteria) this;
        }

        public Criteria andEmailaddressGreaterThanOrEqualTo(String value) {
            addCriterion("emailaddress >=", value, "emailaddress");
            return (Criteria) this;
        }

        public Criteria andEmailaddressLessThan(String value) {
            addCriterion("emailaddress <", value, "emailaddress");
            return (Criteria) this;
        }

        public Criteria andEmailaddressLessThanOrEqualTo(String value) {
            addCriterion("emailaddress <=", value, "emailaddress");
            return (Criteria) this;
        }

        public Criteria andEmailaddressLike(String value) {
            addCriterion("emailaddress like", value, "emailaddress");
            return (Criteria) this;
        }

        public Criteria andEmailaddressNotLike(String value) {
            addCriterion("emailaddress not like", value, "emailaddress");
            return (Criteria) this;
        }

        public Criteria andEmailaddressIn(List<String> values) {
            addCriterion("emailaddress in", values, "emailaddress");
            return (Criteria) this;
        }

        public Criteria andEmailaddressNotIn(List<String> values) {
            addCriterion("emailaddress not in", values, "emailaddress");
            return (Criteria) this;
        }

        public Criteria andEmailaddressBetween(String value1, String value2) {
            addCriterion("emailaddress between", value1, value2, "emailaddress");
            return (Criteria) this;
        }

        public Criteria andEmailaddressNotBetween(String value1, String value2) {
            addCriterion("emailaddress not between", value1, value2, "emailaddress");
            return (Criteria) this;
        }

        public Criteria andLastNameIsNull() {
            addCriterion("last_name is null");
            return (Criteria) this;
        }

        public Criteria andLastNameIsNotNull() {
            addCriterion("last_name is not null");
            return (Criteria) this;
        }

        public Criteria andLastNameEqualTo(String value) {
            addCriterion("last_name =", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotEqualTo(String value) {
            addCriterion("last_name <>", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameGreaterThan(String value) {
            addCriterion("last_name >", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameGreaterThanOrEqualTo(String value) {
            addCriterion("last_name >=", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameLessThan(String value) {
            addCriterion("last_name <", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameLessThanOrEqualTo(String value) {
            addCriterion("last_name <=", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameLike(String value) {
            addCriterion("last_name like", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotLike(String value) {
            addCriterion("last_name not like", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameIn(List<String> values) {
            addCriterion("last_name in", values, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotIn(List<String> values) {
            addCriterion("last_name not in", values, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameBetween(String value1, String value2) {
            addCriterion("last_name between", value1, value2, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotBetween(String value1, String value2) {
            addCriterion("last_name not between", value1, value2, "lastName");
            return (Criteria) this;
        }

        public Criteria andFirstNameIsNull() {
            addCriterion("first_name is null");
            return (Criteria) this;
        }

        public Criteria andFirstNameIsNotNull() {
            addCriterion("first_name is not null");
            return (Criteria) this;
        }

        public Criteria andFirstNameEqualTo(String value) {
            addCriterion("first_name =", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameNotEqualTo(String value) {
            addCriterion("first_name <>", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameGreaterThan(String value) {
            addCriterion("first_name >", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameGreaterThanOrEqualTo(String value) {
            addCriterion("first_name >=", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameLessThan(String value) {
            addCriterion("first_name <", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameLessThanOrEqualTo(String value) {
            addCriterion("first_name <=", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameLike(String value) {
            addCriterion("first_name like", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameNotLike(String value) {
            addCriterion("first_name not like", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameIn(List<String> values) {
            addCriterion("first_name in", values, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameNotIn(List<String> values) {
            addCriterion("first_name not in", values, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameBetween(String value1, String value2) {
            addCriterion("first_name between", value1, value2, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameNotBetween(String value1, String value2) {
            addCriterion("first_name not between", value1, value2, "firstName");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPostalCodeIsNull() {
            addCriterion("postal_code is null");
            return (Criteria) this;
        }

        public Criteria andPostalCodeIsNotNull() {
            addCriterion("postal_code is not null");
            return (Criteria) this;
        }

        public Criteria andPostalCodeEqualTo(String value) {
            addCriterion("postal_code =", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeNotEqualTo(String value) {
            addCriterion("postal_code <>", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeGreaterThan(String value) {
            addCriterion("postal_code >", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeGreaterThanOrEqualTo(String value) {
            addCriterion("postal_code >=", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeLessThan(String value) {
            addCriterion("postal_code <", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeLessThanOrEqualTo(String value) {
            addCriterion("postal_code <=", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeLike(String value) {
            addCriterion("postal_code like", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeNotLike(String value) {
            addCriterion("postal_code not like", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeIn(List<String> values) {
            addCriterion("postal_code in", values, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeNotIn(List<String> values) {
            addCriterion("postal_code not in", values, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeBetween(String value1, String value2) {
            addCriterion("postal_code between", value1, value2, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeNotBetween(String value1, String value2) {
            addCriterion("postal_code not between", value1, value2, "postalCode");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("region is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("region is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(String value) {
            addCriterion("region =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(String value) {
            addCriterion("region <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(String value) {
            addCriterion("region >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(String value) {
            addCriterion("region >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(String value) {
            addCriterion("region <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(String value) {
            addCriterion("region <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLike(String value) {
            addCriterion("region like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotLike(String value) {
            addCriterion("region not like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<String> values) {
            addCriterion("region in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<String> values) {
            addCriterion("region not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(String value1, String value2) {
            addCriterion("region between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(String value1, String value2) {
            addCriterion("region not between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andLocalityIsNull() {
            addCriterion("locality is null");
            return (Criteria) this;
        }

        public Criteria andLocalityIsNotNull() {
            addCriterion("locality is not null");
            return (Criteria) this;
        }

        public Criteria andLocalityEqualTo(String value) {
            addCriterion("locality =", value, "locality");
            return (Criteria) this;
        }

        public Criteria andLocalityNotEqualTo(String value) {
            addCriterion("locality <>", value, "locality");
            return (Criteria) this;
        }

        public Criteria andLocalityGreaterThan(String value) {
            addCriterion("locality >", value, "locality");
            return (Criteria) this;
        }

        public Criteria andLocalityGreaterThanOrEqualTo(String value) {
            addCriterion("locality >=", value, "locality");
            return (Criteria) this;
        }

        public Criteria andLocalityLessThan(String value) {
            addCriterion("locality <", value, "locality");
            return (Criteria) this;
        }

        public Criteria andLocalityLessThanOrEqualTo(String value) {
            addCriterion("locality <=", value, "locality");
            return (Criteria) this;
        }

        public Criteria andLocalityLike(String value) {
            addCriterion("locality like", value, "locality");
            return (Criteria) this;
        }

        public Criteria andLocalityNotLike(String value) {
            addCriterion("locality not like", value, "locality");
            return (Criteria) this;
        }

        public Criteria andLocalityIn(List<String> values) {
            addCriterion("locality in", values, "locality");
            return (Criteria) this;
        }

        public Criteria andLocalityNotIn(List<String> values) {
            addCriterion("locality not in", values, "locality");
            return (Criteria) this;
        }

        public Criteria andLocalityBetween(String value1, String value2) {
            addCriterion("locality between", value1, value2, "locality");
            return (Criteria) this;
        }

        public Criteria andLocalityNotBetween(String value1, String value2) {
            addCriterion("locality not between", value1, value2, "locality");
            return (Criteria) this;
        }

        public Criteria andStreetAddressIsNull() {
            addCriterion("street_address is null");
            return (Criteria) this;
        }

        public Criteria andStreetAddressIsNotNull() {
            addCriterion("street_address is not null");
            return (Criteria) this;
        }

        public Criteria andStreetAddressEqualTo(String value) {
            addCriterion("street_address =", value, "streetAddress");
            return (Criteria) this;
        }

        public Criteria andStreetAddressNotEqualTo(String value) {
            addCriterion("street_address <>", value, "streetAddress");
            return (Criteria) this;
        }

        public Criteria andStreetAddressGreaterThan(String value) {
            addCriterion("street_address >", value, "streetAddress");
            return (Criteria) this;
        }

        public Criteria andStreetAddressGreaterThanOrEqualTo(String value) {
            addCriterion("street_address >=", value, "streetAddress");
            return (Criteria) this;
        }

        public Criteria andStreetAddressLessThan(String value) {
            addCriterion("street_address <", value, "streetAddress");
            return (Criteria) this;
        }

        public Criteria andStreetAddressLessThanOrEqualTo(String value) {
            addCriterion("street_address <=", value, "streetAddress");
            return (Criteria) this;
        }

        public Criteria andStreetAddressLike(String value) {
            addCriterion("street_address like", value, "streetAddress");
            return (Criteria) this;
        }

        public Criteria andStreetAddressNotLike(String value) {
            addCriterion("street_address not like", value, "streetAddress");
            return (Criteria) this;
        }

        public Criteria andStreetAddressIn(List<String> values) {
            addCriterion("street_address in", values, "streetAddress");
            return (Criteria) this;
        }

        public Criteria andStreetAddressNotIn(List<String> values) {
            addCriterion("street_address not in", values, "streetAddress");
            return (Criteria) this;
        }

        public Criteria andStreetAddressBetween(String value1, String value2) {
            addCriterion("street_address between", value1, value2, "streetAddress");
            return (Criteria) this;
        }

        public Criteria andStreetAddressNotBetween(String value1, String value2) {
            addCriterion("street_address not between", value1, value2, "streetAddress");
            return (Criteria) this;
        }

        public Criteria andExtendedAddressIsNull() {
            addCriterion("extended_address is null");
            return (Criteria) this;
        }

        public Criteria andExtendedAddressIsNotNull() {
            addCriterion("extended_address is not null");
            return (Criteria) this;
        }

        public Criteria andExtendedAddressEqualTo(String value) {
            addCriterion("extended_address =", value, "extendedAddress");
            return (Criteria) this;
        }

        public Criteria andExtendedAddressNotEqualTo(String value) {
            addCriterion("extended_address <>", value, "extendedAddress");
            return (Criteria) this;
        }

        public Criteria andExtendedAddressGreaterThan(String value) {
            addCriterion("extended_address >", value, "extendedAddress");
            return (Criteria) this;
        }

        public Criteria andExtendedAddressGreaterThanOrEqualTo(String value) {
            addCriterion("extended_address >=", value, "extendedAddress");
            return (Criteria) this;
        }

        public Criteria andExtendedAddressLessThan(String value) {
            addCriterion("extended_address <", value, "extendedAddress");
            return (Criteria) this;
        }

        public Criteria andExtendedAddressLessThanOrEqualTo(String value) {
            addCriterion("extended_address <=", value, "extendedAddress");
            return (Criteria) this;
        }

        public Criteria andExtendedAddressLike(String value) {
            addCriterion("extended_address like", value, "extendedAddress");
            return (Criteria) this;
        }

        public Criteria andExtendedAddressNotLike(String value) {
            addCriterion("extended_address not like", value, "extendedAddress");
            return (Criteria) this;
        }

        public Criteria andExtendedAddressIn(List<String> values) {
            addCriterion("extended_address in", values, "extendedAddress");
            return (Criteria) this;
        }

        public Criteria andExtendedAddressNotIn(List<String> values) {
            addCriterion("extended_address not in", values, "extendedAddress");
            return (Criteria) this;
        }

        public Criteria andExtendedAddressBetween(String value1, String value2) {
            addCriterion("extended_address between", value1, value2, "extendedAddress");
            return (Criteria) this;
        }

        public Criteria andExtendedAddressNotBetween(String value1, String value2) {
            addCriterion("extended_address not between", value1, value2, "extendedAddress");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeIsNull() {
            addCriterion("createdtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeIsNotNull() {
            addCriterion("createdtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeEqualTo(LocalDateTime value) {
            addCriterion("createdtime =", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotEqualTo(LocalDateTime value) {
            addCriterion("createdtime <>", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeGreaterThan(LocalDateTime value) {
            addCriterion("createdtime >", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("createdtime >=", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeLessThan(LocalDateTime value) {
            addCriterion("createdtime <", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("createdtime <=", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeIn(List<LocalDateTime> values) {
            addCriterion("createdtime in", values, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotIn(List<LocalDateTime> values) {
            addCriterion("createdtime not in", values, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("createdtime between", value1, value2, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("createdtime not between", value1, value2, "createdtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeIsNull() {
            addCriterion("updatedtime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeIsNotNull() {
            addCriterion("updatedtime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeEqualTo(LocalDateTime value) {
            addCriterion("updatedtime =", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeNotEqualTo(LocalDateTime value) {
            addCriterion("updatedtime <>", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeGreaterThan(LocalDateTime value) {
            addCriterion("updatedtime >", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("updatedtime >=", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeLessThan(LocalDateTime value) {
            addCriterion("updatedtime <", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("updatedtime <=", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeIn(List<LocalDateTime> values) {
            addCriterion("updatedtime in", values, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeNotIn(List<LocalDateTime> values) {
            addCriterion("updatedtime not in", values, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("updatedtime between", value1, value2, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("updatedtime not between", value1, value2, "updatedtime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table public.user
     * @mbg.generated
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

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table USER
     *
     * @mbg.generated do_not_delete_during_merge Sun Apr 12 13:08:36 JST 2020
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }
}