package org.csystem.app.api.customer.controller.dto;

/**
 * @author onder sahin
 */
public class CustomerPhoneSaveRequestDTO {

    private int customerId;
    private String phoneNumber;
    private int phoneTypeId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPhoneTypeId() {
        return phoneTypeId;
    }

    public void setPhoneTypeId(int phoneTypeId) {
        this.phoneTypeId = phoneTypeId;
    }
}
