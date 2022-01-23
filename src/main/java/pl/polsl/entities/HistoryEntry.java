package pl.polsl.entities;

import javax.persistence.*;

@Entity
public class HistoryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String value) {
        result = value;
    }

    @OneToOne
    @JoinColumn(name = "bonusinfo_id", referencedColumnName = "id")
    private BonusInfo bonusInfo;

    public BonusInfo getBonusInfo() {
        return bonusInfo;
    }

    public void setBonusInfo(BonusInfo value) {
        bonusInfo = value;
    }

    public HistoryEntry(String res, BonusInfo info) {
        result = res;
        bonusInfo = info;
    }

    public HistoryEntry() {

    }

    @Override
    public String toString() {
        return "HistoryEntry{" +
                "result='" + result + '\'' +
                ", bonusInfo=" + bonusInfo +
                '}';
    }

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
