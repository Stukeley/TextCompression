package pl.polsl.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BonusInfo {

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

    private LocalDateTime dateTime;

    public LocalDateTime getDateTime(){
        return dateTime;
    }

    public void setDateTime(LocalDateTime dt) {
        dateTime = dt;
    }

    private boolean isCompression;

    public boolean getCompression() {
        return isCompression;
    }

    public void setCompression(boolean compression) {
        isCompression = compression;
    }

    public BonusInfo(LocalDateTime dt, boolean compression) {
        dateTime = dt;
        isCompression = compression;
    }

    public BonusInfo() {

    }

    @Override
    public String toString() {
        return "BonusInfo{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", isCompression=" + isCompression +
                '}';
    }

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
