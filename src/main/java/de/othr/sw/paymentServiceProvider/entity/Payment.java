package de.othr.sw.paymentServiceProvider.entity;

import de.othr.sw.paymentServiceProvider.entity.util.SingleIdEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Payment extends SingleIdEntity<Long> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long paymentId;
    private String sender;
    private String receiver;
    @ManyToOne
    private Account senderAccount;

    @OneToOne
    private Account receiverAccount;
    private Double amount; //ToDo: datatype?
    private String reference;
    private Date date;

    public Payment() {
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", senderAccount=" + senderAccount +
                ", receiverAccount=" + receiverAccount +
                ", amount=" + amount +
                ", reference='" + reference + '\'' +
                ", date=" + date +
                '}';
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }
    public Account getReceiverAccount() {
        return receiverAccount;
    }
    public Account getSenderAccount() {
        return senderAccount;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Long getID() {
        return this.paymentId;
    }
}
