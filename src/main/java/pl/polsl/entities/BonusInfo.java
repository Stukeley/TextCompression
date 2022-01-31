package pl.polsl.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * A secondary entity that lies in the HistoryEntry class.
 */
@Entity
public class BonusInfo {

    /**
     * Automatically generated ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * Function used to get ID.
     * @return The id of this object.
     */
    public Long getId() {
        return id;
    }

    /**
     * Function used to set ID.
     * @param id The new id of this object.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * The date and time when the Algorithm functions were called to create this object.
     */
    private LocalDateTime dateTime;

    /**
     * Function used to get dateTime.
     * @return The dateTime value.
     */
    public LocalDateTime getDateTime(){
        return dateTime;
    }

    /**
     * Function used to set dateTime.
     * @param dt The new dateTime value.
     */
    public void setDateTime(LocalDateTime dt) {
        dateTime = dt;
    }

    /**
     * A value representing whether this result was compressed (true), or decompressed (false).
     */
    private boolean isCompression;

    /**
     * Function used to get isCompression.
     * @return The isCompression value.
     */
    public boolean getCompression() {
        return isCompression;
    }

    /**
     * Function used to set isCompression.
     * @param compression The new isCompression value.
     */
    public void setCompression(boolean compression) {
        isCompression = compression;
    }

    /**
     * The default constructor, initializing the fields with respective values.
     * @param dt The dateTime value.
     * @param compression The compression value.
     */
    public BonusInfo(LocalDateTime dt, boolean compression) {
        dateTime = dt;
        isCompression = compression;
    }

    /**
     * The secondary constructor used by JPA when creating and retrieving objects from the database.
     */
    public BonusInfo() {

    }

    /**
     * The override of the toString method, used in many places to simplify showing the object on the website.
     * @return A string representation of this object.
     */
    @Override
    public String toString() {
        return "BonusInfo{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", isCompression=" + isCompression +
                '}';
    }

    /**
     * The override of the equals method, used by JPA to compare objects.
     * @param object Second object to be compared.
     * @return True if the objects are the same (compared by ID); false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BonusInfo)) {
            return false;
        }

        BonusInfo other = (BonusInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }

        return true;
    }
}
