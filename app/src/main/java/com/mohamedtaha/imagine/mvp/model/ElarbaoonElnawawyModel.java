package com.mohamedtaha.imagine.mvp.model;

import javax.inject.Inject;

public class ElarbaoonElnawawyModel {
    private String number_elhadeth;
    private String name_elhadeth;
    private String text_elhadeth;
    private String description_elhadeth;
    private String transelate_elhadeth;
    private int position;

    @Inject
    public ElarbaoonElnawawyModel() {
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getNumber_elhadeth() {
        return number_elhadeth;
    }

    public void setNumber_elhadeth(String number_elhadeth) {
        this.number_elhadeth = number_elhadeth;
    }

    public String getName_elhadeth() {
        return name_elhadeth;
    }

    public void setName_elhadeth(String name_elhadeth) {
        this.name_elhadeth = name_elhadeth;
    }

    public String getText_elhadeth() {
        return text_elhadeth;
    }

    public void setText_elhadeth(String text_elhadeth) {
        this.text_elhadeth = text_elhadeth;
    }

    public String getDescription_elhadeth() {
        return description_elhadeth;
    }

    public void setDescription_elhadeth(String description_elhadeth) {
        this.description_elhadeth = description_elhadeth;
    }

    public String getTranselate_elhadeth() {
        return transelate_elhadeth;
    }

    public void setTranselate_elhadeth(String transelate_elhadeth) {
        this.transelate_elhadeth = transelate_elhadeth;
    }
}
