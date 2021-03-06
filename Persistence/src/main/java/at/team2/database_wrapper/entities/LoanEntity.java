package at.team2.database_wrapper.entities;

import at.team2.database_wrapper.helper.TypeHelper;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Loan", schema = "Library", catalog = "")
public class LoanEntity {
    private int id;
    private int customerId;
    private int mediaMemberId;
    private Integer reminderId;
    private Date start;
    private Date lastRenewalStart;
    private Date end;
    private byte closed;
    private CustomerEntity customerByCustomerId;
    private MediaMemberEntity mediaMemberByMediaMemberId;
    private ReminderEntity reminderByReminderId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "customerId", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "mediaMemberId", nullable = false)
    public int getMediaMemberId() {
        return mediaMemberId;
    }

    public void setMediaMemberId(int mediaMemberId) {
        this.mediaMemberId = mediaMemberId;
    }

    @Basic
    @Column(name = "reminderId", nullable = true)
    public Integer getReminderId() {
        return reminderId;
    }

    public void setReminderId(Integer reminderId) {
        this.reminderId = reminderId;
    }

    @Basic
    @Column(name = "start", nullable = false)
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @Basic
    @Column(name = "lastRenewalStart", nullable = true, length = 45)
    public Date getLastRenewalStart() {
        return lastRenewalStart;
    }

    public void setLastRenewalStart(Date lastRenewalStart) {
        this.lastRenewalStart = lastRenewalStart;
    }

    @Basic
    @Column(name = "end", nullable = false)
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Basic
    @Column(name = "closed", nullable = false)
    public boolean getClosed() {
        return TypeHelper.toBoolean(closed);
    }

    public void setClosed(boolean closed) {
        this.closed = TypeHelper.toByte(closed);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoanEntity that = (LoanEntity) o;

        if (id != that.id) return false;
        if (customerId != that.customerId) return false;
        if (mediaMemberId != that.mediaMemberId) return false;
        if (closed != that.closed) return false;
        if (reminderId != null ? !reminderId.equals(that.reminderId) : that.reminderId != null) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (lastRenewalStart != null ? !lastRenewalStart.equals(that.lastRenewalStart) : that.lastRenewalStart != null)
            return false;
        if (end != null ? !end.equals(that.end) : that.end != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + customerId;
        result = 31 * result + mediaMemberId;
        result = 31 * result + (reminderId != null ? reminderId.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (lastRenewalStart != null ? lastRenewalStart.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (int) closed;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public CustomerEntity getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(CustomerEntity customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "mediaMemberId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public MediaMemberEntity getMediaMemberByMediaMemberId() {
        return mediaMemberByMediaMemberId;
    }

    public void setMediaMemberByMediaMemberId(MediaMemberEntity mediaMemberByMediaMemberId) {
        this.mediaMemberByMediaMemberId = mediaMemberByMediaMemberId;
    }

    @ManyToOne
    @JoinColumn(name = "reminderId", referencedColumnName = "id", insertable = false, updatable = false)
    public ReminderEntity getReminderByReminderId() {
        return reminderByReminderId;
    }

    public void setReminderByReminderId(ReminderEntity reminderByReminderId) {
        this.reminderByReminderId = reminderByReminderId;
    }
}
