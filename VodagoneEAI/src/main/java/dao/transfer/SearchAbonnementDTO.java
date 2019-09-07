package dao.transfer;


public class SearchAbonnementDTO {

    private int id;
    private String aanbieder;
    private String dienst;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SearchAbonnementDTO withId(int id) {
        this.id = id;
        return this;
    }

    public String getAanbieder() {
        return aanbieder;
    }

    public void setAanbieder(String aanbieder) {
        this.aanbieder = aanbieder;
    }

    public SearchAbonnementDTO withAanbieder(String aanbieder) {
        this.aanbieder = aanbieder;
        return this;
    }

    public String getDienst() {
        return dienst;
    }

    public void setDienst(String dienst) {
        this.dienst = dienst;
    }

    public SearchAbonnementDTO withDienst(String dienst) {
        this.dienst = dienst;
        return this;
    }
    }
