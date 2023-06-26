package lk.ijse.gdse.entity;

public class Employee implements SuperEntity{
    private String eid;
    private String first_name;
    private String last_name;
    private String role;
    private String telephone;
    private String dob;

    public Employee() {
    }

    public Employee(String eid, String first_name, String last_name, String role, String telephone, String dob) {
        this.eid = eid;
        this.first_name = first_name;
        this.last_name = last_name;
        this.role = role;
        this.telephone = telephone;
        this.dob = dob;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid='" + eid + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", role='" + role + '\'' +
                ", telephone='" + telephone + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
