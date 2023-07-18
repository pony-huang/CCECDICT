package org.github.ponking66.ccecdit;

/**
 * @author pony
 * @date 2023/7/13
 */
public class Word {

    public int id;
    public String word;
    public String sw;
    public String phonetic;
    public String definition;
    public String translation;
    public String pos;
    public int collins;
    public int oxford;
    public String tag;
    public int bnc;
    public int frq;
    public String exchange;
    public String detail;
    public String audio;
    public int frequency;

    public Word() {
    }

    public Word(String word, String phonetic, String translation, String exchange) {
        this.word = word;
        this.phonetic = phonetic;
        this.translation = translation;
        this.exchange = exchange;
    }

    public Word(int id, String word, String sw, String phonetic, String definition, String translation, String pos,
                int collins, int oxford, String tag, int bnc, int frq, String exchange, String detail, String audio) {
        this.id = id;
        this.word = word;
        this.sw = sw;
        this.phonetic = phonetic;
        this.definition = definition;
        this.translation = translation;
        this.pos = pos;
        this.collins = collins;
        this.oxford = oxford;
        this.tag = tag;
        this.bnc = bnc;
        this.frq = frq;
        this.exchange = exchange;
        this.detail = detail;
        this.audio = audio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSw() {
        return sw;
    }

    public void setSw(String sw) {
        this.sw = sw;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public int getCollins() {
        return collins;
    }

    public void setCollins(int collins) {
        this.collins = collins;
    }

    public int getOxford() {
        return oxford;
    }

    public void setOxford(int oxford) {
        this.oxford = oxford;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getBnc() {
        return bnc;
    }

    public void setBnc(int bnc) {
        this.bnc = bnc;
    }

    public int getFrq() {
        return frq;
    }

    public void setFrq(int frq) {
        this.frq = frq;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
