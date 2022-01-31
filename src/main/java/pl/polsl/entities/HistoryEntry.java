package pl.polsl.entities;

import javax.persistence.*;

/**
 * A primary entity, representing a single output from the Algorithm.
 */
@Entity
public class HistoryEntry {

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
     * The result from the Algorithm.
     */
    private String result;

    /**
     * Function used to get result.
     * @return The result.
     */
    public String getResult() {
        return result;
    }

    /**
     * Function used to set result.
     * @param value The new result value.
     */
    public void setResult(String value) {
        result = value;
    }

    /**
     * A secondary entity, connected with the one-to-one relationship.
     */
    @OneToOne
    @JoinColumn(name = "bonusinfo_id", referencedColumnName = "id")
    private BonusInfo bonusInfo;

    /**
     * Function used to get bonusInfo.
     * @return The bonusInfo object.
     */
    public BonusInfo getBonusInfo() {
        return bonusInfo;
    }

    /**
     * Function used to set bonusInfo.
     * @param value The new bonusInfo object.
     */
    public void setBonusInfo(BonusInfo value) {
        bonusInfo = value;
    }

    /**
     * The default constructor, initializing the fields with respective values.
     * @param res The result value.
     * @param info The bonusInfo value.
     */
    public HistoryEntry(String res, BonusInfo info) {
        result = res;
        bonusInfo = info;
    }

    /**
     * The secondary constructor used by JPA when creating and retrieving objects from the database.
     */
    public HistoryEntry() {

    }

    /**
     * The override of the toString method, used in many places to simplify showing the object on the website.
     * @return A string representation of this object.
     */
    @Override
    public String toString() {
        return "HistoryEntry{" +
                "result='" + result + '\'' +
                ", bonusInfo=" + bonusInfo +
                '}';
    }

    /**
     * The override of the equals method, used by JPA to compare objects.
     * @param object Second object to be compared.
     * @return True if the objects are the same (compared by ID); false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof HistoryEntry)) {
            return false;
        }

        HistoryEntry other = (HistoryEntry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }

        return true;
    }
}
