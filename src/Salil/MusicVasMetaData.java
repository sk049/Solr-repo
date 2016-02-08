package Salil;

/**
 * Created by salil on 3/2/16.
 */
public class MusicVasMetaData {
    String AIRTELVCODE;
    String AIRTELCCODE;
    String SONGNAME;
    String SINGER;
    String MOVIENAME;
    String PAYTO;
    String CONTENTPROVIDERNAME;
    String GENRE;
    String SUB_GENRE;
    String LANGUAGENAME;
    String RIGHTSBODYNAME;
    String STARTDATE;
    String ENDDATE;
    String LABELNAME;
    String KEYWORDS;
    String SONGRELEASEDATE;
    String MOVIERELEASEDATE;
    String ACTOR;
    String MUSICDIRECTOR;
    String MOVIEDIRECTOR;
    String MOVIEPRODUCER;
    String SUBCATEGORYNAME;
    String SONGRELEASEYEAR;
    String MOVIERELEASEYEAR;
    String BINARYMAPID;
    String TRUNCATED_PAYTO;
    String TRUNCATED_CPNAME;
    String DESCRIPTION;
    String ISRC;

    public void setAIRTELVCODE(String AIRTELVCODE) {
        this.AIRTELVCODE = AIRTELVCODE;
    }

    public void setAIRTELCCODE(String AIRTELCCODE) {
        this.AIRTELCCODE = AIRTELCCODE;
    }

    public void setSONGNAME(String SONGNAME) {
        this.SONGNAME = SONGNAME;
    }

    public void setSINGER(String SINGER) {
        this.SINGER = SINGER;
    }

    public void setMOVIENAME(String MOVIENAME) {
        this.MOVIENAME = MOVIENAME;
    }

    public void setPAYTO(String PAYTO) {
        this.PAYTO = PAYTO;
    }

    public void setCONTENTPROVIDERNAME(String CONTENTPROVIDERNAME) {
        this.CONTENTPROVIDERNAME = CONTENTPROVIDERNAME;
    }

    public void setGENRE(String GENRE) {
        this.GENRE = GENRE;
    }

    public void setSUB_GENRE(String SUB_GENRE) {
        this.SUB_GENRE = SUB_GENRE;
    }

    public void setLANGUAGENAME(String LANGUAGENAME) {
        this.LANGUAGENAME = LANGUAGENAME;
    }

    public void setRIGHTSBODYNAME(String RIGHTSBODYNAME) {
        this.RIGHTSBODYNAME = RIGHTSBODYNAME;
    }

    public void setSTARTDATE(String STARTDATE) {
        this.STARTDATE = STARTDATE;
    }

    public void setENDDATE(String ENDDATE) {
        this.ENDDATE = ENDDATE;
    }

    public void setLABELNAME(String LABELNAME) {
        this.LABELNAME = LABELNAME;
    }

    public void setKEYWORDS(String KEYWORDS) {
        this.KEYWORDS = KEYWORDS;
    }

    public void setSONGRELEASEDATE(String SONGRELEASEDATE) {
        this.SONGRELEASEDATE = SONGRELEASEDATE;
    }

    public void setMOVIERELEASEDATE(String MOVIERELEASEDATE) {
        this.MOVIERELEASEDATE = MOVIERELEASEDATE;
    }

    public void setACTOR(String ACTOR) {
        this.ACTOR = ACTOR;
    }

    public void setMUSICDIRECTOR(String MUSICDIRECTOR) {
        this.MUSICDIRECTOR = MUSICDIRECTOR;
    }

    public void setMOVIEDIRECTOR(String MOVIEDIRECTOR) {
        this.MOVIEDIRECTOR = MOVIEDIRECTOR;
    }

    public void setMOVIEPRODUCER(String MOVIEPRODUCER) {
        this.MOVIEPRODUCER = MOVIEPRODUCER;
    }

    public void setSUBCATEGORYNAME(String SUBCATEGORYNAME) {
        this.SUBCATEGORYNAME = SUBCATEGORYNAME;
    }

    public void setSONGRELEASEYEAR(String SONGRELEASEYEAR) {
        this.SONGRELEASEYEAR = SONGRELEASEYEAR;
    }

    public void setMOVIERELEASEYEAR(String MOVIERELEASEYEAR) {
        this.MOVIERELEASEYEAR = MOVIERELEASEYEAR;
    }

    public void setBINARYMAPID(String BINARYMAPID) {
        this.BINARYMAPID = BINARYMAPID;
    }

    public void setTRUNCATED_PAYTO(String TRUNCATED_PAYTO) {
        this.TRUNCATED_PAYTO = TRUNCATED_PAYTO;
    }

    public void setTRUNCATED_CPNAME(String TRUNCATED_CPNAME) {
        this.TRUNCATED_CPNAME = TRUNCATED_CPNAME;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public void setISRC(String ISRC) {
        this.ISRC = ISRC;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MusicVasMetaData{");
        sb.append("AIRTELVCODE='").append(AIRTELVCODE).append('\'');
        sb.append(", AIRTELCCODE='").append(AIRTELCCODE).append('\'');
        sb.append(", SONGNAME='").append(SONGNAME).append('\'');
        sb.append(", SINGER='").append(SINGER).append('\'');
        sb.append(", MOVIENAME='").append(MOVIENAME).append('\'');
        sb.append(", PAYTO='").append(PAYTO).append('\'');
        sb.append(", CONTENTPROVIDERNAME='").append(CONTENTPROVIDERNAME).append('\'');
        sb.append(", GENRE='").append(GENRE).append('\'');
        sb.append(", SUB_GENRE='").append(SUB_GENRE).append('\'');
        sb.append(", LANGUAGENAME='").append(LANGUAGENAME).append('\'');
        sb.append(", RIGHTSBODYNAME='").append(RIGHTSBODYNAME).append('\'');
        sb.append(", STARTDATE='").append(STARTDATE).append('\'');
        sb.append(", ENDDATE='").append(ENDDATE).append('\'');
        sb.append(", LABELNAME='").append(LABELNAME).append('\'');
        sb.append(", KEYWORDS='").append(KEYWORDS).append('\'');
        sb.append(", SONGRELEASEDATE='").append(SONGRELEASEDATE).append('\'');
        sb.append(", MOVIERELEASEDATE='").append(MOVIERELEASEDATE).append('\'');
        sb.append(", ACTOR='").append(ACTOR).append('\'');
        sb.append(", MUSICDIRECTOR='").append(MUSICDIRECTOR).append('\'');
        sb.append(", MOVIEDIRECTOR='").append(MOVIEDIRECTOR).append('\'');
        sb.append(", MOVIEPRODUCER='").append(MOVIEPRODUCER).append('\'');
        sb.append(", SUBCATEGORYNAME='").append(SUBCATEGORYNAME).append('\'');
        sb.append(", SONGRELEASEYEAR='").append(SONGRELEASEYEAR).append('\'');
        sb.append(", MOVIERELEASEYEAR='").append(MOVIERELEASEYEAR).append('\'');
        sb.append(", BINARYMAPID='").append(BINARYMAPID).append('\'');
        sb.append(", TRUNCATED_PAYTO='").append(TRUNCATED_PAYTO).append('\'');
        sb.append(", TRUNCATED_CPNAME='").append(TRUNCATED_CPNAME).append('\'');
        sb.append(", DESCRIPTION='").append(DESCRIPTION).append('\'');
        sb.append(", ISRC='").append(ISRC).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
