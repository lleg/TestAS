package ru.digitalspirit.asaka.fileuploader.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "LOB_TABLE")
public class UploadFile {
    private String id;
    private String contentType;
    private String fileName;
    private String username;
    private Timestamp lastUpdate;
    private byte[] blob;

    @Id
    @Column(name = "ID", length = 16)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CONTENTTYPE", length = 50)
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Basic
    @Column(name = "FILENAME", length = 122)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "USERNAME", length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "LAST_UPDATED")
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "A_BLOB")
    public byte[] getBlob() {
        return blob;
    }

    public void setBlob(byte[] blob) {
        this.blob = blob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UploadFile lobTable = (UploadFile) o;

        return id != null ? id.equals(lobTable.id) : lobTable.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
