/*
 * CustomerForm
 *
 * Version 1
 *
 * Artem Beshevli
 */

package edu.besh.rentacar.forms;

public class CustomerForm {

    private int id;
    private String client;
    private String address;
    private String phone;
    private String email;
    private String car;
    private boolean tookCar;

    public CustomerForm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public boolean isTookCar() {
        return tookCar;
    }

    public void setTookCar(boolean tookCar) {
        this.tookCar = tookCar;
    }

    @Override
    public String toString() {
        return "CustomerForm{" +
                "id=" + id +
                ", client=" + client +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", car=" + car +
                ", tookCar=" + tookCar +
                '}';
    }
}
