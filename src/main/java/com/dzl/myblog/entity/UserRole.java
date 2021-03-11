package com.dzl.myblog.entity;

public class UserRole {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_role.User_id
     *
     * @mbg.generated
     */
    private Integer user_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_role.Role_id
     *
     * @mbg.generated
     */
    private Integer role_id;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_role.User_id
     *
     * @return the value of user_role.User_id
     *
     * @mbg.generated
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_role.User_id
     *
     * @param user_id the value for user_role.User_id
     *
     * @mbg.generated
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_role.Role_id
     *
     * @return the value of user_role.Role_id
     *
     * @mbg.generated
     */
    public Integer getRole_id() {
        return role_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_role.Role_id
     *
     * @param role_id the value for user_role.Role_id
     *
     * @mbg.generated
     */
    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserRole other = (UserRole) that;
        return (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
            && (this.getRole_id() == null ? other.getRole_id() == null : this.getRole_id().equals(other.getRole_id()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getRole_id() == null) ? 0 : getRole_id().hashCode());
        return result;
    }
}