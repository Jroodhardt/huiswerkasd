package dao.transfer;


public class UserAbonnementBodyDTO {

    private int id;
    private String aanbieder;
    private String dienst;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserAbonnementBodyDTO withId(int id) {
        this.id = id;
        return this;
    }

    public String getAanbieder() {
        return aanbieder;
    }

    public void setAanbieder(String aanbieder) {
        this.aanbieder = aanbieder;
    }

    public UserAbonnementBodyDTO withAanbieder(String aanbieder) {
        this.aanbieder = aanbieder;
        return this;
    }

    public String getDienst() {
        return dienst;
    }

    public void setDienst(String dienst) {
        this.dienst = dienst;
    }

    public UserAbonnementBodyDTO withDienst(String dienst) {
        this.dienst = dienst;
        return this;
    }
    }
